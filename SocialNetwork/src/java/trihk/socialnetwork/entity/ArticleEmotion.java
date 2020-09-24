/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TriHuynh
 */
@Entity
@Table(name = "ArticleEmotion", catalog = "SocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "ArticleEmotion.findAll", query = "SELECT a FROM ArticleEmotion a"),
  @NamedQuery(name = "ArticleEmotion.findById", query = "SELECT a FROM ArticleEmotion a WHERE a.id = :id"),
  @NamedQuery(name = "ArticleEmotion.findByIsLike", query = "SELECT a FROM ArticleEmotion a WHERE a.isLike = :isLike"),
  @NamedQuery(name = "ArticleEmotion.findByIsDislike", query = "SELECT a FROM ArticleEmotion a WHERE a.isDislike = :isDislike"),
  @NamedQuery(name = "ArticleEmotion.findByCreatedDate", query = "SELECT a FROM ArticleEmotion a WHERE a.createdDate = :createdDate")})
public class ArticleEmotion implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id", nullable = false)
  private Integer id;
  @Column(name = "is_like")
  private Boolean isLike;
  @Column(name = "is_dislike")
  private Boolean isDislike;
  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;
  @JoinColumn(name = "author_email", referencedColumnName = "email")
  @ManyToOne
  private Account authorEmail;
  @JoinColumn(name = "article_id", referencedColumnName = "id")
  @ManyToOne
  private Article articleId;

  public ArticleEmotion() {
  }

  public ArticleEmotion(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Boolean getIsLike() {
    return isLike;
  }

  public void setIsLike(Boolean isLike) {
    this.isLike = isLike;
  }

  public Boolean getIsDislike() {
    return isDislike;
  }

  public void setIsDislike(Boolean isDislike) {
    this.isDislike = isDislike;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Account getAuthorEmail() {
    return authorEmail;
  }

  public void setAuthorEmail(Account authorEmail) {
    this.authorEmail = authorEmail;
  }

  public Article getArticleId() {
    return articleId;
  }

  public void setArticleId(Article articleId) {
    this.articleId = articleId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof ArticleEmotion)) {
      return false;
    }
    ArticleEmotion other = (ArticleEmotion) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "trihk.socialnetwork.entity.ArticleEmotion[ id=" + id + " ]";
  }
  
}
