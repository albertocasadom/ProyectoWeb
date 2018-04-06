package restaurant;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;


@WebServlet("/close")

public class CloseSession extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect("index.html");

        }

    }
