package team.wuming.modules.admin.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import team.wuming.modules.users.domain.User;

/**
 * @author Tony 接收servlet传递过来的的inputStream，把Excel里面的内容转化为一个List集合
 */
public class InputStudentMessageUitl {

	public List<User> studentMessageToList(InputStream excelFileStream)
			throws ParseException, IOException {
		List<User> userList = new ArrayList<User>();
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(excelFileStream);
			HSSFSheet sheet = workBook.getSheetAt(0);
			HSSFRow row = null;// excel的行
			HSSFCell cell = null;// excel的列

			int totalRow = sheet.getLastRowNum();// 得到Excel表格的行数
			for (int i = 1; i <= totalRow; i++) {
				User user = new User();
				row = sheet.getRow(i);
				// 考生号
				cell = row.getCell(0);
				user.setKsh(cell.getRichStringCellValue().toString());
				// 学号
				cell = row.getCell(1);
				cell.setCellType(cell.CELL_TYPE_STRING);
				user.setUser_acount(cell.getRichStringCellValue().toString());

				// 姓名
				cell = row.getCell(2);
				user.setNickname(cell.getRichStringCellValue().toString());
				// 性别
				cell = row.getCell(3);
				user.setXb(cell.getRichStringCellValue().toString());
				// 出生日期
				cell = row.getCell(4);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Date csrq = sdf.parse(cell.getRichStringCellValue().toString());
				user.setCsrq(csrq);

				// 身份证号
				cell = row.getCell(5);
				user.setSfzh(cell.getRichStringCellValue().toString());

				// 账号密码
				String sfzh = cell.getRichStringCellValue().toString();
				int length = sfzh.length();
				String password = sfzh.substring(length - 6, length);
				user.setPassword(password);
				// 政治面貌
				cell = row.getCell(6);
				user.setZzmm(cell.getRichStringCellValue().toString());
				// 民族
				cell = row.getCell(7);
				user.setMz(cell.getRichStringCellValue().toString());
				// 院校代码
				cell = row.getCell(8);
				user.setYxdm(cell.getRichStringCellValue().toString());
				// 院校名称
				cell = row.getCell(9);
				user.setYxmc(cell.getRichStringCellValue().toString());
				// 专业代码
				cell = row.getCell(10);
				user.setZydm(cell.getRichStringCellValue().toString());
				// 专业代码
				cell = row.getCell(11);
				user.setZymc(cell.getRichStringCellValue().toString());
				// 教学点
				cell = row.getCell(12);
				user.setXxdd(cell.getRichStringCellValue().toString());
				// 班别
				cell = row.getCell(13);
				user.setBh(cell.getRichStringCellValue().toString());
				// 层次
				cell = row.getCell(14);
				user.setCc(cell.getRichStringCellValue().toString());
				// 学制
				cell = row.getCell(15);
				user.setXz(cell.getRichStringCellValue().toString());
				// 学习形式
				cell = row.getCell(16);
				user.setXxxs(cell.getRichStringCellValue().toString());
				// 入学日期
				cell = row.getCell(17);
				Date rxrq = sdf.parse(cell.getRichStringCellValue().toString());
				user.setRxrq(rxrq);
				// 当前所在级
				cell = row.getCell(18);
				user.setDqszj(cell.getRichStringCellValue().toString());
				// 注册状态
				cell = row.getCell(19);
				user.setZczt(cell.getRichStringCellValue().toString());
				// 预计毕业日期
				cell = row.getCell(20);
				Date yjbyrq = sdf.parse(cell.getRichStringCellValue()
						.toString());
				user.setYjbyrq(yjbyrq);
				// 把user放到userList中
				userList.add(user);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return userList;
	}
}
