package net.luferat.java.demoapi1.treco;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trecos")
public class TrecoController {

	@Autowired
	private TrecoRepository repository;

	@GetMapping
	public List<Treco> getAll() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Treco> getOne(@PathVariable Long id) {
		Optional<Treco> entidadeOptional = repository.findById(id);
		return entidadeOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Treco postNew(@RequestBody Treco treco) {
		return repository.save(treco);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		Optional<Treco> entidadeOptional = repository.findById(id);
		if (entidadeOptional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Treco> putById(@PathVariable Long id, @RequestBody Treco entidadeAtualizada) {
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
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Treco> atualizarSuaEntidade(@PathVariable Long id, @RequestBody Treco novaEntidade) {
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
			return ResponseEntity.notFound().build();
		}
	}

}