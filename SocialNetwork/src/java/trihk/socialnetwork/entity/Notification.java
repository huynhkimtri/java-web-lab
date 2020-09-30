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
@Table(name = "Notification", catalog = "SocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
    @NamedQuery(name = "Notification.findById", query = "SELECT n FROM Notification n WHERE n.id = :id"),
    @NamedQuery(name = "Notification.findByArticleAndActor",
            query = "SELECT n FROM Notification n WHERE n.actor.email = :actorEmail AND n.articleId.id = :articleId AND n.typeId.id = :typeId"),
    @NamedQuery(name = "Notification.findByTime", query = "SELECT n FROM Notification n WHERE n.time = :time"),
    @NamedQuery(name = "Notification.findByActorAndTime",
            query = "SELECT n FROM Notification n WHERE n.actor.email = :email AND n.time >= :time"),
    @NamedQuery(name = "Notification.findByNotifier",
            query = "SELECT n FROM Notification n WHERE n.notifier.email = :email ORDER BY n.time DESC")
})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "actor", referencedColumnName = "email")
    @ManyToOne
    private Account actor;
    @JoinColumn(name = "notifier", referencedColumnName = "email")
    @ManyToOne
    private Account notifier;
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    @ManyToOne
    private Article articleId;
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @ManyToOne
    private NotificationType typeId;

    public Notification() {
    }

    public Notification(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Account getActor() {
        return actor;
    }

    public void setActor(Account actor) {
        this.actor = actor;
    }

    public Account getNotifier() {
        return notifier;
    }

    public void setNotifier(Account notifier) {
        this.notifier = notifier;
    }

    public Article getArticleId() {
        return articleId;
    }

    public void setArticleId(Article articleId) {
        this.articleId = articleId;
    }

    public NotificationType getTypeId() {
        return typeId;
    }

    public void setTypeId(NotificationType typeId) {
        this.typeId = typeId;
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
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trihk.socialnetwork.entity.Notification[ id=" + id + " ]";
    }

}
