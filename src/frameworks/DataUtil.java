package frameworks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtil {

	public static Properties getpropertiesfile(String propfilepath) throws IOException {
		FileInputStream fis = new FileInputStream(propfilepath);
		Properties props = new Properties();
		props.load(fis);
		return props;

	}

	public static HashMap<String, String> getTestcaseData(String filepath, String sheetName, String tcId) {
		HashMap<String, String> tcData = new HashMap<>();

		try {
			FileInputStream fis = new FileInputStream(filepath);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			try {
				XSSFSheet sheet = wb.getSheet(sheetName);
				XSSFRow headerRow=sheet.getRow(0);
				XSSFRow testcaseRow=getTestcaserow(sheet, tcId);
				for(int colNum=0;colNum<=headerRow.getLastCellNum()-1;colNum++) {
					XSSFCell headerCell=headerRow.getCell(colNum);
					XSSFCell dataCell=testcaseRow.getCell(colNum);
					if(dataCell==null) {
						tcData.put(headerCell.toString(), "");
					}else {
						tcData.put(headerCell.toString(), dataCell.toString());
					}
					
				}

			} catch (NullPointerException npe) {
				System.out.println();
			}

		} catch (FileNotFoundException e) {

			System.out.println("");

		} catch (IOException e) {
			System.out.println();
		}

		return tcData;

	}
	public static int get_column_poition(XSSFSheet sheet,String colHeader) {
		int colpos=-1;
		//getting header row//
		XSSFRow headerRow =sheet.getRow(0);
		//getting no.of columns in header row//
		int totalcolumns=headerRow.getLastCellNum();
		
		for(int cellNum=0;cellNum< totalcolumns;cellNum++) {
			XSSFCell headerCell=headerRow.getCell(cellNum);
			
			if(headerCell.toString().trim().equalsIgnoreCase(colHeader)) {
				colpos=cellNum;
				break;
			}
		}
		return colpos;
	}

	public static XSSFRow getTestcaserow(XSSFSheet sheet, String tcId) {
      //if tcdatarow is found in the excel it will got to line 75  otherwise it will be null//
		XSSFRow tcDataRow = null;
		// to find the testcase in entire rows so we are taking entire rows
		int totalRows = sheet.getLastRowNum();
		int tcRowNum = -1;
		int tcIdcolNum=get_column_poition(sheet, "TC_ID");
		if(tcIdcolNum>=0) {
			for (int rNum = 1; rNum <= totalRows; rNum++) {
				try {
					XSSFRow row = sheet.getRow(rNum);
					XSSFCell cell = row.getCell(tcIdcolNum);

					if (cell.toString().trim().equalsIgnoreCase(tcId)) {
						tcRowNum = rNum;
						// if required test case is not found tcrownum will be -1//
						break;

				}
				
				}catch(NullPointerException npe) {
					System.out.println("exception is there");
				}
			}
			if (tcRowNum == -1) {
				System.out.println("Given tcId is not found in the data file");
			} else {
				tcDataRow = sheet.getRow(tcRowNum);
			}
			
		}else {
			
		}
		return tcDataRow;
	}

}
