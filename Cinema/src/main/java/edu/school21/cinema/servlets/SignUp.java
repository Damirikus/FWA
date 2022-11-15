package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.dto.CaptchaResponseDto;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserValidator;
import edu.school21.cinema.services.UserValidatorImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@WebServlet(name = "SignUp", value = "/signup")
public class SignUp extends HttpServlet {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private RestTemplate restTemplate;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private UserValidator validator;
    private String captchaSecret;

    @Override
    public void init (ServletConfig config ) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userService = springContext.getBean(UserService.class);
        this.passwordEncoder = springContext.getBean(PasswordEncoder.class);
        this.validator = springContext.getBean(UserValidator.class);
        this.restTemplate = springContext.getBean(RestTemplate.class);
        this.captchaSecret = springContext.getBean(String.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/templates/jsp/signup.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setPhone(request.getParameter("phone"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        String password2 = request.getParameter("password2");
        String captchaResponse = request.getParameter("g-recaptcha-response");

        System.out.println("CAPTHA" + captchaSecret);

        String url = String.format(CAPTCHA_URL, captchaSecret, captchaResponse);
        CaptchaResponseDto res = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        if (res == null || !res.isSuccess()){
            request.setAttribute("captchaError", "Click: I'm not a robot");
            doGet(request, response);
            return;
        }

        if (!StringUtils.equals(user.getPassword(), password2)){
            request.setAttribute("password2Error", "Passwords are not equal!");
            doGet(request, response);
            return;
        }

        Map<String, String> errors = validator.validate(user);

        if (errors != null){
            for (String key : errors.keySet()){
                request.setAttribute(key, errors.get(key));
            }
            doGet(request, response);
            return;
        }

        userService.saveUser(user);
        response.sendRedirect("/signin");

    }
}
