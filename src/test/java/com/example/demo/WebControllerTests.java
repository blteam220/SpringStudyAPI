package com.example.demo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebControllerTests {
	private final static String strUserId = "UserId";
	
	private WebController webController =  new WebController();
	private JSONObject case1 =  new JSONObject();
	private JSONObject case2 =  new JSONObject();
	private JSONObject case3 =  new JSONObject();
	
	@Test
	void SyainInfoTest1() throws JSONException {
		assertTrue(webController.SyainInfo("3").equals(""));
	}
	
	@Test
	void SyainInfoTest2() throws JSONException {
		case2.put("社員番号","1");
		case2.put("名前", "新垣");
		case2.put("年齢", 23);
		case2.put("所属", "営業");
		assertTrue(webController.SyainInfo("1").equals(case2.toString()));
	}
	
	@Test
	void SyainInfoTest3() throws JSONException {	
		case3.put("社員番号","2");
		case3.put("名前", "上原");
		case3.put("年齢", 24);
		case3.put("所属", "経理");
		assertTrue(webController.SyainInfo("2").equals(case3.toString()));
	}
}
