package zlst.common;

public class Constants {
	
	//测试用例地址
	public static final String Path_ExcelFile = "../测试用例.xlsx";
	//元素管理配置文件地址
	public static final String Paht_ConfigurationFile = "../objectMap.properities";
	//测试用例序号 0-第一列
	public static final int Col_TestCaseID = 0;
	//关键字 3-第四列
	public static final int Col_KeyWordAction = 3;
	//操作值 4-第五列
	public static final int Col_ActionValue = 4;
	//定位表达式 5-第六列
	public static final int Col_LocatorExpression = 5;
	//是否运行 2-第三列
	public static final int Col_RunFlag = 2;
	//测试用例集合测试结果 4-第四列
	public static final int Col_TestSuiteTestResult = 4;
	//测试步骤测试结果 4-第四列
	public static final int Col_TestStepsTestResult = 4;
	//测试步骤的sheet名称定义
	public static final String Sheet_TestSteps = "测试步骤";
	//测试用例集的sheet名称定义
	public static final String Sheet_TestSuite = "测试用例集";

}
