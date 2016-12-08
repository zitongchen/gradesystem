package team.wuming.common.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import team.wuming.common.domain.StudentGrade;
import team.wuming.common.service.StudentGradeService;
import team.wuming.common.service.impl.StudentGradeServiceImpl;

public class StudentGradeSheetServlet extends HttpServlet {

	private StudentGradeService studentGradeService = new StudentGradeServiceImpl();


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String classId = request.getParameter("classId");
		String expacount = request.getParameter("expacount");
		List<StudentGrade> studentGrades = studentGradeService // 创建学生成绩集合
				.findClassStudentByClass(classId, expacount);
		List<Object> userSexObjectList = studentGradeService // 创建学生性别集合
				.queryUserSex(classId);
		List<String> userSexList = new ArrayList<String>();
		for (Object object : userSexObjectList) {
			userSexList.add(String.valueOf(object));
		}
		List<Object> userNameObjectList = studentGradeService // 创建学生姓名集合
				.queryUserName(classId);
		List<String> userNameList = new ArrayList<String>();
		for (Object object : userNameObjectList) {
			userNameList.add(String.valueOf(object));
		}


		// 获取项目路径
		String path = request.getServletContext().getRealPath(
				"/resource/studentGradeSheet.xml");
		// 生成sheet表格
		HSSFWorkbook workbook = createSheet(path, studentGrades, userSexList,
				userNameList);
		String fileName = "学生成绩表.xls";
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("content-disposition", "attachment;filename="
				+ fileName);
		OutputStream outputStream = response.getOutputStream();// 创建一个输出流
		workbook.write(outputStream);// 利用poi中的方法下载
		outputStream.flush();
		outputStream.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public static HSSFWorkbook createSheet(String path,
			List<StudentGrade> studentGrades, List<String> userSexList,
			List<String> userNameList) {
		// 获取解析xml文件路径
		/*
		 * String path = System.getProperty("user.dir") +
		 * "/resource/studentGradeSheet.xml";
		 */
		File file = new File(path);
		SAXBuilder builder = new SAXBuilder();
		HSSFWorkbook wb = null;
		try {
			// 解析xml文件
			Document parse = builder.build(file);
			// 创建Excel
			wb = new HSSFWorkbook();
			// 创建sheet
			HSSFSheet sheet = wb.createSheet("Sheet0");


			// 获取xml文件跟节点
			Element root = parse.getRootElement();
			// 获取模板名称
			String templateName = root.getAttribute("name").getValue();
			int rownum = 0;
			int column = 0;
			// 设置列宽
			Element colgroup = root.getChild("colgroup");
			setColumnWidth(sheet, colgroup);

			// 设置标题
			Element title = root.getChild("title");
			List<Element> trs = title.getChildren("tr");
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				List<Element> tds = tr.getChildren("td");
				HSSFRow row = sheet.createRow(rownum);
				HSSFCellStyle cellStyle = wb.createCellStyle();// 创建单元格样式
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置单元格水平居中
				for (column = 0; column < tds.size(); column++) {
					Element td = tds.get(column);
					HSSFCell cell = row.createCell(column);// 创建单元格
					Attribute rowSpan = td.getAttribute("rowspan");
					Attribute colSpan = td.getAttribute("colspan");
					Attribute value = td.getAttribute("value");
					if (value != null) {
						String val = value.getValue();
						cell.setCellValue(val);// 设置单元格内容
						int rspan = rowSpan.getIntValue() - 1;
						int cspan = colSpan.getIntValue() - 1;
						// 设置字体
						HSSFFont font = wb.createFont();
						font.setFontName("仿宋_GB2312");
						// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
						// font.setFontHeight((short)12);
						font.setFontHeightInPoints((short) 16);
						cellStyle.setFont(font);
						cell.setCellStyle(cellStyle);
						// 合并单元格/居中
						sheet.addMergedRegion(new CellRangeAddress(rspan,
								rspan, 0, cspan));
					}
				}
				rownum++;
			}


			// 设置班级信息
			Element ml = root.getChild("ml");
			trs = ml.getChildren("tr");
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				List<Element> tds = tr.getChildren("td");
				HSSFRow row = sheet.createRow(rownum);
				HSSFCellStyle cellStyle = wb.createCellStyle();// 创建单元格样式
				for (column = 0; column < tds.size(); column++) {
					Element td = tds.get(column);
					Attribute value = td.getAttribute("value");
					Attribute rowSpan = td.getAttribute("rowspan");
					Attribute colSpan = td.getAttribute("colspan");
					int rspan = rowSpan.getIntValue() - 1;
					int cspan = colSpan.getIntValue() - 1;
					HSSFCell cell = row.createCell(rspan);// 创建单元格
					if (value != null) {
						String val = value.getValue();

						cell.setCellValue(val);// 设置单元格内容
						// 设置字体
						HSSFFont font = wb.createFont();
						font.setFontName("仿宋_GB2312");

						font.setFontHeightInPoints((short) 16);
						cellStyle.setFont(font);
						cell.setCellStyle(cellStyle);
						// 合并单元格/居中
						sheet.addMergedRegion(new CellRangeAddress(1, 1, rspan,
								cspan));
					}
				}
				rownum++;
			}

