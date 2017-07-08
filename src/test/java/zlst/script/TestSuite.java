package zlst.script;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.log4j.PropertyConfigurator;
import org.testng.AssertJUnit;
import java.lang.reflect.Method;

import zlst.common.Constants;
import zlst.common.KeyWordsAction;
import zlst.util.ExcelUtil;
import zlst.util.Log;

public class TestSuite {
	
	public static Method method[];
	public static String keyword;
	public static String value;
	public static String locatorExpression;
	public static KeyWordsAction keyWordsaction;
	public static int testStep;
	public static int testLastStep;
	public static String testCaseID;
	public static String testCaseRunFlag;
	public static boolean testResult;
	
	@Parameters({"browserName"})
	@Test
	public void testTestSuite() throws InterruptedException{
		
		//Logger Log = Logger.getLogger(TestSuite.class);
		
		//声明一个关键字动作对象
		keyWordsaction = new KeyWordsAction();
		//使用Java的反射机制获取KeyWordsAction类中的所有方法对象
		method = keyWordsaction.getClass().getMethods();
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
				//设定默认测试结果为true
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
					Log.info("从测试步骤sheet中读取的定位表达式是："+locatorExpression);
					//获取操作值
					value = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_ActionValue);
					Log.info("从测试步骤sheet中读取的操作值是："+value);
					
					//执行测试步骤
					int i = 0;
					do{
						execute_Actions();
						if(testResult == true){
							//如果测试步骤都成功执行完成，则设定测试用例为pass
							ExcelUtil.setCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestSuiteTestResult, "Pass");
							Log.endTestCase(testCaseID);
							break;
						}
						i++;
					}while(i<Constants.retryNum);
					
					if(testResult == false){
						//如果测试用例中任何一个步骤执行失败，则设定测试用例为fail
						ExcelUtil.setCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestSuiteTestResult, "Fail");
						//打印测试用例执行结束
						Log.endTestCase(testCaseID);
						break;
					}
				}
			}
		}
	}
	
	//执行测试用例
	private static void execute_Actions(){
		try{
			for(int i=0;i<method.length;i++){
				if(method[i].getName().equals(keyword)){
					System.out.println("方法名称为："+method[i].getName());
					method[i].invoke(keyWordsaction,locatorExpression,value);
					System.out.println("---------测试步骤执行完成-----------");
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
			AssertJUnit.fail("执行出现异常，测试用例执行失败！");
		}
	}
	
	@BeforeClass
	public void BeforeClass(){
		PropertyConfigurator.configure(Constants.Path_LogPro);
	}
	
}
