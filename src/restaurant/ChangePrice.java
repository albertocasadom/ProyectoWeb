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

@WebServlet("/changeprice")

public class ChangePrice extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();

        try (DBManager manager = new DBManager()) {

            String preciostr = request.getParameter("newprice");
            String idplatostr [] = request.getParameterValues("idplato");            
            System.out.println(preciostr);
            System.out.println(idplatostr[0]);
            float precio = Float.parseFloat(preciostr);
            int idplato = Integer.parseInt(idplatostr[0]);
            boolean changeprice = manager.changePrice(idplato, precio);
            Restaurant restaurant = manager.searchRestByPlate(idplato);
            System.out.println(restaurant.getIdRest());
            

            if(changeprice){
                  response.sendRedirect("rest?id=" + restaurant.getIdRest());
            }

        }catch(SQLException | NamingException ex){

                ex.printStackTrace();
                response.sendError(500);
        }

    }
}