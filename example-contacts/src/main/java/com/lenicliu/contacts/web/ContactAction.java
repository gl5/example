package com.lenicliu.contacts.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lenicliu.contacts.core.ContactService;
import com.lenicliu.contacts.entity.Contact;

@Controller
public class ContactAction {

	@Autowired
	private ContactService	contactService;

	@RequestMapping("/contacts")
	public ModelAndView contacts(String keyword) {
		List<Contact> contacts = contactService.findList(keyword);
		return new ModelAndView("contacts", "contacts", contacts);
	}

	@RequestMapping("/contacts/save")
	public String save(Contact contact) {
		if (contact.getId() == null) {
			contactService.create(contact);
		} else {
			contactService.update(contact);
		}
		return "redirect:contacts";
	}

	@RequestMapping("/contacts/input")
	public ModelAndView input(Long id) {
		Contact contact = contactService.find(id);
		return new ModelAndView("input", "contact", contact);
	}
}
