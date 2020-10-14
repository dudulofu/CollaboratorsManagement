package com.devcases.usermanager.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devcases.usermanager.model.Collaborator;
import com.devcases.usermanager.model.Department;
import com.devcases.usermanager.service.CollaboratorService;
import com.devcases.usermanager.service.DepartmentService;
import com.devcases.usermanager.service.ProjectService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CollaboratorController {

	@Autowired
	private CollaboratorService collabService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("collab", collabService.getAll());

		return "home";
	}

	@RequestMapping(value = "/newcollaborator", method = RequestMethod.GET)
	public String newCollaborator(Model model) {

		model.addAttribute("dp", departmentService.getAll());
		model.addAttribute("pj", projectService.getAll());

		Map<String, String> types = new HashMap<String, String>();

		types.put("R", "REGULAR");
		types.put("M", "MANAGER");

		model.addAttribute("types", types);

		return "newcollaborator";
	}
	
	@RequestMapping(value = "/updatecollaborator/{id}", method = RequestMethod.GET)
	public String updateCollaborator(@PathVariable("id") int id,Model model) throws Exception {
		
		model.addAttribute("dp", departmentService.getAll());
		model.addAttribute("pj", projectService.getAll());
		
		Map<String, String> types = new HashMap<String, String>();
		
		types.put("R", "REGULAR");
		types.put("M", "MANAGER");
		
		model.addAttribute("types", types);
		
		
		
		model.addAttribute("id", id);
		Collaborator c = collabService.collaboratorById(id);
		
		model.addAttribute("collaborator", c);
		
		ArrayList<Integer> projectIds = collabService.projectsBycollaborator(id);
		
		Integer projectId=0;
		
		if(projectIds.size()>0){
			projectId=projectIds.get(0);
		}
		
		model.addAttribute("pjId", projectId);
		
		
		Integer countManagerProjects = collabService.countManagerProjectsById(id);
		
		
		model.addAttribute("countManagerProjects", countManagerProjects);
		
		
		if(countManagerProjects>0) {
			model.addAttribute("error", "Collaborator radio type disabled because this manager is associated with 1 or more projects.\n Utilize the Projects page in order to update them.");
		}
		
		return "updatecollaborator";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated Collaborator c, Model model) {

		collabService.add(c);

		return home(model);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Validated Collaborator c, Model model) {
		
		collabService.update(c);
		
		return home(model);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable("id") int id, Model model) {

		try {
			collabService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error",
					"This collaborator can't be deleted because it's associated with 1 or more projects.");
		}

		return home(model);
	}

	/**
	 * @return the collabService
	 */
	public CollaboratorService getCollabService() {
		return collabService;
	}

	/**
	 * @param collabService the collabService to set
	 */
	public void setCollabService(CollaboratorService collabService) {
		this.collabService = collabService;
	}

	/**
	 * @return the departmentService
	 */
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * @param departmentService the departmentService to set
	 */
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * @return the projectService
	 */
	public ProjectService getProjectService() {
		return projectService;
	}

	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

}
