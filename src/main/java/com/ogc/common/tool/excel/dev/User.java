package com.ogc.common.tool.excel.dev;

import java.util.Date;

import com.ogc.common.tool.excel.ExcelField;


public class User {

	@ExcelField(title="ID", type=2, align=2, sort=1)
	private Long id;		// 编号
	
	@ExcelField(title="姓名", align=2, sort=2)
	private String name;	// 姓名
	
	@ExcelField(title="密码", align=2, sort=3)
	private Integer password;// 密码

	@ExcelField(title="手机", align=2, type=0, sort=6)
	private Double mobile;	// 手机
	
	@ExcelField(title="薪水", align=2, sort=5)
	private Float salary;	// 薪水
	
	@ExcelField(title="时间", align=2, sort=7)
	private Date time;	// 薪水
	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	public Integer getPassword() {
		return password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}

	public Double getMobile() {
		return mobile;
	}


	public void setMobile(Double mobile) {
		this.mobile = mobile;
	}


	public Float getSalary() {
		return salary;
	}


	public void setSalary(Float salary) {
		this.salary = salary;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", mobile=" + mobile + ", salary="
				+ salary + ", time=" + time + "]";
	}

}
