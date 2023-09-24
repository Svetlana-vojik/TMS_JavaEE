package by.teachmeskills.servlet;

import by.teachmeskills.model.User;
import by.teachmeskills.utils.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String GET_USER_QUERY = "SELECT * FROM shop.users WHERE email = ? AND password = ?";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = null;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_QUERY);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = User.builder().login(resultSet.getString("email")).password(resultSet.getString("password")).build();
            }
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connectionPool.closeConnection(connection);
        }
        if (user != null) {
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/home").forward(req, resp);
        } else {
            req.setAttribute("error", "Пользователь не зарегистрирован!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}