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

		NumberCode nc=new NumberCode();
		BufferedImage image=nc.getImage();
		String text=nc.getTest();
		
		request.getSession().setAttribute("verificationCode", text);
		
		nc.output(image, response.getOutputStream());

	};

}
