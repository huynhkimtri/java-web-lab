/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javafx.scene.shape.Arc;
import trihk.socialnetwork.dao.AccountDAO;
import trihk.socialnetwork.dao.ArticleDAO;
import trihk.socialnetwork.dao.ArticleStatusDAO;
import trihk.socialnetwork.entity.Article;

/**
 *
 * @author TriHuynh
 */
public class ArticleService {

  public Article create(String title, String des, String content, String img,
          int statusId, String author) {
    Article article = new Article();
    article.setTitle(title);
    article.setDescription(des);
    article.setContents(content);
    article.setImageUrl(img);
    article.setAuthorEmail(new AccountDAO().getByEmail(author));
    article.setStatusId(new ArticleStatusDAO().getById(statusId));
    article.setNumOfLike(0);
    article.setNumOfDislike(0);
    article.setCreatedDate(BigInteger.valueOf(new Date().getTime()));
    article.setPublishDate(BigInteger.valueOf(new Date().getTime()));
    return new ArticleDAO().create(article);
  }

  public List<Article> getList() {
    List<Article> listOfArticles;
    ArticleDAO dao = new ArticleDAO();
    listOfArticles = dao.listAll();
    System.out.println(Arrays.toString(listOfArticles.toArray()));
    return listOfArticles;
  }
}
