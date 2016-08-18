package com.xhf.test.ssm.service;

import com.xhf.test.ssm.dao.entity.SysUser;

public interface IUserService {

	public SysUser getUser(String id) throws Exception;
}
