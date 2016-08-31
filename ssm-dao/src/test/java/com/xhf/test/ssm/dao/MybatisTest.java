package com.xhf.test.ssm.dao;

import java.util.List;

import org.junit.Test;

import com.xhf.test.ssm.entity.User;

public class MybatisTest {

	
	@Test
	public void getUser(){
		Mybatis mybatis = new Mybatis();
        System.out.println(mybatis.getUser());
	}
	
	@Test
	public void testAddUser(){
		Mybatis mybatis = new Mybatis();
		User user = new User();
	    user.setName("用户孤傲苍狼");
	    mybatis.addUser(user);
        System.out.println();
	}
	
	@Test
	public void testUpdateUser(){
		Mybatis mybatis = new Mybatis();
		User user = new User();
		user.setId("00939352-62b0-11e6-9578-00ffaff13132");  
		user.setName("孤傲苍狼124s");
		user.setDescription("备注");
	    mybatis.update(user);
	}
	

	@Test
	public void testDeleteUser(){
		Mybatis mybatis = new Mybatis();
	    mybatis.delete("cda51e34-62b5-11e6-9578-00ffaff13132");
	}
	

	@Test
	public void testGetAllUser(){
		Mybatis mybatis = new Mybatis();
		List<User> users = mybatis.getAll(null);
		for (User user : users) {
			System.out.println(user.getName()+"-----------");
		}
	}
}
