package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "SignIn", value = "/signin")
public class SignIn extends HttpServlet {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void init (ServletConfig config ) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userService = springContext.getBean(UserService.class);
        this.passwordEncoder = springContext.getBean(PasswordEncoder.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/WEB-INF/templates/signin.html").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.getUser(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())){
            // 1.create session
            // 2. redirect to in
            System.out.println("in");
            return;
        }


        doGet(request, response);
        System.out.println("red");
        // 3. redirect to doGet

    }
}
