package by.teachmeskills.servlet;

import by.teachmeskills.model.Category;
import by.teachmeskills.model.Product;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM shop.categories";
    private static final String GET_PRODUCT = "SELECT * FROM shop.products WHERE category_id=?";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", getCategoriesFromDB());
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }


    public List<Category> getCategoriesFromDB() {
        List<Category> categories = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_CATEGORIES);
            while (resultSet.next()) {
                categories.add(Category.builder().id(resultSet.getString(1)).name(resultSet.getString(2))
                        .imageName(resultSet.getString(3)).productList(getProductByIdCategory(resultSet.getString(1))).build());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connectionPool.closeConnection(connection);
        }
        return categories;
    }

    private List<Product> getProductByIdCategory(String id) {
        List<Product> products = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(Product.builder().id(resultSet.getInt(1)).name(resultSet.getString(2))
                        .description(resultSet.getString(3)).price(resultSet.getInt(4)).imageName(resultSet.getString(6)).build());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connectionPool.closeConnection(connection);
        }
        return products;
    }
}