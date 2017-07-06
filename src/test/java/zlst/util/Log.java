package zlst.util;

import org.apache.log4j.Logger;

public class Log {

	private static Logger logger = Logger.getLogger(Log.class);
	
	//定义打印测试用例开始执行日志
	public static void startTestCase(String testCaseName){
		logger.info("-------------------- \""+testCaseName+"\"开始执行 --------------------");
	}
	
	//定义打印测试用例执行结束日志
	public static void endTestCase(String testCaseName){
		logger.info("-------------------- \""+testCaseName+"\"测试结束 --------------------");
	}
	
	//定义打印info级别日志
	public static void info(String message){
		logger.info(message);
	}
	
	//定义打印error级别日志
	public static void error(String message){
		logger.error(message);
	}
	
	//定义打印debug级别日志
	public static void debug(String message){
		logger.debug(message);
	}

}
