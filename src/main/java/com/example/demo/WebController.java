package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@RestController
@RequestMapping("/sampleGET")
public class WebController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String SyainInfo(@RequestParam("UserId") String userId){
		
		//Stringをint型へ
		int userIdInt = Integer.parseInt(userId);
		
		//リストにデータが入っていく
		List<JSONObject> objList = new ArrayList<JSONObject>();
		//↓のオブジェクトにデータを入れ、リストに値を入れていく。
		JSONObject obj =  new JSONObject();

		obj.put("社員番号","1");
        obj.put("名前", "新垣");
        obj.put("年齢", 23);
        obj.put("所属", "営業");
        objList.add(obj);

        obj =  new JSONObject();
		obj.put("社員番号","2");
        obj.put("名前", "上原");
        obj.put("年齢", 24);
        obj.put("所属", "経理");
	    objList.add(obj);
	    
        if(userIdInt == 1) {
        	return objList.get(0).toString();
        }
        else if(userIdInt == 2) {
        	return objList.get(1).toString();
        }
        else {
        	return "";
        }
	}
}


