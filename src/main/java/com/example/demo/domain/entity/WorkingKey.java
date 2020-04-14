package com.example.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingKey implements Serializable {

    @Column(name = "employee_id")
	private Integer employeeId;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "month")
	private String month;
	
	@Column(name = "day")
	private String day;
}