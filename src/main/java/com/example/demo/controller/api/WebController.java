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

import com.example.demo.domain.entity.EmployeeEntity;
import com.example.demo.domain.repository.EmployeeDepartmentJoinRepository;
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
	private EmployeeDepartmentJoinRepository employeeDepartmentJoinRepository;
	
	/*
	 * クライアントからのリクエストに対してマッピングを行う  methodオプションでGETを指定
	 * @RequestParam : URLのパラメータを取得
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String SyainInfo(@RequestParam("UserId") String userId){
		// status番号
		String statusNum;
		
		// WebValidation呼び出し(数字チェック)
		boolean valChack = WebValidation.numberCheck(userId);
		
		//　リストにデータが入っていく
		List<JSONObject> objList = new ArrayList<JSONObject>();
		// 出力用(status,message,data) JSON形式オブジェクト
		JSONObject outputObj =  new JSONObject();
		//　社員データ用(社員番号,名前,年齢,部署)　JSON形式オブジェクト
		JSONObject empObj =  new JSONObject();
		
		// URLのパラメータが数字の場合
		if(valChack == true) {
			// status番号
			statusNum = "200";
			
			// Stringをint型へ
			int userIdInt = Integer.parseInt(userId);
			
			// 引数にIDを入力し、データを取得
			List<EmployeeEntity> emp = employeeDepartmentJoinRepository.findById(userIdInt);
			
			// empObjにDBから取得したデータを入れる
			empObj.put("社員番号", emp.get(0).getEmployeeId().toString());
			empObj.put("名前", emp.get(0).getEmployeeName().toString());
			empObj.put("年齢", emp.get(0).getAge().toString());
			empObj.put("所属", emp.get(0).getDepartmentName().getDepartmentName().toString());
		    
		    // status番号を入れる(成功)
		    outputObj.put("status",statusNum);
		    // messageを入れる
		    outputObj.put("messeage",messageSource.getMessage(statusNum, null, Locale.JAPAN));
		    // 取得した社員データを入れる
		    outputObj.put("data", empObj);
		    
		    // Listにデータ(status,massage,data)が入ったオブジェクトを入れる
		    objList.add(outputObj);
		    
		    // JSON型のListを返す
		    return objList.get(0).toString();
		}
		// URLのパラメータが数字ではないとき
		else {
			// status番号
			statusNum = "300";
			/*
			 * ※メッセージプロパティを使うには
			 * 「application.properties」に設定を記入
			 * 「messages_ja.properties」にキーと表示したい文字を記入
			 * 「messages.properties」のファイルを作成(中身は空)  
			*/
			
			// status番号を入れる(失敗)
		    outputObj.put("status",statusNum);
		    // messageを入れる
		    outputObj.put("messeage",messageSource.getMessage(statusNum, null, Locale.JAPAN));
		    // 取得した社員データを入れる
		    outputObj.put("data", empObj);
		    
		    // Listにデータ(status,massage,data)が入ったオブジェクトを入れる
		    objList.add(outputObj);
		    
		    // JSON型のListを返す
		    return objList.get(0).toString();
		}
	}	
}