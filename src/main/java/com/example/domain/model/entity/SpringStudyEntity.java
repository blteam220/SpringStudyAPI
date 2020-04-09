package com.example.domain.model.entity;

//javax.persistence　のインポートを使うにはpom.xmlの書き換えが必要　62~66行目追加
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

//lombok　のインポートを使うにはpom.xmlの書き換えが必要　69~73行目追加
import lombok.*;


/*
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
*/

/*
 * 使用するアノテーション
 * @Entity　：　Entityクラス
 * @Id : 主キーを指定
 * ↓いらない
 * @GeneratedValue(strategy = GenerationType.IDENTITY) : 自動採番(DBのidentityを利用)
 * 
 * @Getter : getterを自動で実装
 * @Setter : setterを自動で実装
 */


@Entity
@Table(name = "Employee")
@Getter
@Setter
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
