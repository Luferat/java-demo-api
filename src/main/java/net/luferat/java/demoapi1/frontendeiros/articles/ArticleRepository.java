package net.luferat.java.demoapi1.frontendeiros.articles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	// Constante de busca por artigos.
	final String DEFAULTPARAMS = "date <= NOW() AND status = 'on'";

	// Obtém todos os artigos ordenados pela data decrescente.
	@Query(value = "SELECT * FROM articles WHERE " + DEFAULTPARAMS + " ORDER BY date DESC", nativeQuery = true)
	List<Article> findAllValidArticles();

	// Obtém os artigos mais visualizados decrescente.
	@Query(value = "SELECT * FROM articles WHERE " + DEFAULTPARAMS
			+ " ORDER BY views DESC LIMIT :limit", nativeQuery = true)
	List<Article> findMostViewedArticles(@Param("limit") int limit);

	// Obtém um artigo pelo Id.
	@Query(value = "SELECT * FROM articles WHERE " + DEFAULTPARAMS + " AND id = :id", nativeQuery = true)
	Article findArticleById(@Param("id") Long id);

	// Obtém os artigos de um autor, exceto o artigo com "id", em ordem aleatória.
	@Query(value = "SELECT * FROM articles WHERE " + DEFAULTPARAMS
			+ " AND author = :uid AND id != :articleId ORDER BY RAND() LIMIT :limit", nativeQuery = true)
	List<Article> findAllByAuthor(@Param("uid") Long uid, @Param("articleId") Long articleId,
			@Param("limit") int limit);

	// Verifica se um artigo existe ou é ativo.
	@Query(value = "SELECT CASE WHEN COUNT(id) > 0 THEN true ELSE false END FROM articles WHERE " + DEFAULTPARAMS
			+ " AND id = :id", nativeQuery = true)
	boolean existsById(@Param("id") Long id);

	// Atualiza a quantidade de views do artigo pelo Id.
	@Modifying
	@Query(value = "UPDATE articles SET views = views + 1 WHERE " + DEFAULTPARAMS + " AND id = :id", nativeQuery = true)
	void updateViews(@Param("id") Long id);

	// Busca por uma palavra ou termo nos campos "title", "resume" e "content".
	@Query(value = "SELECT * FROM articles WHERE " + DEFAULTPARAMS
			+ " AND UPPER(title) LIKE UPPER(CONCAT('%', :query, '%')) OR UPPER(resume) LIKE UPPER(CONCAT('%', :query, '%')) OR UPPER(content) LIKE UPPER(CONCAT('%', :query, '%'))", nativeQuery = true)
	List<Article> findByWord(@Param("query") String query);
}
