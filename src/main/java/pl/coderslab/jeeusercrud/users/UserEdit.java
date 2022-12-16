package pl.coderslab.jeeusercrud.users;

import pl.coderslab.jeeusercrud.dao.UserDao;
import pl.coderslab.jeeusercrud.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        if (!id.isEmpty()) {
            int userId = Integer.parseInt(id);
            User user = UserDao.read(userId);
            req.setAttribute("user", user);
        }
        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User();
        if (!id.isEmpty()) {
            user.setUserId(Integer.parseInt(id));
        }
        if (!username.isEmpty()) {
            user.setUserName(username);
        }
        if (!username.isEmpty()) {
            user.setEmail(email);
        }
        if (!username.isEmpty()) {
            user.setPassword(password);
        }
        UserDao.update(user);

        //jak to jest zrobione w coderslab

        resp.sendRedirect("/list");

    }
}
