package zlst.script;

import java.lang.reflect.Method;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import zlst.common.Constants;
import zlst.common.KeyWordsAction;
import zlst.util.ExcelUtil;
import zlst.util.Log;

public class TestSuite {
	
	public static Method method[];
	public static String keyword;
	public static String value;
	public static String locatorExpression;
	public static KeyWordsAction keyWordsAction;
	public static int testStep;
	public static int testLastStep;
	public static String testCaseID;
	public static String testCaseRunFlag;
	public static boolean testResult;
	
	@Test
	public void testTestSuite(){
		//声明一个关键字动作对象
		keyWordsAction = new KeyWordsAction();
		//使用Java的反射机制获取KeyWordsAction类中的所有方法对象
		method = keyWordsAction.getClass().getMethods();
		//定义excel文件路径
		String excelFilePath = Constants.Path_ExcelFile;
		//设定读取excel文件
		ExcelUtil.setExcelFile(excelFilePath);
		//读取“测试用例集合”sheet中的测试用例总数
		int testCasesCount = ExcelUtil.getRowNum(Constants.Sheet_TestSuite);
		//循环读取测试用例集合
		for(int testCaseNo=1;testCaseNo<=testCasesCount;testCaseNo++){
			//读取该测试用例ID
			testCaseID = ExcelUtil.getCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestCaseID);
			//读取该测试用例是否自动执行
			testCaseRunFlag = ExcelUtil.getCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_RunFlag);
			//如果该测试用例为自动运行，则执行测试步骤
			if(testCaseRunFlag.equalsIgnoreCase("y")){
				//打印测试用例开始执行
				Log.startTestCase(testCaseID);
				//设定当前结果为true
				testResult = true;
				//在“测试步骤”sheet中，获取当前测试用例的第一个测试步骤行号
				testStep = ExcelUtil.getFirstRowContainsTestCaseID(Constants.Sheet_TestSteps, testCaseID, Constants.Col_TestCaseID);
				//在“测试步骤”sheet中，获取当前测试用例的最后一个测试步骤行号
				testLastStep = ExcelUtil.getTestCaseLastStepRow(Constants.Sheet_TestSteps, testCaseID, testStep);
				
				for(;testStep < testLastStep;testStep++){
					//获取关键字
					keyword = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_KeyWordAction);
					Log.info("从测试步骤sheet中读取的关键字是："+keyword);
					//获取定位表达式
					locatorExpression = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_LocatorExpression);
					//获取操作值
					value = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_ActionValue);
					Log.info("从测试步骤sheet中读取的操作值是："+value);
					//执行测试步骤
					execute_Actions();
					
					if(testResult == false){
						//如果测试用例中任何一个步骤执行失败，则设定测试用例为fail
						ExcelUtil.setCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestSuiteTestResult, "Fail");
						//打印测试用例执行结束
						Log.endTestCase(testCaseID);
						break;
					}
				}
				if(testResult == true){
					//如果测试步骤都成功执行完成，则设定测试用例为pass
					ExcelUtil.setCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestSuiteTestResult, "Pass");
					Log.endTestCase(testCaseID);
				}
				
			}
		}
	}
	
	//执行测试用例
	private static void execute_Actions(){
		try{
			for(int i=0;i<method.length;i++){
				if(method[i].getName().equals(keyword)){
					method[i].invoke(keyWordsAction, locatorExpression,value);
					if(testResult == true){
						//当前测试步骤执行成功时，则设定改测试步骤测试结果为pass
						ExcelUtil.setCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_TestStepsTestResult, "Pass");
						break;
					} else {
						//当前测试步骤执行失败时，则设定改测试步骤测试结果为fail
						ExcelUtil.setCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_TestStepsTestResult, "Fail");
						//测试步骤执行失败时，直接关闭浏览器，不再执行后续操作
						KeyWordsAction.closeBrowser("","");
						break;
					}
				}
			}
		}catch(Exception e){
			Assert.fail("执行出现异常，测试用例执行失败！");
		}
	}
	
	@BeforeClass
	public void BeforeClass(){
		DOMConfigurator.configure("log4j.xml");
	}

}
