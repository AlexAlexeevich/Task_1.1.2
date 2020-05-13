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
import java.util.List;

@WebServlet("/showUsers")
public class ShowUsersServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAllClient();
        req.setAttribute("usersFromServer", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/showUsers.jsp");
        dispatcher.forward(req, resp);
    }
}
