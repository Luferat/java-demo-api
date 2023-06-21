package net.luferat.java.demoapi1.treco;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/trecos")
public class TrecoController {

	@Autowired
	private TrecoRepository repository;

	@GetMapping
	public List<Treco> getAll() {
		return repository.findAll();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
		Treco treco = repository.findById(id).orElse(null);

		if (treco != null) {
			return ResponseEntity.ok(treco);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"not found\"}");
		}
	}

	@PostMapping
	public Treco postNew(@RequestBody Treco treco) {
		return repository.save(treco);
	}

	@DeleteMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Object> deleteUserById(@PathVariable("id") Long id) {
		Treco treco = repository.findById(id).orElse(null);
		if (treco != null) {
			repository.delete(treco);
			return ResponseEntity.ok("{\"status\": \"deleted\"}");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"not-found\"}");
		}
	}

	@PutMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Object> putById(@PathVariable Long id, @RequestBody Treco entidadeAtualizada) {
		Optional<Treco> entidadeOptional = repository.findById(id);
		if (entidadeOptional.isPresent()) {
			Treco entidade = entidadeOptional.get();
			entidade.setDate(entidadeAtualizada.getDate());
			entidade.setName(entidadeAtualizada.getName());
			entidade.setDescription(entidadeAtualizada.getDescription());
			entidade.setStatus(entidadeAtualizada.getStatus());
			Treco usuarioAtualizado = repository.save(entidade);
			return ResponseEntity.ok(usuarioAtualizado);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"not-found\"}");
		}
	}

	@PatchMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Object> atualizarSuaEntidade(@PathVariable Long id, @RequestBody Treco novaEntidade) {
		Optional<Treco> entidadeOptional = repository.findById(id);
		if (entidadeOptional.isPresent()) {
			Treco entidade = entidadeOptional.get();
			if (novaEntidade.getDate() != null)
				entidade.setDate(novaEntidade.getDate());
			if (novaEntidade.getName() != null)
				entidade.setName(novaEntidade.getName());
			if (novaEntidade.getDescription() != null)
				entidade.setDescription(novaEntidade.getDescription());
			if (novaEntidade.getStatus() != null)
				entidade.setStatus(novaEntidade.getStatus());
			Treco entidadeAtualizada = repository.save(entidade);
			return ResponseEntity.ok(entidadeAtualizada);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"not-found\"}");
		}
	}

	@GetMapping(path = "/search/{query}", produces = "application/json")
	public ResponseEntity<Object> buscar(@PathVariable String query) {
		List<Treco> trecos = repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
		if (trecos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"not-found\"}");
		}
		return ResponseEntity.ok(trecos);
	}

}