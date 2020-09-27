/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import trihk.socialnetwork.dao.AccountDAO;
import trihk.socialnetwork.dao.ArticleDAO;
import trihk.socialnetwork.dao.ArticleEmotionDAO;
import trihk.socialnetwork.dao.ArticleStatusDAO;
import trihk.socialnetwork.dao.CommentDAO;
import trihk.socialnetwork.entity.Account;
import trihk.socialnetwork.entity.Article;
import trihk.socialnetwork.entity.ArticleEmotion;
import trihk.socialnetwork.entity.Comment;

/**
 *
 * @author TriHuynh
 */
public class ArticleService {

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

  public List<Article> getList() {
    List<Article> listOfArticles;
    ArticleDAO dao = new ArticleDAO();
    listOfArticles = dao.listAll();
    System.out.println(Arrays.toString(listOfArticles.toArray()));
    return listOfArticles;
  }

  public Article getOne(int id) {
    return new ArticleDAO().getOne(id);
  }

  private void createEmotion(boolean isLike, Article article, int articleId, String authEmail) {
    Account acc = new AccountDAO().getByEmail(authEmail);
    ArticleEmotion emotion = new ArticleEmotion();
    emotion.setAuthorEmail(acc);
    emotion.setArticleId(article);
    emotion.setCreatedDate(new Date());
    emotion.setIsLike(isLike);
    emotion.setIsDislike(!isLike);
    new ArticleEmotionDAO().create(emotion);
  }

  public void like(int articleId, String authEmail) {
    ArticleDAO dao = new ArticleDAO();
    Article article = dao.getOne(articleId);
    int like = article.getNumOfLike() + 1;
    int dislike = article.getNumOfDislike() > 0 ? article.getNumOfDislike() - 1 : 0;
    article.setNumOfLike(like);
    article.setNumOfDislike(dislike);
    createEmotion(Boolean.TRUE, article, articleId, authEmail);
    dao.update(article);
  }

  public void dislike(int articleId, String authEmail) {
    ArticleDAO dao = new ArticleDAO();
    Article article = dao.getOne(articleId);
    int dislike = article.getNumOfDislike() + 1;
    int like = article.getNumOfLike() > 0 ? article.getNumOfLike() - 1 : 0;
    article.setNumOfLike(like);
    article.setNumOfDislike(dislike);
    createEmotion(Boolean.FALSE, article, articleId, authEmail);
    dao.update(article);
  }

  public Comment comment(int articleId, String authEmail, String contents) {
    ArticleDAO dao = new ArticleDAO();
    Article article = dao.getOne(articleId);
    CommentDAO commentDao = new CommentDAO();
    Comment comment = new Comment();
    Account acc = new AccountDAO().getByEmail(authEmail);
    comment.setArticle(article);
    comment.setContents(contents);
    comment.setCreatedDate(new Date());
    comment.setIsDelete(Boolean.FALSE);
    comment.setOwner(acc);
    return commentDao.create(comment);
  }
}
