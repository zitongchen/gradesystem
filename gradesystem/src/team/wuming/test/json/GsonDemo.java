package team.wuming.test.json;

import org.junit.Test;

import com.google.gson.Gson;

public class GsonDemo {
	@Test
	public void gsonTest() {

		Gson gson = new Gson();
		Student student = new Student();
		student.setName("chenzitong");
		student.setAge(22);
		String jsonStr = gson.toJson(student);
		System.out.println(jsonStr);
	}

}
