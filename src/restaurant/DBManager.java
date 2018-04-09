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
        DataSource ds = (DataSource) envCtx.lookup("webrestaurants");
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
                user.setAddress(rs.getString("address_user"));
                user.setCiudad(rs.getString("ciudad_user"));
                user.setPhone(rs.getString("phone"));
                user.setIdType(rs.getInt("id_type"));
                user.setId(rs.getInt("id_user"));
                System.out.println("Usuario encontrado");
               
          
            }else{
                System.out.println("No existe el usuario");
            
            }

        }catch(Exception e){
            System.out.println(e);
        }
        return user;
      
     

    }

    public boolean insertUser(String name, String surname, String mail, String pass, String address, String ciudad, String phone)throws SQLException{

        User checkuser = searchUser(mail, pass);
        boolean success = false;
        connection.setTransactionIsolation(Connection. TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);

        if(checkuser.getMail() == null){

            String query = "INSERT INTO Users (Users.name, Users.surname, Users.mail, Users.pass, Users.address_user, Users.ciudad_user, Users.phone, Users.id_type) VALUES ( ?, ?, ?, ? ,? , ?, ?, 1)";
            try(PreparedStatement st = connection.prepareStatement(query)){
                System.out.println("AÑADIENDO USUARIO A LA BASE DE DATOS...");
                st.setString(1, name);
                st.setString(2, surname);
                st.setString(3, mail);
                st.setString(4, pass);
                st.setString(5, address);
                st.setString(6, ciudad);
                st.setString(7, phone);
                
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

    public ArrayList<Order> searchOrdersUser(int id_user) throws SQLException{

        ArrayList<Order> orderlist = new ArrayList<Order>();

        String query = "SELECT * FROM Orders INNER JOIN Users ON Orders.id_user = Users.id_user WHERE Users.id_user = ?";

        try(PreparedStatement st = connection.prepareStatement(query)){
            st.setInt(1, id_user);
            ResultSet rs = st.executeQuery();
            
           
            while(rs.next()){

                Order order = new Order();
                order.setIdOrder(rs.getInt("id_order"));
                order.setState(rs.getString("state"));
                order.setIdUser(rs.getInt("id_user"));
                order.setCiudad(rs.getString("ciudad"));
                order.setAddressOrder(rs.getString("address_order"));
                order.setPrecioTotal(rs.getFloat("precio_total"));
                order.setFechaHora(rs.getTimestamp("fecha_hora"));
                order.setIdRest(rs.getInt("id_rest"));
                
                orderlist.add(order);

                System.out.println(order.getIdOrder());
               
          
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return orderlist;
    }

    public ArrayList<Order> searchOrdersRest(int id_rest) throws SQLException{

        ArrayList<Order> orderlist = new ArrayList<Order> ();

        String query = "SELECT * FROM Orders INNER JOIN Restaurants ON Orders.id_rest = Restaurants.id_rest WHERE Restaurants.id_rest = ?";

        try(PreparedStatement st = connection.prepareStatement(query)){
            st.setInt(1, id_rest);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){

                Order order = new Order();
                order.setIdOrder(rs.getInt("id_order"));
                order.setState(rs.getString("state"));
                order.setIdUser(rs.getInt("id_user"));
                order.setCiudad(rs.getString("ciudad"));
                order.setAddressOrder(rs.getString("address_order"));
                order.setPrecioTotal(rs.getFloat("precio_total"));
                order.setFechaHora(rs.getTimestamp("fecha_hora"));
                order.setIdRest(rs.getInt("id_rest"));
                
                orderlist.add(order);

                System.out.println(order.getIdOrder());
                System.out.println(order.getState());
               
          
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return orderlist;
    }
    
    public ArrayList<Restaurant> searchRestsofAdmin(int id_user){

        ArrayList<Restaurant> restaurantlist = new ArrayList<Restaurant>();

        String query = "SELECT * FROM Restaurants INNER JOIN AdminRest ON Restaurants.id_rest = AdminRest.id_rest INNER JOIN Users ON AdminRest.id_user_admin = Users.id_user WHERE Users.id_user = ?";

        try(PreparedStatement st = connection.prepareStatement(query)){
            st.setInt(1, id_user);
            ResultSet rs = st.executeQuery();
            
           
            while(rs.next()){

                Restaurant restaurant = new Restaurant();
                restaurant.setIdRest(rs.getInt("id_rest"));
                restaurant.setNameRest(rs.getString("name_rest"));
                restaurant.setAddressRest(rs.getString("address_rest"));
                restaurant.setCiudad(rs.getString("ciudad"));
                restaurant.setPhoneRest(rs.getString("phone_rest"));
                restaurant.setTypeRest(rs.getString("typ_rest"));
                
                restaurantlist.add(restaurant);
                        
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return restaurantlist;
    }

}
