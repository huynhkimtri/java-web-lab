/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trihk.socialnetwork.entity.Account;
import trihk.socialnetwork.entity.Article;
import trihk.socialnetwork.service.ArticleService;
import trihk.socialnetwork.utils.Constants;

/**
 *
 * @author TriHuynh
 */
@WebServlet(name = "CreateArticleServlet", urlPatterns = {"/CreateArticleServlet"})
public class CreateArticleServlet extends HttpServlet {

  private final String IMG_DEFAULT = "uxart.io/downloads/openlist-html/all-template/images/post-8.jpg";

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
    String path = "create-article.jsp";
    int newStatus = 1;
    try {
      String title = request.getParameter("txtTitle").trim();
      String description = request.getParameter("txtDescription").trim();
      String imageUrl = request.getParameter("txtImageUrl").trim();
      if (imageUrl.length() < 0) {
        imageUrl = IMG_DEFAULT;
      }
      String content = request.getParameter("txtContent").trim();
      HttpSession session = request.getSession();
      String email = ((Account) session.getAttribute("USER")).getEmail();
      ArticleService service = new ArticleService();
      Article article = service.create(title, description, content, imageUrl, newStatus, email);
      if (article != null) {
        request.setAttribute("MSG_SUCCESS", Constants.MSG_SUCCESS);
      } else {
        request.setAttribute("MSG_FAIL", Constants.MSG_FAIL);
      }
    } catch (Exception e) {
    } finally {
      RequestDispatcher dispatcher = request.getRequestDispatcher(path);
      dispatcher.forward(request, response);
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
