package by.teachmeskills.servlet;

import by.teachmeskills.commands.BaseCommand;
import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.utils.CommandFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/shop")
public class ApplicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseCommand command = CommandFactory.defineCommand(request);
        String path;
        try {
            path = command.execute(request);
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (CommandException e) {
            System.out.println(e.getMessage());
            request.getRequestDispatcher(PagesPathEnum.LOGIN_PAGE.getPath()).forward(request, response);
        }
    }
}