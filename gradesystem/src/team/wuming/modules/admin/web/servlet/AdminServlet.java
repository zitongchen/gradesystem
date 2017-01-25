package team.wuming.modules.admin.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.omg.CORBA.UserException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import team.wuming.common.domain.Maijor;
import team.wuming.common.domain.Objcenter;
import team.wuming.common.domain.StudentGrade;
import team.wuming.common.domain.Xuexid;
import team.wuming.common.service.StudentGradeService;
import team.wuming.common.service.impl.StudentGradeServiceImpl;
import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.admin.service.AdminService;
import team.wuming.modules.admin.service.impl.AdminServiceImpl;
import team.wuming.modules.admin.util.InputStudentMessageUitl;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.experts.service.ExpertService;
import team.wuming.modules.experts.service.impl.ExpertServiceImpl;
import team.wuming.modules.users.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminServlet extends BaseServlet {
	private AdminService adminService = new AdminServiceImpl();
	private ExpertService expertService = new ExpertServiceImpl();
	private StudentGradeService studentGradeService = new StudentGradeServiceImpl();
	/*
	 * 管理员登陆页面
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		Admin form = new Admin();
		form.setAdmin_acount(userId);
		form.setPassword(password);
		String verification = request.getParameter("verification");
		try {
			String verificationCode = (String) request.getSession()
					.getAttribute("verificationCode");
			if (!(verification.equals(verificationCode))) {
				request.setAttribute("errorMessage", "验证码错误！");
				return "f:/index.jsp";
			}
			Admin Admin = adminService.login(form);
			request.getSession().setAttribute("session_admin", Admin);
			return "f:/jsps/admin/homepage.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
			return "f:/index.jsp";
		}
	}

	/*
	 * 查询教师信息（个人中心）
	 */
	public String findAdminMessage(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("admin_acount");
		Admin admin = adminService.findAdminMessage(userId);
		request.setAttribute("admin", admin);
		return "f:/jsps/admin/person/person_message.jsp";
	}


	/*
	 * 更新教师密码（个人中心）
	 */
	public String updateAdminPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {

		String oldpassword = request.getParameter("oldpassword");
		Admin admin = (Admin) request.getSession()
				.getAttribute("session_admin");
		if (!oldpassword.equals(admin.getPassword())) {
			request.setAttribute("errorMessage", "原密码输入有误");
			return "f:/jsps/admin/person/update_password.jsp";
		} else {
			String password = request.getParameter("newpassword");
			adminService.updateAdminPassword(admin.getAdmin_acount(), password);
		}
		request.getSession().invalidate();// 销毁session并让用户重新登录
		return "r:/index.jsp";
	}



	/*
	 * 添加课程（系统管理）
	 */
	public String addObjcenter(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Objcenter objecenter = CommonUtils.toBean(request.getParameterMap(),
				Objcenter.class);
		// 把对应字段从字符串类型转换到int类型
		if (request.getParameter("sthours") != "") {
			Integer sthours = Integer.valueOf(request.getParameter("sthours"));
			objecenter.setSthours(sthours);
		}
		if (request.getParameter("classhour") != "") {
			Integer classhour = Integer.valueOf(request
					.getParameter("classhour"));
			objecenter.setClasshour(classhour);
		}
		if (request.getParameter("sbhour") != "") {
			Integer sbhour = Integer.valueOf(request.getParameter("sbhour"));

			objecenter.setSbhour(sbhour);
		}
		if (request.getParameter("score") != "") {
			Integer score = Integer.valueOf(request.getParameter("score"));
			objecenter.setScore(score);
		}
		adminService.addObjcenter(objecenter);
		request.setAttribute("successMessage", "课程添加成功！");
		return "f:/jsps/common/active_message.jsp";
	}

	/*
	 * 添加专业（系统管理，现在暂时不用-专业又学生信息表自动生成）
	 */
	public String addMaijor(HttpServletRequest request,
			HttpServletResponse response) {
		// 把表单内容封装到Maijor对象中
		Maijor maijor = CommonUtils.toBean(request.getParameterMap(),
				Maijor.class);
		adminService.addMaijor(maijor);
		request.setAttribute("successMessage", "专业添加成功！");
		return "f:/jsps/common/active_message.jsp";
	}

	/*
	 * 添加学习地点（暂时不用，学习地点表有学生信息表字段自动生成)
	 */
	public String addXuexid(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		Xuexid xuexid = CommonUtils.toBean(request.getParameterMap(),
				Xuexid.class);

		if (request.getParameter("yongdate") != "") {
			// 把Stringe格式转换为Date格式
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date yongdate = formatter.parse(request.getParameter("yongdate"));
			xuexid.setYongdate(yongdate);
		}
		adminService.addXuexid(xuexid);
		request.setAttribute("successMessage", "学习地点添加成功！");
		return "f:/jsps/admin/active_message.jsp";
	}

	/*
	 * 添加班级课程-即向studentgrade表写入班级，课程等主要信息（系统管理)
	 */
	public String addStudentGrade(HttpServletRequest request,
			HttpServletResponse response) {
		String className = request.getParameter("bh");
		String expacount = request.getParameter("expacount");
		String visit_count = request.getParameter("kc");
		adminService.addStudentGrade(className, visit_count, expacount);
		try {
			request.setAttribute("successMessage", "添加班级课程成功！");
			return "f:/jsps/common/active_message.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMessage", "添加班级课程失败！");
			return "f:/jsps/common/active_message.jsp";
		}
	}

	/*
	 * 根据教师编号查询教师所教班级(代老师管理学生成绩，显示教师所教班级)
	 */
	public String searchClassById(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		List<String> classList=adminService.searchClass(id);
		List<String> classFailList=adminService.searchFailClass(id);
		if (classFailList.isEmpty() && classList.isEmpty()) {
			request.setAttribute("errorMessage", "该教师不存在任教班级！");
			request.setAttribute("id", id);
			return "f:/jsps/admin/managegrade/manage_grade.jsp";
		} else {
			request.setAttribute("classList", classList);
			request.setAttribute("classFailList", classFailList);
			request.setAttribute("id", id);
			return "f:/jsps/admin/managegrade/manage_grade.jsp";
		}
	}

	/*
	 * 根据班级，教师编号查询学生(代老师管理某个班级，展示该班级的学生名单，录入成绩）
	 */
	public String findStudent(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String bh = new String(request.getParameter("classId")
				.getBytes("iso-8859-1"), "utf-8");
		String expacount = request.getParameter("expacount");
		List<StudentGrade> studentGrades = studentGradeService // 创建学生成绩集合
				.findClassStudentByClass(bh, expacount);
		String expertName = studentGradeService.findExpertName(expacount);
		request.setAttribute("expertName", expertName);
		request.setAttribute("studentgrades", studentGrades);
		return "f:/jsps/admin/managegrade/add_student_grade.jsp";
	}

	/*
	 * 根据补考班级，教师编号查询学生（代老师管理某个班级，展示该班级的学生名单，录入成绩）
	 */
	public String findFailStudent(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String bh = new String(request.getParameter("classId")
				.getBytes("iso-8859-1"), "utf-8");
		String expacount = request.getParameter("expacount");
		List<StudentGrade> studentLists = studentGradeService.findFailStudent(
				bh, expacount);
		
		String expertName = studentGradeService.findExpertName(expacount);
		request.setAttribute("expertName", expertName);
		request.setAttribute("studentList", studentLists);
		if (studentLists.get(0).getGradelei().equals("正考")) {
			request.setAttribute("gradelei", "补考");
		} else if (studentLists.get(0).getGradelei().equals("补考")) {
			request.setAttribute("gradelei", "补考2");
		} else if (studentLists.get(0).getGradelei().equals("补考2")) {
			request.setAttribute("gradelei", "补考3");
		}

		return "f:/jsps/admin/managegrade/add_student_failgrade.jsp";
	}

	/*
	 * 保存学生成绩，（代老师管理成绩)
	 */
	public String saveStudentGrade(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String kc = request.getParameter("kc");
		String classId = request.getParameter("classId");
		classId = new String(classId.getBytes("utf-8"), "iso-8859-1");
		String expacount = request.getParameter("expacount");
		String[] userId = request.getParameterValues("userId");
		String[] psGrades = request.getParameterValues("psscore");// 平时成绩
		String[] syGrades = request.getParameterValues("syscore");// 实验成绩
		String[] ksGrades = request.getParameterValues("ksscore");// 考试成绩
		String paecetime = request.getParameter("ps");// 平时成绩占比率
		String sytime = request.getParameter("sy");// 实验成绩占比率
		String terminal = request.getParameter("qm");// 考试成绩占比率
		Admin admin=(Admin) request.getSession().getAttribute("session_admin");
		studentGradeService.saveClassStudentGrade(userId, psGrades, syGrades,
				ksGrades, paecetime, sytime, terminal, kc,
				admin.getAdmin_acount());
		return "r:/AdminServlet?method=findStudent&classId=" + classId
				+ "&expacount=" + expacount;

	}

	/*
	 * 保存补考信息（代老师管理成绩)
	 */
	public String saveFailStudentGrade(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String state = request.getParameter("state");
		String classId = request.getParameter("classId");
		classId = new String(classId.getBytes("utf-8"), "iso-8859-1");
		String expacount = request.getParameter("expacount");
		String gradelei = request.getParameter("gradelei");

		// 获取课程
		String kc = request.getParameter("kc");
		String[] userId = request.getParameterValues("userId");
		String[] bkGrades = request.getParameterValues("bkscore");// 补考成绩
		Admin admin = (Admin) request.getSession()
				.getAttribute("session_admin");
		studentGradeService.saveFailStudentGrade(userId, bkGrades, kc,
				admin.getAdmin_acount(), gradelei);
		
		return "r:/AdminServlet?method=searchClassById&id=" + expacount;

	}

	/*
	 * 根据学生姓名或学号查询学生成绩(成绩管理-查询学生成绩)
	 */
	public String searchStudentGrade(HttpServletRequest request,
			HttpServletResponse response) {
		String type = request.getParameter("search_type");
		String value = request.getParameter("text");

		List<StudentGrade> studentGradeList = new ArrayList<StudentGrade>();
		if (type.equals("acount")) {
			studentGradeList = studentGradeService.queryUserGrade(value);
		} else if (type.equals("nickname")) {
			studentGradeList = studentGradeService
					.serchStudentGradeByName(value);
		}
		if (studentGradeList.isEmpty()) {
			request.setAttribute("errorMessage", "not_null");
			return "f:/jsps/admin/managestudent/search_student_grade.jsp";
		} else {
			request.setAttribute("studentGradeList", studentGradeList);
			return "f:/jsps/admin/managestudent/display_student_grade.jsp";
		}
	}

	/*
	 * 显示未审核成绩的班级（成绩管理-审核成绩)
	 */
	public String displayNoAuditGrades(HttpServletRequest request,
			HttpServletResponse response) {
		List<StudentGrade> gradeList = adminService.displayNoAuditGrade();
		if (gradeList.isEmpty()) {
			request.setAttribute("errorMessage", "not null");
		} else {
			request.setAttribute("gradeList", gradeList);
		}
		return "f:/jsps/admin/managegrade/check_grades.jsp";
	}

	/*
	 * 审核成绩（成绩管理）
	 */
	public String auditGrades(HttpServletRequest request,
			HttpServletResponse response) {
		String[] items = request.getParameterValues("items");

		String shenhe = request.getParameter("shenhe");
		List<String[]> messageList = new ArrayList<String[]>();
		for (int index = 0; index < items.length; index++) {
			String[] message = items[index].split("[+]");
			messageList.add(message);
		}
		adminService.auditGrades(messageList, shenhe);

		return "r:/AdminServlet?method=displayNoAuditGrades";
	}

	/*
	 * 审核成绩，详细展示某班级，某课程的信息（成绩管理-审核成绩)
	 */
	public String searchClassGrade(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String bh = new String(request.getParameter("bh")
				.getBytes("iso-8859-1"), "utf-8");
		String visit_count = request.getParameter("visit_count");
		List<StudentGrade> classGradeList = adminService.searchClassGrade(bh,
				visit_count);
		request.setAttribute("classGradeList", classGradeList);
		return "f:/jsps/admin/managegrade/display_grade.jsp";
	}
}
