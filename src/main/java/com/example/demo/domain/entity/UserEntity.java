package com.example.demo.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 使用するアノテーション
 * @Entity　：　Entityクラス
 * @@Table : テーブル名
 * @DATA ：　getter,setter生成
 * @Column : カラム名
 * @AllArgsConstructor : 全メンバセット用コンストラクタ生成
 * @NoArgsConstructor : (デフォルト＋全メンバ)セット用コンストラクタ生成
 * @GeneratedValue : 主キー生成
 */

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false, length = 128)
	private String name;
	
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	
	@Column(name = "email", nullable = false, unique = true, length = 255)
	private String email;
	
	@Column(name = "admin_flag", nullable = false)
	private Boolean admin;
	
//	public static UserEntity of(String name, String password, String email) {
//		return UserEntity.builder().name(name).password(password).email(email).admin(false).build();
//	}
}