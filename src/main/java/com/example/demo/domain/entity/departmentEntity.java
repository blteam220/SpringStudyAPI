package com.example.demo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import lombok.Data;

/*
 * 使用するアノテーション
 * @Entity　：　Entityクラス
 * @@Table : テーブル名
 * @DATA ：　getter,setter生成
 * @Id : 主キーを指定
 * @Column : カラム名
 */

@Entity
@Table(name = "department")
//@Data
public class departmentEntity {
	
	@Id
	@Column(name = "department_id")
	private Integer departmentId;
	
	@Column(name = "department_name")
	private String departmentName;
	
	@Column(name = "updated_user")
	private String updated_user;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getUpdated_user() {
		return updated_user;
	}

	public void setUpdated_user(String updated_user) {
		this.updated_user = updated_user;
	}
}
