package team.wuming.modules.experts.web.filter;

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
import team.wuming.modules.experts.domain.Expert;


@WebFilter("/ExpertFilter")
public class ExpertFilter implements Filter {


    public ExpertFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * 利用拦截器判断所访问的页面是否登陆了，若没有登陆跳转到登陆页面
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Expert expert = (Expert) httpRequest.getSession().getAttribute(
				"session_expert");
		Admin admin = (Admin) httpRequest.getSession().getAttribute(
				"session_admin");
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (expert != null || admin != null) {
			chain.doFilter(httpRequest, response);
		} else {
			httpResponse.sendRedirect(httpRequest.getContextPath()
					+ "/index.jsp");// 重定向到登录页面
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
