package net.luferat.java.demoapi1.treco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrecoRepository extends JpaRepository<Treco, Long> {
	List<Treco> findByNameContaining(String query);
}