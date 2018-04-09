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

        try (DBManager manager = new DBManager()) {

            String id_rest_str = request.getParameter("id");
            int id_rest = Integer.parseInt(id_rest_str);
            ArrayList<Plato> cart = manager.searchCart(id_rest);
            request.setAttribute("cart", cart);
            RequestDispatcher rd = request.getRequestDispatcher("ViewRestaurant.jsp");
            rd.forward(request, response);

        }catch(SQLException | NamingException ex){

                ex.printStackTrace();
                response.sendError(500);
        }

    }
}