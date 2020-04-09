package com.example.demo.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.SpringStudyEntity;
import com.example.demo.domain.repository.SpringStudyRepository;
import com.example.demo.domain.validation.WebValidation;

/* 
 * @RestController : sonやXML等を返すWebAPI用のコントローラで使用する
 * @RequestMapping : クライアントからのリクエストに対してマッピングを行う
 */
@RestController
@RequestMapping("/sampleGET")
public class WebController {
	
	// @Autowired : newをしてくれる(メッセージプロパティで使用)
	@Autowired
	protected MessageSource messageSource;

	// @Autowired : newをしてくれる(DBからのデータの取得で使用)
	@Autowired
	private SpringStudyRepository springStudyRepository;
	
	/*
	 * クライアントからのリクエストに対してマッピングを行う  methodオプションでGETを指定
	 * @RequestParam : URLのパラメータを取得
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String SyainInfo(@RequestParam("UserId") String userId){
		
		// WebValidation呼び出し(数字チェック)
		boolean valChack = WebValidation.numberCheck(userId);
		
		// URLのパラメータが数字の場合
		if(valChack == true) {
			// Stringをint型へ
			int userIdInt = Integer.parseInt(userId);
			
			// 引数にIDを入力し、データを取得
			List<SpringStudyEntity> emp = springStudyRepository.findById(userIdInt);
			
			//　リストにデータが入っていく
			List<JSONObject> objList = new ArrayList<JSONObject>();
			//　JSON用のオブジェクト生成
			JSONObject obj =  new JSONObject();
			
			// ListにDBから取得したデータを入れる
			obj.put("社員番号",emp.get(0).getEmployeeId().toString());
		    obj.put("名前", emp.get(0).getEmployeeName().toString());
		    obj.put("年齢", emp.get(0).getAge().toString());
		    obj.put("所属", emp.get(0).getDepartment().toString());
		    objList.add(obj);
    
		    // JSON型のListを返す
		    return objList.get(0).toString();
		}
		// URLのパラメータが数字ではないとき
		else {
			/*
			 * ※メッセージプロパティを使うには
			 * 「application.properties」に設定を記入
			 * 「messages_ja.properties」にキーと表示したい文字を記入
			 * 「messages.properties」のファイルを作成(中身は空)  
			*/
			// messages_ja.propertiesのキーと場所を指定して、メッセージプロパティの値を返す
			return messageSource.getMessage("numberCheck", null, Locale.JAPAN);
		}
	}	
}