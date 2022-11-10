package edu.school21.cinema.listeners;

import edu.school21.cinema.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CinemaContextListener implements ServletContextListener {

    /**
    NOTE: Здесь мы устанавливаем спринговый контекст, после чего сможем вызывать контекст и доставать бины
    */

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext()
                .setAttribute("springContext",
                        new AnnotationConfigApplicationContext(SpringConfig.class));
    }
}
