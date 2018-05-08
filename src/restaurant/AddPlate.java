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
        User user = (User) session.getAttribute("user");
       
        if((user == null) || (user.getIdType() != User.TYPE_ADMIN)){
            session.invalidate();
            response.sendRedirect("index.html");
        }else{

            try (DBManager manager = new DBManager()) {

                String idreststr[] = request.getParameterValues("idrest");
                System.out.println(idreststr[0]);
                int idrest = Integer.parseInt(idreststr[0]);
                String nameplate = request.getParameter("nameplate");
                String precioplatestr = request.getParameter("precio");
                float precioplate = Float.parseFloat(precioplatestr);
                String descripcionplate = request.getParameter("descripcion");

                if(!(manager.isAdminOfRest(user.getId(), idrest))){
                    System.out.println( user.getId() + " : Está intentando acceder a un resaturante que no gestiona");
                    session.invalidate();
                    response.sendRedirect("index.html");
                }else{

                    Restaurant restaurant = manager.searchRestByIdRest(idrest);
                    boolean addplate = manager.insertPlate(nameplate, precioplate, idrest, descripcionplate);
                    System.out.println(restaurant.getIdRest());
                
                    if(addplate){
                        response.sendRedirect("rest?id=" + restaurant.getIdRest());
                    }else{
                        response.sendRedirect("rest?id=" + restaurant.getIdRest() + "&err=1"); //codigo de error cuando el plato ya existe.
                    }
                }
            }catch(SQLException | NamingException ex){

                    ex.printStackTrace();
                    response.sendRedirect("Error.jsp");
            }
        }
    }
}