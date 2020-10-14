package com.devcases.usermanager.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.devcases.usermanager.model.Department;
import com.devcases.usermanager.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @see
	 */
	public List<Department> getAll() {

		List<Department> department = jdbcTemplate.query(SELECT_ALL_DEPARTMENT,

				new RowMapper<Department>() {

					public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
						Department c = new Department();
						c.setId(rs.getInt("id"));
						c.setName(rs.getString("name"));
						return c;
					}
				});

		return department;

	}

	public Integer add(Department c) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
