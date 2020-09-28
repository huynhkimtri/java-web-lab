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
@Table(name = "NotificationType", catalog = "SocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "NotificationType.findAll", query = "SELECT n FROM NotificationType n"),
  @NamedQuery(name = "NotificationType.findById", query = "SELECT n FROM NotificationType n WHERE n.id = :id"),
  @NamedQuery(name = "NotificationType.findByNotiDescription", query = "SELECT n FROM NotificationType n WHERE n.notiDescription = :notiDescription")})
public class NotificationType implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "id", nullable = false)
  private Integer id;
  @Column(name = "noti_description", length = 2147483647)
  private String notiDescription;
  @OneToMany(mappedBy = "typeId")
  private Collection<Notification> notificationCollection;

  public NotificationType() {
  }

  public NotificationType(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNotiDescription() {
    return notiDescription;
  }

  public void setNotiDescription(String notiDescription) {
    this.notiDescription = notiDescription;
  }

  @XmlTransient
  public Collection<Notification> getNotificationCollection() {
    return notificationCollection;
  }

  public void setNotificationCollection(Collection<Notification> notificationCollection) {
    this.notificationCollection = notificationCollection;
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
    if (!(object instanceof NotificationType)) {
      return false;
    }
    NotificationType other = (NotificationType) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "trihk.socialnetwork.entity.NotificationType[ id=" + id + " ]";
  }
  
}
