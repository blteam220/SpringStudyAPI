package com.example.demo.domain.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class employeeDepartmentJoin implements Serializable{
	/*
	 * @OneToOne : Entityクラス間の一つの関連を指定するアノテーション
	 * @JoinColumn : Entityクラス間を結合するために結合先テーブルのカラム名を指定するアノテーション
	 * @name : 対象テーブルを結合するために使用する外部キーカラム名を指定する属性
	 */
	/*
	@OneToOne
	@JoinColumn(name="employee_ibfk_1")
	private  departmentEntity department;
	*/
}