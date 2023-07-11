package net.luferat.java.demoapi1.frontendeiros.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class TeamController {

	@Autowired
	private TeamRepository repository;

	@GetMapping(path = "/{id}")
	public Team getUser(@PathVariable Long id) {

		// Se o registro com o Id existe.
		if (repository.existsById(id)) {
			return repository.findById(id).get();
		}

		// Se o registro não existe.
		return null;

	}

	@GetMapping
	public List<Team> getAll() {
		return repository.findAllUsers();
	}

}
