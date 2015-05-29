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

import com.lenicliu.contacts.entity.Contact;
import com.lenicliu.contacts.entity.Log;

@Service
@Transactional
public class ContactService {

	@Autowired
	private JdbcTemplate		jdbcTemplate;

	private RowMapper<Contact>	rowMapper	= new BeanPropertyRowMapper<Contact>(Contact.class);

	@Autowired
	private LogService			logService;

	public void create(Contact contact) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("tb_contact");
		insert.execute(new BeanPropertySqlParameterSource(contact));

		Log log = new Log();
		log.setUserid(contact.getUserid());
		log.setCreated(new Date());
		log.setContent("添加联系人[" + contact.getUsername() + "(" + contact.getMobile() + ")]");
		logService.create(log);
	}

	public void update(Contact contact) {

		Contact exists = find(contact.getId());

		if (exists != null) {

			jdbcTemplate.update("update tb_contact set username = ?, mobile = ? where id = ?", contact.getUsername(), contact.getMobile(), contact.getId());

			Log log = new Log();
			log.setUserid(contact.getUserid());
			log.setCreated(new Date());
			log.setContent("修改联系人[" + exists.getUsername() + "(" + exists.getMobile() + ")] -> [" + contact.getUsername() + "(" + contact.getMobile() + ")]");
			logService.create(log);
		}
	}

	public Contact find(Long id) {
		if (id == null) {
			return null;
		}
		List<Contact> contacts = jdbcTemplate.query("select * from tb_contact where id = ?", rowMapper, id);
		return contacts.size() == 1 ? contacts.get(0) : null;
	}

	public void delete(Long id) {
		Contact exists = find(id);
		if (exists != null) {
			jdbcTemplate.update("delete from tb_contact where id = ?", exists.getId());

			Log log = new Log();
			log.setUserid(exists.getUserid());
			log.setCreated(new Date());
			log.setContent("删除联系人[" + exists.getUsername() + "(" + exists.getMobile() + ")]");
			logService.create(log);
		}
	}

	public List<Contact> findList(String keyword) {
		if (keyword == null) {
			keyword = "";
		}
		keyword = "%" + keyword.trim() + "%";
		return jdbcTemplate.query("select * from tb_contact where username like ? or mobile like ? order by username", rowMapper, keyword, keyword);
	}
}
