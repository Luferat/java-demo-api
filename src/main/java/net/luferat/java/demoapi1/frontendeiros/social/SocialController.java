package net.luferat.java.demoapi1.frontendeiros.social;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/users/social")
public class SocialController {

	@Autowired
	private SocialRepository repository;

	@GetMapping("/{userId}")
	public List<Social> getSocial(@PathVariable Long userId) {
		return repository.findByUser(userId);
	}

}
