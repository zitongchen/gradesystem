package team.wuming.test;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

public class TestUserServlet extends BaseServlet {

	public void testArray(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String[]> test = request.getParameterMap();

		Set<String> keys = test.keySet();
		for (String keyid : keys) {
			System.out.println(test.get(keyid)[0]);
		}
	}
}
