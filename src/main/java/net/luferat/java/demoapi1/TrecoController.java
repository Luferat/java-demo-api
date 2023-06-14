package net.luferat.java.demoapi1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/trecos")
public class TrecoController {

	@Autowired
	private TrecoRepository trecoRepository;

	@GetMapping
	public List<Treco> getAll() {
		return trecoRepository.findAll();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public String getOne(@PathVariable Long id) {

		if (trecoRepository.existsById(id)) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Treco treco = trecoRepository.findById(id).get();
				return mapper.writeValueAsString(treco);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return "{ \"status\" : \"not found\" }";

	}

	@PostMapping
	public Treco post(@RequestBody Treco treco) {
		return trecoRepository.save(treco);
	}

	@DeleteMapping(path = "/{id}", produces = "application/json")
	public String delete(@PathVariable Long id) {
		if (trecoRepository.existsById(id)) {
			trecoRepository.deleteById(id);
			return "{ \"status\" : \"deleted\" }";
		}
		return "{ \"status\" : \"error\" }";
	}

	@PutMapping(path = "/{id}")
	public Treco put(@PathVariable Long id, @RequestBody Treco treco) {
		return null;
	}

	@PatchMapping(path = "/{id}")
	public Treco patch(@PathVariable Long id, @RequestBody Treco treco) {
		return null;
	}

}