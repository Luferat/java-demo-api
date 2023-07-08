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
	final String DEFAULTPARAMS = "adate <= NOW() AND astatus = 'on'";
	final String NOUSERPASSWORD = " '' as upassword";

	// Obtém todos os artigos ordenados pela data decrescente.
	@Query(value = "SELECT * FROM articles WHERE " + DEFAULTPARAMS + " ORDER BY adate DESC", nativeQuery = true)
	List<Article> findAllValidArticles();

	// Obtém os artigos mais visualizados decrescente.
	@Query(value = "SELECT * FROM articles WHERE " + DEFAULTPARAMS
			+ " ORDER BY aviews DESC LIMIT :limit", nativeQuery = true)
	List<Article> findMostViewedArticles(@Param("limit") int limit);

	// Obtém um artigo pelo Id.
	@Query(value = "SELECT * FROM articles WHERE " + DEFAULTPARAMS + " AND aid = :id", nativeQuery = true)
	Article findArticleById(@Param("id") Long id);

	// Obtém os artigos de um autor, exceto o artigo com "id", em ordem aleatória.
	@Query(value = "SELECT * FROM articles WHERE " + DEFAULTPARAMS
			+ " AND aauthor = :uid AND aid != :articleId ORDER BY RAND() LIMIT :limit", nativeQuery = true)
	List<Article> findAllByAuthor(@Param("uid") Long uid, @Param("articleId") Long articleId,
			@Param("limit") int limit);

	// Verifica se um artigo existe ou é ativo.
	@Query(value = "SELECT CASE WHEN COUNT(aid) > 0 THEN true ELSE false END FROM articles WHERE " + DEFAULTPARAMS
			+ " AND aid = :id", nativeQuery = true)
	boolean existsById(@Param("id") Long id);

	// Atualiza a quantidade de views do artigo pelo Id.
	@Modifying
	@Query(value = "UPDATE articles SET aviews = aviews + 1 WHERE " + DEFAULTPARAMS
			+ " AND aid = :id", nativeQuery = true)
	void updateViews(@Param("id") Long id);

	// Busca por uma palavra ou termo nos campos "title", "resume" e "content".
	@Query(value = "SELECT * FROM articles WHERE " + DEFAULTPARAMS
			+ " AND UPPER(atitle) LIKE UPPER(CONCAT('%', :query, '%')) OR UPPER(aresume) LIKE UPPER(CONCAT('%', :query, '%')) OR UPPER(acontent) LIKE UPPER(CONCAT('%', :query, '%')) ORDER BY adate DESC", nativeQuery = true)
	List<Article> findByWord(@Param("query") String query);

	// Busca artigos com os dados do autor.
	@Query(value = "SELECT articles.*, uid, ubio, ubirth, udate, uemail, uname, '' AS upassword, uphoto, ustatus, utype FROM articles INNER JOIN users WHERE adate <= NOW() AND astatus = 'on' ORDER BY adate DESC", nativeQuery = true)
	List<Article> findArticlesWithUserData();
}
