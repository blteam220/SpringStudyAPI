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

import com.example.demo.domain.entity.DepartmentEntity;
import com.example.demo.domain.entity.DeveloperEntity;
import com.example.demo.domain.entity.EmployeeEntity;
import com.example.demo.domain.entity.WorkingEntity;
import com.example.demo.domain.repository.DepartmentRepository;
import com.example.demo.domain.repository.DeveloperRepository;
import com.example.demo.domain.repository.EmployeeDepartmentJoinRepository;
import com.example.demo.domain.repository.WorkingRepository;


@SpringBootTest
class WebControllerTests {
	
	@Autowired
	private WebController webController;
	
	private JSONObject caseToken =  new JSONObject();

	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private DeveloperRepository developerRepository;
	
	@Autowired
	private EmployeeDepartmentJoinRepository employeeDepartmentJoinRepository;
	
	@Autowired
	private WorkingRepository workingRepository;
	
	@Test
	void SyainInfoTest1() throws JSONException {
		
		JSONObject empObj =  new JSONObject();
		empObj.put("社員番号","10001");
		empObj.put("名前", "新垣");
		empObj.put("年齢", "23");
		empObj.put("所属", "開発者");		
		
		// passwordを入れる
		caseToken.put("password","password");
		caseToken.put("status","200");
		caseToken.put("messeage",messageSource.getMessage("200", null, Locale.JAPAN));
		System.out.print(webController.getToken("password").toString());
		System.out.print(caseToken.toString());
		assertTrue(webController.getToken("password").equals(caseToken.toString()));
		
		JSONObject case1 =  new JSONObject();
		// status番号を入れる(失敗)
		case1.put("status","200");
	    // messageを入れる
		case1.put("messeage",messageSource.getMessage("200", null, Locale.JAPAN));
	    // 取得した社員データを入れる
		case1.put("data", empObj);
		System.out.print(webController.SyainInfo("10001","password").toString());
		System.out.print(case1.toString());
		assertTrue(webController.SyainInfo("10001","password").equals(case1.toString()));
	}
	
	@Test
	void DepartmentEntityTest() {
		int departmentId = 0;
		String departmentName = "";
		String updatedUser = "";
		
		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setDepartmentId(1001);
		departmentEntity.setDepartmentName("企画");
		departmentEntity.setUpdated_user("親富祖");
		
		List<DepartmentEntity> departmentEntityget = departmentRepository.findById(1001);
		
		departmentId = departmentEntityget.get(0).getDepartmentId();
		departmentName = departmentEntityget.get(0).getDepartmentName();
		updatedUser = departmentEntityget.get(0).getUpdated_user();
		
		assertEquals(1001, departmentId);
		assertEquals("開発者", departmentName);
		assertEquals("親富祖", updatedUser);
		
	}
	
	@Test
	void DeveloperEntityTest() {
		int developerId = 0;
		String developerName = "";
		String updatedUser = "";
		
		DeveloperEntity developerEntity = new DeveloperEntity();
		developerEntity.setDeveloperId(104);
		developerEntity.setDeveloperName("企画");
		developerEntity.setUpdated_user("親富祖");
		
		List<DeveloperEntity> developerEntityget = developerRepository.findById(101);
		
		developerId = developerEntityget.get(0).getDeveloperId();
		developerName = developerEntityget.get(0).getDeveloperName();
		updatedUser = developerEntityget.get(0).getUpdated_user();
		
		assertEquals(101, developerId);
		assertEquals("経理", developerName);
		assertEquals("親富祖", updatedUser);
	}
	
	@Test
	void EmployeeEntityTest() {
		int employeeId = 0;
		String employeeName = "";
		int age = 0;
		int department = 0;
		String updated_user = "";
		DepartmentEntity departmentName;
		List<WorkingEntity> workingList = new ArrayList<>();
		
		DepartmentEntity departmentEntity = new DepartmentEntity();
		List<WorkingEntity> workingListSet = new ArrayList<WorkingEntity>();
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setEmployeeId(10005);
		employeeEntity.setEmployeeName("佐藤");
		employeeEntity.setAge(30);
		employeeEntity.setDepartment(1005);
		employeeEntity.setUpdated_user("親富祖");
		employeeEntity.setDepartmentName(departmentEntity);
		employeeEntity.setWorkingList(workingListSet);
		
		List<EmployeeEntity> developerEntityget = employeeDepartmentJoinRepository.findById(10001);
		
		employeeId = developerEntityget.get(0).getEmployeeId();
		employeeName = developerEntityget.get(0).getEmployeeName();
		age = developerEntityget.get(0).getAge();
		department = developerEntityget.get(0).getDepartment();
		updated_user = developerEntityget.get(0).getUpdated_user();
		departmentName = developerEntityget.get(0).getDepartmentName();
		workingList = developerEntityget.get(0).getWorkingList();
		
		assertEquals(10001, employeeId);
		assertEquals("新垣", employeeName);
		assertEquals(23, age);
		assertEquals(1001, department);
		assertEquals("親富祖", updated_user);
		
		employeeId = developerEntityget.get(0).getEmployeeId();
		employeeName = developerEntityget.get(0).getEmployeeName();
		age = developerEntityget.get(0).getAge();
		department = developerEntityget.get(0).getDepartment();
		updated_user = developerEntityget.get(0).getUpdated_user();
		departmentName = developerEntityget.get(0).getDepartmentName();
		workingList = developerEntityget.get(0).getWorkingList();
		
		
		/*
		assertEquals(, );
		assertEquals(, );
		*/
		/*
		JSONObject case1 =  new JSONObject();
		// status番号を入れる(失敗)
		case1.put("status","200");
	    // messageを入れる
		case1.put("messeage",messageSource.getMessage("200", null, Locale.JAPAN));
	    // 取得した社員データを入れる
		case1.put("data", empObj);
		System.out.print(webController.SyainInfo("10001","password").toString());
		System.out.print(case1.toString());
		assertTrue(webController.SyainInfo("10001","password").equals(case1.toString()));
		
		assertTrue(webController.SyainInfo("10001","password").equals(case1.toString()));
		*/
	}
	
	
	
	
}
