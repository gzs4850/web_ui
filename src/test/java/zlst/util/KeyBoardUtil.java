package zlst.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyBoardUtil {
	
	//按TAB键方法实现
	public static void pressTabKey(){
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		//调用keyPress方法按下TAB键
		robot.keyPress(KeyEvent.VK_TAB);
		//调用keyRelease方法释放TAB键
		robot.keyRelease(KeyEvent.VK_TAB);
	}
	
	//按enter键方法实现
	public static void pressEnterKey(){
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		//调用keyPress方法按下enter键
		robot.keyPress(KeyEvent.VK_ENTER);
		//调用keyRelease方法释放enter键
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	//将指定字符串设为剪切板内容，然后执行粘贴操作
	public static void setAndctrlVClipboarData(String string){
		StringSelection StringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(StringSelection, null);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e){
			e.printStackTrace();
		}
		//实现Ctrl+V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	

}
