package net.luferat.java.demoapi1.frontendeiros.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentRepository repository;

	@GetMapping(path = "/last/{limit}")
	public List<Comment> getLast(@PathVariable int limit) {
		return repository.findLastComments(limit);
	}

	@GetMapping(path = "/{articleId}")
	public List<Comment> getAll(@PathVariable Long articleId) {
		return repository.findAllCommentByArticle(articleId);
	}

	// Busca po um comentário específico.
	// Exemplo:
	// http://domínio.api/comments/find?uid=Q1W2E3R4T5Y6U7&art=2&txt=Comentário do
	// usuário
	// Busca por comentários que contenham exatamente
	// Id do usuário = "Q1W2E3R4T5Y6U7" E
	// Id do artigo = 2 E
	// Texto do comentário = "Comentário do usuário"
	// Serve para evitar que um mesmo comentário seja enviado de forma repetida.
	@GetMapping(path = "/find")
	public List<Comment> alreadyExists(@RequestParam("uid") String uid, @RequestParam("art") Long articleId,
			@RequestParam("txt") String comment) {
		return repository.findCommentsByAuthorArticleAndContent(uid, articleId, comment);
	}

	@PostMapping
	public Comment postComment(@RequestBody Comment comment) {
		return repository.save(comment);
	}
}
