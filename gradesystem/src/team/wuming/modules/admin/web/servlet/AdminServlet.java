package team.wuming.modules.admin.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.UserException;

import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.admin.service.AdminService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminServlet extends BaseServlet {
	private AdminService adminService = new AdminService();

	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException {
		Admin form = CommonUtils.toBean(request.getParameterMap(), Admin.class);
		String verification = request.getParameter("verification");
		try {
			Admin Admin = adminService.login(form);

			String verificationCode = (String) request.getSession()
					.getAttribute("verificationCode");
			if (verification != verificationCode) {
				request.setAttribute("verificationError", "验证码错误！");
				request.setAttribute("form", form);
				return "f:/index.jsp";
			}
			request.getSession().setAttribute("session_Admin", Admin);
			return "r:/jsp/admin/index.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/index.jsp";
		}
	}

	// 教师退出
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException {
		request.getSession().invalidate();
		return "r:/index.jsp";
	}

	// 教师更新数据
	public String updateAdminMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		Admin form = CommonUtils.toBean(request.getParameterMap(), Admin.class);
		adminService.updateAdminMessage(form);
		return "r:/AdminServlet?method=findAdminMessage";
	}

	// 教师查找数据
	public String findUserMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		String AdminId = request.getParameter("expacount");
		Admin Admin = adminService.findAdminMessage(AdminId);
		request.setAttribute("request_Admin", Admin);
		return "f:/jsp/admin/usermessage.jsp";
	}

	// 教师更新密码
	public String updateAdminPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		Admin form = CommonUtils.toBean(request.getParameterMap(), Admin.class);
		if (form.getPassword() != request.getSession().getAttribute("password")) {
			request.setAttribute("passwordError", "原密码输入有误");
			return "f:/jsp/admin/updateAdminpassword.jsp";
		} else {
			form.setPassword(request.getParameter("newPassword"));
		}
		adminService.updateAdminPassword(form);
		request.getSession().invalidate();// 销毁session并让用户重新登录
		return "r:/index.jsp";
	}

}
