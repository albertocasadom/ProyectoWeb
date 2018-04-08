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

public class ViewUser extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        System.out.println(user.getIdType());
        if(user.getIdType() == 1){

            try (DBManager manager = new DBManager()) {  
                ArrayList<Order> orders = manager.searchOrdersUser(user.getId());
                session.setAttribute("orders", orders);
                RequestDispatcher rd = request.getRequestDispatcher("ViewClient.jsp");
                rd.forward(request, response);
            }catch(SQLException | NamingException ex){

                ex.printStackTrace();
                response.sendError(500);
            }

		}else{

           /* if(session.getAttribute("restaurants") == null){
                ArrayList<Restaurant> restaurants = new ArrayList<Restaurant> ();
                session.setAttribute("restaurants", restaurants);
            }*/

            

            RequestDispatcher rd = request.getRequestDispatcher("ViewAdmin.jsp");
            rd.forward(request, response);
        }

    }
}