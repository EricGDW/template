package com.ogc.common.tool.excel;

import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.ogc.common.tool.excel.dev.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ImportExcel ei;
		try {
			ei = new ImportExcel("d://import.xls", 1);
			
			List<User> users = ei.getData(User.class);
			
			System.out.print(users.toString());
			
			System.out.print("done");
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
     }
}
