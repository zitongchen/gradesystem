package team.wuming.modules.admin.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.SimpleFormatter;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.admin.service.AdminService;
import team.wuming.modules.admin.service.impl.AdminServiceImpl;
import team.wuming.modules.users.domain.User;

/**
 * 下载毕业班级的成绩登记表
 * 
 * @author Tony
 * 
 */
public class DownGraduateGradeSheet extends HttpServlet {

	private AdminService adminServict = new AdminServiceImpl();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String bh = request.getParameter("bh");
		String college = request.getParameter("college");
		User user = adminServict.searchUserByBh(bh);
		Map<String, ArrayList<StudentGrade>> mapList = new HashMap<String, ArrayList<StudentGrade>>();
		mapList = adminServict.SearchGraduateGrade(bh);
		if (mapList.isEmpty()) {
			request.setAttribute("errorMessage", "该班级学生不存在成绩！");
			request.getRequestDispatcher(
					"/jsps/admin/managestudent/down_graduate_sheet.jsp")
					.forward(request, response);
			return;
		}

		// 获取项目路径
		String path = request.getServletContext().getRealPath(
				"/resource/graduateStudentGradeSheet.xml");
		// 生成sheet表格
		File file = new File(path);
		SAXBuilder builder = new SAXBuilder();
		HSSFWorkbook wb = null;
		// 解析xml文件
		Document parse = null;
		try {
			parse = builder.build(file);
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		// 创建Excel
		wb = new HSSFWorkbook();
		// 创建sheet
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String time = sdf.format(date);
		Set<String> mapNameList = mapList.keySet();
		Iterator iterator = mapNameList.iterator();
		int i = 0;
		for (; iterator.hasNext();) {
			String user_acount = (String) iterator.next();
			ArrayList<StudentGrade> list = mapList.get(user_acount);
			HSSFSheet sheet = wb.createSheet(user_acount
					+ list.get(0).getNickname());
			makeSheet(list, time, college, user, sheet, parse, wb);
			i++;
		}
		String fileName = "毕业生成绩登记表.xls";
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("content-disposition", "attachment;filename="
				+ fileName);
		OutputStream outputStream = response.getOutputStream();// 创建一个输出流
		wb.write(outputStream);// 利用poi中的方法下载
		outputStream.flush();
		outputStream.close();
	}


	private void makeSheet(ArrayList<StudentGrade> list, String time,
			String college, User user, HSSFSheet sheet, Document parse,
			HSSFWorkbook wb) {
		try {
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
			String[][] titleValue = {
					{ " " },
					{ " " },
					{ list.get(0).getUser_acount(), list.get(0).getNickname() },
					{ user.getZymc(), user.getXxxs(), user.getCc(),
							user.getXz() } };
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				List<Element> tds = tr.getChildren("td");
				HSSFRow row = sheet.createRow(rownum);
				HSSFCellStyle cellStyle = wb.createCellStyle();// 创建单元格样式
				// 设置字体
				HSSFFont font = wb.createFont();
				font.setFontName("宋体");
				String fontWeigth = tr.getAttributeValue("fontweigth");// 字体是否加粗
				String fontSize = tr.getAttributeValue("fontsize");// 字体大小
				String center = tr.getAttributeValue("center");// 是否居中
				String rowHeigth = tr.getAttributeValue("rowheight");// 行高
				if (center != "0") { // 设置居中
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置单元格水平居中
					cellStyle
							.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
				}
				row.setHeightInPoints(Integer.parseInt(rowHeigth));// 设置行高
				if (fontWeigth != "0") {
					font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
				}
				font.setFontHeightInPoints((short) Integer.parseInt(fontSize));// 设置字体大小
				cellStyle.setFont(font);
				for (column = 0; column < tds.size(); column++) {
					Element td = tds.get(column);
					Attribute rowSpan = td.getAttribute("rowspan");
					Attribute colSpan = td.getAttribute("colspan");
					Attribute value = td.getAttribute("value");
					int rspan = rowSpan.getIntValue() - 1;
					int cspan = colSpan.getIntValue() - 1;
					HSSFCell cell = row.createCell(rspan);// 应为存在合并单元格，所以创建单元格在获取单元格合并后的列数
					if (value != null) {
						String val = value.getValue();
						cell.setCellValue(val + titleValue[rownum][column]);// 设置单元格内容
						cell.setCellStyle(cellStyle);
						if (cspan != 0) {
							sheet.addMergedRegion(new CellRangeAddress(rownum,
									rownum, rspan, cspan));
						}
					}
				}
				rownum++;
			}

			// 设置表头
			Element thead = root.getChild("thead");
			trs = thead.getChildren("tr");
			
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				HSSFRow row = sheet.createRow(rownum);
				List<Element> ths = tr.getChildren("th");
				HSSFCellStyle cellStyle = wb.createCellStyle();// 创建单元格样式
				// 设置字体
				HSSFFont font = wb.createFont();
				font.setFontName("宋体");
				row.setHeightInPoints(18);
				font.setFontHeightInPoints((short) 12);
				cellStyle.setFont(font);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置单元格水平居中
				cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
				cellStyle.setBorderBottom(BorderStyle.THIN);
				cellStyle.setBorderLeft(BorderStyle.THIN);
				cellStyle.setBorderRight(BorderStyle.THIN);
				cellStyle.setBorderTop(BorderStyle.THIN);
				for (column = 0; column < ths.size(); column++) {
					Element th = ths.get(column);
					Attribute valueAttr = th.getAttribute("value");
					HSSFCell cell = row.createCell(column);
					if (valueAttr != null) {
						String value = valueAttr.getValue();
						cell.setCellValue(value);
						cell.setCellStyle(cellStyle);
					}
				}
				rownum++;
			}

