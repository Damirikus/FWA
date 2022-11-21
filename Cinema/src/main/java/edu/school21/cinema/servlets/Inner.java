package edu.school21.cinema.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Inner", value = "/inner")
public class Inner extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("We are doGet InServlet");
        if (request.getSession().getAttribute("currentUser") == null){
            response.sendRedirect("/main");
            System.out.println("IN redirect main");
            return;
        }

        Long id = (Long) request.getSession().getAttribute("currentUser");
//        User user =

        System.out.println("IN inner");
        request.getRequestDispatcher("/WEB-INF/templates/html/inner.html").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
