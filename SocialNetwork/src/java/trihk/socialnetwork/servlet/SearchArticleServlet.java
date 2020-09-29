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
import org.eclipse.jdt.internal.compiler.impl.Constant;
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
        String path = "search.jsp";
        String keyword = request.getParameter("keyword");
        String page = request.getParameter("page");
        ArticleService service = new ArticleService();
        List<Article> listOfArticles = service.getList(keyword);
        int numOfPages = listOfArticles.size() / Constants.SIZE_OF_PAGE;
        int pageIndex;
        try {
            pageIndex = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            pageIndex = 1;
        }
        request.setAttribute("LIST_SEARCH_ARTICLES", listOfArticles);
        request.setAttribute("NUMBER_OF_PAGES", numOfPages);
        request.setAttribute("PAGE_INDEX", pageIndex);
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
