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
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("defName", req.getParameter("defaultName"));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/updateUser.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String oldName = req.getParameter("oldName");
        String password = req.getParameter("password");
        User user = new User(name, password);

        try {
            while (!userService.updateClient(user, oldName)) {
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
