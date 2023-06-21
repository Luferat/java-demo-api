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

	// Obtém todos os registros.
	// Retorna uma coleção ([]) com todos os registros obtidos.
	@GetMapping
	public List<Treco> getAll() {
		return repository.findAll();
	}

	// Obtém um registro pelo Id.
	// Retorna um objeto com o registro.
	// Caso o registro não exista, retorna {"status": "not-found"}.
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
		Treco treco = repository.findById(id).orElse(null);
		if (treco != null) {
			return ResponseEntity.ok(treco);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"not found\"}");
		}
	}

	// Cria um novo registro.
	// Retorna um objeto com o novo registro.
	@PostMapping
	public Treco postNew(@RequestBody Treco treco) {
		return repository.save(treco);
	}

	// Apaga um registro pelo Id.
	// Retorna {"status": "deleted"}.
	// Caso o registro não exista, retorna {"status": "not-found"}.
	// CUIDADO! Este método realmente elimina o registro do banco de dados → DELETE.
	@DeleteMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		Treco treco = repository.findById(id).orElse(null);
		if (treco != null) {
			repository.delete(treco);
			return ResponseEntity.ok("{\"status\": \"deleted\"}");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"not-found\"}");
		}
	}

	// Atualiza um registro completo pelo Id.
	// Retorna um objeto com o registro atualizado.
	// Caso o registro não exista, retorna {"status": "not-found"}.
	@PutMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody Treco entidadeAtualizada) {
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

	// Atualiza um registro parcialmente, pelo Id.
	// Somente os campos informados terão os valores atualizados.
	// Retorna um objeto com o registro atualizado.
	// Caso o registro não exista, retorna {"status": "not-found"}.
	@PatchMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Object> patch(@PathVariable Long id, @RequestBody Treco novaEntidade) {
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

	// Obtém os registros à partir de uma "string de busca".
	// Por exemplo, para pesquisar por "biscoito":
	// GET → http://localhost:8080/trecos/search/biscoito
	// A busca é "case-insensitive", ou seja, busca por "biscoito" ou "BISCOITO" tem
	// o mesmo resultado.
	// Retorna uma coleção ([]) com todos os registros em que os campos "name" e/ou
	// "description" contenham a "string de busca".
	// Se não encontrar nada, retorna {"status": "not-found"}.
	@GetMapping(path = "/search/{query}", produces = "application/json")
	public ResponseEntity<Object> search(@PathVariable String query) {
		List<Treco> trecos = repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
		if (trecos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": \"not-found\"}");
		}
		return ResponseEntity.ok(trecos);
	}

}