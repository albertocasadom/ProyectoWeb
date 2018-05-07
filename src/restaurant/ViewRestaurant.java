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

@WebServlet("/rest")

public class ViewRestaurant extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
       
        if(user != null && user.getIdType() == 2){  
            try (DBManager manager = new DBManager()) {
                String id_rest_str = request.getParameter("id");
                request.setAttribute("id",id_rest_str);
                int id_rest = Integer.parseInt(id_rest_str);
                boolean isadminrest = manager.isAdminOfRest(user.getId(), id_rest);

                if(isadminrest){
                    ArrayList<Plato> cart = manager.searchCart(id_rest);
                    request.setAttribute("cart", cart);
                    String err = request.getParameter("err");
                    request.setAttribute("err",err);
                    RequestDispatcher rd = request.getRequestDispatcher("ViewRestaurant.jsp");
                    rd.forward(request, response);
                }else{

                    response.sendError(500);
                }
            }catch(SQLException | NamingException ex){

                    ex.printStackTrace();
                    response.sendError(500);
            }
        }else if(user != null && user.getIdType() == 1){

            String ciudad = request.getParameter("cit");

            try (DBManager manager = new DBManager()) {

                String id_rest_str = request.getParameter("id");
                request.setAttribute("cit",ciudad);
                int id_rest = Integer.parseInt(id_rest_str);
                request.setAttribute("id_rest", id_rest);
                ArrayList<Plato> cart = manager.searchCart(id_rest);
                request.setAttribute("cart", cart);
                RequestDispatcher rd = request.getRequestDispatcher("ViewRestaurantUser.jsp");
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