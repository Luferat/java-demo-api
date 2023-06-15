package net.luferat.java.demoapi1.employe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/users")
public class EmployeController {

	@Autowired
	private EmployeRepository employRepository;

	@GetMapping
	public List<Employe> getAll() {
		return employRepository.findAll();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public String getOne(@PathVariable Long id) {

		if (employRepository.existsById(id)) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Employe employe = employRepository.findById(id).get();
				return mapper.writeValueAsString(employe);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return "{ \"status\" : \"not found\" }";

	}

	@PostMapping
	public Employe post(@RequestBody Employe employe) {
		return employRepository.save(employe);
	}

}
