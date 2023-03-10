package pl.coderslab.jeeusercrud.users;

import pl.coderslab.jeeusercrud.model.User;
import pl.coderslab.jeeusercrud.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/users/list")
public class UserList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("user");
        UserDao userDao = new UserDao();
        User[] users = userDao.findAll();
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/users/list.jsp").forward(req,resp);
    }
}
