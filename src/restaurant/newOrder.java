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

@WebServlet("/newOrder")

public class newOrder extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

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
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(id_order);

        }catch(SQLException | NamingException ex){

                ex.printStackTrace();
                response.sendError(500);
        }

    }
}