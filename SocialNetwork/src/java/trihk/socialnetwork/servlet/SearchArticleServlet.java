/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trihk.socialnetwork.entity.Article;
import trihk.socialnetwork.service.ArticleService;
import trihk.socialnetwork.utils.Constants;

/**
 *
 * @author TriHuynh
 */
@WebServlet(name = "SearchArticleServlet", urlPatterns = {"/SearchArticleServlet"})
public class SearchArticleServlet extends HttpServlet {

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
        String path = "home.jsp";
        String keyword = request.getParameter("keyword").trim();
        String page = request.getParameter("page");
        ArticleService service = new ArticleService();
        int size = service.count(keyword, 1);
        int numOfPages = size / Constants.SIZE_OF_PAGE;
        if (size % Constants.SIZE_OF_PAGE != 0) {
            numOfPages = size / Constants.SIZE_OF_PAGE + 1;
        }
        int pageIndex = 0;
        try {
            if (page != null) {
                pageIndex = Integer.parseInt(page.trim()) - 1;
            }
        } catch (NumberFormatException e) {
            pageIndex = 0;
        }
        List<Article> listOfArticles = service.getListPagination(keyword, Constants.SIZE_OF_PAGE, pageIndex);
        request.setAttribute("LIST_ARTILCES", listOfArticles);
        request.setAttribute("NUMBER_OF_PAGES", numOfPages);
        request.setAttribute("PAGE_INDEX", pageIndex);
        request.setAttribute("CURRENT_PAGE", pageIndex + 1);
        request.setAttribute("LASTED_KEYWORD", keyword);
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
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
