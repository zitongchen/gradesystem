package team.wuming.test.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class JsonServlet extends HttpServlet {

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String std = request.getParameter("datalist");
		if (std != null) {
			List<Student> studentList = JSON.parseArray(std, Student.class);

			System.out.println(studentList.get(0).toString());
		} else {
			System.out.println("ajax传递数据出错");
		}
		Student stu1 = new Student();
		stu1.setName("陈");
		stu1.setAge(21);
		Student stu2 = new Student();
		stu2.setName("李");
		stu2.setAge(20);
		List<Student> listTest = new ArrayList<Student>();
		listTest.add(stu1);
		listTest.add(stu2);
		String listTestString = JSON.toJSONString(listTest);
		System.out.println(listTestString);
		PrintWriter pw = response.getWriter();
		pw.println(listTestString);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("success");

	}

}
