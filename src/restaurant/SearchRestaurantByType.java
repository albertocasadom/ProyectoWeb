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

@WebServlet("/resttype")

public class SearchRestaurantByType extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user != null){
            try (DBManager manager = new DBManager()) {

                String ciudad = user.getCiudad();
                System.out.println(ciudad);
                String type_rest = request.getParameter("id");
                System.out.println("Este es el tipo de restaurante :"+ type_rest);
                ArrayList<Restaurant> foundrestaurants = manager.searchResultsFoodType(ciudad, type_rest);
                request.setAttribute("foundrestaurants", foundrestaurants);
                request.setAttribute("ciudad", ciudad);
                RequestDispatcher rd = request.getRequestDispatcher("ViewResults.jsp");
                rd.forward(request, response);

            }catch(SQLException | NamingException ex){

                    ex.printStackTrace();
                    response.sendRedirect("Error.jsp");
            }
        }else{

            response.sendRedirect("index.html");
        }
    }
}