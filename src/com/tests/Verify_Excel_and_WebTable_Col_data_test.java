package com.tests;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.utilities.ExcelReader;

public class Verify_Excel_and_WebTable_Col_data_test extends TestBase{
	
	public Excel_and_webTable_data_reader_comm_methods excel_webtable_obj=null;
	Object[] webtableData_obj=null;
	Object[] excelData_obj=null;
	ExcelReader excel;
	
	@Test
	public void verify_Last_Three_Columns_excel_and_WebTable_data()
	{
		try
		{
			
		webtableData_obj = Excel_and_webTable_data_reader_comm_methods.capture_three_coloums_data_of_webTable("table_name_xpath");
		excelData_obj = Excel_and_webTable_data_reader_comm_methods.capture_three_coloums_data_of_excel("TestData");
		
		System.out.println("Size of webtableData_obj : " +webtableData_obj.length);
		
		System.out.println("Size of excelData_obj    : " +excelData_obj.length);
		
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"); 
		for(int i=0;i<webtableData_obj.length;i++)
		 {
			 Hashtable<String,String> h1_obji=new Hashtable();
			 
			 h1_obji=(Hashtable)webtableData_obj[i];
			 //System.out.println(h1_obji);
			 TreeMap<String, String> l1hashmap= new TreeMap<String, String>(h1_obji);
			 
			 
             Hashtable<String,String> h2_obji=new Hashtable();
			 
             h2_obji=(Hashtable)excelData_obj[i];
			// System.out.println(h2_obji);
			 TreeMap<String, String> l2hashmap= new TreeMap<String, String>(h2_obji);
			 
			 System.out.println(" ");
			 System.out.println(+i +" -Record in WeBtable");
			 System.out.println("-------------- WebTable Data -----------------------------------------------"); 
			  for(Map.Entry m1 : l1hashmap.entrySet())
			 {    
				  
				 System.out.println(m1.getKey() +" : " + m1.getValue());
			 }
			  			 
			  System.out.println(+i +" -Record in Excel ");
			  System.out.println("-------------- Excel Data -----------------------------------------------");
			 for(Map.Entry m2 : l2hashmap.entrySet())
			 {
				 
				 System.out.println(m2.getKey() +" : " + m2.getValue());
			 }
			
			
			 if(l1hashmap.equals(l2hashmap))
			 {
				 System.out.println("");
				 System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				 System.out.println("All columns data of Excel and WebTable Are same and Verified Successfully");
				 Assert.assertTrue(true);
				 System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				 
			 }
			 else
			 {
				 System.out.println("All columns data of Excel and WebTable Are Not same");
				 Assert.assertTrue(false);
			 }
			 
		 }
		
		 System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
