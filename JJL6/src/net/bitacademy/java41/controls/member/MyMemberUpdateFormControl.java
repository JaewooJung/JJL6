package net.bitacademy.java41.controls.member;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.annotations.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.vo.Member;
@Component("/member/mymemberupdateForm.do")
public class MyMemberUpdateFormControl implements PageControl{
	MemberService memberService;
	
	public MyMemberUpdateFormControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		session.setAttribute("memberUpdate", (Member) session.getAttribute("member"));
		
		return "../member/mymemberUpdateForm.jsp";
		
	}

}
