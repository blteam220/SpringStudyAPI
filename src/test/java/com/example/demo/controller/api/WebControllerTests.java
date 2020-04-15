package com.example.demo.controller.api;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;


@SpringBootTest
class WebControllerTests {
	
	@Autowired
	private WebController webController;
	private JSONObject case1 =  new JSONObject();

	@Autowired
	protected MessageSource messageSource;
	
	@Test
	void SyainInfoTest1() throws JSONException {
		JSONObject empObj =  new JSONObject();
		empObj.put("社員番号","10001");
		empObj.put("名前", "新垣");
		empObj.put("年齢", "23");
		empObj.put("所属", "開発者");		
		
		// status番号を入れる(失敗)
		case1.put("status","200");
	    // messageを入れる
		case1.put("messeage",messageSource.getMessage("200", null, Locale.JAPAN));
	    // 取得した社員データを入れる
		case1.put("data", empObj);
		
		assertTrue(webController.SyainInfo("10001").equals(case1.toString()));
	}
	
}
