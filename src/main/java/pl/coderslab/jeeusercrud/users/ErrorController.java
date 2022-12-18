package pl.coderslab.jeeusercrud.users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/users/*")
//public class ErrorController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Throwable throwable = (Throwable) req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
//        if (throwable.getMessage().equals("HTTP Status 404 – Not Found")) {
//            getServletContext().getRequestDispatcher("/users/error.jsp").forward(req, resp);
//        }
//    }
//
//}
