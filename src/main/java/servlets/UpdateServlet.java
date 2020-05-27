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

@WebServlet("/admin/updateUser")
public class UpdateServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        try {
            req.setAttribute("name", userService.getUserById(id).getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("id", id);
        req.getServletContext().getRequestDispatcher("/jsp/updateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("name");
        String password = req.getParameter("password");
        Long id = Long.parseLong(req.getParameter("id"));

        try {
            if (!userService.updateClient(id, login, password)) {
                req.setAttribute("repeatInput", "Repeat input");
                req.getServletContext().getRequestDispatcher("/jsp/updateUser.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/admin");
    }
}
