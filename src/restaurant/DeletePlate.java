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
        User user = (User) session.getAttribute("user");
       
        if((user == null) || (user.getIdType() != User.TYPE_ADMIN)){
            session.invalidate();
            response.sendRedirect("index.html");
        }else{

            try (DBManager manager = new DBManager()) {

                String idplatostr [] = request.getParameterValues("idplatodelete");            
                System.out.println(idplatostr[0]);
                int idplato = Integer.parseInt(idplatostr[0]);
                System.out.println(idplato);
                Restaurant restaurant = manager.searchRestByPlate(idplato);
                if(!(manager.isAdminOfRest(user.getId(), restaurant.getIdRest()))){
                    System.out.println( user.getId() + " : Está intentando acceder a un resaturante que no gestiona");
                    session.invalidate();
                    response.sendRedirect("index.html");
                }else{
                    boolean deleteplate = manager.deletePlate(idplato);
                    if(deleteplate){
                          response.sendRedirect("rest?id=" + restaurant.getIdRest());
                    }
                }
            }catch(SQLException | NamingException ex){

                    ex.printStackTrace();
                    response.sendRedirect("Error.jsp");
            }
        }
    }
}