package com.lenicliu.contacts.entity;

import java.io.Serializable;

public class Contact implements Serializable {

	private static final long	serialVersionUID	= 836021247085919429L;
	private Long	id;
	private Long	userid;
	private String	username;
	private String	mobile;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
