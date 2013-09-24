package net.bitacademy.java41.dao;

import java.sql.Connection;
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
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Photo;
@Component
public class MemberDao {
	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public MemberDao() {	}

	public Member getMember(String email, String password) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("email", email);
			paramMap.put("password", password);

			Member member = sqlSession.selectOne(
					"net.bitacademy.java41.dao.MemberMapper.getMember",
					paramMap);
			String[] url = new String[1]; 
			url[0] = sqlSession.selectOne("net.bitacademy.java41.dao.MemberMapper.getMember2",paramMap);
			if(url[0] != null){
			member.setPhotos(url);
			}
			return member;

		} catch (Exception e) {
			throw e;

		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}
	}		


	public int add(Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			int count = sqlSession.insert(
					"net.bitacademy.java41.dao.MemberMapper.add", member);
			sqlSession.commit();

			return count;

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;

		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}
	}

	public List<Member> list() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{		
			return sqlSession.selectList("net.bitacademy.java41.dao.MemberMapper.list");
			 

		} catch (Exception e) {
			throw e;

		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}
	}




	public Member get(String email) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("net.bitacademy.java41.dao.MemberMapper.get1",email);

		} catch (Exception e) {
			throw e;

		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}
	}

	public List<Member> get(int pno) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {

			return sqlSession.selectList("net.bitacademy.java41.dao.MemberMapper.get2",pno);

		} catch (Exception e) {
			throw e;

		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}
	}



	public int change(Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("member", member);
			int a = sqlSession.update("net.bitacademy.java41.dao.MemberMapper.change",paramMap);
			sqlSession.commit();
			return a;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}


	public int remove(String email) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int a = sqlSession.delete("net.bitacademy.java41.dao.MemberMapper.remove1",email);
			sqlSession.delete("net.bitacademy.java41.dao.MemberMapper.remove2",email);
			sqlSession.commit();
			return a;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;

		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}

	public int changePassword(String email, String oldPassword, String newPassword) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("email", email);
			paramMap.put("oldPassword", oldPassword);
			paramMap.put("newPassword", newPassword);

			System.out.println(paramMap.get("email"));
			System.out.println(paramMap.get("oldPassword"));
			System.out.println(paramMap.get("newPassword"));
			int a = sqlSession.update("net.bitacademy.java41.dao.MemberMapper.changePassword",paramMap);
			sqlSession.commit();
			return a;

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}



	public void addPhoto(String email, String path) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("email", email);
			paramMap.put("path", path);
			
			sqlSession.insert(
				"net.bitacademy.java41.dao.MemberMapper.addPhoto", paramMap);
			sqlSession.commit();

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;

		} finally {
			try {sqlSession.close();} catch(Exception e) {}
			
		}
	}

	public List<Photo> listPhoto(String email) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			return sqlSession.selectList("net.bitacademy.java41.dao.MemberMapper.listPhoto",email);
			
		} catch (Exception e) {
			throw e;

		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}	

	}
}




