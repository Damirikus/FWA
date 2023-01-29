package edu.school21.cinema.servlets;

import edu.school21.cinema.config.SpringConfig;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "ImageServlet", value = "/images/*")
public class ImageServlet extends HttpServlet {

    private SpringConfig config;

    @Override
    public void init (ServletConfig config ) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.config = springContext.getBean(SpringConfig.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getHttpServletMapping().getMatchValue();
        String path = config.pathToSave + "/" + filename;
        ServletOutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(path);
        readImage(out, in);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private void readImage(ServletOutputStream out, FileInputStream in) throws IOException {
        byte[] buff = new byte[1024];
        int count;
        while ((count = in.read(buff)) >= 0) {
            out.write(buff, 0, count);
        }
        out.flush();
        in.close();
        out.close();
    }

}
