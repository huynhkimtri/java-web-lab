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
import trihk.socialnetwork.entity.Notification;
import trihk.socialnetwork.utils.DBUtils;

/**
 *
 * @author TriHuynh
 */
public class NotificationDAO implements Serializable {

  public Notification create(Notification noti) {
    EntityManager em = DBUtils.getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(noti);
      em.getTransaction().commit();
    } catch (Exception e) {
      Logger.getLogger(getClass().getName())
              .log(Level.SEVERE, "exception caught", e);
    } finally {
      if (em != null) {
        em.close();
      }
    }
    return noti;
  }

  public List<Notification> getListByNoitifier(String email) {
    EntityManager em = DBUtils.getEntityManager();
    List<Notification> listNoti = new ArrayList<>();
    try {
      em.getTransaction().begin();
      listNoti = em.createNamedQuery("Notification.findByNotifirer")
              .setParameter("notifier", email)
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
    return listNoti;
  }

  public Notification getOne(String actor, int articleId) {
    EntityManager em = DBUtils.getEntityManager();
    Notification noti = null;
    try {
      em.getTransaction().begin();
      noti = (Notification) em.createNamedQuery("Notification.findOne")
              .setParameter("actor", actor)
              .setParameter("articleId", articleId)
              .getSingleResult();
      em.getTransaction().commit();
    } catch (Exception e) {
    } finally {
      if (em != null) {
        em.close();
      }
    }
    return noti;
  }

  public Notification update(Notification noti) {
    EntityManager em = DBUtils.getEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(noti);
      em.getTransaction().commit();
    } catch (Exception e) {
      Logger.getLogger(getClass().getName())
              .log(Level.SEVERE, "exception caught", e);
    } finally {
      if (em != null) {
        em.close();
      }
    }
    return noti;
  }
}
