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

@WebServlet("/seeorder")

public class SeeOrder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null){
            session.invalidate();
            response.sendRedirect("index.html");
        }else{

            String id_order_str = request.getParameter("id");
            int id_order = Integer.parseInt(id_order_str);

            try (DBManager manager = new DBManager()){

                Order order = manager.searchOrder(id_order);
                request.setAttribute("order", order);

                Restaurant restaurant = manager.searchRestByIdRest(order.getIdRest());
                request.setAttribute("restaurant", restaurant);

                boolean isOrderOfAdmin = false;

                ArrayList<Restaurant> listadminrest = manager.searchRestsofAdmin(user.getId());

                for(int i = 0; i< listadminrest.size(); i++){
                    if(listadminrest.get(i).getIdRest() == restaurant.getIdRest()){
                        isOrderOfAdmin  = true;
                    }
                }

                if((manager.isOrderOfUser(user.getId(), id_order)) || (isOrderOfAdmin == true)){

                    ArrayList<Plato> listaplatos = manager.searchPlatesOfOrder(id_order);
                    request.setAttribute("listaplatos", listaplatos);

                    int cantidadplatos [] = new int[listaplatos.size()];

                    for(int i = 0; i< listaplatos.size(); i++){
                        cantidadplatos[i] = manager.getNumOfPlates(listaplatos.get(i).getIdPlato(), order.getIdOrder());
                    }

                    request.setAttribute("cantidad", cantidadplatos);

                    RequestDispatcher rd = request.getRequestDispatcher("ViewOrder.jsp");
                    rd.forward(request, response);

                }else{
                    session.invalidate();
                    response.sendRedirect("index.html");
                }

            }catch(SQLException | NamingException ex){

                    ex.printStackTrace();                   
                    response.sendRedirect("Error.jsp");
            }
        }

    }
}