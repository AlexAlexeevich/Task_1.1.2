package servlets;

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
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = new User(name, password);
        //System.out.println(name + " 99 " + password);
        try {
            userService.deleteClient(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/showUsers") ;
        //req.getServletContext().getRequestDispatcher("/jsp/showUsers.jsp").forward(req, resp);
    }
}
