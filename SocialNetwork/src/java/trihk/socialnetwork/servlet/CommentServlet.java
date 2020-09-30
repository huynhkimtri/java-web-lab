/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trihk.socialnetwork.entity.Account;
import trihk.socialnetwork.entity.ArticleComment;
import trihk.socialnetwork.service.ArticleService;
import trihk.socialnetwork.service.NotificationService;

/**
 *
 * @author TriHuynh
 */
@WebServlet(name = "CommentServlet", urlPatterns = {"/CommentServlet"})
public class CommentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String contents = request.getParameter("comment");
        try {
            int articleId = Integer.parseInt(request.getParameter("articleId"));
            String notifierEmail = request.getParameter("notifierEmail");
            HttpSession session = request.getSession();
            Account user = (Account) session.getAttribute("USER");
            String email = user.getEmail();
            int role = user.getRoleId().getId();
            if (role == 2) {
                ArticleService service = new ArticleService();
                NotificationService notiService = new NotificationService();
                ArticleComment comment = service.comment(articleId, email, contents);
                if (comment != null && !email.equals(notifierEmail)) {
                    int typeNoti = 1; // comment has type equals 1 in database;
                    notiService.notify(articleId, typeNoti, email, notifierEmail);
                }
            }
            String urlRewrite = "MainController?action=view&id=" + String.valueOf(articleId);
            response.sendRedirect(urlRewrite);
        } catch (NullPointerException e) {
            response.sendRedirect("./");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
