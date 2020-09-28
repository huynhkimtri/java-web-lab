/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.dao;

import javax.persistence.EntityManager;
import trihk.socialnetwork.entity.NotificationType;
import trihk.socialnetwork.utils.DBUtils;

/**
 *
 * @author TriHuynh
 */
public class NotificationTypeDAO {

  public NotificationType getOneById(int id) {
    EntityManager em = DBUtils.getEntityManager();
    NotificationType type = null;
    try {
      em.getTransaction().begin();
      type = (NotificationType) em.createNamedQuery("NotificationType.findById")
              .setParameter("id", id)
              .getSingleResult();
      em.getTransaction().commit();
    } catch (Exception e) {
    } finally {
      if (em != null) {
        em.close();
      }
    }
    return type;
  }
}
