package pgodi.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream fis = new FileInputStream("E:\\myworkspace_selenium\\framework\\ExcelData.xlsx");
		XSSFWorkbook xl  = new XSSFWorkbook(fis);
		ArrayList al = new ArrayList();
		
		int sheetCount = xl.getNumberOfSheets();
		
		for(int i=0;i<sheetCount;i++) {
			
			if (xl.getSheetName(i).equalsIgnoreCase("testData"))
			{
				XSSFSheet  sheet = xl.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				
				Row firstRow =rows.next();
				Iterator<Cell> cell = firstRow.cellIterator();
				
				int k=0;
				int column = 0;
				while(cell.hasNext()) {
					Cell value=cell.next();
					if(value.getStringCellValue().equalsIgnoreCase("testCases")) {
						column=k;
					}k++;
				}
				
//				System.out.println(column);
				
				
				while(rows.hasNext()) {
					Row r= rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase("Purchase")) {
						Iterator<Cell> cr = r.cellIterator();
						while(cr.hasNext()) {
//							System.out.println(cr.next().getStringCellValue()); 
							al.add(cr.next().getStringCellValue());
							
						}
					}
				}
				
			}
			
			
			
//			
			
		}System.out.println(al);

	}

}
