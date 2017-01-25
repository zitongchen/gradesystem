package team.wuming.modules.users.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.omg.CORBA.UserException;

import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.common.service.StudentGradeService;
import team.wuming.common.service.impl.StudentGradeServiceImpl;
import team.wuming.modules.users.domain.User;
import team.wuming.modules.users.service.UserService;
import team.wuming.modules.users.service.impl.UserServiceImpl;
import cn.itcast.commons.CommonUtils;

public class UserServlet extends cn.itcast.servlet.BaseServlet {
	private UserService userService = new UserServiceImpl();
	private StudentGradeService studentGradeService = new StudentGradeServiceImpl();

	/**
	 * 学生登陆到成绩关系系统
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws UserException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		User form = new User();
		form.setUser_acount(userId);
		form.setPassword(password);
		String verification = request.getParameter("verification");
		try {

			/*
			 * 后台的验证码跟前台的验证码是否一致，不一致提示错误相信/
			 */
			String verificationCode = (String) request.getSession()
					.getAttribute("verificationCode");
			if (!(verification.equals(verificationCode))) {
				request.setAttribute("errorMessage", "验证码错误！");
				return "f:/jsps/user/update_password.jsp";
			}
			User user = userService.login(form);
			request.getSession().setAttribute("session_user", user);
			return "f:/jsps/user/homepage.jsp";
		} catch (team.wuming.modules.users.service.UserException e) {
			request.setAttribute("errorMessage", "账号或密码有错！");
			return "f:/index.jsp";
		}
	}




	// 查询学生信息
	public String findUserMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		String userId = request.getParameter("user_acount");
		User user = userService.findUserMessage(userId);
		request.setAttribute("user", user);
		return "f:/jsps/user/person_message.jsp";
	}

	/**
	 * 学生修改密码功能
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws UserException
	 */
	public String updateUserPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		String oldpassword=request.getParameter("oldpassword");
		User user = (User) request.getSession().getAttribute("session_user");
		if (!oldpassword.equals(user.getPassword())) {
			request.setAttribute("errorMessage", "原密码输入有误");
			return "f:/jsps/user/update_password.jsp";
		} else {
			String password = request.getParameter("newpassword");
			userService.updateUserPassword(user.getUser_acount(),password);
		}
		request.getSession().invalidate();// 销毁session并让用户重新登录
		return "r:/index.jsp";
	}

	/**
	 * 学生成绩查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws UserException
	 */
	public String queryUserGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {

		String userId = request.getParameter("user_acount");
		List<StudentGrade> studentGradeList = new ArrayList<StudentGrade>();
		studentGradeList = studentGradeService.queryUserGrade(userId);
		request.setAttribute("studentGradeList", studentGradeList);
		return "f:/jsps/user/query_grade.jsp";
	}
}