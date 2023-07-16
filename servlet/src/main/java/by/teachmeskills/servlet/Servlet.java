package by.teachmeskills.servlet;

import by.teachmeskills.model.Calculator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String operand1 = request.getParameter("a");
        String operand2 = request.getParameter("b");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        String operation = request.getParameterValues("operation")[0];

        if (operation != null) {
            switch (operation) {
                case "sum" ->
                        request.setAttribute("result", Calculator.sum(Integer.parseInt(operand1), Integer.parseInt(operand2)));
                case "sub" ->
                        request.setAttribute("result", Calculator.sub(Integer.parseInt(operand1), Integer.parseInt(operand2)));
                case "mul" ->
                        request.setAttribute("result", Calculator.mul(Integer.parseInt(operand1), Integer.parseInt(operand2)));
                case "div" ->
                        request.setAttribute("result", Calculator.div(Integer.parseInt(operand1), Integer.parseInt(operand2)));
            }
        }
        requestDispatcher.forward(request, response);
    }
}
