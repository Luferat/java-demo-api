package net.luferat.java.demoapi1.frontendeiros.articles;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/articles")
public class ArticleController {

	private final ArticleRepository repository;

	public ArticleController(ArticleRepository repository) {
		this.repository = repository;
	}

	// Lista todos os artigos válidos.
	@GetMapping
	public List<Article> getAll() {
		return repository.findAllValidArticles();
	}

	// Lista um artigo válido pelo Id.
	@GetMapping(path = "/{id}")
	public Article getOne(@PathVariable Long id) {
		return repository.findArticleById(id);
	}

	// Lista artigos mais visualizados.
	@GetMapping(path = "/views/{limit}")
	public List<Article> getByViews(@PathVariable int limit) {
		return repository.findMostViewedArticles(limit);
	}

	@PatchMapping(path = "/{id}", produces = "application/json")
	public String updateViews(@PathVariable Long id) {
		repository.updateViews(id);
		return "{\"status\": \"success\"}";
	}

	// Obtém os artigos do autor.
	// Observe que a rota contém 3 parâmetros numéricos:
	// {uid} → Id do autor do artigo
	// {art} → Id do artigo que será excluído da listagem
	// {lim} → Quantos artigos serão obtidos
	// Exemplo de rota: http://domain.api/articles/author?uid=1&art=2&lim=5
	@GetMapping(path = "/author")
	public List<Article> getByAuthor(@RequestParam("uid") Long uid, @RequestParam("art") Long articleId,
			@RequestParam("lim") int limit) {
		return repository.findAllByAuthor(uid, articleId, limit);
	}

	// Busca por uma palavra ou termo nos campos "title", "resume" e "content".
	// As buascas são "case-insensitive". Por exemplo, para procurar por "Biscoito":
	// GET → http://domain.api/articles/find?q=Biscoito
	@GetMapping(path = "/find")
	public List<Article> findArticleByWord(@RequestParam("q") String q) {
		return repository.findByWord(q);
	}

	@GetMapping(path = "/authordata")
	public List<Article> getPosts() {
		return repository.findArticlesWithUserData();
	}

}
