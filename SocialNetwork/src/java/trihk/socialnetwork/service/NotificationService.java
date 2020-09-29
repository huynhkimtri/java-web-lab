/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.service;

import java.util.Date;
import trihk.socialnetwork.dao.AccountDAO;
import trihk.socialnetwork.dao.ArticleDAO;
import trihk.socialnetwork.dao.NotificationDAO;
import trihk.socialnetwork.dao.NotificationTypeDAO;
import trihk.socialnetwork.entity.Notification;

/**
 *
 * @author TriHuynh
 */
public class NotificationService {

  public void notify(int articleId, int type, String actorEmail, String notifierEmail) {
    NotificationDAO notiDao = new NotificationDAO();
    Notification noti = notiDao.getOne(actorEmail, articleId);
    if (noti != null) {
      if (noti.getTypeId().getId() == 2 || noti.getTypeId().getId() == 3) {
        noti.setTime(new Date());
        noti.setTypeId(new NotificationTypeDAO().getOneById(type));
        notiDao.update(noti);
      }
    } else {
      newNoti(articleId, type, actorEmail, notifierEmail);
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
}
