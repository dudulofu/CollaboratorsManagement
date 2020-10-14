package com.devcases.usermanager.service;

import java.util.List;

import com.devcases.usermanager.model.Department;

public interface DepartmentService {

	/** seleciona todos os departamentos cadastrado no sistema */
	final String SELECT_ALL_DEPARTMENT = 
			"select id, name from DEPARTMENT";
	/**
	 * Metodo responsavel por listar todos os departamentos
	 * 
	 * @return List departament
	 */
	List<Department> getAll();

	Integer add(Department c);

	Integer delete(int id);

}