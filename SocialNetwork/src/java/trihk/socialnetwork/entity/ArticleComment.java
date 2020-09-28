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
@Table(name = "ArticleComment", catalog = "SocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "ArticleComment.findAll", query = "SELECT a FROM ArticleComment a"),
  @NamedQuery(name = "ArticleComment.findById", query = "SELECT a FROM ArticleComment a WHERE a.id = :id"),
  @NamedQuery(name = "ArticleComment.findByContents", query = "SELECT a FROM ArticleComment a WHERE a.contents = :contents"),
  @NamedQuery(name = "ArticleComment.findByTime", query = "SELECT a FROM ArticleComment a WHERE a.time = :time"),
  @NamedQuery(name = "ArticleComment.findByIsDelete", query = "SELECT a FROM ArticleComment a WHERE a.isDelete = :isDelete"),
  @NamedQuery(name = "ArticleComment.findAllByArticle", 
          query = "SELECT a FROM ArticleComment a WHERE a.articleId.id = :articleId AND a.isDelete = :isDelete  ORDER BY a.time"),
  @NamedQuery(name = "ArticleComment.countByArticle",
          query = "SELECT COUNT(a.id) FROM ArticleComment a WHERE a.articleId = :articleId AND a.isDelete = :isDelete ORDER BY a.time"),
  @NamedQuery(name = "ArticleComment.findAllByArticleAndOwner",
          query = "SELECT a FROM ArticleComment a WHERE a.articleId = :articleId AND a.ownerEmail = :ownerEmail AND a.isDelete = :isDelete ORDER BY a.time")
})
public class ArticleComment implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id", nullable = false)
  private Integer id;
  @Column(name = "contents", length = 2147483647)
  private String contents;
  @Column(name = "time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date time;
  @Column(name = "is_delete")
  private Boolean isDelete;
  @JoinColumn(name = "owner_email", referencedColumnName = "email")
  @ManyToOne
  private Account ownerEmail;
  @JoinColumn(name = "article_id", referencedColumnName = "id")
  @ManyToOne
  private Article articleId;

  public ArticleComment() {
  }

  public ArticleComment(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Boolean getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Boolean isDelete) {
    this.isDelete = isDelete;
  }

  public Account getOwnerEmail() {
    return ownerEmail;
  }

  public void setOwnerEmail(Account ownerEmail) {
    this.ownerEmail = ownerEmail;
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
    if (!(object instanceof ArticleComment)) {
      return false;
    }
    ArticleComment other = (ArticleComment) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "trihk.socialnetwork.entity.ArticleComment[ id=" + id + " ]";
  }

}
