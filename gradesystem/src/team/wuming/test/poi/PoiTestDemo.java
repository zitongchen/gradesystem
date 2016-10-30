package team.wuming.test.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class PoiTestDemo {

	@Test
	public void demo1() throws IOException {
		String[] title = { "id", "name", "sex" };
		// 创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();

		FileOutputStream out = new FileOutputStream(new File(
				"createworkbook.xlsx"));
		workbook.write(out);
		out.close();
		System.out.println("create success");

	}

	@Test
	public void demo2() throws IOException {
		File file = new File("openexcel.xlsx");
		FileInputStream fIP = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fIP);
		if (file.isFile() && file.exists()) {
			System.out.println("success");
		} else {
			System.out.println("flase");
		}
		fIP.close();
	}

	@Test
	public void demo3() throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Employee Info");
		XSSFRow row;
		Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();
		empinfo.put("1", new Object[] { "EMP ID", "EMP NAME", "DESIGNATION" });
		empinfo.put("2", new Object[] { "tp01", "Gopal", "Technical Manager" });
		empinfo.put("3", new Object[] { "tp02", "Gopal", "Technical Manager" });
		empinfo.put("4", new Object[] { "tp02", "Gopal", "Technical Manager" });
		Set<String> keyid = empinfo.keySet();
		int rowid = 0;
		for (String key : keyid) {
			row = sheet.createRow(rowid++);
			Object[] objectArr = empinfo.get(key);
			int cellid = 0;
			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}
		FileOutputStream out = new FileOutputStream(
				new File("Writersheet.xlsx"));
		workbook.write(out);
		out.close();
		System.out.println("writer out success!");
	}
}
