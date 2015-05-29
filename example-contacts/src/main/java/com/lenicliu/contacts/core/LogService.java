package com.lenicliu.contacts.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenicliu.contacts.entity.Log;

@Service
@Transactional
public class LogService {

	@Autowired
	private JdbcTemplate	jdbcTemplate;
	private RowMapper<Log>	rowMapper	= new BeanPropertyRowMapper<Log>(Log.class);

	public void create(Log log) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("tb_log");
		insert.execute(new BeanPropertySqlParameterSource(log));
	}

	public List<Log> findUserLogList(Long userid) {
		return jdbcTemplate.query("select * from tb_log where userid = ? order by created desc", rowMapper, userid);
	}
}
