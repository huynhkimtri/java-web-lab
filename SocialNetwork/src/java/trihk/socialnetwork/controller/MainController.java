/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TriHuynh
 */
public class MainController extends HttpServlet {

  private final String ACTION_LOGIN = "login";
  private final String ACTION_REGISTER = "register";
  private final String ACTION_LOGOUT = "logout";
  private final String ACTION_HOME = "home";
  private final String ACTION_CREATE = "create-article";
  private final String ACTION_DETAIL = "article-detail";
  private final String ACTION_VIEW = "view";
  private final String ACTION_EMOTION = "emotion";
  private final String ACTION_COMMENT = "comment";

  private final String homeServlet = "HomeServlet";
  private final String homeAdminServlet = "HomeAdminServlet";
  private final String loginServlet = "LoginServlet";
  private final String registerServlet = "RegisterServlet";
  private final String logoutServlet = "LogoutServlet";
  private final String createArticleServlet = "CreateArticleServlet";
  private final String detailArticleServlet = "ViewArticleServlet";
  private final String emotionArticleServlet = "EmotionServlet";
  private final String commentArticleServlet = "CommentServlet";

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
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      String url = homeServlet;
      String action = request.getParameter("action");
      if (action != null) {
        switch (action) {
          case ACTION_HOME:
            url = homeServlet;
            break;
          case ACTION_LOGIN:
            url = loginServlet;
            break;
          case ACTION_REGISTER:
            url = registerServlet;
            break;
          case ACTION_LOGOUT:
            url = logoutServlet;
            break;
          case ACTION_CREATE:
            url = createArticleServlet;
            break;
          case ACTION_VIEW:
            url = detailArticleServlet;
            break;
          case ACTION_EMOTION:
            url = emotionArticleServlet;
            break;
          case ACTION_COMMENT:
            url = commentArticleServlet;
            break;
          default:
            break;
        }
      }
      RequestDispatcher dispatcher = request.getRequestDispatcher(url);
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
