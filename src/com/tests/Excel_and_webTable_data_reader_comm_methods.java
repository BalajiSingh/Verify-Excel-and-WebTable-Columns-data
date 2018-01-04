package com.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;
import com.utilities.ExcelReader;
import com.base.TestBase;

public class Excel_and_webTable_data_reader_comm_methods extends TestBase{
	
	public static Hashtable<String,String> hashtable =null;
	public static Object [] data_webtable = null;
	public static Object [] data_excel = null;
	
	public  FileInputStream fis = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	
	
	public static void disp()
	{
		System.out.println("Balaji Balaji");
	}
	
	public static Object[] capture_three_coloums_data_of_webTable(String locator)
	{
	
		WebElement table;
		List  no_rows = null , no_cols=null;
		int no_of_rows=0;
		
		 if(locator.endsWith("_css"))
		 {
			 table=driver.findElement(By.xpath(OR.getProperty(locator)));
			  no_rows=driver.findElements(By.xpath(OR.getProperty(locator)+"/tr[*]"));
			 System.out.println("Number of Rows present in table :"+no_rows.size());
			  no_cols=driver.findElements(By.xpath(OR.getProperty(locator)+"/tr[1]/th"));
			 System.out.println("Number of Columns present in table :"+no_cols.size());
			 no_of_rows= no_rows.size();
		 }
		 if(locator.endsWith("_xpath"))
		 {
			 table=driver.findElement(By.xpath(OR.getProperty(locator)));
			  no_rows=driver.findElements(By.xpath(OR.getProperty(locator)+"/tr[*]"));
			 System.out.println("Number of Rows present in table :"+no_rows.size());
			  no_cols=driver.findElements(By.xpath(OR.getProperty(locator)+"/tr[1]/th"));
			 System.out.println("Number of Columns present in table :"+no_cols.size());
			 no_of_rows= no_rows.size();
		 }
		 

		  //  two dimensional Object  array initialization
		  data_webtable =new Object[no_of_rows-1];
		 
		 for(int row=2; row<=no_rows.size(); row++)
		 {
			 hashtable = new Hashtable<String, String>();
			 for(int col=1; col<=no_cols.size(); col++)
			 {
				 WebElement header= driver.findElement(By.xpath(".//*[@id='t01']/tbody/tr[1]/th["+col+"]"));
				  if(header.getText().equalsIgnoreCase("Interview Questions") || header.getText().equalsIgnoreCase("Mock Interview Available") || header.getText().equalsIgnoreCase("Placement Assistance Available"))
				  {
					//  System.out.println("Hello");
					   String key =header.getText().toString();
					 
					   String value = driver.findElement(By.xpath(".//*[@id='t01']/tbody/tr["+row +"]/td["+col+"]")).getText();
					   hashtable.put(key, value);
					   data_webtable[row-2]  =hashtable;
				  }
				  
			 }
		 }
		
		
		return data_webtable;
	}
	
	
	//capture last three columns data from excel 
	public static  Object[] capture_three_coloums_data_of_excel( String  excel_sheetName)
	{
		
		//sheet = workbook.getSheetAt(0);
		String sheetName = excel_sheetName;
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		 data_excel = new Object[rows - 1];
		
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0]
				 String Col_Header=excel.getCellData(sheetName, colNum, 1);
				if(Col_Header.equalsIgnoreCase("Interview Questions") || Col_Header.equalsIgnoreCase("Mock Interview Available") || Col_Header.equalsIgnoreCase("Placement Assistance Available"))
				{
					table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
					data_excel[rowNum - 2] = table;
				}
				
			}

		}

		
		
		
		return data_excel;
	}

	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		log.debug("Selecting from an element : "+locator+" value as : "+value);
		test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);

	}

	
	
	
}
