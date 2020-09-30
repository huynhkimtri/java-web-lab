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

    private void remove(ArticleEmotionDAO dao, ArticleEmotion emotion) {
//        dao.remove()
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
        emotion = dao.getOneLike(authEmail, articleId);
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
     * @return
     */
    public boolean like(int articleId, String authEmail) {
        boolean isNotify;
        ArticleEmotionDAO emotionDao = new ArticleEmotionDAO();
        ArticleEmotion likeEmotion = emotionDao.getOneLike(authEmail, articleId);
        ArticleEmotion dislikeEmotion = emotionDao.getOneDisLike(authEmail, articleId);
        ArticleDAO dao = new ArticleDAO();
        Article article = dao.getOne(articleId);
        if (likeEmotion != null) { // đã like rồi -> remove -> không like nữa
            int like = article.getNumOfLike() > 0 ? article.getNumOfLike() - 1 : 0; // trừ lượt like
            article.setNumOfLike(like); // cập nhật lượt like mới
            emotionDao.remove(likeEmotion);
            isNotify = false;
        } else { // chưa like -> create -> tăng like cho bài viết
            // kiểm tra có dislike hay không
            if (dislikeEmotion != null) { // đã dislike bài viết -> không dislike nữa
                int dislike = article.getNumOfDislike() > 0 ? article.getNumOfDislike() - 1 : 0;
                article.setNumOfDislike(dislike);
                emotionDao.remove(dislikeEmotion);
            }
            int like = article.getNumOfLike() + 1; // tăng lượt like
            article.setNumOfLike(like); // cập nhật lượt like mới
            this.create(emotionDao, Boolean.TRUE, article, authEmail);
            isNotify = true;
        }
        dao.update(article);
        return isNotify;
    }

    /**
     *
     * @param articleId
     * @param authEmail
     * @return
     */
    public boolean dislike(int articleId, String authEmail) {
        boolean isNotify;
        ArticleEmotionDAO emotionDao = new ArticleEmotionDAO();
        ArticleEmotion likeEmotion = emotionDao.getOneLike(authEmail, articleId);
        ArticleEmotion dislikeEmotion = emotionDao.getOneDisLike(authEmail, articleId);
        ArticleDAO dao = new ArticleDAO();
        Article article = dao.getOne(articleId);
        if (dislikeEmotion != null) { // đã dislike rồi -> remove -> không dislike nữa
            int dislike = article.getNumOfDislike() > 0 ? article.getNumOfDislike() - 1 : 0; // trừ lượt dislike
            article.setNumOfDislike(dislike); // cập nhật lượt dislike mới
            emotionDao.remove(dislikeEmotion); // remove
            isNotify = false;
        } else { // chưa dislike -> create -> tăng dislike cho bài viết
            // kiểm tra có like hay không
            if (likeEmotion != null) { // đã like bài viết -> không like nữa -> remove
                int like = article.getNumOfLike() > 0 ? article.getNumOfLike() - 1 : 0;
                article.setNumOfLike(like); // cập nhật lượt like
                emotionDao.remove(likeEmotion); // remove
            }
            int dislike = article.getNumOfDislike() + 1; // tăng lượt dislike
            article.setNumOfDislike(dislike); // cập nhật lượt dislike mới
            this.create(emotionDao, Boolean.FALSE, article, authEmail);
            isNotify = true;
        }
        dao.update(article);
        return isNotify;
    }
}
