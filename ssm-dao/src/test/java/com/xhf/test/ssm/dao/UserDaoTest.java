package com.xhf.test.ssm.dao;

import org.junit.Test;

import com.xhf.test.ssm.entity.SysUser;

public class UserDaoTest {

	@Test
	public void testGetSysUser() {
		UserDao userDao = new UserDao();
        
        SysUser user = userDao.getSysUser("40283a834d03c6ff014d03d1a95c0001");
        System.err.println(user);
	}
	
	@Test
	public void testGetSysUser2() {
		UserDao userDao = new UserDao();
        
        SysUser user = userDao.getSysUser2("40283a834d03c6ff014d03d1a95c0001");
        System.err.println(user);
	}

}
