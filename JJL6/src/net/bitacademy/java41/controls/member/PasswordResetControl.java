package net.bitacademy.java41.controls.member;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.annotations.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.vo.Member;
@Component("/member/passwordreset.do")
public class PasswordResetControl implements PageControl{
	MemberService memberService;
	
	public PasswordResetControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}
	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {

		HttpSession session = (HttpSession) model.get("session");	
		Map<String, String[]> params = (Map<String, String[]>)model.get("params");
		Member member = (Member) session.getAttribute("memberUpdate");
		String email = member.getEmail();
		String oldPassword = params.get("password")[0];
		String newPassword = params.get("newPassword")[0];
		String newPassword2 = params.get("newPassword2")[0];
		
		boolean boo = memberService.changePassword(email, oldPassword, newPassword);
		
		if (newPassword.equals(newPassword2)) {
			if (boo) {
				session.setAttribute("status", "SUCCESS");
				member.setPassword(newPassword);
				session.setAttribute("member", member);
			} else {
				session.setAttribute("status", "OLD_PASSWORD_ERROR");
			}
		} else {
			session.setAttribute("status", "NEW_PASSWORD_ERROR");
		}
		
		
		
		return "/member/passwordChangeResult.jsp";
				
	}

}
