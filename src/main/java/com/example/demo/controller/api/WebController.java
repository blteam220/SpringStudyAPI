package com.example.demo.controller.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;

import com.example.demo.domain.entity.SpringStudyEntity;
import com.example.demo.domain.repository.SpringStudyRepository;
import com.example.demo.domain.validation.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;





@RestController
@RequestMapping("/sampleGET")
public class WebController {
	
	/*
	 * ※メッセージプロパティを使うには
	 * 「application.properties」に設定を記入
	 * 「messages_ja.properties」にキーと表示したい文字を記入
	 * 「messages.properties」のファイルを作成(中身は空) 
	 * */
	@Autowired
	protected MessageSource messageSource;
	
	//@Autowired
	//private SpringStudyEntity studyEntity;

	//ここをコメントにしないとエラーが出る
	@Autowired
	private SpringStudyRepository springStudyRepository;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String SyainInfo(@RequestParam("UserId") String userId){
		
		//WebValidation呼び出し
		boolean valChack = WebValidation.numberCheck(userId);
		
		System.out.println("Controller:" + valChack);
		
		//Stringをint型へ
		int userIdInt = Integer.parseInt(userId);
		
		//List<SpringStudyEntity> players = springStudyRepository.findAll();
		List<SpringStudyEntity> emp = springStudyRepository.findById(userIdInt);
		
		return emp.get(1).getAge().toString();
		/*
		if(valChack == true) {
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
		else {
			return messageSource.getMessage("numberCheck", null, Locale.JAPAN);
		}
		*/
	}	
}