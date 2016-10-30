package team.wuming.modules.users.web.servlet;

import java.io.IOException;
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
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws UserException
	 *             学生登录到成绩管理系统
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
				return "f:/jsps/common/login.jsp";
			}
			User user = userService.login(form);

			request.getSession().setAttribute("session_user", user);
			return "f:/jsps/user/index.jsp";

		} catch (team.wuming.modules.users.service.UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("userId", userId);
			request.setAttribute("password", password);
			return "f:/jsps/common/login.jsp";
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws UserException
	 *             退出登录
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
	 * 学生修改密码
	 * 
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
	 * 学生查询成绩信息（按学年，学期）
	 * 
	 */
	public String queryUserGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		String userId = request.getParameter("user_acount");
		int pc = getPc(request);
		int ps = 3;// 给定ps的值，每页显示3条记录
		PageBean<StudentGrade> pb = studentGradeService.queryUserGrade(pc, ps,
				userId);
		/*
		 * List<StudentGrade> studentGradeList = userService
		 * .queryUserGrade(userId);
		 */
		pb.setUrl(getUrl(request));
		request.setAttribute("pb", pb);

		return "f:/jsps/test/PageBean.jsp";
	}

	/**
	 * @param request
	 * @return 获取pc,获取要请求的页面的页面数
	 */
	private int getPc(HttpServletRequest request) {
		String value = request.getParameter("pc");
		if (value == null || value.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(value);
	}

	/**
	 * @param request
	 * @return 获取请求路径，把pc的值处理掉然后返回pc值前的路径
	 */
	private String getUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String quesyString = request.getQueryString();
		if (quesyString.contains("&pc=")) {
			int endIndex = quesyString.lastIndexOf("&pc=");
			quesyString = quesyString.substring(0, endIndex);
		}
		return contextPath + servletPath + "?" + quesyString;
	}
}
