package servlets;

import models.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateUser")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("defName", req.getParameter("defaultName"));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/updateUser.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String newName = req.getParameter("newName");
        String password = req.getParameter("password");
        User user = new User(name, password);

        try {
            while (!UserService.getInstance().updateClient(user, newName)) {
                req.setAttribute("repeatInput", "Repeat input");
                req.getServletContext().getRequestDispatcher("/jsp/updateUser.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("repeatInput", "User updated");
        doGet(req, resp);
    }
}
