package com.lenicliu.contacts.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenicliu.contacts.core.LogService;
import com.lenicliu.contacts.core.UserService;
import com.lenicliu.contacts.entity.Log;
import com.lenicliu.contacts.entity.User;

@Controller
public class UserAction {

	@Autowired
	private UserService	userService;
	@Autowired
	private LogService	logService;

	@RequestMapping("/user/register")
	public String register(User user) {
		userService.createUser(user);
		return "redirect:/login";
	}

	@RequestMapping("/user/login")
	public String login(User user) {
		User exists = userService.findUser(user.getUsername());
		if (exists == null || !exists.getPassword().equals(user.getPassword())) {
			return "redirect:/login";
		}
		Log log = new Log();
		log.setUserid(exists.getId());
		log.setCreated(new Date());
		log.setContent("登陆成功!");
		logService.create(log);
		return "redirect:/contacts";
	}
}