			// 设置表头
			Element thead = root.getChild("thead");
			trs = thead.getChildren("tr");
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont forn = wb.createFont();
			forn.setFontHeightInPoints((short) 12);
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				HSSFRow row = sheet.createRow(rownum);
				List<Element> ths = tr.getChildren("th");
				for (column = 0; column < ths.size(); column++) {
					Element th = ths.get(column);
					Attribute valueAttr = th.getAttribute("value");
					HSSFCell cell = row.createCell(column);
					if (valueAttr != null) {
						String value = valueAttr.getValue();
						cell.setCellValue(value);
					}
					style.setFont(forn);
					cell.setCellStyle(style);
				}
				rownum++;
			}

			// 设置数据区域样式
			Element tbody = root.getChild("tbody");
			Element tr = tbody.getChild("tr");
			int repeat = tr.getAttribute("repeat").getIntValue();
			List<Element> tds = tr.getChildren("td");
			HSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			int gradeListNumber = studentGrades.size();
			for (int i = 0; i < gradeListNumber; i++) {
				HSSFRow row = sheet.createRow(rownum);
				for (column = 0; column < tds.size(); column++) {
					Element td = tds.get(column);
					HSSFCell cell = row.createCell(column);
					setType(wb, cell, td);// 设置数据区类型
					cell.setCellValue(" ");
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);

					cell.setCellStyle(cellStyle);// 设置单元格样式
				}

				sheet.getRow(rownum).getCell(0)
						.setCellValue(studentGrades.get(i).getUser_acount());
				sheet.getRow(rownum).getCell(1)
						.setCellValue(userNameList.get(i).toString());
				sheet.getRow(rownum).getCell(2)
						.setCellValue(userSexList.get(i).toString());
				sheet.getRow(rownum).getCell(3)
						.setCellValue(studentGrades.get(i).getPsgrade());
				sheet.getRow(rownum).getCell(4)
						.setCellValue(studentGrades.get(i).getKsgrade());
				sheet.getRow(rownum).getCell(5)
						.setCellValue(studentGrades.get(i).getGrade());

				rownum++;
			}

			// 设置教师签名区域
			Element foot = root.getChild("foot");
			trs = foot.getChildren("tr");
			for (int i = 0; i < trs.size(); i++) {
				tr = trs.get(i);
				tds = tr.getChildren("td");
				HSSFRow row = sheet.createRow(rownum);
				cellStyle = wb.createCellStyle();// 创建单元格样式
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置单元格水平居中
				for (column = 0; column < tds.size(); column++) {
					Element td = tds.get(column);
					HSSFCell cell = row.createCell(column);// 创建单元格
					Attribute rowSpan = td.getAttribute("rowspan");
					Attribute colSpan = td.getAttribute("colspan");
					Attribute value = td.getAttribute("value");
					if (value != null) {
						String val = value.getValue();
						cell.setCellValue(val);// 设置单元格内容
						int rspan = rowSpan.getIntValue() - 1;
						int cspan = colSpan.getIntValue() - 1;
						// 设置字体
						HSSFFont font = wb.createFont();
						font.setFontName("仿宋_GB2312");
						// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
						// font.setFontHeight((short)12);
						font.setFontHeightInPoints((short) 16);
						cellStyle.setFont(font);
						cell.setCellStyle(cellStyle);
						// 合并单元格/居中
						sheet.addMergedRegion(new CellRangeAddress(rownum,
								rownum, rspan, cspan));
					}
				}
				rownum++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb;
	}

	/**
	 * 测试单元格样式
	 * 
	 * @author David
	 * @param wb
	 * @param cell
	 * @param td
	 */
	private static void setType(HSSFWorkbook wb, HSSFCell cell, Element td) {
		Attribute typeAttr = td.getAttribute("type");
		String type = typeAttr.getValue();
		HSSFDataFormat format = wb.createDataFormat();
		HSSFCellStyle cellStyle = wb.createCellStyle();
		if ("STRING".equalsIgnoreCase(type)) {// 匹配String类型的数据
			// cell.setCellValue();
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellStyle.setDataFormat(format.getFormat("@"));

		}
		cell.setCellStyle(cellStyle);// 设置单元格样式
	}

	/**
	 * 设置列宽
	 * 
	 * @author David
	 * @param sheet
	 * @param colgroup
	 */
	private static void setColumnWidth(HSSFSheet sheet, Element colgroup) {
		List<Element> cols = colgroup.getChildren("col");
		for (int i = 0; i < cols.size(); i++) {
			Element col = cols.get(i);
			Attribute width = col.getAttribute("width");
			String unit = width.getValue().replaceAll("[0-9,\\.]", "");// 使用空串代替数字符号，获取列宽的后缀
			String value = width.getValue().replaceAll(unit, "");// 使用空字符串代替列宽的后缀，获取列宽的值
			int v = 0;
			if (StringUtils.isBlank(unit) || "px".endsWith(unit)) {
				v = Math.round(Float.parseFloat(value) * 37F);
			} else if ("em".endsWith(unit)) {
				v = Math.round(Float.parseFloat(value) * 267.5F);
			}
			sheet.setColumnWidth(i, v);// 设置电子表格的列宽
		}
	}

}
