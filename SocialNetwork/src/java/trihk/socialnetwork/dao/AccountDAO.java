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
import trihk.socialnetwork.entity.Account;
import trihk.socialnetwork.utils.DBUtils;

/**
 *
 * @author TriHuynh
 */
public class AccountDAO implements Serializable {

  public Account create(Account account) {
    EntityManager em = DBUtils.getEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(account);
      em.getTransaction().commit();
      return account;
    } catch (Exception e) {
      Logger.getLogger(getClass().getName())
              .log(Level.SEVERE, "exception caught", e);
    } finally {
      em.close();
    }
    return null;
  }

  public Account update(Account account) {
    EntityManager em = DBUtils.getEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(account);
      em.getTransaction().commit();
      return account;
    } catch (Exception e) {
      Logger.getLogger(getClass().getName())
              .log(Level.SEVERE, "exception caught", e);
    } finally {
      em.close();
    }
    return null;
  }

  public Account getByEmail(String email) {
    EntityManager em = DBUtils.getEntityManager();
    try {
      em.getTransaction().begin();
      Account account = (Account) em.createNamedQuery("Account.findByEmail")
              .setParameter("email", email)
              .getSingleResult();
      em.getTransaction().commit();
      return account;
    } catch (Exception e) {
      Logger.getLogger(getClass().getName())
              .log(Level.SEVERE, "exception caught", e);
    } finally {
      em.close();
    }
    return null;
  }

}
