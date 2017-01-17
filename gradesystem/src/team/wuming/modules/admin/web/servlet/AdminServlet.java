package team.wuming.modules.admin.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import team.wuming.common.domain.Xuexid;
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
				request.setAttribute("verificationError", "验证码错误！");
				request.setAttribute("userId", userId);
				request.setAttribute("password", password);
				return "f:/index.jsp";
			}
			Admin Admin = adminService.login(form);
			request.getSession().setAttribute("session_admin", Admin);
			return "f:/jsps/admin/homepage.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("userId", userId);
			request.setAttribute("password", password);
			return "f:/index.jsp";
		}
	}

	// 退出
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException {
		request.getSession().invalidate();
		return "r:/index.jsp";
	}

	//管理员信息 更新
	public String updateAdminMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		Admin form = CommonUtils.toBean(request.getParameterMap(), Admin.class);
		adminService.updateAdminMessage(form);
		return "r:/AdminServlet?method=findAdminMessage";
	}

	// 管理员更新密码
	public String updateAdminPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		Admin form = new Admin();
		form.setAdmin_acount(request.getParameter("userId"));
		form.setPassword(request.getParameter("password"));
		Admin admin = (Admin) request.getSession()
				.getAttribute("session_admin");
		if (!form.getPassword().equals(admin.getPassword())) {
			request.setAttribute("passwordError", "原密码输入有误");
			return "f:/jsps/admin/update_password.jsp";
		} else {
			form.setPassword(request.getParameter("newPassword"));
		}
		adminService.updateAdminPassword(form);
		request.getSession().invalidate();// 销毁session并让用户重新登录
		return "r:/index.jsp";
	}

	

	//代老师管理成绩：根据教师编号查询教师所教的班级
	public String findClassByExpertId(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		String expacount = request.getParameter("expertId");
		List<Object> classLists = expertService
				.findClassNameByExpert(expacount);
		List<String> classNameList = new ArrayList<String>();
		for (Object object : classLists) {
			String className = String.valueOf(object);
			classNameList.add(className);
		}
		request.setAttribute("classList", classNameList);
		request.setAttribute("expacount", expacount);
		return "f:/jsps/admin/search_experts.jsp";
	}


	// 添加课程信息，不涉及到图片的上传
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
	// 添加专业
	public String addMaijor(HttpServletRequest request,
			HttpServletResponse response) {
		// 把表单内容封装到Maijor对象中
		Maijor maijor = CommonUtils.toBean(request.getParameterMap(),
				Maijor.class);
		adminService.addMaijor(maijor);
		request.setAttribute("successMessage", "专业添加成功！");
		return "f:/jsps/common/active_message.jsp";
	}

	// 添加学习地点
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

	// 管理员根据情况向studentGrade写入内容,并返回操作信息！
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

}
