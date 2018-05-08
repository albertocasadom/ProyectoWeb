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
       
        if(user != null && user.getIdType() == User.TYPE_ADMIN){  
            try (DBManager manager = new DBManager()) {
                String id_rest_str = request.getParameter("id");
                request.setAttribute("id",id_rest_str);
                int id_rest = Integer.parseInt(id_rest_str);
                boolean isadminrest = manager.isAdminOfRest(user.getId(), id_rest);
                Restaurant restexist = manager.searchRestByIdRest(id_rest);

                if(isadminrest || restexist!= null){
                    ArrayList<Plato> cart = manager.searchCart(id_rest);
                    request.setAttribute("cart", cart);
                    String err = request.getParameter("err");
                    request.setAttribute("err",err);
                    RequestDispatcher rd = request.getRequestDispatcher("ViewRestaurant.jsp");
                    rd.forward(request, response);
                }else{
                    session.invalidate();
                    response.sendRedirect("index.html");
                }
            }catch(SQLException | NamingException ex){

                    ex.printStackTrace();
                    response.sendRedirect("Error.jsp");
            }
        }else if(user != null && user.getIdType() == User.TYPE_CUSTOMER){

            String ciudad = request.getParameter("cit");

            try (DBManager manager = new DBManager()) {

                String id_rest_str = request.getParameter("id");
                request.setAttribute("cit",ciudad);
                int id_rest = Integer.parseInt(id_rest_str);
                request.setAttribute("id_rest", id_rest);
                ArrayList<Plato> cart = manager.searchCart(id_rest);
                request.setAttribute("cart", cart);
                Restaurant restexist = manager.searchRestByIdRest(id_rest);
                if(restexist != null){
                    RequestDispatcher rd = request.getRequestDispatcher("ViewRestaurantUser.jsp");
                    rd.forward(request, response);
                }else{
                    response.sendRedirect("Error.jsp");
                }

            }catch(SQLException | NamingException ex){

                    ex.printStackTrace();
                    response.sendRedirect("Error.jsp");
            }    
        }else{
            session.invalidate();
            response.sendRedirect("index.html");
        }
    }
}