package team.wuming.modules.users.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.UserException;

import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.common.service.StudentGradeService;
import team.wuming.common.service.impl.StudentGradeServiceImpl;
import team.wuming.modules.users.domain.User;
import team.wuming.modules.users.service.UserService;
import team.wuming.modules.users.service.impl.UserServiceImpl;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class UserServlet extends BaseServlet {
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
				request.setAttribute("verificationError", "验证码错误！");
				request.setAttribute("userId", userId);
				request.setAttribute("password", password);
				return "f:/jsps/common/login_system.jsp";
			}
			User user = userService.login(form);
			request.getSession().setAttribute("session_user", user);
			return "f:/jsps/user/user_homepage.jsp";
		} catch (team.wuming.modules.users.service.UserException e) {
			request.setAttribute("msg", e.getMessage());// 账号密码有误，保存错误信息并回原来的账号跟密码
			request.setAttribute("userId", userId);
			request.setAttribute("password", password);
			return "f:/jsps/common/login_system.jsp";
		}
	}

	/**
	 * 注销登陆
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
		request.getSession().invalidate();
		return "r:/index.jsp";
	}
	/**
	 * 学生修改信息
	 * 
	 */
	public String updateUserMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		userService.updateUserMessage(form);
		return "r:/UserServlet?method=findUserMessage";
	}

	// 查询学生信息
	public String findUserMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		String userId = request.getParameter("user_acount");
		User user = userService.findUserMessage(userId);
		request.setAttribute("request_user", user);
		return "f:/jsps/user/index.jsp";
		// return "f:/jsps/user/usermessage.jsp";
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
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		User user = (User) request.getSession().getAttribute("session_user");
		if (!form.getPassword().equals(user.getPassword())) {
			request.setAttribute("passwordError", "原密码输入有误");
			return "f:/jsps/user/updateuserpassword.jsp";
		} else {
			form.setPassword(request.getParameter("newPassword"));
		}
		userService.updateUserPassword(form);
		request.getSession().invalidate();// 销毁session并让用户重新登录
		return "r:/index.jsp";
	}

	/**
	 * 学生忘记密码(暂时不做)
	 * 
	 */
	public String forgetUserPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {

		return null;
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

	/**
	 * 学生补考科目查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String queryUserFail(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("user_acount");
		List<StudentGrade> studentGradeList = studentGradeService
				.queryUserFail(userId);
		request.setAttribute("studentGradeFail", studentGradeList);
		return "f:/jsps/user/query_fail_grade.jsp";
	}


}