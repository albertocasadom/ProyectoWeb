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

        System.out.println(addr + " " + city);

        JSONObject prods_js = jsob.getJSONObject("productos");
        String [] productos = jsob.getNames(prods_js);
        int cant [] = new int [productos.length];

        for(int i = 0; i< productos.length; i++){
            cant[i] = jsob.getJSONObject("productos").getInt(productos[i]);
            System.out.println(cant[i]);
        }

        try (DBManager manager = new DBManager()) {

            


        }catch(SQLException | NamingException ex){

                ex.printStackTrace();
                response.sendError(500);
        }

    }
}