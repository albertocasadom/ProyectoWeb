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


    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();

        try (DBManager manager = new DBManager()) {

            String mail = request.getParameter("login");
            String userpass = request.getParameter("pass");
            User user = manager.searchUser(mail, userpass);

            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            System.out.println(gRecaptchaResponse);
            boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

            if(user != null  && verify){
                    session.setAttribute("user",user);
                    response.sendRedirect("init");
                
            }else{               
                response.sendRedirect("index.html");
            }    

        }catch(SQLException | NamingException ex){

                ex.printStackTrace();
                response.sendRedirect("Error.jsp");
        }

    }
}