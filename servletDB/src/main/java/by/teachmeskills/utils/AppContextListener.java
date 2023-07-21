package by.teachmeskills.utils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        String dbURL = ctx.getInitParameter("dbURL");
        String dbUser = ctx.getInitParameter("dbLogin");
        String dbPassword = ctx.getInitParameter("dbPassword");

        DBConnectionManager dbManager = new DBConnectionManager(dbURL, dbUser, dbPassword);
        ctx.setAttribute("DBManager", dbManager);
        System.out.println("Database connection initialized for Application.");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
        dbManager.closeConnection();
        System.out.println("Database connection closed for Application.");

    }
}