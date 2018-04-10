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

@WebServlet("/changestate")

public class ChangeState extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();

        try (DBManager manager = new DBManager()) {

            String state= request.getParameter("state");
            String idorderstr [] = request.getParameterValues("idorder");
            System.out.println(state);
            System.out.println(idorderstr[0]);
            int idorder = Integer.parseInt(idorderstr[0]);
            boolean changestate = manager.changeState(idorder, state);

            if(changestate){
                response.sendRedirect("init");
            }
            
        }catch(SQLException | NamingException ex){

                ex.printStackTrace();
                response.sendError(500);
        }

    }
}