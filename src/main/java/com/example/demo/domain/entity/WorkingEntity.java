package com.example.demo.domain.entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
	
	@EmbeddedId
    private WorkingKey workingKey;
	
	@Column(name = "working_hour")
	private Integer working_hour;
	
	@Column(name = "updated_user")
	private String updated_user;
	
	//テーブルをjoin
	//@ManyToOne
	//@JoinColumn(name="employee_ibfk_2", insertable = false, updatable = false)
	//private  EmployeeEntity employee;
}