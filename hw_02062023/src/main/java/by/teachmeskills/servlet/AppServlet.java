package by.teachmeskills.servlet;

import by.teachmeskills.model.Category;
import by.teachmeskills.model.Product;
import by.teachmeskills.model.User;
import by.teachmeskills.utils.CRUDUtils;
import by.teachmeskills.utils.DBConnectionManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shop")
public class AppServlet extends HttpServlet {
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM shop.categories";
    private static final String GET_PRODUCT = "SELECT * FROM shop.products WHERE category_id=?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/home.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        ServletContext servletContext = request.getServletContext();
        DBConnectionManager dbConnectionManager = (DBConnectionManager) servletContext.getAttribute("DBManager");
        User user = CRUDUtils.getUserDB(login, password, dbConnectionManager.getConnection());
        if (user != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", user);
            httpSession.setAttribute("categories", getCategoriesFromDB());
            response.sendRedirect("/home.jsp");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    public List<Category> getCategoriesFromDB() {
        List<Category> categories = new ArrayList<>();
        ServletContext servletContext = getServletContext();

        try {
            DBConnectionManager dbConnectionManager = (DBConnectionManager) servletContext.getAttribute("DBManager");
            Connection connection = dbConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CATEGORIES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                             categories.add( Category.builder().id(resultSet.getString(1)).name(resultSet.getString(2))
                                     .imageName(resultSet.getString(3)).productList(getProductByIdCategory(resultSet.getString(1))).build());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    private List<Product> getProductByIdCategory(String id) {
        List<Product> products = new ArrayList<>();
        ServletContext ctx = getServletContext();
        try {
            DBConnectionManager dbConnectionManager = (DBConnectionManager) ctx.getAttribute("DBManager");
            Connection connection = dbConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(Product.builder().id(resultSet.getInt(1)).name(resultSet.getString(2))
                        .description(resultSet.getString(3)).price(resultSet.getInt(4)).imageName(resultSet.getString(6)).build());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
}