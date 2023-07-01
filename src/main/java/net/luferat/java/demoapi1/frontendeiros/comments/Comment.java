package net.luferat.java.demoapi1.frontendeiros.comments;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import net.luferat.java.demoapi1.frontendeiros.articles.Article;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cid;
	private String cdate;
	private String fbname;
	private String fbphoto;
	private String fbemail;
	private String fbuid;

	@ManyToOne
	@JoinColumn(name = "carticle")
	private Article carticle;

	@Column(columnDefinition = "TEXT")
	private String ccomment;

	@Column(length = 3)
	private String cstatus;

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getFbname() {
		return fbname;
	}

	public void setFbname(String fbname) {
		this.fbname = fbname;
	}

	public String getFbphoto() {
		return fbphoto;
	}

	public void setFbphoto(String fbphoto) {
		this.fbphoto = fbphoto;
	}

	public String getFbemail() {
		return fbemail;
	}

	public void setFbemail(String fbemail) {
		this.fbemail = fbemail;
	}

	public String getFbuid() {
		return fbuid;
	}

	public void setFbuid(String fbuid) {
		this.fbuid = fbuid;
	}

	public Article getCarticle() {
		return carticle;
	}

	public void setCarticle(Article carticle) {
		this.carticle = carticle;
	}

	public String getCcomment() {
		return ccomment;
	}

	public void setCcomment(String ccomment) {
		this.ccomment = ccomment;
	}

	public String getCstatus() {
		return cstatus;
	}

	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}

}