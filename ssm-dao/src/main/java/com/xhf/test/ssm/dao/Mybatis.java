package com.xhf.test.ssm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xhf.test.ssm.entity.User;

public class Mybatis {
	
	public User getUser(){
		 
		SqlSession session = MyBatisUtil.getSession(false);
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        User user = session.selectOne(statement, "40283a834c77a20d014c7811c80c0005");
        
        return user;
	}
	
	
	
	public int addUser(User user){
	    //SqlSession sqlSession = MyBatisUtil.getSession(false);
	    SqlSession sqlSession = null;
		//执行插入操作
		int retResult=0;
		try {
			sqlSession = MyBatisUtil.getSession(false);
			/**
			 * 映射sql的标识字符串，
			 * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
			 * addUser是insert标签的id属性值，通过insert标签的id属性值就可以找到要执行的SQL
			 */
			String statement = "userMapper.addUser";//映射sql的标识字符串
			retResult = sqlSession.insert(statement,user);
			
			//手动提交事务
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
			//使用SqlSession执行完SQL之后需要关闭SqlSession
			if(sqlSession != null){
				sqlSession.close();
			}
		}
	    return retResult;
	}
	
	public int update(User user){
	    SqlSession sqlSession = null;
		//执行修改操作
		int retResult = 0;
		try {
			sqlSession = MyBatisUtil.getSession(false);
			/**
			 * 映射sql的标识字符串，
			 * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
			 * updateUser是update标签的id属性值，通过update标签的id属性值就可以找到要执行的SQL
			 */
			String statement = "userMapper.updateUser";//映射sql的标识字符串
			
			retResult = sqlSession.update(statement,user);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			//使用SqlSession执行完SQL之后需要关闭SqlSession
			if(sqlSession != null){
				sqlSession.close();
			}
		}
	    return retResult;
	}
	
	public void delete(String id){
	    SqlSession sqlSession = null;
		//执行删除操作
		int retResult = 0;
		try {
			sqlSession = MyBatisUtil.getSession(true);
			/**
			 * 映射sql的标识字符串，
			 * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
			 * deleteUser是delete标签的id属性值，通过delete标签的id属性值就可以找到要执行的SQL
			 */
			String statement = "userMapper.deleteUser";//映射sql的标识字符串
			retResult = sqlSession.delete(statement,id);
			System.out.println(retResult);
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			//使用SqlSession执行完SQL之后需要关闭SqlSession
			if(sqlSession != null){
				sqlSession.close();
			}
		}
	
	}
	
	public List<User> getAll(User user){
	    SqlSession sqlSession = null;
		//执行查询操作，将查询结果自动封装成List<User>返回
		List<User> lstUsers = new ArrayList<User>();
		try {
			sqlSession = MyBatisUtil.getSession(true);
			String statement = "userMapper.getAllUsers";//映射sql的标识字符串
			lstUsers = sqlSession.selectList(statement);
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			//使用SqlSession执行完SQL之后需要关闭SqlSession
			if(sqlSession != null){
				sqlSession.close();
			}
		}
	    //使用SqlSession执行完SQL之后需要关闭SqlSession
	    return lstUsers;
	}

	
}
