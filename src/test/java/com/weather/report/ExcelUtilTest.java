package com.weather.report;
import com.weather.excel.util.Xls_Reader;

public class ExcelUtilTest {

	public static void main(String[] args) {

		Xls_Reader reader = new Xls_Reader("C:\\Users\\shravyav\\automation_WS\\WhatsappWeatherReport\\src\\main\\"
				+ "java\\com\\weather\\excel\\util\\WeatherReportExcel.xlsx");
		
		String sheetName = "sheet1";
		
		String data = reader.getCellData(sheetName, 0,1);
		System.out.println(data);
		
		System.out.println(reader.getRowCount(sheetName));
		
		reader.addColumn(sheetName, "WritingColumn");
		
		reader.addSheet("Addingsheet");

	}

}
