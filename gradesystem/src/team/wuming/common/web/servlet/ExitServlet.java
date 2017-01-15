package team.wuming.common.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.UserException;

public class ExitServlet extends HttpServlet {

	/*
	 * 公共的退出功能，退出后返回到登陆页面 (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();// 注销登陆时生成的session并转发到登陆页面
		// request.getRequestDispatcher("/index.jsp").forward(request,
		// response);请求转发
		response.sendRedirect(request.getContextPath() + "/index.jsp");// 重定向
	}

}
