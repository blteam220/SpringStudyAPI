package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

@RestController
@RequestMapping("/sampleGET")
public class WebController {
	@RequestMapping(method=RequestMethod.GET)
	public String home() {
		return "sample";
	}
	/*
	 * http://localhost:8080/sampleGET?UserId=001　で送られてきたパラメータを取得
	 */
	//public String ParameterGet
	
	
	
	
	
	/*JsonInfoメソッド
	 * リストを作り、JSONObjectをリストの中に入れていく
	 * リストの中身をreturnで返す
	 */
	
	
	
	/*
	@RequestMapping("/")
	public String JsonInfo() {
		List<JSONObject> JsonList = new ArrayList<JSONObject>();
		JSONObject JsonObj = new JSONObject();
		
		//※putは重複なし、setは重複あり
		JsonObj.put("a","");
		
		
		
		
		return "";
	}
	*/
	
}


