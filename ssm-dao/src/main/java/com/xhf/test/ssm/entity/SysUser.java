package com.xhf.test.ssm.entity;

/**
 * 用户实体类，1、测试表字段与实体属性无法对应	 2、测试关联关系                                                                                                
 * @equals			
 * @author			xiaohf
 * @createDate		2016年8月16日 上午11:11:56
 * @version			v1.0
 */
public class SysUser {
	private String id;
	
	
	private String loginName;

	private String name;

	private String password;
	
	private Department dept;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", loginName=" + loginName + ", dept=" + dept.getName() + "]";
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	
}
