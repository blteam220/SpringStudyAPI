package com.example.demo.domain.entity;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

/*
 * 使用するアノテーション
 * @Entity　：　Entityクラス
 * @@Table : テーブル名
 * @DATA ：　getter,setter生成
 * @Column : カラム名
 */

@Entity
@Table(name = "working")
@Data
public class WorkingEntity {
	@Id
	@Column(name = "employee_id")
	private Integer employeeId;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "month")
	private String month;
	
	@Column(name = "day")
	private String day;
	
	@Column(name = "working_hour")
	private Integer working_hour;
	
	@Column(name = "updated_user")
	private String updated_user;
}