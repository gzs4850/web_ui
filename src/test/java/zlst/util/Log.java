package zlst.util;

import org.testng.log4testng.Logger;

public class Log {
	private static Logger Log = Logger.getLogger(Log.class);
	
	//定义打印测试用例开始执行日志
	public static void startTestCase(String testCaseName){
		Log.info("-------------------- \""+testCaseName+"\"开始执行 --------------------");
	}
	
	//定义打印测试用例执行结束日志
	public static void endTestCase(String testCaseName){
		Log.info("-------------------- \""+testCaseName+"\"测试结束 --------------------");
	}
	
	//定义打印info级别日志
	public static void info(String message){
		Log.info(message);
	}
	
	//定义打印error级别日志
	public static void error(String message){
		Log.error(message);
	}
	
	//定义打印debug级别日志
	public static void debug(String message){
		Log.debug(message);
	}

}
