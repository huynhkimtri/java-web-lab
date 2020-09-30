/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import trihk.socialnetwork.entity.Article;
import trihk.socialnetwork.utils.DBUtils;

/**
 *
 * @author TriHuynh
 */
public class ArticleDAO implements Serializable {

    /**
     * Create article using JPA
     *
     * @param article
     * @return
     */
    public Article create(Article article) {
        EntityManager entityManager = DBUtils.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(article);
            entityManager.getTransaction().commit();
            return article;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    /**
     * Update article using JPA
     *
     * @param article
     * @return
     */
    public Article update(Article article) {
        EntityManager entityManager = DBUtils.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(article);
            entityManager.getTransaction().commit();
            return article;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    public List<Article> listAllPagination(String keyword, int numOfItem, int pageIndex) {
        List<Article> list = null;
        EntityManager entityManager = DBUtils.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            list = entityManager.createNamedQuery("Article.findAllByContentsLike")
                    .setParameter("search", '%' + keyword + '%')
                    .setMaxResults(numOfItem)
                    .setFirstResult(pageIndex * numOfItem)
                    .getResultList();
            if (list.size() > 0) {
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    if (((Article) iterator.next()).getStatusId().getId() == 2) {
                        iterator.remove();
                    }
                }
                System.out.println(Arrays.toString(list.toArray()));
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return list;
    }

    public List<Article> listAllPagination(int numOfItem, int pageIndex) {
        List<Article> list = null;
        EntityManager entityManager = DBUtils.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            list = entityManager.createNamedQuery("Article.findAll")
                    .setMaxResults(numOfItem)
                    .setFirstResult((pageIndex - 1) * numOfItem)
                    .getResultList();
            if (list.size() > 0) {
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    if (((Article) iterator.next()).getStatusId().getId() == 2) {
                        iterator.remove();
                    }
                }
                System.out.println(Arrays.toString(list.toArray()));
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return list;
    }

    public List<Article> listAll() {
        List<Article> list = null;
        EntityManager entityManager = DBUtils.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            list = entityManager.createNamedQuery("Article.findAll")
                    .getResultList();
            if (list.size() > 0) {
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    if (((Article) iterator.next()).getStatusId().getId() == 2) {
                        iterator.remove();
                    }
                }
                System.out.println(Arrays.toString(list.toArray()));
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return list;
    }

    public List<Article> listAll(String keyword) {
        List<Article> list = null;
        EntityManager entityManager = DBUtils.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            list = entityManager.createNamedQuery("Article.findAllByContentsLike")
                    .setParameter("search", keyword)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return list;
    }

    public Article getOne(int id) {
        Article article = null;
        EntityManager entityManager = DBUtils.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            article = (Article) entityManager.createNamedQuery("Article.findById")
                    .setParameter("id", id)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return article;
    }

    public int getCount(String keyword, int status) {
        int count = 0;
        EntityManager entityManager = DBUtils.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Article> list = entityManager.createNamedQuery("Article.findAllByStatus")
                    .setParameter("search", keyword)
                    .setParameter("id", status)
                    .getResultList();
            count = list.size();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return count;
    }
}
