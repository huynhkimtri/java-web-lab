/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.dao;

import java.io.Serializable;
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

  public ArticleEmotion getOne(String accountEmail, int articleId) {
    EntityManager em = DBUtils.getEntityManager();
    ArticleEmotion emotion = null;
    try {
      em.getTransaction().begin();
      emotion = (ArticleEmotion) em.createNamedQuery("ArticleEmotion.findByAccountAndArticle")
              .setParameter("email", accountEmail)
              .setParameter("id", articleId)
              .getSingleResult();
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
