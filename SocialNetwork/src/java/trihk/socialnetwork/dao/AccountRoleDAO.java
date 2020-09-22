/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import trihk.socialnetwork.entity.AccountRole;
import trihk.socialnetwork.utils.DBUtils;

/**
 *
 * @author TriHuynh
 */
public class AccountRoleDAO {

    public AccountRole getById(int id) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            List<AccountRole> roles
                    = em.createNamedQuery("AccountRole.findById")
                            .setParameter("id", id)
                            .getResultList();
            em.getTransaction().commit();
            if (roles.size() > 0) {
                return roles.get(0);
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }
}
