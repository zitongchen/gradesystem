package team.wuming.common.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseLoginServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}


	/**
	 * 判断访问的人员类型是什么，通过不通的人员类型请求转发到到相应的 Servlet
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type.equals("1")) {
			request.getRequestDispatcher("/UserServlet").forward(request,
					response);
		} else if (type.equals("2")) {
			request.getRequestDispatcher("/ExpertServlet").forward(request,
					response);
		} else if (type.equals("3")) {
			request.getRequestDispatcher("/AdminServlet").forward(request,
					response);
		}

	}

}
