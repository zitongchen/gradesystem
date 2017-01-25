package team.wuming.modules.admin.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import team.wuming.common.domain.Maijor;
import team.wuming.common.domain.Objcenter;
import team.wuming.common.domain.StudentGrade;
import team.wuming.common.domain.Xuexid;
import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.admin.service.AdminService;
import team.wuming.modules.admin.service.impl.AdminServiceImpl;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.users.domain.User;

/**
 * 下载某个教学点补考学生名单
 * 
 * @author Tony
 * 
 */
public class DownFailGradeByJxd extends HttpServlet {
	private AdminService adminService = new AdminServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jxd = new String(request.getParameter("jxd").getBytes(
				"iso-8859-1"), "utf-8");
		List<StudentGrade> failStudentList = new ArrayList<StudentGrade>();
		failStudentList = adminService.searchFailStudentByXuxid(jxd);

		// 获取项目路径
		String path = request.getServletContext().getRealPath(
				"/resource/failGradeSheet.xml");
		// 生成sheet表格

		HSSFWorkbook workbook = createSheet(path, failStudentList, jxd);
		String fileName = jxd + "补考学生明细表.xls";
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("content-disposition", "attachment;filename="
				+ fileName);
		OutputStream outputStream = response.getOutputStream();// 创建一个输出流
		workbook.write(outputStream);// 利用poi中的方法下载
		outputStream.flush();
		outputStream.close();
	}

	public static HSSFWorkbook createSheet(String path,
			List<StudentGrade> studentGrades, String jxd) {
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
			Element title = root.getChild("title");// 学生成绩登记表
			List<Element> trs = title.getChildren("tr");
			HSSFFont font = wb.createFont();
			font.setFontName("宋体");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
			// 字体高度
			font.setFontHeightInPoints((short) 18);
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				List<Element> tds = tr.getChildren("td");
				HSSFRow row = sheet.createRow(rownum);// 创建一行
				row.setHeightInPoints(35);
				HSSFCellStyle cellStyle = wb.createCellStyle();// 创建单元格样式
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置单元格水平居中
				cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
				for (column = 0; column < tds.size(); column++) { // 获取td的数量
					Element td = tds.get(column); // 获取title中第一个td
					HSSFCell cell = row.createCell(column); // 创建单元格
					Attribute rowSpan = td.getAttribute("rowspan");// 获取行数对象
					Attribute colSpan = td.getAttribute("colspan");// 获取列数对象
					Attribute value = td.getAttribute("value"); // 获取td的值
																// 对象--学生成绩登记表
					if (value != null) {
						String val = value.getValue();
						cell.setCellValue(val);// 设置单元格内容
						int rspan = rowSpan.getIntValue() - 1;
						int cspan = colSpan.getIntValue() - 1;
						// 设置字体

						cellStyle.setFont(font);
						cell.setCellStyle(cellStyle);
						// 合并单元格/居中 合并单元格第rspand行到rspand行，0列岛cspan列，行列都是从0开始的。
						sheet.addMergedRegion(new CellRangeAddress(rownum,
								rownum, rspan, cspan));
					}
				}
				rownum++;// 行数加1
			}
			// 教学点
			String values = "教学点:" + jxd;
			HSSFCellStyle cellStyle = wb.createCellStyle();// 创建单元格样式
			// 不需要水平居中
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
			font = wb.createFont();
			font.setFontName("宋体");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
			font.setFontHeightInPoints((short) 12);
			HSSFRow row = sheet.createRow(rownum);
			row.setHeightInPoints(25);
			HSSFCell cell = row.createCell(0);// 创建单元格

			cell.setCellValue(values);// 设置单元格内容

			// 设置字体
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
			// 合并单元格/居中 第1行到第1行的rspan到cspan合并单元格！特别注意！
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 4));
			rownum++;


			// 设置表头
			Element thead = root.getChild("thead");
			trs = thead.getChildren("tr");
			cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置单元格水平居中
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
			font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 12);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				row = sheet.createRow(rownum);
				row.setHeightInPoints(25);
				List<Element> ths = tr.getChildren("th");
				for (column = 0; column < ths.size(); column++) {
					Element th = ths.get(column);
					Attribute valueAttr = th.getAttribute("value");
					cell = row.createCell(column);
					if (valueAttr != null) {
						String value = valueAttr.getValue();
						cell.setCellValue(value);
					}
					cellStyle.setFont(font);
					cell.setCellStyle(cellStyle);
				}
				rownum++;
			}

			// 设置数据区域样式
			Element tbody = root.getChild("tbody");
			Element tr = tbody.getChild("tr");
			int repeat = tr.getAttribute("repeat").getIntValue();
			List<Element> tds = tr.getChildren("td");
			cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置单元格水平居中
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 11);
			int gradeListNumber = studentGrades.size();
			for (int i = 0; i < gradeListNumber; i++) {
				row = sheet.createRow(rownum);
				row.setHeightInPoints(20);
				for (column = 0; column < tds.size(); column++) {
					Element td = tds.get(column);
					cell = row.createCell(column);

					cell.setCellValue(" ");
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);

					cell.setCellStyle(cellStyle);// 设置单元格样式
				}

				sheet.getRow(rownum).getCell(0)
						.setCellValue(studentGrades.get(i).getUser_acount());
				sheet.getRow(rownum).getCell(1)
						.setCellValue(studentGrades.get(i).getNickname());
				sheet.getRow(rownum).getCell(2)
						.setCellValue(studentGrades.get(i).getTitle());
				sheet.getRow(rownum).getCell(3)
						.setCellValue(studentGrades.get(i).getTotalscores());
				rownum++;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return wb;
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
