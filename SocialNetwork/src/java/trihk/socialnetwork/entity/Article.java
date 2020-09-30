/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TriHuynh
 */
@Entity
@Table(name = "Article", catalog = "SocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.getCount", query = "SELECT COUNT(a.id) FROM Article a WHERE a.statusId.id = :id ORDER BY a.createdDate DESC"),
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a ORDER BY a.createdDate DESC"),
    @NamedQuery(name = "Article.findAllByStatus", query = "SELECT a FROM Article a WHERE a.contents LIKE CONCAT('%',:search,'%') AND a.statusId.id = :id ORDER BY a.createdDate DESC"),
    @NamedQuery(name = "Article.findAllByContentsLike",
            query = "SELECT a FROM Article a WHERE a.contents LIKE CONCAT('%',:search,'%') ORDER BY a.createdDate DESC"),
    @NamedQuery(name = "Article.findById", query = "SELECT a FROM Article a WHERE a.id = :id"),
    @NamedQuery(name = "Article.findByTitle", query = "SELECT a FROM Article a WHERE a.title = :title"),
    @NamedQuery(name = "Article.findByDescription", query = "SELECT a FROM Article a WHERE a.description = :description"),
    @NamedQuery(name = "Article.findByImageUrl", query = "SELECT a FROM Article a WHERE a.imageUrl = :imageUrl"),
    @NamedQuery(name = "Article.findByCreatedDate", query = "SELECT a FROM Article a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "Article.findByPublishDate", query = "SELECT a FROM Article a WHERE a.publishDate = :publishDate"),
    @NamedQuery(name = "Article.findByNumOfLike", query = "SELECT a FROM Article a WHERE a.numOfLike = :numOfLike"),
    @NamedQuery(name = "Article.findByNumOfDislike", query = "SELECT a FROM Article a WHERE a.numOfDislike = :numOfDislike"),
    @NamedQuery(name = "Article.findByContents", query = "SELECT a FROM Article a WHERE a.contents = :contents")})
public class Article implements Serializable {

    @OneToMany(mappedBy = "articleId")
    private Collection<ArticleEmotion> articleEmotionCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "title", length = 2147483647)
    private String title;
    @Column(name = "description", length = 2147483647)
    private String description;
    @Column(name = "image_url", length = 2147483647)
    private String imageUrl;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "publish_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;
    @Column(name = "num_of_like")
    private Integer numOfLike;
    @Column(name = "num_of_dislike")
    private Integer numOfDislike;
    @Column(name = "contents", length = 2147483647)
    private String contents;
    @JoinColumn(name = "author_email", referencedColumnName = "email")
    @ManyToOne
    private Account authorEmail;
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne
    private ArticleStatus statusId;

    public Article() {
    }

    public Article(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getNumOfLike() {
        return numOfLike;
    }

    public void setNumOfLike(Integer numOfLike) {
        this.numOfLike = numOfLike;
    }

    public Integer getNumOfDislike() {
        return numOfDislike;
    }

    public void setNumOfDislike(Integer numOfDislike) {
        this.numOfDislike = numOfDislike;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Account getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(Account authorEmail) {
        this.authorEmail = authorEmail;
    }

    public ArticleStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(ArticleStatus statusId) {
        this.statusId = statusId;
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
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trihk.socialnetwork.entity.Article[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<ArticleEmotion> getArticleEmotionCollection() {
        return articleEmotionCollection;
    }

    public void setArticleEmotionCollection(Collection<ArticleEmotion> articleEmotionCollection) {
        this.articleEmotionCollection = articleEmotionCollection;
    }

}
