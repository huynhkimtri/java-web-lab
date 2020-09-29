/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.service;

import java.util.Date;
import trihk.socialnetwork.dao.AccountDAO;
import trihk.socialnetwork.dao.ArticleCommentDAO;
import trihk.socialnetwork.dao.ArticleDAO;
import trihk.socialnetwork.dao.NotificationDAO;
import trihk.socialnetwork.dao.NotificationTypeDAO;
import trihk.socialnetwork.entity.ArticleComment;
import trihk.socialnetwork.entity.Notification;

/**
 *
 * @author TriHuynh
 */
public class NotificationService {

    public void notify(int articleId, int type, String actorEmail, String notifierEmail) {
        NotificationDAO notiDao = new NotificationDAO();
        Notification currentNoti;
        Notification newNoti;
        switch (type) {
            case 1:
                this.newNoti(articleId, type, actorEmail, notifierEmail);
                break;
            case 2:
                currentNoti = notiDao.getOne(actorEmail, articleId, type);
                if (currentNoti == null) {
                    newNoti = notiDao.getOne(actorEmail, articleId, 3);
                    if (newNoti != null) {
                        newNoti.setTime(new Date());
                        newNoti.setTypeId(new NotificationTypeDAO().getOneById(type));
                        notiDao.update(newNoti);
                    } else {
                        this.newNoti(articleId, type, actorEmail, notifierEmail);
                    }
                }
                break;
            case 3:
                currentNoti = notiDao.getOne(actorEmail, articleId, type);
                if (currentNoti == null) {
                    newNoti = notiDao.getOne(actorEmail, articleId, 2);
                    if (newNoti != null) {
                        newNoti.setTime(new Date());
                        newNoti.setTypeId(new NotificationTypeDAO().getOneById(type));
                        notiDao.update(newNoti);
                    } else {
                        this.newNoti(articleId, type, actorEmail, notifierEmail);
                    }
                }
                break;
            default:
                break;
        }
    }

    private Notification newNoti(int articleId, int type, String actorEmail, String notifierEmail) {
        Notification noti = new Notification();
        NotificationDAO notiDao = new NotificationDAO();
        noti.setArticleId(new ArticleDAO().getOne(articleId));
        noti.setActor(new AccountDAO().getByEmail(actorEmail));
        noti.setNotifier(new AccountDAO().getByEmail(notifierEmail));
        noti.setTime(new Date());
        noti.setTypeId(new NotificationTypeDAO().getOneById(type));
        return notiDao.create(noti);
    }

    public boolean deleteNotiByComment(int commentId) {
        NotificationDAO dao = new NotificationDAO();
        ArticleComment comment = new ArticleCommentDAO().getOne(commentId);
        Notification noti = dao.getOneByNoitifierAndTime(comment.getOwnerEmail().getEmail(), comment.getTime());
        return dao.delete(noti);
    }
}
