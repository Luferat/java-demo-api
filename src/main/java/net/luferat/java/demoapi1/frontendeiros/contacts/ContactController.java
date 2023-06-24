package net.luferat.java.demoapi1.frontendeiros.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private ContactRepository repository;

	@PostMapping
	public Contact saveContact(@RequestBody Contact contact) {
		return repository.save(contact);
	}
}
