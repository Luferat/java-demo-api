package net.luferat.java.demoapi1.usuario;

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
	public ResponseEntity<Usuario> patchById(@PathVariable Long id, @RequestBody Usuario novosDadosDoUsuario) {
		Optional<Usuario> atualDadosUsusario = repository.findById(id);

		if (atualDadosUsusario.isPresent()) {
			Usuario entidade = atualDadosUsusario.get();

			if (novosDadosDoUsuario.getName() != null)
				entidade.setName(novosDadosDoUsuario.getName());

			if (novosDadosDoUsuario.getEmail() != null)
				entidade.setEmail(novosDadosDoUsuario.getEmail());

			if (novosDadosDoUsuario.getPassword() != null)
				entidade.setPassword(novosDadosDoUsuario.getPassword());

			Usuario usuarioAtualizado = repository.save(entidade);
			return ResponseEntity.ok(usuarioAtualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
