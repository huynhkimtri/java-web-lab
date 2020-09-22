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
import trihk.socialnetwork.service.AccountService;
import trihk.socialnetwork.utils.Constants;

/**
 *
 * @author TriHuynh
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private final String registerPage = "register.jsp";
    private final String homeServlet = "HomeServlet";
    private final int memberRole = 2;

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
        String path = registerPage;
        String msgError = "MSG_ERROR";
        try {
            String email = request.getParameter("txtEmail");
            String pws = request.getParameter("txtPassword");
            String confirmPws = request.getParameter("txtConfirmPassword");
            String fullName = request.getParameter("txtFullName");
            if (pws.equals(confirmPws)) {
                AccountService accountService = new AccountService();
                boolean isExisted = accountService.checkExistEmail(email);
                if (!isExisted) {
                    Account account = accountService.create(email, fullName, pws, memberRole);
                    if (account != null) {
                        HttpSession session = request.getSession();
                        if (session.getAttribute("USER") != null) {
                            session.removeAttribute("USER");
                        }
                        path = homeServlet;
                    }
                } else {
                    request.setAttribute(msgError, Constants.MSG_EXISTED_EMAIL);
                }
            } else {
                request.setAttribute(msgError, Constants.MSG_MATCHING_PWD);
            }
            if (path.equals(registerPage)) {
                request.setAttribute("LASTED_EMAIL", email);
                request.setAttribute("LASTED_FULL_NAME", fullName);
            }
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
