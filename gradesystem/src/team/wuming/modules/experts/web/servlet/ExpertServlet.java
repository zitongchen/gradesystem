package team.wuming.modules.experts.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.UserException;

import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.experts.service.ExpertException;
import team.wuming.modules.experts.service.ExpertService;
import team.wuming.modules.experts.service.impl.ExpertServiceImpl;
import team.wuming.modules.users.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class ExpertServlet extends BaseServlet {
	private ExpertService expertService = new ExpertServiceImpl();

	// 教师登录
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException {

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		Expert form = new Expert();

		form.setExpacount(userId);
		form.setPassword(password);

		String verification = request.getParameter("verification");
		System.out.println("teacher" + verification);
		try {

			String verificationCode = (String) request.getSession()
					.getAttribute("verificationCode");
			if (!(verification.equals(verificationCode))) {
				request.setAttribute("verificationError", "验证码错误！");
				request.setAttribute("userId", userId);
				request.setAttribute("password", password);
				return "f:/jsps/common/login.jsp";
			}

			Expert expert = expertService.login(form);
			System.out.println("test" + expert);
			request.getSession().setAttribute("session_expert", expert);
			return "f:/jsps/expert/index.jsp";

		} catch (ExpertException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("userId", userId);
			request.setAttribute("password", password);
			return "f:/jsps/common/login.jsp";
		}
	}

	// 教师退出
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException {
		request.getSession().invalidate();
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

	// 教师查找数据
	public String findUserMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		String expertId = request.getParameter("expacount");
		Expert expert = expertService.findExpertMessage(expertId);
		request.setAttribute("request_expert", expert);
		return "f:/jsp/expert/usermessage.jsp";
	}

	// 教师更新密码
	public String updateExpertPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		Expert form = CommonUtils.toBean(request.getParameterMap(),
				Expert.class);
		if (form.getPassword() != request.getSession().getAttribute("password")) {
			request.setAttribute("passwordError", "原密码输入有误");
			return "f:/jsp/expert/updateexpertpassword.jsp";
		} else {
			form.setPassword(request.getParameter("newPassword"));
		}
		expertService.updateExpertPassword(form);
		request.getSession().invalidate();// 销毁session并让用户重新登录
		return "r:/index.jsp";
	}



}
