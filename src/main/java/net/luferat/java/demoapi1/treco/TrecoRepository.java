package net.luferat.java.demoapi1.treco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrecoRepository extends JpaRepository<Treco, Long> {
	// Método que retorna a busca por "String queryName" no campos "name" e por
	// "String queryRepository" no campo "description". A busca é
	// "case-insensitive".
	List<Treco> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String queryName,
			String queryRepository);
}
