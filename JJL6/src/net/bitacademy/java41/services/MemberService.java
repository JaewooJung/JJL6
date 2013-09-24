package net.bitacademy.java41.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.annotations.Component;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;

@Component
public class MemberService {
	DBConnectionPool dbConnectionPool;
	MemberDao memberDao;
	
	public void setDbConnectionPool(DBConnectionPool dbConnectionPool) {
		this.dbConnectionPool = dbConnectionPool;
	}
	
	public MemberService setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	public void signUp(Member member) throws Exception {
		
		try {
			memberDao.add(member);
			String[] photos = member.getPhotos();
			if (photos != null) {
				for (String path : photos) {
					memberDao.addPhoto(member.getEmail(), path);
				}
			}
			
		} catch (Exception e) {
			throw e;
			
		} finally {
		}
	}
	
	public List<Member> getMemberList() throws Exception {
		return memberDao.list();
			
	}
	
	public Member getMember(String strings) throws Exception {
			return memberDao.get(strings);
			
	}
	
	public ArrayList<Member> list() throws Exception{
		return (ArrayList<Member>) memberDao.list();
			
	}

	
	public int change(Member member) throws Exception{
		return memberDao.change(member);
			
	}
	
	public boolean changePassword(String email, String oldPassword, String newPassword) throws Exception {
		int a = memberDao.changePassword(email, oldPassword, newPassword);
		
		if(a > 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	public int remove(String email) throws Exception{
		return memberDao.remove(email);
			
	}
	
	
	public List<Member> get(int pno) throws Exception {
		return memberDao.get(pno); 
		
	}
	
}
