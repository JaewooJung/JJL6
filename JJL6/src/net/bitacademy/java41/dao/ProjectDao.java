package net.bitacademy.java41.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.bitacademy.java41.annotations.Component;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Project;
@Component
public class ProjectDao {
	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public ProjectDao() {}

	public Project getProject(int pno) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			return sqlSession.selectOne("net.bitacademy.java41.dao.ProjectMapper.getProject1",pno);

		} catch (Exception e) {
			throw e;

		} finally {
			try {sqlSession.close();} catch (Exception e) {}

		}
	}

	public List<Project> getProject(String email) throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("net.bitacademy.java41.dao.ProjectMapper.getProject2", email);
		} catch (Exception e) {
			throw e;

		} finally {
			try {sqlSession.close();} catch (Exception e) {}
			
		}

	}

	public List<Project> getProject() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("net.bitacademy.java41.dao.ProjectMapper.getProject3");
		} catch (Exception e) {
			throw e;

		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}		
	}
	
	public int add(Project project) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.insert(
					"net.bitacademy.java41.dao.ProjectMapper.add", project);
				
				HashMap<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("email", project.getLeader());
				paramMap.put("projectNo", project.getPno());
				paramMap.put("memberLevel", 0);
				
				sqlSession.insert(
					"net.bitacademy.java41.dao.ProjectMapper.addProjectMember", 
					paramMap);
				
				sqlSession.commit();
				return project.getPno();
				
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
				
			} finally {
				try {sqlSession.close();} catch(Exception e) {}
			}
	}
	
	public int change(Project project) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int a = sqlSession.update("net.bitacademy.java41.dao.ProjectMapper.change",project);
			sqlSession.commit();
			return a;

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;

		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}

	public void remove(int pno) throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			sqlSession.delete(
					"net.bitacademy.java41.dao.ProjectMapper.remove1", pno);
			sqlSession.delete(
					"net.bitacademy.java41.dao.ProjectMapper.remove2", pno);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}


}




