/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TriHuynh
 */
@Entity
@Table(name = "ArticleStatus", catalog = "SocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "ArticleStatus.findAll", query = "SELECT a FROM ArticleStatus a"),
  @NamedQuery(name = "ArticleStatus.findById", query = "SELECT a FROM ArticleStatus a WHERE a.id = :id"),
  @NamedQuery(name = "ArticleStatus.findByName", query = "SELECT a FROM ArticleStatus a WHERE a.name = :name")})
public class ArticleStatus implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "id", nullable = false)
  private Integer id;
  @Column(name = "name", length = 50)
  private String name;
  @OneToMany(mappedBy = "statusId")
  private Collection<Article> articleCollection;

  public ArticleStatus() {
  }

  public ArticleStatus(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @XmlTransient
  public Collection<Article> getArticleCollection() {
    return articleCollection;
  }

  public void setArticleCollection(Collection<Article> articleCollection) {
    this.articleCollection = articleCollection;
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
    if (!(object instanceof ArticleStatus)) {
      return false;
    }
    ArticleStatus other = (ArticleStatus) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "trihk.socialnetwork.entity.ArticleStatus[ id=" + id + " ]";
  }
  
}
