package com.te.consolebasedapplication.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "emp")
@Entity
public class EmpInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpInfo() {
	}

	@Id
	@Column
	private Integer empno;

	@Column
	private String ename;

	@Column
	private Date hiredate;

	@Column
	private Double salary;

	@Column
	private String role;

	@Column
	private Integer deptno;

	@Column
	private String password;

}
