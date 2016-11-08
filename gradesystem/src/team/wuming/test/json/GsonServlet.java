package team.wuming.test.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GsonServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String std = request.getParameter("datalist");
		if (std != null) {
			System.out.println(std.toString());
		} else {
			System.out.println("ajax传递数据出错");
		}
		// 通过gson把对象数组反序列化到List中去
		Gson gson = new Gson();
		List<Student> stus = new ArrayList<Student>();
		JsonParser parser = new JsonParser();
		JsonArray jarray = parser.parse(std).getAsJsonArray();
		for (JsonElement jsonElement : jarray) {
			stus.add(gson.fromJson(jsonElement, Student.class));
		}
		System.out.println(stus.toString());
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("success");

	}

}
