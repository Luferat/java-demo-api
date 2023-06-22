package net.luferat.java.demoapi1.frontendeiros.articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin
@RestController
@RequestMapping("/articles")
public class ArticleController {

	@Autowired
	private ArticleRepository repository;

	// Lista todos os artigos válidos.
	@GetMapping
	public List<Article> getAll() {
		return repository.findAllValidArticles();
	}

	// Lista um artigo válido pelo Id.
	@GetMapping(path = "/{id}")
	public Article getOne(@PathVariable Long id) throws JsonProcessingException {
		if (repository.existsById(id)) {
			return repository.findArticleById(id);
		}
		return null;
	}

	@GetMapping("/views/{limit}")
	public List<Article> getByViews(@PathVariable int limit) {
		return repository.findMostViewedArticles(limit);
	}

	@PatchMapping(path = "/{id}", produces = "application/json")
	public String updateViews(@PathVariable Long id) {
		repository.updateViews(id);
		return "{\"status\": \"success\"}";
	}

}
