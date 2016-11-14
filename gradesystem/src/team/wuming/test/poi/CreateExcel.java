package team.wuming.test.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class CreateExcel {
	/**
	 * 创建模板文件
	 * 
	 * @author David
	 * @param args
	 */
	public static void main(String[] args) {
		// 获取解析xml文件路径
		String path = System.getProperty("user.dir")
				+ "/resource/studentGrade.xml";
		File file = new File(path);
		SAXBuilder builder = new SAXBuilder();
		try {
			// 解析xml文件
			Document parse = builder.build(file);
			// 创建Excel
			HSSFWorkbook wb = new HSSFWorkbook();

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
						font.setFontHeightInPoints((short) 12);
						cellStyle.setFont(font);
						cell.setCellStyle(cellStyle);
						// 合并单元格/居中
						sheet.addMergedRegion(new CellRangeAddress(rspan,
								rspan, 0, cspan));
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
				for (column = 0; column < ths.size(); column++) {
					Element th = ths.get(column);
					Attribute valueAttr = th.getAttribute("value");
					HSSFCell cell = row.createCell(column);
					if (valueAttr != null) {
						String value = valueAttr.getValue();
						cell.setCellValue(value);
					}
				}
				rownum++;
			}

			// 设置数据区域样式
			Element tbody = root.getChild("tbody");
			Element tr = tbody.getChild("tr");
			int repeat = tr.getAttribute("repeat").getIntValue();

			List<Element> tds = tr.getChildren("td");
			for (int i = 0; i < repeat; i++) {
				HSSFRow row = sheet.createRow(rownum);
				for (column = 0; column < tds.size(); column++) {
					Element td = tds.get(column);
					HSSFCell cell = row.createCell(column);
					setType(wb, cell, td);
				}
				rownum++;
			}

			// 生成Excel导入模板
			File tempFile = new File("e:/" + templateName + ".xls");
			tempFile.delete();
			tempFile.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(tempFile);
			wb.write(stream);
			stream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if ("NUMERIC".equalsIgnoreCase(type)) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			Attribute formatAttr = td.getAttribute("format");
			String formatValue = formatAttr.getValue();
			formatValue = StringUtils.isNotBlank(formatValue) ? formatValue
					: "#,##0.00";
			cellStyle.setDataFormat(format.getFormat(formatValue));
		} else if ("STRING".equalsIgnoreCase(type)) {
			cell.setCellValue("");
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellStyle.setDataFormat(format.getFormat("@"));
		} else if ("DATE".equalsIgnoreCase(type)) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellStyle.setDataFormat(format.getFormat("yyyy-m-d"));
		} else if ("ENUM".equalsIgnoreCase(type)) {
			CellRangeAddressList regions = new CellRangeAddressList(
					cell.getRowIndex(), cell.getRowIndex(),
					cell.getColumnIndex(), cell.getColumnIndex());
			Attribute enumAttr = td.getAttribute("format");
			String enumValue = enumAttr.getValue();
			// 加载下拉列表内容
			DVConstraint constraint = DVConstraint
					.createExplicitListConstraint(enumValue.split(","));
			// 数据有效性对象
			HSSFDataValidation dataValidation = new HSSFDataValidation(regions,
					constraint);
			wb.getSheetAt(0).addValidationData(dataValidation);
		}
		cell.setCellStyle(cellStyle);
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
