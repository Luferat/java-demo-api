package net.luferat.java.demoapi1.treco;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping("/trecos")
public class TrecoController {

	@Autowired
	private TrecoRepository repository;
	
	@GetMapping
	public List<Treco> getAll() {
		return repository.findAllWithStatusOnOrderByDateDesc();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public String getOne(@PathVariable Long id) throws JsonProcessingException {

		// Se o registro com o Id existe.
		if (repository.existsById(id)) {

			// ObjectMapper tenta converter um objeto para JSON.
			ObjectMapper mapper = new ObjectMapper();

			// Obtém o registro pelo Id e armazena no objeto "treco".
			Treco treco = repository.findById(id).get();

			// Retorna "treco" convertido para JSON (String → JSON).
			return mapper.writeValueAsString(treco);
		}

		// Se o registro não existe, retorna o JSON.
		return "{ \"status\" : \"not found\" }";

	}

	@GetMapping("/search/{query}")
	public List<Treco> search(@PathVariable String query) {
		return repository.findByNameContaining(query);
	}

	@PostMapping
	public Treco post(@RequestBody Treco treco) {

		// O método "save()" de JPA cria um novo registro
		// e armazena o objeto nele.
		return repository.save(treco);
	}

	@DeleteMapping(path = "/{id}", produces = "application/json")
	public String delete(@PathVariable Long id) {

		// Se o registro com o Id existe.
		if (repository.existsById(id)) {

			// O método "deleteById()" de JPA apaga o registro pelo Id.
			repository.deleteById(id);

			// Se apagou, retorna o JSON.
			return "{ \"status\" : \"deleted\" }";
		}

		// Se o registro não existe, retorna o JSON.
		return "{ \"status\" : \"not found\" }";
	}

	@PutMapping(path = "/{id}", produces = "application/json")
	public String updateAll(@PathVariable Long id, @RequestBody Treco treco) throws JsonProcessingException {

		// Se o registro com o Id existe.
		if (repository.existsById(id)) {

			// Obtém o registro pelo Id e mapeia seus atributos para "temp".
			repository.findById(id).map(temp -> {

				// Atualiza os campos, 1 por 1 conforme os valores fornecidos por "treco".
				temp.setDate(treco.getDate());
				temp.setName(treco.getName());
				temp.setDescription(treco.getDescription());
				temp.setStatus(treco.getStatus());

				// Salva o registro atualizado.
				return repository.save(temp);

				// Se Se não consegue obter o registro, ex. Id errado...
			}).orElseGet(() -> {

				// Não faz nada.
				return null;
			});

			// Retorna o registro atualizado usando o método GET.
			// Nota: adicione "throws JsonProcessingException" ao método "updateAll()".
			return getOne(id);

		}

		// Se o registro não existe, retorna o JSON.
		return "{ \"status\" : \"not found\" }";

	}

	@PatchMapping(path = "/{id}", produces = "application/json")
	public String updatePartial(@PathVariable Long id, @RequestBody Treco treco) throws JsonProcessingException {

		// Se o registro com o Id existe.
		if (repository.existsById(id)) {

			// Obtém o registro do banco e armazena em "original".
			Treco original = repository.findById(id).get();

			// Copia o Id no novo registro.
			treco.setId(id);

			// Testa cada campo.
			// Se o campo não foi fornecido na requisição, pega o valor deste no banco de
			// dados.
			// Se o campo teve um novo valor fornecido., mantém este valor, ignorando o
			// original.
			if (treco.getDate() == null || treco.getDate().equals(""))
				treco.setDate(original.getDate());
			if (treco.getName() == null || treco.getName().equals(""))
				treco.setName(original.getName());
			if (treco.getDescription() == null || treco.getDescription().equals(""))
				treco.setDescription(original.getDescription());
			if (treco.getStatus() == null || treco.getStatus().equals(""))
				treco.setStatus(original.getStatus());

			// Salva o registro atualizado.
			repository.save(treco);

			// Retorna o registro atualizado usando o método GET.
			// Nota: adicione "throws JsonProcessingException" ao método "updateAll()".
			return getOne(id);

		}

		// Se o registro não existe, retorna o JSON.
		return "{ \"status\" : \"not found\" }";

	}

}