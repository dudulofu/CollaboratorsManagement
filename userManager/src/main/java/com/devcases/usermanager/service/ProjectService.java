package com.devcases.usermanager.service;

import java.util.List;

import com.devcases.usermanager.model.Project;

public interface ProjectService {
	
	/** seleciona todos os projetos cadastrado no sistema */
	final String SELECT_PROJECTS = 
			"select id, name from PROJECT";
	/**
	 * Metodo responsavel por listar todos os projetos
	 * 
	 * @return List project
	 */
	List<Project> getAll();

	Integer add(Project c);

	Integer delete(int id);

}
