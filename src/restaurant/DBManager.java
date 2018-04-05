package restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.*;
import java.util.*;


public class DBManager implements AutoCloseable {

    private Connection connection;

    public DBManager() throws SQLException,NamingException {
        connect();
    }

    private void connect() throws SQLException,NamingException {

        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/Users");
        connection = ds.getConnection();
       
    }

    /**
     * Close the connection to the database if it is still open.
     *
     */
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    public User searchUser(String mail, String pass) throws SQLException {
        
        String query = "SELECT * FROM Users WHERE mail = ? AND pass = ?";
        User user = new User();

        try(PreparedStatement st = connection.prepareStatement(query)){

            st.setString(1, mail);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
          
            if(rs.next()){
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setMail(rs.getString("mail"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setId(rs.getInt("id"));
                user.setIdType(rs.getInt("id_type"));

                System.out.println("Usuario encontrado");
               
          
            }else{
                System.out.println("No existe el usuario");
            
            }

        }catch(Exception e){
            System.out.println(e);
        }
        return user;
      
     

    }

    public boolean insertUser(String name, String surname, String mail, String pass, String address, String phone)throws SQLException{

        User checkuser = searchUser(mail, pass);
        boolean success = false;
        connection.setTransactionIsolation(Connection. TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);

        if(checkuser.getMail() == null){

            String query = "INSERT INTO Users (Users.name, Users.surname, Users.mail, Users.pass, Users.address, Users.phone, Users.id_type) VALUES ( ?, ?, ?, ? ,? , ?, 1)";
            try(PreparedStatement st = connection.prepareStatement(query)){
                System.out.println("AÑADIENDO USUARIO A LA BASE DE DATOS...");
                st.setString(1, name);
                st.setString(2, surname);
                st.setString(3, mail);
                st.setString(4, pass);
                st.setString(5, address);
                st.setString(6, phone);
                
                st.executeUpdate();

                success = true;
            
            }catch(Exception e){
                System.out.println(e);  
            }
        }    

        if(success){

            connection.commit();

        }else{

            connection.rollback();

        }

        connection.setAutoCommit(true);
        return success;
    }

}
