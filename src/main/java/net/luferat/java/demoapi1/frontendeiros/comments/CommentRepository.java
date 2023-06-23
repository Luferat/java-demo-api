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

	// Lista os comentários mais recentes.
	@Query(value = "SELECT * FROM comments WHERE " + DEFAULTPARAMS
			+ " ORDER BY date DESC LIMIT :limit  ", nativeQuery = true)
	List<Comment> findLastComments(@Param("limit") int limit);

	// Pesquisa por comentários pelo autor, artigo e comentário.
	@Query(value = "SELECT * FROM comments WHERE " + DEFAULTPARAMS
			+ " AND uid = :uid AND article = :art AND comment = :txt", nativeQuery = true)
	List<Comment> findCommentsByAuthorArticleAndContent(@Param("uid") String uid, @Param("art") Long art,
			@Param("txt") String txt);

	// Salva um novo comentário.
	@Query(value = "SELECT * FROM comments WHERE " + DEFAULTPARAMS
			+ " AND article = :articleId ORDER BY date DESC", nativeQuery = true)
	List<Comment> findAllCommentByArticle(@Param("articleId") Long articleId);

}
