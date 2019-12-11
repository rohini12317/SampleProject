package frameworks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutil {

	
	public static HashMap<String, String> getdata(String filepath,String sheetname,String tcid) throws IOException {
		String fileExtension=FileUtils.getfileExtension(filepath);
		
		HashMap<String, String>testcaseData=new HashMap<>();
		if(fileExtension.equalsIgnoreCase("xlsx")) {
			try {
				FileInputStream fis=new FileInputStream(filepath);
				XSSFWorkbook wb=new XSSFWorkbook(fis);
				XSSFSheet datasheet=wb.getSheet(sheetname);
				
				if(datasheet !=null) {
					XSSFRow testcaseRow=get_testcase_row(datasheet,tcid);
					if(testcaseRow !=null) {
						XSSFRow headerRow=datasheet.getRow(0);
						int totalcols=headerRow.getLastCellNum();
						//it loops from first  col to last col in header row//
						for(int colNum=0;colNum<=totalcols-1;colNum++) {
							
							String colHeader=headerRow.getCell(colNum).toString();
							String colValue;
							XSSFCell dataCell = testcaseRow.getCell(colNum);
							if (dataCell != null) {
								colValue = dataCell.toString();
							} else {
								colValue = "";
							}
							testcaseData.put(colHeader, colValue);
						}
					}else {
						System.out.println("Test case is not found in the sheet");
					}
					
				}else {
					System.out.println("Given sheet does not exist");
				wb.close();	
				
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("the data file is not a valid excel file");
			System.exit(0);
		}
		return testcaseData;
		
	}
	
	public static int  get_col_position(XSSFSheet sheet,String colHeader) {
		int colPos=-1;
		XSSFRow headerRow=sheet.getRow(0);
		//it will access the header row//
		int totalcolumns=headerRow.getLastCellNum();
		// count of columns//
		
		for(int colNum=0;colNum<=totalcolumns-1;colNum++) {
			//System.out.println(colNum);
			XSSFCell cell=headerRow.getCell(colNum);
			//it will access all the cells in the every column upto the value in totalcolumns //
			String headerText=cell.getStringCellValue();
			System.out.println(headerText);
			if(headerText.equalsIgnoreCase(colHeader)) {
				colPos=colNum;
				break;
			}		
		}
 return colPos;		
	}
	
	private static XSSFRow get_testcase_row(XSSFSheet sheet,String tcId) {
		
		XSSFRow tcRow=null;
		int tcid_col_pos=get_col_position(sheet,"TC_ID");
		
		if(tcid_col_pos>=0) {
			int totalrows=sheet.getLastRowNum();
			
			for(int rnum=1;rnum<=totalrows;rnum++) {
				XSSFRow row=sheet.getRow(rnum);
				//taking every row//
				if(row.getCell(tcid_col_pos).getStringCellValue().trim().equalsIgnoreCase(tcId)) {
					//if test case id matches with accessed one//
					tcRow=row;
					//we are taking entire row in to tcrow//
					break;
				}
			}
			
		}else {
			System.out.println("Test case id column is not found");
		}
		
		return tcRow;
	}
	
	public static void Createnewexcelfile(String filepath,String sheetname) throws IOException {
		
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet(sheetname);
		XSSFRow row=sheet.createRow(0);
		XSSFCell cell=row.createCell(0);
		
		cell.setCellValue("Test data");		
		
		try {
			FileOutputStream fos=new FileOutputStream(filepath);
			wb.write(fos);
			wb.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	public static void Writedatainntoexistingfile(String filepath,String sheetname) throws IOException {
		
		try {
			FileInputStream fis=new FileInputStream(filepath);
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet sheet=wb.createSheet(sheetname);
			XSSFRow row=sheet.createRow(0);
			XSSFCell cell=row.createCell(0);
			
			cell.setCellValue("Test data 2");
			FileOutputStream fos=new FileOutputStream(filepath);
			wb.write(fos);
			wb.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public static void creatingmultiplerows(String filepath,String sheetname) {
		String[][] allvalues=new String[3][3];
		allvalues[0][0]="a1";
		allvalues[0][1]="a2";
		allvalues[0][2]="a3";
		allvalues[1][0]="b1";
		allvalues[1][1]="b2";
		allvalues[1][2]="b3";
		allvalues[2][0]="c1";
		allvalues[2][1]="c2";
		allvalues[2][2]="c3";
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet(sheetname);
		
		for(int i=0;i<=allvalues.length;i++) {
			XSSFRow row=sheet.createRow(i);
		
		
		
		for(int j=0;j<=allvalues[i].length;j++) {
			XSSFCell cell=row.createCell(j);
		}
		}
		
	}
	
}
