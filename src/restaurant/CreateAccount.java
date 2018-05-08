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

@WebServlet("/register")

public class CreateAccount extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        HttpSession session = request.getSession();
        String from = "0317102@lab.it.uc3m.es";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.user", "0317102@lab.it.uc3m.es");
        properties.setProperty("mail.password", "situriu2");
        Session sesionmail = Session.getDefaultInstance(properties);
        MimeMessage message;

        try (DBManager manager = new DBManager()) {

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String mail = request.getParameter("nick");
            String remail = request.getParameter("nickrepeat");
            String userpass = request.getParameter("newpass");
            String reuserpass = request.getParameter("newpassrepeat");
            String address = request.getParameter("address");
            String ciudad = request.getParameter("ciudad");
            String tel = request.getParameter("phone");
            boolean insertok = false;

            if((mail.equals(remail)) && (userpass.equals(reuserpass))){
                insertok = manager.insertUser(name,surname,mail,userpass,address,ciudad,tel);
                if(insertok == true){
                    try{
                        message = new MimeMessage(sesionmail);
                        message.setFrom(new InternetAddress(from));
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
                        message.setSubject("¡ Bienvenid@ !");
                        message.setText("¡ YA PUEDES DISFRUTAR DE TODOS LOS RESTAURANTES DE TU CIUDAD !");
                        Transport.send(message);
                    }catch (MessagingException mex) {
                        mex.printStackTrace();
                    }     
                    response.sendRedirect("index.html");  
                }else{
                    response.sendRedirect("createaccount.html");
                }

            }else{
                    response.sendRedirect("createaccount.html");
            }



        }catch(SQLException | NamingException ex){

                ex.printStackTrace();
                response.sendRedirect("Error.jsp");
        }

    }
}