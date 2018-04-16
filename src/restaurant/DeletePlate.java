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

@WebServlet("/deleteplate")

public class DeletePlate extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();

        try (DBManager manager = new DBManager()) {

            String idplatostr [] = request.getParameterValues("idplatodelete");            
            System.out.println(idplatostr[0]);
            int idplato = Integer.parseInt(idplatostr[0]);
            System.out.println(idplato);
            Restaurant restaurant = manager.searchRestByPlate(idplato);
            boolean deleteplate = manager.deletePlate(idplato);

            if(deleteplate){
                  response.sendRedirect("rest?id=" + restaurant.getIdRest());
            }

        }catch(SQLException | NamingException ex){

                ex.printStackTrace();
                response.sendError(500);
        }

    }
}