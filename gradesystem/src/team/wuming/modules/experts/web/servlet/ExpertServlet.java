package team.wuming.modules.experts.web.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.UserException;

import team.wuming.common.domain.StudentGrade;
import team.wuming.common.service.StudentGradeService;
import team.wuming.common.service.impl.StudentGradeServiceImpl;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.experts.service.ExpertException;
import team.wuming.modules.experts.service.ExpertService;
import team.wuming.modules.experts.service.impl.ExpertServiceImpl;
import team.wuming.modules.users.domain.User;
import cn.itcast.commons.CommonUtils;

public class ExpertServlet extends cn.itcast.servlet.BaseServlet {
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
				request.setAttribute("errorMessage", "验证码错误！");
				return "f:/index.jsp";
			}
			Expert expert = expertService.login(form);// 验证输入的用户名跟密码时候正确
			request.getSession().setAttribute("session_expert", expert);
			return "f:/jsps/expert/homepage.jsp";
		} catch (ExpertException e) {
			request.setAttribute("errorMessage", e.getMessage());
			return "f:/index.jsp";
		}
	}

	// 查询老师的信息
	public String findExpertMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		String expertId = request.getParameter("expacount");
		Expert expert = expertService.findExpertMessage(expertId);
		request.setAttribute("expert", expert);
		return "f:/jsps/expert/person_message.jsp";
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
		String oldpassword = request.getParameter("oldpassword");
		Expert expert = (Expert) request.getSession().getAttribute(
				"session_expert");

		if (oldpassword.equals(expert.getPassword())) {// 判断输入的原密码是否跟session中的密码一致，不一致回显错误信息
			request.setAttribute("errorMessage", "原密码输入有误");
			return "f:/index.jsp";
		} else {
			String password = request.getParameter("newpassord");
			expertService.updateExpertPassword(expert.getExpacount(), password);
		}
		request.getSession().invalidate();// 销毁session并让用户重新登录
		return "r:/index.jsp";
	}

	/**
	 * 显示教师所教的班级(包含要补考的名单)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String findClassNameByExpert(HttpServletRequest request,
			HttpServletResponse response) {
		String expacount = request.getParameter("expacount");
		List<Object> classLists = expertService
				.findClassNameByExpert(expacount);
		List<Object> classListsFails = expertService
				.findClassNameFail(expacount);
		List<String> classNameFail = new ArrayList<String>();
		List<String> classNameList = new ArrayList<String>();
		for (Object object : classLists) {
			String className = String.valueOf(object);
			classNameList.add(className);
		}
		for (Object object : classListsFails) {
			String fail = String.valueOf(object);
			classNameFail.add(fail);
		}
		request.setAttribute("classList", classNameList);
		request.setAttribute("failList", classNameFail);
		return "f:/jsps/expert/show_classes.jsp";

	}

	/**
	 * 成绩录入功能：根据班级编号，教师编号信息获取相应班级的学生名列
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findClassStudentByClass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String classId = new String(request.getParameter("classId").getBytes(
				"iso-8859-1"), "utf-8");
		String expacount = request.getParameter("expacount");
		List<StudentGrade> studentGrades = studentGradeService // 创建学生成绩集合
				.findClassStudentByClass(classId, expacount);
		request.setAttribute("studentgrades", studentGrades);
		return "f:/jsps/expert/add_student_grade.jsp";
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
		String state=request.getParameter("state");
		String kc = request.getParameter("kc");
		if(state!="0"){
			return null;
		}
		String[] userId = request.getParameterValues("userId");
		String[] psGrades = request.getParameterValues("psscore");// 平时成绩
		String[] syGrades = request.getParameterValues("syscore");// 实验成绩
		String[] ksGrades = request.getParameterValues("ksscore");// 考试成绩
		String paecetime = request.getParameter("ps");// 平时成绩占比率
		String sytime = request.getParameter("sy");// 实验成绩占比率
		String terminal = request.getParameter("qm");// 考试成绩占比率
		Expert expert = (Expert) request.getSession().getAttribute(
				"session_expert");
		String classId = request.getParameter("classId");
		studentGradeService.saveClassStudentGrade(userId, psGrades, syGrades,
				ksGrades, paecetime, sytime, terminal, kc,
				expert.getExpacount());

		return "f:/ExpertServlet?method=findClassStudentByClass&classId="
				+ request.getParameter("classId") + "&expacount="
				+ expert.getExpacount();

	}

	// 补考考生查询
	public String findFailStudent(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String classId = new String(request.getParameter("classId").getBytes(
				"iso-8859-1"), "utf-8");
		String expacount = request.getParameter("expacount");
		List<StudentGrade> studentLists = studentGradeService.findFailStudent(
				classId, expacount);
		request.setAttribute("studentList", studentLists);
		return "f:/jsps/expert/add_student_failgrade.jsp";
	}

	// 保存补考成绩
	public String saveFailStudentGrade(HttpServletRequest request,
			HttpServletResponse response) {
		String state = request.getParameter("state");
		// 获取课程
		String kc = request.getParameter("kc");
		String[] userId = request.getParameterValues("userId");
		String[] bkGrades = request.getParameterValues("bkscore");// 补考成绩
		Expert expert = (Expert) request.getSession().getAttribute(
				"session_expert");
		String classId = request.getParameter("classId");
		studentGradeService.saveFailStudentGrade(userId, bkGrades, kc,
				expert.getExpacount());
		return "f:/ExpertServlet?method=findFailStudent&classId="
				+ request.getParameter("classId") + "&expacount="
				+ expert.getExpacount();

	}

	/**
	 * 改变学生成绩状态state,让老师提交后不能够更改
	 */
	public void changeGradeState(HttpServletRequest request,
			HttpServletResponse response) {
		String kcId = request.getParameter("kcId");
		String bh = request.getParameter("bh");
		String message = null;
		PrintWriter out = null;
		try {
			out = response.getWriter();
			try {
				expertService.changeGradeState(bh, kcId);
				request.setAttribute("successMessage", "提交成绩成功！");
				out.println("成绩提交成功！");
			} catch (Exception e) {
				out.println("成绩提交失败！");
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			out.close();
		}
	}

}
