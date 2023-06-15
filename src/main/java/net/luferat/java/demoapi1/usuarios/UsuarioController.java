package net.luferat.java.demoapi1.usuarios;

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

import net.luferat.java.demoapi1.trecos.Treco;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@GetMapping
	public List<Usuario> getAll() {
		return repository.findAll();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public String getOne(@PathVariable Long id) {

		if (repository.existsById(id)) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Usuario employe = repository.findById(id).get();
				return mapper.writeValueAsString(employe);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return "{ \"status\" : \"not found\" }";

	}

	@PostMapping
	public Usuario post(@RequestBody Usuario employe) {
		return repository.save(employe);
	}
	
	@DeleteMapping(path = "/{id}", produces = "application/json")
	public String delete(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return "{ \"status\" : \"deleted\" }";
		}
		return "{ \"status\" : \"error\" }";
	}

	@PutMapping(path = "/{id}")
	public Treco put(@PathVariable Long id, @RequestBody Usuario usuario) {
		return null;
	}

	@PatchMapping(path = "/{id}")
	public Treco patch(@PathVariable Long id, @RequestBody Usuario usuario) {
		return null;
	}

}
