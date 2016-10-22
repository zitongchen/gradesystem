package team.wuming.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class GsonServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		Gson gson = new Gson();

		String std = request.getParameter("student");
		String stdName = request.getParameter("name");
		System.out.println(stdName);
		System.out.println(std);
		// Student student = gson.fromJson(std, Student.class);

		// System.out.println(student);

		Student stu = new Student();
		stu.setName("chenzitong");
		stu.setAge(33);
		String str = gson.toJson(stu);
		response.getWriter().write(str);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("success");

	}

}
