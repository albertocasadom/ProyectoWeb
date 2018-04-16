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

@WebServlet("/addplate")

public class AddPlate extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();

        try (DBManager manager = new DBManager()) {

            String idreststr[] = request.getParameterValues("idrest");
            System.out.println(idreststr);
            int idrest = Integer.parseInt(idreststr[0]);
            String nameplate = request.getParameter("nameplate");
            String precioplatestr = request.getParameter("precio");
            float precioplate = Float.parseFloat(precioplatestr);
            String descripcionplate = request.getParameter("descripcion");
            Restaurant restaurant = manager.searchRestByIdRest(idrest);
            boolean addplate = manager.insertPlate(nameplate, precioplate, idrest, descripcionplate);
            System.out.println(restaurant.getIdRest());
            

            if(addplate){
                  response.sendRedirect("rest?id=" + restaurant.getIdRest());
            }

        }catch(SQLException | NamingException ex){

                ex.printStackTrace();
                response.sendError(500);
        }

    }
}