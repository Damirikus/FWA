package edu.school21.cinema.servlets;

import edu.school21.cinema.config.SpringConfig;
import edu.school21.cinema.models.User;
import edu.school21.cinema.models.dto.CaptchaResponseDto;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@WebServlet(name = "SignUp", value = "/signup")
public class SignUpServlet extends HttpServlet {

    private static final String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private RestTemplate restTemplate;
    private UserService userService;
    private UserValidator validator;
    private String captchaSecret;

    @Override
    public void init (ServletConfig config ) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userService = springContext.getBean(UserService.class);
        this.validator = springContext.getBean(UserValidator.class);
        this.restTemplate = springContext.getBean(RestTemplate.class);
        SpringConfig springConfig = springContext.getBean(SpringConfig.class);
        this.captchaSecret = springConfig.captchaSecret;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("currentUser") != null){
            response.sendRedirect("/profile");
            return;
        }
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

        if (!errors.isEmpty()){
            for (String key : errors.keySet()){
                request.setAttribute(key, errors.get(key));
            }
            doGet(request, response);
            return;
        }

        if (!userService.saveUser(user)){
            errors.put("emailError", "User already exist!");
            doGet(request, response);
            return;
        }
        response.sendRedirect("/signin");

    }
}
