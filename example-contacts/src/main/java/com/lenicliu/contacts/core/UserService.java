package com.lenicliu.contacts.core;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lenicliu.contacts.entity.Log;
import com.lenicliu.contacts.entity.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private JdbcTemplate	jdbcTemplate;
	@Autowired
	private LogService		logService;
	private RowMapper<User>	rowMapper	= new BeanPropertyRowMapper<User>(User.class);

	public void createUser(User user) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("tb_user");
		insert.setGeneratedKeyName("id");
		Number userid = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(user));

		Log log = new Log();
		log.setUserid(userid.longValue());
		log.setCreated(new Date());
		log.setContent("注册成功");
		logService.create(log);
	}

	public void updateUser(User user) {
		jdbcTemplate.update("update tb_user set password = ? where id = ?", user.getPassword(), user.getId());
		Log log = new Log();
		log.setUserid(user.getId());
		log.setCreated(new Date());
		log.setContent("修改密码");
		logService.create(log);
	}

	public User findUser(String username) {
		if (!StringUtils.hasText(username)) {
			return null;
		}
		List<User> users = jdbcTemplate.query("select * from tb_user where username = ?", rowMapper, username);
		return users.size() == 1 ? users.get(0) : null;
	}
}