package net.luferat.java.demoapi1.frontendeiros.articles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import net.luferat.java.demoapi1.frontendeiros.team.Team;

@Entity
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aid;
	private String adate;

	@ManyToOne
	@JoinColumn(name = "aauthor")
	private Team aauthor;

	private String atitle;
	private String athumbnail;
	private String aresume;

	@Column(columnDefinition = "LONGTEXT")
	private String acontent;
	private Long aviews;

	@Column(length = 3)
	private String astatus;

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public String getAdate() {
		return adate;
	}

	public void setAdate(String adate) {
		this.adate = adate;
	}

	public Team getAauthor() {
		return aauthor;
	}

	public void setAauthor(Team aauthor) {
		this.aauthor = aauthor;
	}

	public String getAtitle() {
		return atitle;
	}

	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}

	public String getAthumbnail() {
		return athumbnail;
	}

	public void setAthumbnail(String athumbnail) {
		this.athumbnail = athumbnail;
	}

	public String getAresume() {
		return aresume;
	}

	public void setAresume(String aresume) {
		this.aresume = aresume;
	}

	public String getAcontent() {
		return acontent;
	}

	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}

	public Long getAviews() {
		return aviews;
	}

	public void setAviews(Long aviews) {
		this.aviews = aviews;
	}

	public String getAstatus() {
		return astatus;
	}

	public void setAstatus(String astatus) {
		this.astatus = astatus;
	}

}
