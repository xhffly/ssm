package com.xhf.test.ssm.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xhf.test.ssm.entity.SysUser;

@Repository
public class UserDao {
	
	public SysUser getSysUser(String id) {

		// 执行查询返回一个唯一user对象的sql
		SysUser user = null;
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSession(false);
			/**
			 * 映射sql的标识字符串， me.gacl.mapping.userMapper是userMapper.
			 * xml文件中mapper标签的namespace属性的值，
			 * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
			 */
			String statement = "sysUser.getSysUser";// 映射sql的标识字符串
			user = session.selectOne(statement, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//使用SqlSession执行完SQL之后需要关闭SqlSession
			if(session != null){
				session.close();
			}
		}

		return user;
	}
	
	public SysUser getSysUser2(String id) {

		// 执行查询返回一个唯一user对象的sql
		SysUser user = null;
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSession(false);
			/**
			 * 映射sql的标识字符串， me.gacl.mapping.userMapper是userMapper.
			 * xml文件中mapper标签的namespace属性的值，
			 * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
			 */
			String statement = "sysUser.getSysUser2";// 映射sql的标识字符串
			user = session.selectOne(statement, id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//使用SqlSession执行完SQL之后需要关闭SqlSession
			if(session != null){
				session.close();
			}
		}

		return user;
	}
	
}
