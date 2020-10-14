package com.devcases.usermanager.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.devcases.usermanager.model.Collaborator;
import com.devcases.usermanager.service.CollaboratorService;

@Service
public class CollaboratorServiceImpl implements CollaboratorService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @see
	 */
	public List<Collaborator> getAll() {

		List<Collaborator> collaborator = jdbcTemplate.query(SELECT_COLLABORATORS,

				new RowMapper<Collaborator>() {

					public Collaborator mapRow(ResultSet rs, int rowNum) throws SQLException {
						Collaborator c = new Collaborator();
						c.setId(rs.getInt("id"));
						c.setDepartmentId(rs.getInt("DEP_ID"));
						c.setProjectId(rs.getInt("PROJECT_ID"));
						c.setName(rs.getString("name"));
						c.setType(rs.getString("type"));
						c.setDepartmentName(rs.getString("DEPARTMENT"));
						c.setProjectName(rs.getString("PROJECT"));
						return c;
					}
				});

		return collaborator;

	}

	public Integer insertCollabProj(int collaboratorId, int projectId) {
		return jdbcTemplate.update(INSERT_COLLAB_PROJECT, collaboratorId, projectId);
	}

	public Integer countManagerProjectsById(final int collaboratorId) throws Exception {
		int result = jdbcTemplate.queryForObject(SELECT_COUNT_MANAGER_PROJECTS, new Object[] { collaboratorId },
				Integer.class);

		return result;
	}

	public Integer add(final Collaborator c) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(INSERT_COLLABORATOR);
				int i = 1;
				ps.setInt(i++, c.getDepartmentId());
				ps.setString(i++, c.getName());
				ps.setString(i++, c.getType());
				return ps;
			}
		}, keyHolder);
		Number idCollaborator = keyHolder.getKey();
		if (c.getType().equals("M") || c.getProjectId() == null) {
			return 0;
		}
		return insertCollabProj(idCollaborator.intValue(), c.getProjectId());
	}

	public Integer update(final Collaborator c) {

		if (c.getProjectId() != null) {
			jdbcTemplate.update(UPDATE_COLLABORATOR_PROJECT, c.getProjectId(), c.getId());
		}
		return jdbcTemplate.update(UPDATE_COLLABORATOR, c.getDepartmentId(), c.getName(), c.getType(), c.getId());

	}

	public Integer delete(final int id) throws Exception {

		try {
			PreparedStatementSetter set = new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, id);
				}
			};
			return jdbcTemplate.update(DELETE_COLLABORATOR, set);

		} catch (DataIntegrityViolationException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	public Collaborator collaboratorById(final int id) throws Exception {
		Collaborator c = null;
		try {
			PreparedStatementSetter set = new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, id);
				}
			};
			c = jdbcTemplate.query(SELECT_COLLABORATOR_BY_ID, set, new RowMapper<Collaborator>() {
				public Collaborator mapRow(ResultSet rs, int rowNum) throws SQLException {
					Collaborator c = new Collaborator();
					c.setId(rs.getInt("id"));
					c.setName(rs.getString("name"));
					c.setDepartmentId(rs.getInt("departmentId"));
//					c.setProjectId(rs.getInt("projectId"));
					c.setType(rs.getString("type"));
					return c;
				}
			}).get(0);
		} catch (DataIntegrityViolationException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}

		return c;
	}

	public ArrayList<Integer> projectsBycollaborator(final int id) throws Exception {
		ArrayList<Integer> p = null;
		try {
			PreparedStatementSetter set = new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, id);
				}
			};
			p = (ArrayList<Integer>) jdbcTemplate.query(SELECT_COLLAB_PROJECT, set, new RowMapper<Integer>() {
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Integer pId = 0;
					pId = rs.getInt("projectId");
					return pId;
				}
			});
		} catch (DataIntegrityViolationException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return p;
	}

}
