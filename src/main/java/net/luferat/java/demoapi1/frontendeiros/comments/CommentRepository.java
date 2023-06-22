package net.luferat.java.demoapi1.frontendeiros.comments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	final String DEFAULTPARAMS = "status = 'on'";

	@Query(value = "SELECT * FROM comments WHERE " + DEFAULTPARAMS
			+ " ORDER BY date DESC LIMIT :limit  ", nativeQuery = true)
	List<Comment> findLastComments(@Param("limit") int limit);

}
