package zlst.util;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import zlst.common.Constants;

public class BaseUtil {
	
	// 在10秒内不停的查找元素,直到找到元素
	public static WebElement findElement(WebDriver driver,String elementName) {
		WebElement webElement = null;
		System.out.println("查找的元素："+elementName);
		long timeBegins = System.currentTimeMillis();
		do {
			try {
				webElement = driver.findElement(getLocator(elementName));
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(timeBegins);
			WaitUtil.sleep(500);
		} while (System.currentTimeMillis() - timeBegins <= 10000);
		return webElement;
	}
	
	//切换frame
	public static void swichToFrame(){
		
	}
	
	//使用js更改元素状态
	public static void changeStatus(WebDriver driver,WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("");
	}

	// 截图
	public static void snapshot(WebDriver driver) {
		try {
			Date date = new Date();
			// 根据年月日生成截图存储的文件夹
			String picDir = Constants.Path_pic + String.valueOf(DateUtil.getYear(date)) + "-"
					+ String.valueOf(DateUtil.getMonth(date)) + "-" + String.valueOf(DateUtil.getDay(date));
			if (!new File(picDir).exists()) {
				FileUtil.createDir(picDir);
			}
			String picPath = picDir + "-" + String.valueOf(DateUtil.getHour(new Date())) + "-"
					+ String.valueOf(DateUtil.getMinute(new Date())) + "-"
					+ String.valueOf(DateUtil.getSecond(new Date()));
			// 进行截图，并将文件内容保存在srcFile对象中
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// 生成截图文件
			FileUtils.copyFile(srcFile, new File(picPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//返回定位方式和定位值
	public static By getLocator(String locatorExpress) throws Exception{
		String locatorType = locatorExpress.split(">")[0];
		String locatorValue = locatorExpress.split(">")[1];
		locatorValue = new String(locatorValue.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("获取定位类型："+ locatorType + "，获取的定位表达式：" + locatorValue);
		
		if(locatorType.toLowerCase().equals("id")){
			return By.id(locatorValue);
		}
		else if(locatorType.toLowerCase().equals("name")){
			return By.name(locatorValue);
		}
		else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class"))){
			return By.className(locatorValue);
		}
		else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag"))){
			return By.tagName(locatorValue);
		}
		else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link"))){
			return By.linkText(locatorValue);
		}
		else if(locatorType.toLowerCase().equals("partiallinktext")){
			return By.partialLinkText(locatorValue);
		}
		else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css"))){
			return By.cssSelector(locatorValue);
		}
		else if(locatorType.toLowerCase().equals("xpath")){
			return By.xpath(locatorValue);
		}
		else
			throw new Exception("输入的定位方式未在程序中定义："+locatorType);
	}

}
