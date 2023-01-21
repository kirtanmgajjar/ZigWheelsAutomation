package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Class to read and write data in the excel files (Uses Apache POI)
public class ExcelUtility 
{
	private String filePath;
	private InputStream inputStream;
	private XSSFWorkbook workBook;
	private XSSFSheet sheet;
	private int rowCount;
	private int columnCount;
	
	//Class Constructor
	public ExcelUtility(String path) throws Exception 
	{
		this.filePath = path;
		this.inputStream = new FileInputStream(filePath);
		this.workBook = new XSSFWorkbook(inputStream);
	}
	
	//Class Constructor
	public ExcelUtility(InputStream fileStream) throws Exception
	{
		this.inputStream = fileStream;
		this.workBook = new XSSFWorkbook(fileStream);
	}
	
	//Method to open the specified sheet from the excel
	public void openSheet(String sheetName)
	{
		this.sheet = workBook.getSheet(sheetName);
		this.rowCount = this.sheet.getLastRowNum();
		this.columnCount = this.sheet.getRow(0).getLastCellNum() - 1;
	}
	
	//Method to get the number of data rows in the excel
	public int getRowCount()
	{
		return this.rowCount;
	}
	
	//Method to get the number of data columns in the excel
	public int getColumnCount()
	{
		return this.columnCount;
	}
	
	//Method to read the string data from the specified cell
	public String getCellData(int row, int column)
	{
		String data = this.sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}
	
	//Method to write the string data into the specified cell
	public void setCellData(int row, int column,String data) throws Exception
	{
		try
		{
			if(filePath != null)
			{
				FileOutputStream outputStream = new FileOutputStream(filePath);
				XSSFCell cell = this.sheet.getRow(row).createCell(column);
				cell.setCellValue(data);
				this.workBook.write(outputStream);
				outputStream.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Method to close the input stream and workbook
	public void closeFile() throws Exception
	{
		this.inputStream.close();
		this.workBook.close();
	}
	

	
}
