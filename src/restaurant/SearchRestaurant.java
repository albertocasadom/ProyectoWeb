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

@WebServlet("/search")

public class SearchRestaurant extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user != null){
            try (DBManager manager = new DBManager()) {

                String ciudad = request.getParameter("rest");
                ArrayList<Restaurant> foundrestaurants = manager.searchResults(ciudad);
                request.setAttribute("foundrestaurants", foundrestaurants);
                request.setAttribute("ciudad", ciudad);
                RequestDispatcher rd = request.getRequestDispatcher("ViewResults.jsp");
                rd.forward(request, response);

            }catch(SQLException | NamingException ex){

                    ex.printStackTrace();
                    response.sendError(500);
            }
        }else{

            response.sendError(500);
        }
    }
}