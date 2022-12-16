package pl.coderslab.jeeusercrud.users;

import pl.coderslab.jeeusercrud.model.User;
import pl.coderslab.jeeusercrud.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/list")
public class UserList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao userDao = new UserDao();
        User[] users = userDao.findAll();
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/users/list.jsp").forward(req,resp);
    }
}
