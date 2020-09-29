/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.service;

import java.util.Date;
import java.util.Objects;
import trihk.socialnetwork.dao.AccountDAO;
import trihk.socialnetwork.dao.ArticleDAO;
import trihk.socialnetwork.dao.ArticleEmotionDAO;
import trihk.socialnetwork.entity.Article;
import trihk.socialnetwork.entity.ArticleEmotion;

/**
 *
 * @author TriHuynh
 */
public class EmotionService {

  /**
   *
   * @param dao
   * @param emotion
   */
  private void update(ArticleEmotionDAO dao, ArticleEmotion emotion) {
    boolean isLike = emotion.getIsLike();
    boolean isDisLike = emotion.getIsDislike();
    if (!Objects.equals(isLike, isDisLike)) {
      emotion.setIsLike(isDisLike);
      emotion.setIsDislike(isLike);
      emotion.setTime(new Date());
      dao.update(emotion);
    }
  }

  /**
   *
   * @param isLike
   * @param article
   * @param authEmail
   */
  private void create(ArticleEmotionDAO dao, boolean isLike, Article article, String authEmail) {
    ArticleEmotion emotion = new ArticleEmotion();
    emotion.setAccountEmail(new AccountDAO().getByEmail(authEmail));
    emotion.setArticleId(article);
    emotion.setTime(new Date());
    emotion.setIsLike(isLike);
    emotion.setIsDislike(!isLike);
    dao.create(emotion);
  }

  private boolean checkExistedAndSave(int articleId, String authEmail, Article article, boolean isLike) {
    ArticleEmotion emotion;
    ArticleEmotionDAO dao = new ArticleEmotionDAO();
    emotion = dao.getOne(authEmail, articleId);
    if (emotion != null) {
      if ((isLike && isLike != emotion.getIsLike()) // isLike=1, getIsLike=0, getIsDislike=1
              || (!isLike && isLike == emotion.getIsDislike())) {
        this.update(dao, emotion);
        return true;
      }
    } else {
      this.create(dao, isLike, article, authEmail);
      return true;
    }
    return false;
  }

  /**
   *
   * @param articleId
   * @param authEmail
   */
  public void like(int articleId, String authEmail) {
    ArticleDAO dao = new ArticleDAO();
    Article article = dao.getOne(articleId);
    int like = article.getNumOfLike() + 1;
    int dislike = article.getNumOfDislike() > 0 ? article.getNumOfDislike() - 1 : 0;
    article.setNumOfLike(like);
    article.setNumOfDislike(dislike);
    boolean check = this.checkExistedAndSave(articleId, authEmail, article, Boolean.TRUE);
    if (check) {
      dao.update(article);
    }
  }

  /**
   *
   * @param articleId
   * @param authEmail
   */
  public void dislike(int articleId, String authEmail) {
    ArticleDAO dao = new ArticleDAO();
    Article article = dao.getOne(articleId);
    int dislike = article.getNumOfDislike() + 1;
    int like = article.getNumOfLike() > 0 ? article.getNumOfLike() - 1 : 0;
    article.setNumOfLike(like);
    article.setNumOfDislike(dislike);
    boolean check = this.checkExistedAndSave(articleId, authEmail, article, Boolean.FALSE);
    if (check) {
      dao.update(article);
    }
  }
}
