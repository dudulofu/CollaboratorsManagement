package com.devcases.usermanager.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.devcases.usermanager.model.Project;
import com.devcases.usermanager.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @see
	 */
	public List<Project> getAll() {

		List<Project> project = jdbcTemplate.query(SELECT_PROJECTS,

				new RowMapper<Project>() {

					public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
						Project c = new Project();
						c.setId(rs.getInt("id"));
						c.setName(rs.getString("name"));
						return c;
					}
				});

		return project;

	}

	public Integer add(Project c) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}


}
