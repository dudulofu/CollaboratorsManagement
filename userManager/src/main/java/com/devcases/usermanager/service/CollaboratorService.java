package com.devcases.usermanager.service;

import java.util.ArrayList;
import java.util.List;

import com.devcases.usermanager.model.Collaborator;

public interface CollaboratorService {

	/** seleciona todos os colaboradores cadastrado no sistema */
//	final String SELECT_ALL_COLLABORATOR = "select id, projectId, departmentId, name, manager from COLLABORATOR";
//	
//	final String INSERT_COLLABORATOR = "INSERT INTO COLLABORATOR(projectId, departmentId, name, manager) VALUES (?,?,?,?)";
//
//	final String DELETE_COLLABORATOR = "DELETE FROM COLLABORATOR WHERE id = ?";
	
	//selects
	final String SELECT_COLLABORATORS = "SELECT COLLABORATOR.ID, COLLABORATOR.NAME , COLLABORATOR.TYPE , DEPARTMENT.NAME AS \"DEPARTMENT\",DEPARTMENT.ID AS \"DEP_ID\",PROJECT.ID AS \"PROJECT_ID\",PROJECT.NAME AS \"PROJECT\" FROM COLLABORATOR  LEFT JOIN DEPARTMENT ON COLLABORATOR.DEPARTMENTID =DEPARTMENT.ID LEFT JOIN COLLAB_PROJECT ON COLLABORATOR.ID=COLLAB_PROJECT .COLLABORATORID  LEFT JOIN PROJECT ON PROJECT .ID=COLLAB_PROJECT .PROJECTID";
	final String SELECT_COLLABORATOR_BY_ID = "SELECT * FROM COLLABORATOR where id =?";
	final String SELECT_COLLAB_PROJECT = "SELECT PROJECTID FROM COLLAB_PROJECT where COLLABORATORID =?";
	
	final String SELECT_PROJECTS = "SELECT PROJECT.ID,PROJECT.NAME,COLLABORATOR.NAME AS \"MANAGER\" FROM PROJECT  LEFT JOIN MANAGER_PROJECT ON PROJECT .ID=MANAGER_PROJECT .PROJECTID  LEFT JOIN COLLABORATOR ON COLLABORATOR .ID=MANAGER_PROJECT .COLLABORATORID";
	
	final String SELECT_DEPARTMENTS = "SELECT * FROM DEPARTMENT";
	final String SELECT_COUNT_MANAGER_PROJECTS = "SELECT count(collaboratorid) FROM MANAGER_PROJECT where collaboratorid = ?";
	
	//inserts
	final String INSERT_COLLABORATOR = "INSERT INTO COLLABORATOR VALUES (DEFAULT, ?, ?, ?);";
	
	final String INSERT_DEPARTMENT = "INSERT INTO DEPARTMENT VALUES (DEFAULT, ?);";
	
	final String INSERT_PROJECT = "INSERT INTO PROJECT VALUES (DEFAULT, ?);";
	
	final String INSERT_COLLAB_PROJECT = "INSERT INTO COLLAB_PROJECT VALUES (?,?);";
	
	final String INSERT_MANAGER_PROJECT = "INSERT INTO MANAGER_PROJECT VALUES (?,?);";
	
	//updates
	final String UPDATE_COLLABORATOR = "UPDATE COLLABORATOR SET DEPARTMENTID =?, NAME = ?, TYPE=? WHERE ID =?";
	
	final String UPDATE_COLLABORATOR_PROJECT = "UPDATE COLLAB_PROJECT SET PROJECTID =? WHERE COLLABORATORID =?";
	
	final String UPDATE_PROJECT_NAME = "UPDATE PROJECT SET NAME =? WHERE ID =?";
	
	final String UPDATE_PROJECT_MANAGER = "UPDATE MANAGER_PROJECT SET COLLABORATORID =? WHERE PROJECTID =?;";
		
	//deletes
	final String DELETE_COLLABORATOR = "DELETE FROM COLLABORATOR WHERE ID =?";
	
	
	final String DELETE_DEPARTMENT = "DELETE FROM DEPARTMENT WHERE ID =?";
	
	final String DELETE_PROJECT = "DELETE FROM PROJECT WHERE ID =?";
	
	//BONUS IN CASE OF NOT ASSIGNING A PROJECT TO A COLLAB	
	final String DELETE_PROJECT_FROM_COLLABORATOR = "DELETE FROM COLLAB_PROJECT WHERE COLLABORATORID =?";
	
	/**
	 * Metodo responsavel por listar todos os colaboradores
	 * 
	 * @return List collaborator
	 */
	List<Collaborator> getAll();

	Integer add(Collaborator c);
	
	Integer insertCollabProj(int collaboratorId, int projectId);

	Integer delete(int id) throws Exception;
	
	public Collaborator collaboratorById(int id) throws Exception;
	public ArrayList<Integer> projectsBycollaborator(int id) throws Exception;

	Integer update(Collaborator c);
	
	public Integer countManagerProjectsById(int collaboratorId) throws Exception;

}