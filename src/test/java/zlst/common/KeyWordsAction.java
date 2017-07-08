package zlst.common;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.*;
import zlst.script.TestSuite;
import zlst.util.BaseUtil;
import zlst.util.KeyBoardUtil;
import zlst.util.Log;
import zlst.util.WaitUtil;

public class KeyWordsAction {
	public static WebDriver driver;
	
	static{
		PropertyConfigurator.configure(Constants.Path_LogPro);
	}
	
	//打开浏览器
	public static void openBrowser(String string,String browserName){
		if(browserName.equals("ie")){
			driver = new InternetExplorerDriver();
			Log.info("IE浏览器实例已经声明");
		} else if (browserName.equals("firefox")){
			driver = new FirefoxDriver();
			Log.info("Firefox浏览器实例已经声明");
		} else if(browserName.equals("chrome")){
			driver = new ChromeDriver();
			Log.info("Chrome浏览器实例已经声明");
			System.out.println("浏览器已打开");
			sleep("","2000");
		}
	}
	
	// 打开网址
	public static void navigate(String string, String url) {
		driver.get(url);
		Log.info("访问浏览器网址：" + url + "成功");
	}
	
	//在指定元素内输入内容
	public static void input(String locatorExpression,String inputString){
		try{
			WebElement element = BaseUtil.findElement(driver,locatorExpression);
			element.clear();
			//Log.info("清空输入框内容");
			element.sendKeys(inputString);
			Log.info("往输入框中输入："+inputString+"成功");
		} catch(Exception e) {
			//截取屏幕
			BaseUtil.snapshot(driver);
			TestSuite.testResult=false;
			Log.error("输入内容出现异常，具体异常信息："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//对指定元素进行点击操作
	public static void click(String locatorExpression,String string){
		try{
			System.out.println("开始执行click操作"+locatorExpression);
			WebElement element = BaseUtil.findElement(driver,locatorExpression);
			element.click();
			Log.info("点击按钮："+locatorExpression+"成功");
		} catch(Exception e){
			//截取屏幕
			BaseUtil.snapshot(driver);
			TestSuite.testResult=false;
			Log.error("点击按钮出现异常，具体异常信息："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//对指定元素进行双击操作
	public static void doubleClick(String locatorExpression,String string){
		try{
			WebElement element = BaseUtil.findElement(driver, locatorExpression);
			Actions builder = new Actions(driver);
			builder.doubleClick(element);
			Log.info("双击按钮："+locatorExpression+"成功");
		}catch(Exception e){
			//截取屏幕
			BaseUtil.snapshot(driver);
			TestSuite.testResult=false;
			Log.error("双击按钮异常，具体异常信息："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//对指定元素进行右键操作
	public static void rightClick(String locatorExpression,String string){
		try{
			WebElement element = BaseUtil.findElement(driver, locatorExpression);
			Actions builder = new Actions(driver);
			builder.contextClick(element);
			Log.info("右键按钮："+locatorExpression+"成功");
		}catch(Exception e){
			//截取屏幕
			BaseUtil.snapshot(driver);
			TestSuite.testResult=false;
			Log.error("右键元素异常，具体异常信息："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//处理Alert弹窗
	public static void handleAlert(String string,String value){
		try{
			WaitUtil.sleep(2000);
			Alert alert = driver.switchTo().alert();
			if(value.equals("确定")){
				alert.accept();
				Log.info("点击弹窗确定按钮成功");
			}else if(value.equals("取消")){
				alert.dismiss();
				Log.info("点击弹窗取消按钮成功");
			}else{
				Log.info("只能输入“确定/取消”");
			}
		}catch(Exception e){
			//截取屏幕
			BaseUtil.snapshot(driver);
			TestSuite.testResult=false;
			Log.error("处理弹窗异常，具体异常信息："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//选择下拉列表中指定内容
	public static void selectDropList(String locatorExpression,String value){
		try{
			Select dropList = new Select(BaseUtil.findElement(driver,locatorExpression));
			dropList.selectByVisibleText(value);
			Log.info("选择下拉列表："+value+"成功");
		}catch(Exception e){
			//截取屏幕
			BaseUtil.snapshot(driver);
			TestSuite.testResult=false;
			Log.error("下拉列表选择异常，具体异常信息："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//断言
	public static void assertString(String string,String assertString){
		try{
			assertTrue(driver.getPageSource().contains(assertString));
			Log.info("断言关键字:"+assertString+"成功");
		}catch(Exception e){
			//截取屏幕
			BaseUtil.snapshot(driver);
			TestSuite.testResult=false;
			Log.error("断言关键字出现异常，具体失败信息："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//线程休眠
	public static void sleep(String string1,String sleeptime){
		try{
			WaitUtil.sleep(Integer.parseInt(sleeptime));
			Log.info("休眠"+Integer.parseInt(sleeptime)+"秒成功");
		}catch(Exception e){
			//截取屏幕
			BaseUtil.snapshot(driver);
			TestSuite.testResult=false;
			Log.error("线程休眠时异常，具体异常信息："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//按回车键
	public static void pressEnter(String string1,String string2){
		try{
			KeyBoardUtil.pressEnterKey();
			Log.info("按回车键成功");
		}catch(Exception e){
			//截取屏幕
			BaseUtil.snapshot(driver);
			TestSuite.testResult=false;
			Log.error("按回车键出现异常，具体异常信息："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//按tab键
	public static void pressTab(String string1,String string2){
		try{
			Thread.sleep(2000);
			KeyBoardUtil.pressTabKey();
			Log.info("按tab键成功");
		}catch(Exception e){
			//截取屏幕
			BaseUtil.snapshot(driver);
			TestSuite.testResult=false;
			Log.error("按tab键出现异常，具体异常信息："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//滑动到指定元素
	public static void scrollToElement(String locatorExpression,String string){
		WebElement element = BaseUtil.findElement(driver, locatorExpression);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
	}
	
	//粘贴内容
	public static void pasteString(String string1,String pasteContent){
		try{
			KeyBoardUtil.setAndctrlVClipboarData(pasteContent);
			Log.info("粘贴"+pasteContent+"成功");
		}catch(Exception e){
			//截取屏幕
			BaseUtil.snapshot(driver);
			TestSuite.testResult=false;
			Log.error("粘贴内容出现异常，具体异常信息："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//关闭浏览器
	public static void closeBrowser(String string1,String string2){
		try{
			driver.quit();
			Log.info("关闭浏览器窗口成功");
		}catch(Exception e){
			//截取屏幕
			BaseUtil.snapshot(driver);
			TestSuite.testResult=false;
			Log.error("关闭浏览器出现异常，具体异常信息："+e.getMessage());
			e.printStackTrace();
		}
	}

}
