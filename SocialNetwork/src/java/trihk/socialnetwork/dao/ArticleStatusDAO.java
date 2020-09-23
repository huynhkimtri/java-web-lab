/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import trihk.socialnetwork.entity.ArticleStatus;
import trihk.socialnetwork.utils.DBUtils;

/**
 *
 * @author TriHuynh
 */
public class ArticleStatusDAO {

  public ArticleStatus getById(int id) {
    ArticleStatus status = null;
    EntityManager em = DBUtils.getEntityManager();
    try {
      em.getTransaction().begin();
      status = (ArticleStatus) em.createNamedQuery("ArticleStatus.findById")
              .setParameter("id", id)
              .getSingleResult();
      em.getTransaction().commit();
    } catch (Exception e) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
    } finally {
      em.close();
    }
    return status;
  }
}
