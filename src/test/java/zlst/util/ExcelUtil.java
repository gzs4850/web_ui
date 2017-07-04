package zlst.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import zlst.common.Constants;

public class ExcelUtil {
	
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow	Row;
	
	//设定要操作的excel的路径
	public static void setExcelFile(String Path){
		FileInputStream ExcelFile;
		try{
			ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		}catch(Exception e){
			//TestSuiteByExcel.testResult=false;
			System.out.println("excel路径设定失败");
			e.printStackTrace();
		}
	}
	
	//设定要操作的excel的路径和sheet名
	public static void setExcelFile(String Path,String SheetName){
		FileInputStream ExcelFile;
		try{
			ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		}catch(Exception e){
			//TestSuiteByExcel.testResult=false;
			System.out.println("excel路径设定失败");
			e.printStackTrace();
		}
	}
	
	//获取指定单元格的内容
	public static String getCellData(String SheetName,int RowNum,int ColNum){
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try{
			//通过制定单元格的行号和列号，获取制定单元格对象
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			//如果单元格内容为字符串类型，则使用getStringCellValue方法，否则使用getNumericCellValue方法
			@SuppressWarnings("deprecation")
			String CellData = Cell.getCellType() == XSSFCell.CELL_TYPE_STRING?Cell.getStringCellValue()+"":String.valueOf(Math.round(Cell.getNumericCellValue()));
			return CellData;
		}catch(Exception e){
			//TestSuiteByExcel.testResult=false;
			e.printStackTrace();
			//读取遇到异常，返回空字符串
			return "";
		}
	}
	
	//获取excel文件中最后一行的行号
	public static int getLastRowNum(){
		return ExcelWSheet.getLastRowNum();
	}
	
	//获取指定sheet中的数据总行数
	public static int getRowNum(String SheetName){
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		return ExcelWSheet.getLastRowNum();
	}
	
	//获取指定测试用例在测试步骤中第一次出现的行号
	public static int getFirstRowContainsTestCaseID(String SheetName,String testcaseName,int colNum){
		try{
			int i;
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int rowCount = getRowNum(SheetName);
			for(i=0;i<rowCount;i++){
				if(getCellData(SheetName,i,colNum).equals(testcaseName)){
					break;
				}
			}
			return i;
		}catch(Exception e){
			//TestSuiteByExcel.testResult=false;
			e.printStackTrace();
			return 0;
		}
		
	}
	
	//获取指定测试用例的测试步骤的最后一行
	public static int getTestCaseLastStepRow(String SheetName,String testCaseID,int testCaseStartRowNumber){
		try{
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			for(int i=testCaseStartRowNumber;i<=getRowNum(SheetName);i++){
				if(!testCaseID.equals(getCellData(SheetName,i,Constants.Col_TestCaseID))){
					return i-1;
				}
			}
			return getRowNum(SheetName);
		}catch(Exception e){
			//TestSuiteByExcel.testResult=false;
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void setCellData(String SheetName,int RowNum,int ColNum,String Result){
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try{
			//获取行对象
			Row = ExcelWSheet.getRow(RowNum);
			//如果单元格为空，则返回null
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			
			//如果单元格为空，无法直接调用单元格对象的setCellValue方法设定单元格的值，需先创建单元格
			if(Cell == null){
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}
			//实例化写入excel文件的文件输出流对象
			FileOutputStream fileOut = new FileOutputStream(Constants.Path_ExcelFile);
			//将内容写入excel文件
			ExcelWBook.write(fileOut);
			//调用flush方法强制刷新写入文件
			fileOut.flush();
			//关闭文件输出流对象
			fileOut.close();
		}catch(Exception e){
			//TestSuiteByExcel.testResult=false;
			e.printStackTrace();
		}
	}

}
