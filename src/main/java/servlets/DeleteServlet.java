package servlets;

import exception.DBException;
import models.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteUser")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/deleteUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = new User(name, password);

        try {
            while (!UserService.getInstance().deleteClient(user)) {
                req.setAttribute("repeatInput", "Repeat input");
                req.getServletContext().getRequestDispatcher("/jsp/deleteUser.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("repeatInput", "User deleted");
        doGet(req, resp);
    }
}
