package team.wuming.common.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.wuming.common.util.NumberCode;

public class VerificationServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 创建一个NumberCode对象
		NumberCode nc=new NumberCode();
		BufferedImage image = nc.getImage();// 获取图片
		String text = nc.getTest();// 获取图片上面的字符串
		request.getSession().setAttribute("verificationCode", text);// 把字符串保存到session中
		nc.output(image, response.getOutputStream());// 输出图片到前端
	};

}
