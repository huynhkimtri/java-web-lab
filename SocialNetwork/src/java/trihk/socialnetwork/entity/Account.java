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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "Account", catalog = "SocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
  @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
  @NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a WHERE a.name = :name"),
  @NamedQuery(name = "Account.findByIsActive", query = "SELECT a FROM Account a WHERE a.isActive = :isActive")})
public class Account implements Serializable {

  @OneToMany(mappedBy = "accountEmail")
  private Collection<ArticleEmotion> articleEmotionCollection;

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "email", nullable = false, length = 50)
  private String email;
  @Column(name = "name", length = 50)
  private String name;
  @Lob
  @Column(name = "encrypt_password", length = 2147483647)
  private String encryptPassword;
  @Column(name = "is_active")
  private Boolean isActive;
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  @ManyToOne
  private AccountRole roleId;
  @OneToMany(mappedBy = "authorEmail")
  private Collection<Article> articleCollection;

  public Account() {
  }

  public Account(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEncryptPassword() {
    return encryptPassword;
  }

  public void setEncryptPassword(String encryptPassword) {
    this.encryptPassword = encryptPassword;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public AccountRole getRoleId() {
    return roleId;
  }

  public void setRoleId(AccountRole roleId) {
    this.roleId = roleId;
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
    hash += (email != null ? email.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Account)) {
      return false;
    }
    Account other = (Account) object;
    if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "trihk.socialnetwork.entity.Account[ email=" + email + " ]";
  }

  @XmlTransient
  public Collection<ArticleEmotion> getArticleEmotionCollection() {
    return articleEmotionCollection;
  }

  public void setArticleEmotionCollection(Collection<ArticleEmotion> articleEmotionCollection) {
    this.articleEmotionCollection = articleEmotionCollection;
  }
  
}
