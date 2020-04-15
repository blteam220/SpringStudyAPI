package com.example.demo.controller.api;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
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
	
	private JSONObject caseToken =  new JSONObject();

	@Autowired
	protected MessageSource messageSource;
	
	@Test
	void SyainInfoTest1() throws JSONException {
		
		JSONObject empObj =  new JSONObject();
		empObj.put("社員番号","10001");
		empObj.put("名前", "新垣");
		empObj.put("年齢", "23");
		empObj.put("所属", "開発者");		
		
		// passwordを入れる
		caseToken.put("password","password2");
		caseToken.put("messeage",messageSource.getMessage("200", null, Locale.JAPAN));
//		System.out.print(webController.getToken("password2").toString());
//		System.out.print(caseToken.toString());
		assertTrue(webController.getToken("password2").equals(caseToken.toString()));
		
		JSONObject case1 =  new JSONObject();
		// status番号を入れる(失敗)
		case1.put("status","200");
	    // messageを入れる
		case1.put("messeage",messageSource.getMessage("200", null, Locale.JAPAN));
	    // 取得した社員データを入れる
		case1.put("data", empObj);
//		System.out.print(webController.SyainInfo("10001","password2").toString());
//		System.out.print(case1.toString());
		assertTrue(webController.SyainInfo("10001","password2").equals(case1.toString()));
	}
	
	@Test
	void SyainInfoOK() throws JSONException {
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
		
		webController.getToken("0123");
		assertTrue(webController.SyainInfo("10001","0123").equals(case1.toString()));
	}
	
	@Test
	void SyainInfoNG() throws JSONException {
		JSONObject empObj =  new JSONObject();
		
		// status番号を入れる(失敗)
		case1.put("status","300");
	    // messageを入れる
		case1.put("messeage",messageSource.getMessage("300", null, Locale.JAPAN));
	    // 取得した社員データを入れる
		case1.put("data", empObj);
		
		webController.getToken("0123");
		assertTrue(webController.SyainInfo("a","0123").equals(case1.toString()));
	}
	
	@Test
	void getTokenOK() throws JSONException {
	    // 取得した社員データを入れる
		case1.put("password", "321");
		// messageを入れる
		case1.put("messeage",messageSource.getMessage("200", null, Locale.JAPAN));
		
		assertTrue(webController.getToken("321").equals(case1.toString()));
	}
	
	@Test
	void getTokenNG() throws JSONException {
		JSONObject empObj =  new JSONObject();
		empObj.put("password","12345");

	    // messageを入れる
		case1.put("messeage",messageSource.getMessage("400", null, Locale.JAPAN));
		
		assertTrue(webController.getToken("12345").equals(case1.toString()));
	}
	
	@Test
	void GetEmployeeWorkingOK() throws JSONException {
		
		// status番号を入れる(失敗)
		case1.put("status","200");
	    // messageを入れる
		case1.put("messeage",messageSource.getMessage("200", null, Locale.JAPAN));
	    // 取得した社員データを入れる
		case1.put("employee_id","10001");
		case1.put("year", "2020");
		case1.put("month", "1");
		case1.put("workingHours", "70");
		
		webController.getToken("0123");
		assertTrue(webController.GetEmployeeWorking("10001","2020","1","0123").equals(case1.toString()));
	}
	
	@Test
	void GetEmployeeWorkingNG() throws JSONException {
		JSONObject empObj =  new JSONObject();
		
		// status番号を入れる(失敗)
		case1.put("status","300");
	    // messageを入れる
		case1.put("messeage",messageSource.getMessage("300", null, Locale.JAPAN));
		
		webController.getToken("0123");
		assertTrue(webController.GetEmployeeWorking("10001","2020","a","0123").equals(case1.toString()));
	}
	
	@Test
	void GetEmployeeWorkingListOK() throws JSONException {
		//　勤務時間データ用　JSON形式オブジェクト
		List<JSONObject> workObj = new ArrayList<JSONObject>();
		
		// 1件目
		JSONObject empObj1 =  new JSONObject();
		empObj1.put("employee_id",10001);
		empObj1.put("year", "2020");
		empObj1.put("month", "1");
		empObj1.put("day", "7");
		empObj1.put("working_hour", 9);
		workObj.add(empObj1);
		
		// 2件目
		JSONObject empObj2 =  new JSONObject();
		empObj2.put("employee_id",10001);
		empObj2.put("year", "2020");
		empObj2.put("month", "1");
		empObj2.put("day", "8");
		empObj2.put("working_hour", 7);
		workObj.add(empObj2);
		
		// 3件目
		JSONObject empObj3 =  new JSONObject();
		empObj3.put("employee_id",10001);
		empObj3.put("year", "2020");
		empObj3.put("month", "1");
		empObj3.put("day", "9");
		empObj3.put("working_hour", 11);
		workObj.add(empObj3);
		
		// 4件目
		JSONObject empObj4 =  new JSONObject();
		empObj4.put("employee_id",10001);
		empObj4.put("year", "2020");
		empObj4.put("month", "1");
		empObj4.put("day", "10");
		empObj4.put("working_hour", 12);
		workObj.add(empObj4);
		
		// 5件目
		JSONObject empObj5 =  new JSONObject();
		empObj5.put("employee_id",10001);
		empObj5.put("year", "2020");
		empObj5.put("month", "1");
		empObj5.put("day", "14");
		empObj5.put("working_hour", 23);
		workObj.add(empObj5);
		
		// 6件目
		JSONObject empObj6 =  new JSONObject();
		empObj6.put("employee_id",10001);
		empObj6.put("year", "2020");
		empObj6.put("month", "1");
		empObj6.put("day", "15");
		empObj6.put("working_hour", 24);
		workObj.add(empObj6);
		
		// 7件目
		JSONObject empObj7 =  new JSONObject();
		empObj7.put("employee_id",10001);
		empObj7.put("year", "2020");
		empObj7.put("month", "1");
		empObj7.put("day", "16");
		empObj7.put("working_hour", 25);
		workObj.add(empObj7);
		
		// 8件目
		JSONObject empObj8 =  new JSONObject();
		empObj8.put("employee_id",10001);
		empObj8.put("year", "2020");
		empObj8.put("month", "1");
		empObj8.put("day", "17");
		empObj8.put("working_hour", 26);
		workObj.add(empObj8);
		
		// 9件目
		JSONObject empObj9 =  new JSONObject();
		empObj9.put("employee_id",10001);
		empObj9.put("year", "2020");
		empObj9.put("month", "1");
		empObj9.put("day", "27");
		empObj9.put("working_hour", 8);
		workObj.add(empObj9);
		
		// status番号を入れる(失敗)
		case1.put("status","200");
	    // messageを入れる
		case1.put("messeage",messageSource.getMessage("200", null, Locale.JAPAN));
	    // 取得した社員データを入れる
		case1.put("data", workObj.toString());
		
		webController.getToken("0123");
//		System.out.println(case1.toString());
//		System.out.println(webController.GetEmployeeWorkingList("10001","2020","1","0123"));
				
		assertTrue(webController.GetEmployeeWorkingList("10001","2020","1","0123").equals(case1.toString()));
	}
	
	@Test
	void GetEmployeeWorkingListNG() throws JSONException {
		JSONObject empObj =  new JSONObject();
		
		// status番号を入れる(失敗)
		case1.put("status","300");
	    // messageを入れる
		case1.put("messeage",messageSource.getMessage("300", null, Locale.JAPAN));
	    // 取得した社員データを入れる
		case1.put("data", "");
		
		webController.getToken("0123");
		assertTrue(webController.GetEmployeeWorkingList("10001","2020","a","0123").equals(case1.toString()));
	}
}
