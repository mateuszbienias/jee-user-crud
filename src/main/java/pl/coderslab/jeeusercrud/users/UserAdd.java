package pl.coderslab.jeeusercrud.users;

import pl.coderslab.jeeusercrud.dao.UserDao;
import pl.coderslab.jeeusercrud.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/users/add")
public class UserAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/users/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDao.read(email);

        User user = new User();
        if (Objects.nonNull(username) && Objects.nonNull(email) && Objects.nonNull(password)) {
            if (UserDao.checkUserName(username) || UserDao.checkEmail(email))  {
                if (UserDao.checkUserName(username) && UserDao.checkEmail(email)) {
                    req.setAttribute("errorName", "login is already taken !!!");
                    req.setAttribute("errorEmail", "Email is already taken !!!");
                    req.setAttribute("userName", username);
                    req.setAttribute("email", email);
                    req.setAttribute("password", password);
                    getServletContext().getRequestDispatcher("/users/add.jsp").forward(req,resp);
                } else if (UserDao.checkUserName(username)) {
                    req.setAttribute("errorName", "login is already taken !!!");
                    req.setAttribute("userName", username);
                    req.setAttribute("email", email);
                    req.setAttribute("password", password);
                    getServletContext().getRequestDispatcher("/users/add.jsp").forward(req,resp);
                } else {
                    req.setAttribute("errorEmail", "Email is already taken !!!");
                    req.setAttribute("userName", username);
                    req.setAttribute("email", email);
                    req.setAttribute("password", password);
                    getServletContext().getRequestDispatcher("/users/add.jsp").forward(req,resp);
                }

            } else {
                if (!UserDao.verifyEmail(email) && !UserDao.verifyUserName(username) && !UserDao.verifyPasswordStrength(password)) {
                    user.setUserName(username);
                    user.setEmail(email);
                    user.setPassword(password);
                    UserDao.create(user);
                    resp.sendRedirect("/users/list");
                } else if (!UserDao.verifyEmail(email) && !UserDao.verifyUserName(username)) {
                    req.setAttribute("errorName", "Login must be min 3 max 16 characters, and only number and letters !!!");
                    req.setAttribute("errorEmail", "This is not an email !!!");
                    req.setAttribute("errorPassword", "Wrong Password!!! Must be min 10 max 15 characters and min 1 special characters");
                    req.setAttribute("userName", username);
                    req.setAttribute("email", email);
                    req.setAttribute("password", password);
                    getServletContext().getRequestDispatcher("/users/add.jsp").forward(req,resp);
                } else if (!UserDao.verifyUserName(username) && !UserDao.verifyPasswordStrength(password)) {
                    req.setAttribute("errorName", "Login must be min 3 max 16 characters, and only number and letters !!!");
                    req.setAttribute("errorPassword", "Wrong Password!!! Must be min 10 max 15 characters and min 1 special characters");
                    req.setAttribute("userName", username);
                    req.setAttribute("email", email);
                    req.setAttribute("password", password);
                    getServletContext().getRequestDispatcher("/users/add.jsp").forward(req,resp);
                } else if (!UserDao.verifyEmail(email) && !UserDao.verifyPasswordStrength(password)) {
                    req.setAttribute("errorEmail", "This is not an email !!!");
                    req.setAttribute("errorPassword", "Wrong Password!!! Must be min 10 max 15 characters and min 1 special characters");
                    req.setAttribute("userName", username);
                    req.setAttribute("email", email);
                    req.setAttribute("password", password);
                    getServletContext().getRequestDispatcher("/users/add.jsp").forward(req,resp);
                } else if (!UserDao.verifyEmail(email)) {
                    req.setAttribute("errorEmail", "This is not an email !!!");
                    req.setAttribute("userName", username);
                    req.setAttribute("email", email);
                    req.setAttribute("password", password);
                    getServletContext().getRequestDispatcher("/users/add.jsp").forward(req,resp);
                } else if (!UserDao.verifyUserName(username)) {
                    req.setAttribute("errorName", "Login must be min 3 max 16 characters, and only number and letters !!!");
                    req.setAttribute("userName", username);
                    req.setAttribute("email", email);
                    req.setAttribute("password", password);
                    getServletContext().getRequestDispatcher("/users/add.jsp").forward(req,resp);
                } else if (!UserDao.verifyPasswordStrength(password)) {
                    req.setAttribute("errorPassword", "Wrong Password!!! Must be min 8 max 15 characters and min 1 special characters, and only number and letters.");
                    req.setAttribute("userName", username);
                    req.setAttribute("email", email);
                    req.setAttribute("password", password);
                    getServletContext().getRequestDispatcher("/users/add.jsp").forward(req,resp);
                } else {
                    user.setUserName(username);
                    user.setEmail(email);
                    user.setPassword(password);
                    UserDao.create(user);
                    resp.sendRedirect("/users/list");
                }

            }
        }
        //jak to jest zrobione w coderslab


    }
}
