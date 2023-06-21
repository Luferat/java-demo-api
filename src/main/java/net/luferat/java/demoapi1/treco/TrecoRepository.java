package net.luferat.java.demoapi1.treco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrecoRepository extends JpaRepository<Treco, Long> {
	List<Treco> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String queryName, String queryRepository);
}
