/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import trihk.socialnetwork.dao.AccountDAO;
import trihk.socialnetwork.dao.ArticleDAO;
import trihk.socialnetwork.dao.ArticleStatusDAO;
import trihk.socialnetwork.dao.ArticleCommentDAO;
import trihk.socialnetwork.entity.Account;
import trihk.socialnetwork.entity.Article;
import trihk.socialnetwork.entity.ArticleComment;

/**
 *
 * @author TriHuynh
 */
public class ArticleService {

    public int count(String keyword, int statusId) {
        ArticleDAO dao = new ArticleDAO();
        return dao.getCount(keyword, statusId);
    }

    public Article create(String title, String des, String content, String img,
            int statusId, String author) {
        Article article = new Article();
        LocalDateTime time = LocalDateTime.now();
        article.setTitle(title);
        article.setDescription(des);
        article.setContents(content);
        article.setImageUrl(img);
        article.setAuthorEmail(new AccountDAO().getByEmail(author));
        article.setStatusId(new ArticleStatusDAO().getById(statusId));
        article.setNumOfLike(0);
        article.setNumOfDislike(0);
        article.setCreatedDate(new Date());
        article.setPublishDate(new Date());
        return new ArticleDAO().create(article);
    }

    public void delete(int articleId) {
        ArticleDAO dao = new ArticleDAO();
        Article article = dao.getOne(articleId);
        article.setStatusId(new ArticleStatusDAO().getById(2));
        dao.update(article);
    }

    public List<Article> getList(int size, int page) {
        List<Article> listOfArticles;
        ArticleDAO dao = new ArticleDAO();
        listOfArticles = dao.listAllPagination(size, page);
        return listOfArticles;
    }

    public List<Article> getList(String keyword) {
        List<Article> listOfArticles;
        ArticleDAO dao = new ArticleDAO();
        listOfArticles = dao.listAll(keyword);
        return listOfArticles;
    }

    public List<Article> getListPagination(String keyword, int numOfItems, int pageIndex) {
        List<Article> listOfArticles;
        ArticleDAO dao = new ArticleDAO();
        listOfArticles = dao.listAllPagination(keyword, numOfItems, pageIndex);
        return listOfArticles;
    }

    public Article getOne(int id) {
        return new ArticleDAO().getOne(id);
    }

    public List<ArticleComment> getListComment(int articleId) {
        ArticleCommentDAO dao = new ArticleCommentDAO();
        return dao.getList(articleId, Boolean.FALSE);
    }

    public ArticleComment comment(int articleId, String authEmail, String contents) {
        ArticleDAO dao = new ArticleDAO();
        Article article = dao.getOne(articleId);
        ArticleCommentDAO commentDao = new ArticleCommentDAO();
        ArticleComment comment = new ArticleComment();
        Account acc = new AccountDAO().getByEmail(authEmail);
        comment.setArticleId(article);
        comment.setContents(contents);
        comment.setTime(new Date());
        comment.setIsDelete(Boolean.FALSE);
        comment.setOwnerEmail(acc);
        return commentDao.create(comment);
    }

}
