/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author TriHuynh
 */
public class DBUtils {

  private static EntityManagerFactory managerFactory;
  private static final String SOCIAL_NETWORK_PU = "SocialNetworkPU";

  public static EntityManager getEntityManager() {
    if (managerFactory == null) {
      managerFactory = Persistence.createEntityManagerFactory(SOCIAL_NETWORK_PU);
    }
    return managerFactory.createEntityManager();
  }
}
