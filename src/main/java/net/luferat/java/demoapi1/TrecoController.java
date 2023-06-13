package net.luferat.java.demoapi1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trecos")
public class TrecoController {

	@Autowired
	private TrecoRepository trecoRepository;

	@GetMapping
	public List<Treco> getAll() {
		return trecoRepository.findAll();
	}

}