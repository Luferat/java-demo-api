package net.luferat.java.demoapi1.frontendeiros.team;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query(value = "SELECT * FROM users WHERE uid = :id", nativeQuery = true)
	Optional<Team> findById(@Param("id") Long id);

	@Query(value = "SELECT * FROM users WHERE ustatus = 'on'", nativeQuery = true)
	List<Team> findAllUsers();
}
