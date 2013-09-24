package net.bitacademy.java41.controls.project;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.annotations.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Project;
@Component("/project/projectupdateForm.do")
public class ProjectUpdateFormControl implements PageControl{
	ProjectService projectService;
	
	public ProjectUpdateFormControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Map<String, String[]> params = (Map<String, String[]>)model.get("params");
		
		Project project = projectService.getProject(Integer.parseInt(params.get("pno")[0]));
		HttpSession session = (HttpSession) model.get("session");
		session.setAttribute("projectUpdate", project);
		
		return "redirect:../project/projectUpdateForm.jsp";
		
	}

}
