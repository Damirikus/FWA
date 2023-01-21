package edu.school21.cinema.servlets;

import edu.school21.cinema.config.SpringConfig;
import edu.school21.cinema.models.ImageInfo;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        request.getRequestDispatcher("/WEB-INF/templates/jsp/profile.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Part filePart = request.getPart("file");
            String fileShortName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            String fileName = UUID.randomUUID() + "." + fileShortName;

            String path = config.pathToSave + "/" + fileName;

            if (!Files.isDirectory(Paths.get(config.pathToSave))) {
                Files.createDirectories(Paths.get(config.pathToSave));
            }

            try (OutputStream outputStream = new FileOutputStream(path);
                 InputStream fileContent = filePart.getInputStream()) {
                while (fileContent.available() > 0){
                    outputStream.write(fileContent.read());
                }
            } catch (Exception e){
                e.printStackTrace();
                response.sendRedirect("/profile");
            }

            User currentUser = (User) request.getSession().getAttribute("currentUser");

            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setUsr_id(currentUser.getId());
            imageInfo.setName(fileName);
            imageInfo.setSize(Files.size(Paths.get(path)));
            imageInfo.setMime("images/" + FilenameUtils.getExtension(path));
            userService.addImageInfo(imageInfo);


            User user = userService.getUserById(currentUser.getId());
            request.getSession().setAttribute("currentUser", user);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/profile");
        }
        response.sendRedirect("/profile");
//        request.getRequestDispatcher("/WEB-INF/templates/jsp/profile.jsp").forward(request,response);
    }



}
