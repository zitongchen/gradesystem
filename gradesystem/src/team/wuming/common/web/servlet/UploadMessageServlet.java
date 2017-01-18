package team.wuming.common.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.wuming.common.service.UploadMessageService;
import team.wuming.common.service.impl.UploadMessageServiceImpl;
import cn.itcast.servlet.BaseServlet;

public class UploadMessageServlet extends BaseServlet {
	private UploadMessageService ulMessage = new UploadMessageServiceImpl();
	public void uploadPersonMessage(HttpServletRequest request,
			HttpServletResponse response) {
		String type = request.getParameter("type");
		String userId = request.getParameter("userId");
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		ulMessage.savePersonMessage(type, userId, key, val);
	}
}
