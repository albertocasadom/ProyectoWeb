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
import org.json.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@WebServlet("/newOrder")

public class newOrder extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if((user != null) || (user.getIdType() == User.TYPE_CUSTOMER)){

            BufferedReader buffer = request.getReader();

            String line = null;
            StringBuffer buff = new StringBuffer();
            while ((line = buffer.readLine()) != null) {
                buff.append(line);
            }
            String body = buff.toString();
            System.out.println(body);

            JSONObject jsob = new JSONObject(body);
            String addr = jsob.getString("dir");
            String city = jsob.getString("city");
            String phone = jsob.getString("tel");
            String id_rest_str = jsob.getString("id_rest");
            int id_rest = Integer.parseInt(id_rest_str);

            System.out.println(addr + " " + city);

            JSONObject prods_js = jsob.getJSONObject("productos");
            String [] productos = jsob.getNames(prods_js);
            int cant [] = new int [productos.length];
            float precio_total = 0;
            int id_order;

            for(int i = 0; i< productos.length; i++){
                cant[i] = jsob.getJSONObject("productos").getInt(productos[i]);
                System.out.println(cant[i]);
            }
            int prodint [] = new int [productos.length];

            try (DBManager manager = new DBManager()) {
                for(int i = 0; i< productos.length; i++){
                    prodint[i] = Integer.parseInt(productos[i]);
                }
                for(int i = 0; i<productos.length; i++){
                    Plato plato = manager.searchPlatoById(prodint[i]);
                    precio_total += (plato.getPrecio()*cant[i]);
                    System.out.println(precio_total);
                }
                id_order = manager.insertPlatoOnOrder(user.getId(), city, addr, precio_total, id_rest, prodint, cant);

                if(id_order != 0){
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
                    ArrayList<Plato> listaplatos = manager.searchPlatesOfOrder(id_order);

                    for(int i = 0; i< listaplatos.size(); i++){
                            listaplatostr += "Plato " + (i+1) + " : " + listaplatos.get(i).getNamePlate() + " x " + cant[i] + "\t>> "+ (listaplatos.get(i).getPrecio()*cant[i]) + " €\n";
                    }
                    try{    

                        msgpedido = "Tu pedido con identificador: " + id_order + " está en proceso. \n\n" + listaplatostr + "\n\t" + "Precio Total: " + precio_total+ " €\n\n\t Disfrute de su pedido, ¡gracias por confiar en nosotros!";
                        message = new MimeMessage(sesionmail);
                        message.setFrom(new InternetAddress(from));
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getMail()));
                        message.setSubject("¡ Oído cocina !");
                        message.setText(msgpedido);
                        Transport.send(message);

                    }catch (MessagingException mex) {
                        mex.printStackTrace();
                    }     
                
                }
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println(id_order);

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