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
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@WebServlet("/seeorder")

public class SeeOrder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String id_order_str = request.getParameter("id");
        int id_order = Integer.parseInt(id_order_str);

        String from = "0317102@lab.it.uc3m.es";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.user", "0317102@lab.it.uc3m.es");
        properties.setProperty("mail.password", "situriu2");
        Session sesionmail = Session.getDefaultInstance(properties);
        MimeMessage message;
        String msgpedido;
        String listaplatostr = "";

        try (DBManager manager = new DBManager()){

        Order order = manager.searchOrder(id_order);
        request.setAttribute("order", order);

        Restaurant restaurant = manager.searchRestByIdRest(order.getIdRest());
        request.setAttribute("restaurant", restaurant);

        ArrayList<Plato> listaplatos = manager.searchPlatesOfOrder(id_order);
        request.setAttribute("listaplatos", listaplatos);

        int cantidadplatos [] = new int[listaplatos.size()];

        for(int i = 0; i< listaplatos.size(); i++){
            cantidadplatos[i] = manager.getNumOfPlates(listaplatos.get(i).getIdPlato(), order.getIdOrder());
        }

        request.setAttribute("cantidad", cantidadplatos);

        for(int i = 0; i< listaplatos.size(); i++){
            listaplatostr += "Plato " + (i+1) + " : " + listaplatos.get(i).getNamePlate() + " x " + cantidadplatos[i] + "\t>> "+ (listaplatos.get(i).getPrecio()*cantidadplatos[i]) + " €\n";
        }

            try{    

                    msgpedido = "Tu pedido con identificador: " + id_order + " está en proceso. \n\n" + listaplatostr + "\n\t" + "Precio Total: " + order.getPrecioTotal()+ " €\n\n\t Disfrute de su pedido, ¡gracias por confiar en nosotros!";
                    message = new MimeMessage(sesionmail);
                    message.setFrom(new InternetAddress(from));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getMail()));
                    message.setSubject("¡ Oído cocina !");
                    message.setText(msgpedido);
                    Transport.send(message);
                }catch (MessagingException mex) {
                    mex.printStackTrace();
                }     
            

        RequestDispatcher rd = request.getRequestDispatcher("ViewOrder.jsp");
        rd.forward(request, response);
 

        }catch(SQLException | NamingException ex){

                ex.printStackTrace();
                response.sendError(500);
        }

    }
}