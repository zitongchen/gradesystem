package team.wuming.modules.users.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import team.wuming.modules.users.domain.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * 验证学生用户是否登陆了，还没有登陆便转发到底登陆页面
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User) httpRequest.getSession()
				.getAttribute("session_user");
		if (user != null) {
			chain.doFilter(httpRequest, response);
		} else {
			// httpRequest.setAttribute("msg", "您还没有登陆！");
			// 没有登陆的用户直接跳转到登陆页面.
			httpRequest.getRequestDispatcher("/jsps/common/login.jsp").forward(
					httpRequest, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
