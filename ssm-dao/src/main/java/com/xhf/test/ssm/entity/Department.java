package com.xhf.test.ssm.entity;

/**
 * 部门表 1、测试和用户的关联关系	                                                                                                 
 * @equals			
 * @author			xiaohf
 * @createDate		2016年8月16日 下午2:03:54
 * @version			v1.0
 */
public class Department {
	
	private String id;
	
	private String name;
	
	private String code;
	
	private String pId;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}


	
	
}
