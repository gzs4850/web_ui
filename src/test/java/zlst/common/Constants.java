package zlst.common;

public class Constants {
	
	static String curPath = System.getProperty("user.dir");
	
	//测试用例地址
	public static final String Path_ExcelFile = curPath+"/src/test/java/zlst/data/测试用例.xlsx";
	//截图地址
	public static final String Path_pic = curPath+"/pic/";
	//测试用例序号 0-第一列
	public static final int Col_TestCaseID = 0;
	//关键字 3-第四列
	public static final int Col_KeyWordAction = 3;
	//操作值 4-第五列
	public static final int Col_ActionValue = 5;
	//定位表达式 5-第六列
	public static final int Col_LocatorExpression = 4;
	//是否运行 2-第三列
	public static final int Col_RunFlag = 2;
	//测试用例集合测试结果 4-第四列
	public static final int Col_TestSuiteTestResult = 3;
	//测试步骤测试结果 4-第四列
	public static final int Col_TestStepsTestResult = 6;
	//测试步骤的sheet名称定义
	public static final String Sheet_TestSteps = "测试步骤";
	//测试用例集的sheet名称定义
	public static final String Sheet_TestSuite = "测试用例集合";
	//重复执行次数
	public static final int retryNum = 0;

}
