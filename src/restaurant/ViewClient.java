package restaurant;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.SQLException;

@WebServlet("/init")

public class ViewClient extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();

        if(session.getAttribute("orders") == null){

            ArrayList<Order> orders = new ArrayList<Order> ();

            session.setAttribute("orders", orders);
        }

        RequestDispatcher rd = request.getRequestDispatcher("ViewClient.jsp");
        rd.forward(request, response);

		 

    }
}