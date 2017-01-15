package team.wuming.test.json;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class jsontest {

	@Test
	public void demo() {
		List<Object> list = new ArrayList<Object>();
		list.add("12");
		list.add("12");
		list.add("12");
		System.out.println(JSON.toJSONString(list));
	}
}
