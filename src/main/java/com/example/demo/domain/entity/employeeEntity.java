package com.example.demo.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/*
 * 使用するアノテーション
 * @Entity　：　Entityクラス
 * @@Table : テーブル名
 * @DATA ：　getter,setter生成
 * @Id : 主キーを指定
 * @Column : カラム名
 */

@Entity
@Table(name = "employee")
@Data
public class EmployeeEntity {
	
	@Id
	@Column(name = "employee_id")
	private Integer employeeId;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "department")
	private Integer department;
	
	@Column(name = "updated_user")
	private String updated_user;
	
	//テーブルをjoin
	@ManyToOne
	@JoinColumn(name="employee_ibfk_1")
	private  DepartmentEntity departmentName;
	
	@OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name = "employee_ibfk_2")
    private List<WorkingEntity> workingList = new ArrayList<>();
}