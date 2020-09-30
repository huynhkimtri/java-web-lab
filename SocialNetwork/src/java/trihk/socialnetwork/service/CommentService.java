/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.service;

import trihk.socialnetwork.dao.ArticleCommentDAO;
import trihk.socialnetwork.entity.ArticleComment;

/**
 *
 * @author TriHuynh
 */
public class CommentService {

    public boolean delete(ArticleComment comment) {
        boolean isDeleted = false;
        comment.setIsDelete(Boolean.TRUE);
        ArticleCommentDAO dao = new ArticleCommentDAO();
        if (dao.update(comment) != null) {
            isDeleted = true;
        }
        return isDeleted;
    }

    public ArticleComment getOne(int id) {
        ArticleComment comment;
        ArticleCommentDAO dao = new ArticleCommentDAO();
        comment = dao.getOne(id);
        return comment;
    }
}
