package team.wuming.modules.admin.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.wuming.modules.admin.domain.Admin;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/AdminFilter")
public class AdminFilter implements Filter {


    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}


	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * 判断用户是否登陆了，登陆了便放行，还没有等的从定向到登陆页面
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Admin admin = (Admin) httpRequest.getSession().getAttribute(
				"session_admin");
		if (admin != null) {
			chain.doFilter(httpRequest, response);
		} else {
			httpResponse.sendRedirect(httpRequest.getContextPath()
					+ "/index.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
