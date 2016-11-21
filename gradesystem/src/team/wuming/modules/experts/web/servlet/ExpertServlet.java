package team.wuming.modules.experts.web.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.UserException;

import team.wuming.common.domain.Classes;
import team.wuming.common.domain.StudentGrade;
import team.wuming.common.service.StudentGradeService;
import team.wuming.common.service.impl.StudentGradeServiceImpl;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.experts.service.ExpertException;
import team.wuming.modules.experts.service.ExpertService;
import team.wuming.modules.experts.service.impl.ExpertServiceImpl;
import team.wuming.modules.users.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class ExpertServlet extends BaseServlet {
	private ExpertService expertService = new ExpertServiceImpl();
	private StudentGradeService studentGradeService = new StudentGradeServiceImpl();

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws UserException
	 *             教师登陆功能：根据教师输入的账号跟密码核对数据库，查询是否账号密码匹配
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		Expert form = new Expert();
		form.setExpacount(userId);
		form.setPassword(password);
		String verification = request.getParameter("verification");// 获取前端传输过来的验证码
		try {
			String verificationCode = (String) request.getSession()// 获取后台中的验证码
					.getAttribute("verificationCode");
			if (!(verification.equals(verificationCode))) { // 前端验证码跟后台验证码进行比较，若验证码错误在前端页面显示错误信息，并回显用户名跟密码
				request.setAttribute("verificationError", "验证码错误！");
				request.setAttribute("userId", userId);
				request.setAttribute("password", password);
				return "f:/jsps/common/login_system.jsp";
			}
			Expert expert = expertService.login(form);// 验证输入的用户名跟密码时候正确
			request.getSession().setAttribute("session_expert", expert);
			return "f:/jsps/expert/expert_homepage.jsp";
		} catch (ExpertException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("userId", userId);
			request.setAttribute("password", password);
			return "f:/jsps/common/login_system.jsp";
		}
	}


	/**
	 * 退出功能
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws UserException
	 */
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException {
		request.getSession().invalidate();// 注销登陆时生成的session并转发到登陆页面
		return "r:/index.jsp";
	}

	// 教师更新数据
	public String updateExpertMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		Expert form = CommonUtils.toBean(request.getParameterMap(),
				Expert.class);
		expertService.updateExpertMessage(form);
		return "r:/ExpertServlet?method=findExpertMessage";
	}

	// 获取教师信息(直接通过session获取教师的信息)
	public String findExpertMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		String expertId = request.getParameter("expacount");
		Expert expert = expertService.findExpertMessage(expertId);
		request.setAttribute("request_expert", expert);
		return "f:/jsp/expert/usermessage.jsp";
	}

	/**
	 * 密码修改功能
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws UserException
	 */
	public String updateExpertPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		Expert form = new Expert();
		form.setPassword(request.getParameter("password"));

		Expert expert = (Expert) request.getSession().getAttribute(
				"session_expert");
		form.setExpacount(expert.getExpacount());
		if (!form.getPassword().equals(expert.getPassword())) {// 判断输入的原密码是否跟session中的密码一致，不一致回显错误信息
			request.setAttribute("passwordError", "原密码输入有误");
			return "f:/jsps/expert/update_password.jsp";
		} else {
			form.setPassword(request.getParameter("newPassword"));
		}

		expertService.updateExpertPassword(form);

		request.getSession().invalidate();// 销毁session并让用户重新登录
		return "r:/index.jsp";
	}

	/**
	 * 显示教师所教的班级
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String findClassNameByExpert(HttpServletRequest request,
			HttpServletResponse response) {
		String expacount = request.getParameter("expacount");
		List<Classes> classList = expertService
				.findClassNameByExpert(expacount);
		request.setAttribute("classList", classList);
		return "f:/jsps/expert/show_classes.jsp";
		/*
		 * List<Object> class_ids =
		 * expertService.findClassNameByExpert(expacount); List<String> classes
		 * = new ArrayList<String>(); Iterator<Object> iterator =
		 * class_ids.iterator();// 创建一个Object迭代器，并把Object转化为String对象并存入到List集合中
		 * while (iterator.hasNext()) {
		 * classes.add(String.valueOf(iterator.next())); }
		 * request.setAttribute("class_id", class_ids);
		 */
	}

	/**
	 * 成绩录入功能：根据班级编号，教师编号信息获取相应班级的学生名列
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String findClassStudentByClass(HttpServletRequest request,HttpServletResponse response){
		String classId=request.getParameter("classId");
		String expacount = request.getParameter("expacount");
		String className = request.getParameter("className");
		List<StudentGrade> studentGrades = studentGradeService // 创建学生成绩集合
				.findClassStudentByClass(classId, expacount);
		List<Object> userNameObjectList = studentGradeService // 创建学生姓名集合
				.queryUserName(classId);
		Classes classes = studentGradeService.findClassNameByClassId(classId);
		List<String> userNameList=new ArrayList<String>();
		for (Object object : userNameObjectList) {
			userNameList.add(String.valueOf(object));
		}
		request.setAttribute("studentgrades", studentGrades);
		request.setAttribute("studentNameList", userNameList);
		request.setAttribute("classes", classes);
		return "f:/jsps/expert/class_normal.jsp";
	}

	/**
	 * 学生成绩保存功能
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String saveClassStudentGrade(HttpServletRequest request,
			HttpServletResponse response) {
		String[] userId = request.getParameterValues("userId");
		String[] psGrades = request.getParameterValues("psgrade");// 平时成绩
		String[] ksGrades = request.getParameterValues("ksgrade");// 考试成绩
		String paecetime = request.getParameter("paecetime");// 平时成绩占比率
		String terminal = request.getParameter("terminal");// 考试成绩占比率
		Expert expert = (Expert) request.getSession().getAttribute(
				"session_expert");
		String classId = request.getParameter("classId");
		studentGradeService.saveClassStudentGrade(userId, psGrades, ksGrades,
				paecetime, terminal);

		return "f:/ExpertServlet?method=findClassStudentByClass&classId="
				+ request.getParameter("classId") + "&expacount="
				+ expert.getExpacount();

	}


	/**
	 * 打印学生成绩
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void printStudentGrade(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String classId = request.getParameter("classId");
		String expacount = request.getParameter("expacount");

		/*
		 * List<StudentGrade> studentGrades = studentGradeService
		 * .findClassStudentByClass(classId, expacount);
		 * 
		 * List<Object> userNameObjectList = studentGradeService
		 * .queryUserName(classId); List<String> userNameList=new
		 * ArrayList<String>(); for (Object object : userNameObjectList) {
		 * userNameList.add(String.valueOf(object)); }
		 */

		studentGradeService.createGradeSheet(classId, expacount);
		// 创建一个空白的工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 创建一个电子表
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
		/*
		 * FileOutputStream out = new FileOutputStream( new
		 * File("Writersheet.xlsx")); workbook.write(out); out.close();
		 * System.out.println("writer out success!");
		 */
		String fileName = "学生成绩表.xlsx";
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("content-disposition", "attachment;filename="
				+ fileName);
		OutputStream outputStream = response.getOutputStream();// 创建一个输出流
		workbook.write(outputStream);// 利用poi中的方法下载
		outputStream.flush();
		outputStream.close();
	}

}
