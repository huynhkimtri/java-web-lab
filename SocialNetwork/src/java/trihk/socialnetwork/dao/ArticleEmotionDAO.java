/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import trihk.socialnetwork.entity.ArticleEmotion;
import trihk.socialnetwork.utils.DBUtils;

/**
 *
 * @author TriHuynh
 */
public class ArticleEmotionDAO implements Serializable {

    public ArticleEmotion create(ArticleEmotion emotion) {
        EntityManager entityManager = DBUtils.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(emotion);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return emotion;
    }

    public ArticleEmotion update(ArticleEmotion emotion) {
        EntityManager entityManager = DBUtils.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(emotion);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return emotion;
    }

    public boolean remove(ArticleEmotion emotion) {
        boolean isDeleted = false;
        EntityManager em = DBUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            if (!em.contains(emotion)) {
                emotion = em.merge(emotion);
            }
            em.remove(emotion);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName())
                    .log(Level.SEVERE, "exception caught", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return isDeleted;
    }

    public ArticleEmotion getOneLike(String accountEmail, int articleId) {
        EntityManager em = DBUtils.getEntityManager();
        ArticleEmotion emotion = null;
        try {
            em.getTransaction().begin();
            List<ArticleEmotion> list = em.createNamedQuery("ArticleEmotion.findByAccountAndArticleAndLike")
                    .setParameter("email", accountEmail)
                    .setParameter("id", articleId)
                    .getResultList();
            if (list.size() > 0) {
                emotion = list.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return emotion;
    }

    public ArticleEmotion getOneDisLike(String accountEmail, int articleId) {
        EntityManager em = DBUtils.getEntityManager();
        ArticleEmotion emotion = null;
        try {
            em.getTransaction().begin();
            List<ArticleEmotion> list = em.createNamedQuery("ArticleEmotion.findByAccountAndArticleAndDisLike")
                    .setParameter("email", accountEmail)
                    .setParameter("id", articleId)
                    .getResultList();
            if (list.size() > 0) {
                emotion = list.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return emotion;
    }
}
