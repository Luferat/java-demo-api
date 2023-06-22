package net.luferat.java.demoapi1.treco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrecoRepository extends JpaRepository<Treco, Long> {
	
	@Query(value = "SELECT * FROM treco WHERE status = 'on' ORDER BY date DESC", nativeQuery = true)
	List<Treco> findAllWithStatusOnOrderByDateDesc();
	
	
	
	List<Treco> findByNameContaining(String query);
}