package net.luferat.java.demoapi1.frontendeiros.articles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String date;
	private Long author;
	private String title;
	private String thumbnail;
	private String resume;
	@Column(columnDefinition = "LONGTEXT")
	private String content;
	private Long views;
	@Column(length = 3)
	private String status;

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public String getdate() {
		return date;
	}

	public void setdate(String date) {
		this.date = date;
	}

	public Long getauthor() {
		return author;
	}

	public void setauthor(Long author) {
		this.author = author;
	}

	public String gettitle() {
		return title;
	}

	public void settitle(String title) {
		this.title = title;
	}

	public String getthumbnail() {
		return thumbnail;
	}

	public void setthumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getresume() {
		return resume;
	}

	public void setresume(String resume) {
		this.resume = resume;
	}

	public String getcontent() {
		return content;
	}

	public void setcontent(String content) {
		this.content = content;
	}

	public Long getviews() {
		return views;
	}

	public void setviews(Long views) {
		this.views = views;
	}

	public String getstatus() {
		return status;
	}

	public void setstatus(String status) {
		this.status = status;
	}

}