			// 设置数据区域样式
			Element tbody = root.getChild("tbody");
			Element tr = tbody.getChild("tr");
			List<Element> tds = tr.getChildren("td");

			for (int i = 0; i < list.size(); i++) {
				HSSFRow row = sheet.createRow(rownum);
				HSSFCellStyle cellStyle = wb.createCellStyle();// 创建单元格样式
				// 设置字体
				HSSFFont font = wb.createFont();
				font.setFontName("宋体");
				row.setHeightInPoints(18);
				font.setFontHeightInPoints((short) 12);
				cellStyle.setFont(font);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置单元格水平居中
				cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
				cellStyle.setBorderBottom(BorderStyle.THIN);
				cellStyle.setBorderLeft(BorderStyle.THIN);
				cellStyle.setBorderRight(BorderStyle.THIN);
				cellStyle.setBorderTop(BorderStyle.THIN);
				for (column = 0; column < tds.size(); column++) {
					Element td = tds.get(column);
					HSSFCell cell = row.createCell(column);
					cell.setCellValue(" ");
					cell.setCellStyle(cellStyle);
				}
				sheet.getRow(rownum).getCell(0)
						.setCellValue(list.get(i).getTitle());
				sheet.getRow(rownum).getCell(1)
						.setCellValue(list.get(i).getSthours());
				sheet.getRow(rownum).getCell(2)
						.setCellValue(list.get(i).getGradelei());
				sheet.getRow(rownum).getCell(3)
						.setCellValue(list.get(i).getTermth());
				sheet.getRow(rownum).getCell(4)
						.setCellValue(list.get(i).getTotalscores());
				sheet.getRow(rownum).getCell(5)
						.setCellValue(list.get(i).getRemark());
				rownum++;
			}

			// 设置落款样式
			Element foot = root.getChild("foot");
			trs = foot.getChildren("tr");
			rownum = rownum + 3;
			String[] footvalue = { college, time };
			for (int i = 0; i < trs.size(); i++) {
				tr = trs.get(i);
				HSSFRow row = sheet.createRow(rownum);
				tds = tr.getChildren("td");
				HSSFCellStyle cellStyle = wb.createCellStyle();// 创建单元格样式
				// 设置字体
				HSSFFont font = wb.createFont();
				font.setFontName("宋体");
				row.setHeightInPoints(18);
				font.setFontHeightInPoints((short) 12);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
				cellStyle.setFont(font);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置单元格水平居中
				cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
				for (column = 0; column < tds.size(); column++) {
					Element td = tds.get(column);
					Attribute rowSpan = td.getAttribute("rowspan");
					Attribute colSpan = td.getAttribute("colspan");
					int rspan = rowSpan.getIntValue() - 1;
					int cspan = colSpan.getIntValue() - 1;
					HSSFCell cell = row.createCell(rspan);
					if (footvalue != null) {
						String value = footvalue[i];
						cell.setCellValue(value);
						cell.setCellStyle(cellStyle);
						if (cspan != 0) {
							sheet.addMergedRegion(new CellRangeAddress(rownum,
									rownum, rspan, cspan));
						}
					}
				}
				rownum++;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
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
			String unit = width.getValue().replaceAll("[0-9,\\.]", "");
			String value = width.getValue().replaceAll(unit, "");
			int v = 0;
			if (StringUtils.isBlank(unit) || "px".endsWith(unit)) {
				v = Math.round(Float.parseFloat(value) * 37F);
			} else if ("em".endsWith(unit)) {
				v = Math.round(Float.parseFloat(value) * 267.5F);
			}
			sheet.setColumnWidth(i, v);
		}
	}
}
