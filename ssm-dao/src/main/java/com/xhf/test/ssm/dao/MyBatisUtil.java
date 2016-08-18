package com.xhf.test.ssm.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	private static SqlSessionFactory sessionFactory;

	static{
		//mybatis的配置文件
        String resource = "config.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        //构建sqlSession的工厂
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
	}
	
	
	
	public static SqlSession getSession(boolean autoCommit){
		SqlSession session = sessionFactory.openSession(autoCommit);
		return session;
	}
}
