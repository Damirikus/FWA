package edu.school21.cinema.servlets;

import edu.school21.cinema.config.SpringConfig;
import edu.school21.cinema.models.ImageInfo;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet(name = "Profile", value = "/profile")
@MultipartConfig
public class ProfileServlet extends HttpServlet {

    private UserService userService;
    private SpringConfig config;

    @Override
    public void init (ServletConfig config ) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userService = springContext.getBean(UserService.class);
        this.config = springContext.getBean(SpringConfig.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("currentUser") == null){
            response.sendRedirect("/main");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/templates/jsp/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Part filePart = request.getPart("file");

            if (StringUtils.isEmpty(filePart.getSubmittedFileName())){
                response.sendRedirect("/profile");
                return;
            }

            String fileShortName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileName = UUID.randomUUID() + "." + fileShortName;

            String path = config.pathToSave + "/" + fileName;

            if (!Files.isDirectory(Paths.get(config.pathToSave))) {
                Files.createDirectories(Paths.get(config.pathToSave));
            }

            filePart.write(path);

            User currentUser = (User) request.getSession().getAttribute("currentUser");

            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setUsr_id(currentUser.getId());
            imageInfo.setName(fileName);
            imageInfo.setSize(Files.size(Paths.get(path)));
            imageInfo.setMime("images/" + FilenameUtils.getExtension(path));
            userService.addImageInfo(imageInfo);

            currentUser.setFilename(fileName);

            request.getSession().setAttribute("currentUser", currentUser);

            response.sendRedirect("/profile");
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            response.sendRedirect("/profile");
        }
    }
}
