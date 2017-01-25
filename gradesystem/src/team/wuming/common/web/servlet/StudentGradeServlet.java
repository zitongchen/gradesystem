package team.wuming.common.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.wuming.common.service.StudentGradeService;
import team.wuming.common.service.impl.StudentGradeServiceImpl;
import cn.itcast.servlet.BaseServlet;

public class StudentGradeServlet extends BaseServlet {

	private StudentGradeService studentGradeService = new StudentGradeServiceImpl();

	public void saveBkScore(HttpServletRequest request,
			HttpServletResponse response) {

		String[] user_acount = request.getParameter("user_acount").split(" ");
		String[] bkscore = request.getParameter("bkscore").split(" ");
		String kc = request.getParameter("kcId");
		String bh = request.getParameter("bh");
		studentGradeService.saveBkScore(user_acount, bkscore, kc, bh);
		PrintWriter write = null;
		try {
			write = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		write.println("保存补考成绩成功！");
		write.flush();
		write.close();
	}
}
