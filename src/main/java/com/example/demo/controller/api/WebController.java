package com.example.demo.controller.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.EmployeeEntity;
import com.example.demo.domain.entity.WorkingEntity;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.domain.entity.WorkingKey;
import com.example.demo.domain.repository.EmployeeDepartmentJoinRepository;
import com.example.demo.domain.repository.WorkingRepository;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.validation.WebValidation;
import org.springframework.jdbc.core.JdbcTemplate;

/* 
 * @RestController : sonやXML等を返すWebAPI用のコントローラで使用する
 * @RequestMapping : クライアントからのリクエストに対してマッピングを行う
 */
@RestController
@RequestMapping("/")
public class WebController {
	
	// @Autowired : newをしてくれる(メッセージプロパティで使用)
	@Autowired
	protected MessageSource messageSource;
	
	// @Autowired : newをしてくれる(DBからのデータの取得で使用) 社員情報検索
	@Autowired
	private EmployeeDepartmentJoinRepository employeeDepartmentJoinRepository;
	
	// @Autowired : newをしてくれる(DBからのデータの取得で使用)
	@Autowired
	private WorkingRepository workingRepository;
	
	// @Autowired : newをしてくれる(DBへの登録処理で使用)
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/*
	 * クライアントからのリクエストに対してマッピングを行う  methodオプションでGETを指定
	 * @RequestParam : URLのパラメータを取得
	 */
	@RequestMapping("/employeeInfo")
	public String SyainInfo(@RequestParam("UserId") String userId,
			@RequestParam("password") String password){
		
		// status番号
		String statusNum = "200";
		
		// WebValidation呼び出し(数字チェック)
		boolean valChack = WebValidation.numberCheck(userId);
		
		//　リストにデータが入っていく
		List<JSONObject> objList = new ArrayList<JSONObject>();
		// 出力用(status,message,data) JSON形式オブジェクト
		JSONObject outputObj =  new JSONObject();
		
		// トークン(password)チェック
	   	if(!passwordCheck(password)) {
	   		statusNum = "500";
	   		outputObj.put("status",statusNum);
	   		outputObj.put("messeage",messageSource.getMessage(statusNum, null, Locale.JAPAN));
	   		objList.add(outputObj);
    		return objList.toString();
    	}
		
		//　社員データ用(社員番号,名前,年齢,部署)　JSON形式オブジェクト
		JSONObject empObj =  new JSONObject();
		
		// URLのパラメータが数字の場合
		if(valChack == true) {
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
	
	/*
	 * クライアントからのリクエストに対してマッピングを行う
	 * @RequestParam : URLのパラメータを取得
	 */
	@GetMapping(path = "auth")
    public String getToken(@RequestParam("password") String password) {
    	// 登録データ設定
    	UserEntity userEntity = new UserEntity();
    	userEntity.setName(password);
    	userEntity.setEmail(password);
    	userEntity.setAdmin(true);
    	userEntity.setPassword(password);
    	
    	String statusNum = "200";
		//　リストにデータが入っていく
    	JSONObject outputObj =  new JSONObject();
    	
    	try {
        	// データ登録
    		UserEntity returnData = userRepository.save(userEntity);
        	outputObj.put("password",returnData.getPassword());
    	} catch (Exception e) {
    		statusNum  = "400";
     	}
	    // Listにデータ(status,massage,data)が入ったオブジェクトを入れる
    	List<JSONObject> objList = new ArrayList<JSONObject>();
    	outputObj.put("messeage",messageSource.getMessage(statusNum, null, Locale.JAPAN));
	    objList.add(outputObj);
	    
	    // JSON型のListを返す
	    return objList.get(0).toString();
    }
	
	/*
	 * クライアントからのリクエストに対してマッピングを行う  methodオプションでGETを指定
	 * @RequestParam : URLのパラメータを取得
	 */
	@GetMapping("/employee/working")
	public String GetEmployeeWorking(@RequestParam("userid") String userId,
			@RequestParam("year") String year,
			@RequestParam("month") String month,
			@RequestParam("password") String password){

		// status番号
		String statusNum = "200";
		
		// WebValidation呼び出し(数字チェック)
		boolean valChackId = WebValidation.numberCheck(userId);
		boolean valChackYear = WebValidation.numberCheck(year);
		boolean valChackMonth = WebValidation.numberCheck(month);
		
		//　リストにデータが入っていく
		List<JSONObject> objList = new ArrayList<JSONObject>();
		// 出力用(status,message,data) JSON形式オブジェクト
		JSONObject outputObj =  new JSONObject();

		// トークン(password)チェック
	   	if(!passwordCheck(password)) {
	   		statusNum = "500";
	   		outputObj.put("status",statusNum);
	   		outputObj.put("messeage",messageSource.getMessage(statusNum, null, Locale.JAPAN));
	   		objList.add(outputObj);
    		return objList.toString();
    	}
		
		// URLのパラメータが数字の場合
		if(valChackId == true && valChackYear == true && valChackMonth == true) {
			// Stringをint型へ
			int userIdInt = Integer.parseInt(userId);			

			//SQL実行データ取得(employee_id,year,monthを入れて、勤務時間を戻す)			
			List<Map<String, Object>> workTimeData = jdbcTemplate.queryForList("SELECT sum(CASE WHEN working_hour < 24 THEN working_hour ELSE NULL END) as '作業時間' FROM employee JOIN working ON employee.employee_id = working.employee_id WHERE employee.employee_id = " + userIdInt + " AND working.year = '" + year + "' AND working.month = '" + month + "'");

			//勤務時間合計
			String workingHours = "";

            // データの作業時間
            workingHours = workTimeData.get(0).get("作業時間").toString();
            System.out.println("作業時間=" + workingHours);
			
		    // JSON形式でデータを入れる
		    // status番号を入れる(成功)
		    outputObj.put("status",statusNum);
		    // messageを入れる
		    outputObj.put("messeage",messageSource.getMessage(statusNum, null, Locale.JAPAN));
		    // 社員IDを入れる
		    outputObj.put("employee_id", userId);
		    // 年入れる
		    outputObj.put("year", year);
		    // 月を入れる
		    outputObj.put("month", month);
		    // 勤務時間を入れる
		    outputObj.put("workingHours", workingHours);
		    
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
		    
		    // Listにデータ(status,massage,data)が入ったオブジェクトを入れる
		    objList.add(outputObj);
		    
		    // JSON型のListを返す
		    return objList.get(0).toString();
		}
	}
		
	/*
	 * クライアントからのリクエストに対してマッピングを行う  methodオプションでGETを指定
	 * @RequestParam : URLのパラメータを取得
	 */
	@GetMapping("/employee/working/list")
	public String GetEmployeeWorkingList(@RequestParam("userid") String userId,
			@RequestParam("year") String year,
			@RequestParam("month") String month,
			@RequestParam("password") String password){

		// status番号
		String statusNum = "200";
		
		// WebValidation呼び出し(数字チェック)
		boolean valChack = this.valCheck(new ArrayList<String>(Arrays.asList(userId,year,month)));
		
		//　リストにデータが入っていく
		List<JSONObject> objList = new ArrayList<JSONObject>();
		// 出力用(status,message,data) JSON形式オブジェクト
		JSONObject outputObj =  new JSONObject();
		//　勤務時間データ用　JSON形式オブジェクト
		List<JSONObject> workObj = new ArrayList<JSONObject>();
		
		// トークン(password)チェック
	   	if(!passwordCheck(password)) {
	   		statusNum = "500";
	   		outputObj.put("status",statusNum);
	   		outputObj.put("messeage",messageSource.getMessage(statusNum, null, Locale.JAPAN));
	   		objList.add(outputObj);
    		return objList.toString();
    	}
		
		// URLのパラメータが数字の場合
		if(true == valChack) {
			
			// Stringをint型へ
			int userIdInt = Integer.parseInt(userId);
		
			// 引数にIDを入力し、データを取得
			WorkingEntity working = new WorkingEntity();
			WorkingKey key = new WorkingKey();
			
			// 検索条件の設定
			key.setEmployeeId(userIdInt);
			key.setYear(year);
			key.setMonth(month);
			working.setWorkingKey(key);
			
			List<WorkingEntity> work = workingRepository.findAll(Example.of(working));
			for(WorkingEntity w : work){
				JSONObject obj =  new JSONObject();
				obj.put("employee_id", w.getWorkingKey().getEmployeeId());
				obj.put("year", w.getWorkingKey().getYear());
				obj.put("month", w.getWorkingKey().getMonth());
				obj.put("day", w.getWorkingKey().getDay());
				obj.put("working_hour", w.getWorking_hour());
				workObj.add(obj);
	        }
					    
		    // status番号を入れる(成功)
		    outputObj.put("status",statusNum);
		    // messageを入れる
		    outputObj.put("messeage",messageSource.getMessage(statusNum, null, Locale.JAPAN));
		    // 取得した社員データを入れる
		    outputObj.put("data", workObj);
		    
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
		    outputObj.put("data", workObj);
		    
		    // Listにデータ(status,massage,data)が入ったオブジェクトを入れる
		    objList.add(outputObj);
		    
		    // JSON型のListを返す
		    return objList.get(0).toString();
		}
	}
	
	/*
	 * 値のチェックを行う
	 * obj : Stringオブジェクトを指定
	 */
	private Boolean valCheck(List<String> obj) {
		for(String s : obj){
			boolean valChack = WebValidation.numberCheck(s);
			if (false == valChack) {
				return false;
			}
        }
		return true;
	}
	
	/*
	 * パスワードのチェックを行う
	 * obj : Stringオブジェクトを指定
	 */
	private Boolean passwordCheck(String password) {
		
		try {
			//パスワードチェック
			Optional<UserEntity> tmpOpt = userRepository.findFirstByPassword(password);
			//tmpOpt.get().equals(tmpOpt);
	    	if(!tmpOpt.get().getPassword().equals(password)) {
	    		return false;
	    	} else {
	    		UserEntity tmpUserEntity = tmpOpt.get();
	    		userRepository.delete(tmpUserEntity);
	    	}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}