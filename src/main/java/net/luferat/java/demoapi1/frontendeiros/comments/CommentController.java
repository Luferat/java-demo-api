package net.luferat.java.demoapi1.frontendeiros.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentRepository repository;
	
	@GetMapping("/last/{limit}")
	public List<Comment> getLast(@PathVariable int limit) {
		return repository.findLastComments(limit);
	}

}
