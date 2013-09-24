package net.bitacademy.java41.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.bitacademy.java41.annotations.Component;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Project;
import net.bitacademy.java41.vo.Task;
import net.bitacademy.java41.vo.Task;
import net.bitacademy.java41.vo.Task;
import net.bitacademy.java41.vo.Task;
@Component
public class TaskDao {
	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public TaskDao() {	}
	
	public List<Task> list(int pno) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
		return sqlSession.selectList("net.bitacademy.java41.dao.TaskMapper.list",pno);
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}
	}
	public int add(Task task) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int a = sqlSession.insert("net.bitacademy.java41.dao.TaskMapper.add",task);
			sqlSession.commit();
			return a;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}
	
	public Task getTask(int tno) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			return sqlSession.selectOne("net.bitacademy.java41.dao.TaskMapper.getTask", tno);
			
				
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}		
	}
	public int change(Task task) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int a = sqlSession.update("net.bitacademy.java41.dao.TaskMapper.change",task);
			sqlSession.commit();
			return a;

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}
	public int remove(int tno) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int a = sqlSession.delete("net.bitacademy.java41.dao.TaskMapper.remove",tno);
			sqlSession.commit();
			return a;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
			
		}
	}


}
