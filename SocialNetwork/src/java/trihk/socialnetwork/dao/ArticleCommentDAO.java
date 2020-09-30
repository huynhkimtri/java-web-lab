/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import trihk.socialnetwork.entity.ArticleComment;
import trihk.socialnetwork.utils.DBUtils;

/**
 *
 * @author TriHuynh
 */
public class ArticleCommentDAO implements Serializable {

    public ArticleComment create(ArticleComment comment) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(comment);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName())
                    .log(Level.SEVERE, "exception caught", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return comment;
    }

    public ArticleComment update(ArticleComment comment) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(comment);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName())
                    .log(Level.SEVERE, "exception caught", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return comment;
    }

    public List<ArticleComment> getList(int articleId, boolean isDelete) {
        EntityManager em = DBUtils.getEntityManager();
        List<ArticleComment> comments = new ArrayList<>();
        try {
            em.getTransaction().begin();
            comments = em.createNamedQuery("ArticleComment.findAllByArticle")
                    .setParameter("isDelete", Boolean.FALSE)
                    .setParameter("articleId", articleId)
                    .getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName())
                    .log(Level.SEVERE, "exception caught", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return comments;
    }

    public List<ArticleComment> getList(int articleId, String author, boolean isDelete) {
        EntityManager em = DBUtils.getEntityManager();
        List<ArticleComment> comments = new ArrayList<>();
        try {
            em.getTransaction().begin();
            comments = em.createNamedQuery("ArticleComment.findAllByArticleAndOwner")
                    .setParameter("isDelete", isDelete)
                    .setParameter("ownerEmail", author)
                    .setParameter("articleId", articleId)
                    .getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName())
                    .log(Level.SEVERE, "exception caught", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return comments;
    }

    public ArticleComment getOne(int commentId) {
        EntityManager em = DBUtils.getEntityManager();
        ArticleComment comment = null;
        try {
            comment = em.find(ArticleComment.class, commentId);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName())
                    .log(Level.SEVERE, "exception caught", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return comment;
    }
}
