/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TemporalType;
import trihk.socialnetwork.entity.Notification;
import trihk.socialnetwork.utils.DBUtils;

/**
 *
 * @author TriHuynh
 */
public class NotificationDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SocialNetworkPU");

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
            listNoti = em.createNamedQuery("Notification.findByNotifier")
                    .setParameter("email", email)
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

    public Notification getOneByNoitifierAndTime(String email, Date time) {
        EntityManager em = DBUtils.getEntityManager();
        Notification noti = null;
        try {
            em.getTransaction().begin();
            List<Notification> listNoti = em.createNamedQuery("Notification.findByActorAndTime")
                    .setParameter("email", email)
                    .setParameter("time", time, TemporalType.TIMESTAMP)
                    .getResultList();
            em.getTransaction().commit();
            if (listNoti.size() > 0) {
                noti = listNoti.get(0);
            }
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

    public Notification getOne(String actorEmail, int articleId, int typeId) {
        EntityManager em = DBUtils.getEntityManager();
        Notification noti = null;
        try {
            em.getTransaction().begin();
            List<Notification> list = em.createNamedQuery("Notification.findByArticleAndActor")
                    .setParameter("actorEmail", actorEmail)
                    .setParameter("articleId", articleId)
                    .setParameter("typeId", typeId)
                    .getResultList();
            if (list.size() > 0) {
                noti = list.get(0);
            }
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

    public boolean delete(Notification noti) {
        boolean isDeleted = false;
        EntityManager em = DBUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            if (!em.contains(noti)) {
                noti = em.merge(noti);
            }
            em.remove(noti);
            em.getTransaction().commit();
            isDeleted = true;
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
}
