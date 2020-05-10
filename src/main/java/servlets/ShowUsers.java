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
import java.util.List;

@WebServlet("/showUsers")
public class ShowUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = null;
        try {
            users = UserService.getInstance().getAllClient();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("usersFromServer", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/showUsers.jsp");
        dispatcher.forward(req, resp);
    }
}
