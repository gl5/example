package com.lenicliu.contacts.entity;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {

	private static final long	serialVersionUID	= 7110486467345933258L;
	private Long				id;
	private Long				userid;
	private String				content;
	private Date				created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
