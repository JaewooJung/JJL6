package net.bitacademy.java41.controls.project;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.annotations.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;
@Component("/project/projectdetail.do")
public class ProjectDetailControl implements PageControl{
	ProjectService projectService;
	MemberService memberService;
	public ProjectDetailControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}

	public ProjectDetailControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Map<String, String[]> params = (Map<String, String[]>)model.get("params");
		
		HttpSession session = (HttpSession) model.get("session");
		
		String pno = params.get("pno")[0];
		Project project = projectService.getProject(Integer.parseInt(pno));
		session.setAttribute("project_detail", project);
		session.setAttribute("project_detail_members", memberService.get(Integer.parseInt(params.get("pno")[0])));
		
		return "../project/projectdetail.jsp";
		
	}
}
