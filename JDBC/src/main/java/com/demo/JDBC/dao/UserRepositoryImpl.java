package com.demo.JDBC.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.JDBC.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private static final String INSERT_USER_QUERY = "INSERT INTO USER(id,fname,lname,email) values(?,?,?,?)";
	private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE USER SET fname=? WHERE ID=?";
	private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM USER WHERE ID=?";
	private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM USER WHERE ID=?";
	private static final String GET_USERS_QUERY = "SELECT * FROM USER";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub

		jdbcTemplate.update(INSERT_USER_QUERY, user.getId(), user.getFname(), user.getLname(), user.getEmail());
		return user;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(UPDATE_USER_BY_ID_QUERY, user.getFname(), user.getId());
		return user;
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY, (rs, rowsNum) -> {
			return new User(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("email"));
		}, id);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(GET_USERS_QUERY, (rs, rowsNum) -> {
			return new User(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("email"));
		});
	}

	@Override
	public String deleteUser(int id) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, id);
		return "user deleted successfully";
	}

}
