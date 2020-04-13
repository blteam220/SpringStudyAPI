package com.example.demo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "developer")
@Data
public class DeveloperEntity {
	
	@Id
	@Column(name = "developer_id")
	private Integer developerId;
	
	@Column(name = "developer_name")
	private String developerName;
	
	@Column(name = "updated_user")
	private String updated_user;
}
