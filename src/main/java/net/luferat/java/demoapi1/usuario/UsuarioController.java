package net.luferat.java.demoapi1.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@GetMapping
	public List<Usuario> getAll() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getOne(@PathVariable Long id) {
		Optional<Usuario> entidadeOptional = repository.findById(id);
		return entidadeOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Usuario postNew(@RequestBody Usuario usuario) {
		return repository.save(usuario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		Optional<Usuario> entidadeOptional = repository.findById(id);
		if (entidadeOptional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> putById(@PathVariable Long id, @RequestBody Usuario entidadeAtualizada) {
		Optional<Usuario> entidadeOptional = repository.findById(id);

		if (entidadeOptional.isPresent()) {
			Usuario entidade = entidadeOptional.get();
			entidade.setName(entidadeAtualizada.getName());
			entidade.setEmail(entidadeAtualizada.getEmail());
			entidade.setPassword(entidadeAtualizada.getPassword());
			Usuario usuarioAtualizado = repository.save(entidade);
			return ResponseEntity.ok(usuarioAtualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Usuario> atualizarSuaEntidade(@PathVariable Long id, @RequestBody Usuario novaEntidade) {
		Optional<Usuario> entidadeOptional = repository.findById(id);
		if (entidadeOptional.isPresent()) {
			Usuario entidade = entidadeOptional.get();
			if (novaEntidade.getName() != null)
				entidade.setName(novaEntidade.getName());
			if (novaEntidade.getEmail() != null)
				entidade.setEmail(novaEntidade.getEmail());
			if (novaEntidade.getPassword() != null)
				entidade.setPassword(novaEntidade.getPassword());
			Usuario entidadeAtualizada = repository.save(entidade);
			return ResponseEntity.ok(entidadeAtualizada);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
