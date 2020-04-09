package com.example.demo.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

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
public class SpringStudyEntity {

	@Id
	@Column(name = "employee_id")
	private Integer employeeId;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "department")
	private String department;
	
}
