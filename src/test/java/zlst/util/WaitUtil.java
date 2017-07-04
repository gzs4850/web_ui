package zlst.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
	
	//调用sleep方法休眠
	public static void sleep(long millisecond){
		try{
			Thread.sleep(millisecond);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//显示等待页面元素出现，参数为xpath对象
	public static void waitElement(WebDriver driver,String xpathExpression){
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
	}
	
	//显示等待页面元素出现，参数为by对象
	public static void waitElement(WebDriver driver,By by){
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	
}
