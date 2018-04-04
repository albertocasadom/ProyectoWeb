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

@WebServlet("/check")

public class InitSession extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();

        try (DBManager manager = new DBManager()) {

            String mail = request.getParameter("init");
            String userpass = request.getParameter("pass");
            User user = manager.searchUser(mail);

            if(user!= null){
                if(user.getPass().equals(userpass)){
                    session.setAttribute("user",user);
                    response.sendRedirect("init");
                }else{
                    response.sendRedirect("index.html");
                }
            }else{
                response.sendRedirect("createaccount.html");
            }

        }catch(SQLException | NamingException ex){

                ex.printStackTrace();
                response.sendError(500);
        }

    }
}