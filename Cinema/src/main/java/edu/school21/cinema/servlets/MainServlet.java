package edu.school21.cinema.servlets;

import edu.school21.cinema.listeners.CinemaContextListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JavaxServletWebApplication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Main", value = "/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("currentUser") != null){
            response.sendRedirect("/inner");
            System.out.println("main redirect in");
            return;
        }
        System.out.println("main main");
        request.getRequestDispatcher("/WEB-INF/templates/html/main.html").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
