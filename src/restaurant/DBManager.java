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
import java.util.Date.*;
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

    private static java.sql.Timestamp setTimestamp() {

    java.util.Date now = new java.util.Date();
    return new java.sql.Timestamp(now.getTime());

    }

    public User searchUser(String mail, String pass) throws SQLException {
        
        String query = "SELECT * FROM Users WHERE mail = ? AND pass = ?";
        User user = null;

        try(PreparedStatement st = connection.prepareStatement(query)){

            st.setString(1, mail);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
          
            if(rs.next()){
                user = new User();
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

        }
        return user;
      
     

    }

    public boolean insertUser(String name, String surname, String mail, String pass, String address, String ciudad, String phone)throws SQLException{

        User checkuser = searchUser(mail, pass);
        boolean success = false;
        connection.setTransactionIsolation(Connection. TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);

        if(checkuser == null){

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
    
    public ArrayList<Restaurant> searchRestsofAdmin(int id_user)throws SQLException{

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

    public ArrayList<Plato> searchCart(int id_rest) throws SQLException{

        ArrayList<Plato> cart = new ArrayList<Plato>();

        String query = "SELECT * FROM Platos INNER JOIN Restaurants ON Platos.id_rest = Restaurants.id_rest WHERE Restaurants.id_rest = ?";

        try(PreparedStatement st = connection.prepareStatement(query)){
            st.setInt(1, id_rest);
            ResultSet rs = st.executeQuery();
            
           
            while(rs.next()){

                Plato plate = new Plato();
                plate.setIdPlato(rs.getInt("id_plato"));
                plate.setNamePlate(rs.getString("nameplate"));
                plate.setPrecio(rs.getFloat("precio"));
                plate.setIdRest(rs.getInt("id_rest"));
                //faltaria añadir imagen para completar el objeto java. consultar como se hace.
                plate.setDescripcion(rs.getString("descripcion"));
                
                cart.add(plate);
                        
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return cart;
    }

    public ArrayList<Restaurant> searchResults(String ciudad) throws SQLException{

        ArrayList<Restaurant> foundrestaurants = new ArrayList<Restaurant>();

        String query = "SELECT * FROM Restaurants WHERE Restaurants.ciudad = ? ";

        try(PreparedStatement st = connection.prepareStatement(query)){
            st.setString(1, ciudad);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){

                Restaurant restaurant = new Restaurant();
                restaurant.setIdRest(rs.getInt("id_rest"));
                restaurant.setNameRest(rs.getString("name_rest"));
                restaurant.setAddressRest(rs.getString("address_rest"));
                restaurant.setCiudad(rs.getString("ciudad"));
                restaurant.setPhoneRest(rs.getString("phone_rest"));
                restaurant.setTypeRest(rs.getString("typ_rest"));
                
                foundrestaurants.add(restaurant);
                        
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return foundrestaurants;
    }

    public boolean changeState(int idOrder, String state) throws SQLException{
        boolean success = false;

        connection.setTransactionIsolation(Connection. TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);

        String query = "UPDATE Orders SET state = ? WHERE Orders.id_order = ?";
        try(PreparedStatement st = connection.prepareStatement(query)){

            st.setString(1, state);
            st.setInt(2, idOrder);
            st.executeUpdate();
            success = true;

        }catch(Exception e){
                System.out.println(e);  
        }
          
        if(success){

            connection.commit();

        }else{

            connection.rollback();

        }

        connection.setAutoCommit(true);
        return success;


    }  

       public boolean changePrice(int idPlato, Float newprice) throws SQLException{
        boolean success = false;

        connection.setTransactionIsolation(Connection. TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);

        String query = "UPDATE Platos SET precio = ?  WHERE Platos.id_plato = ?";
        try(PreparedStatement st = connection.prepareStatement(query)){

            st.setFloat(1, newprice);
            st.setInt(2, idPlato);
            System.out.println("Consulta correcta");
            st.executeUpdate();
            success = true;

        }
          
        if(success){

            connection.commit();

        }else{

            connection.rollback();

        }

        connection.setAutoCommit(true);
        return success;
    }  

        public Restaurant searchRestByPlate(int id_plato) throws SQLException{

        Restaurant restaurant = null;
        String query = "SELECT * FROM Restaurants INNER JOIN Platos ON Platos.id_rest = Restaurants.id_rest WHERE Platos.id_plato = ?";

        try(PreparedStatement st = connection.prepareStatement(query)){
            st.setInt(1, id_plato);
            ResultSet rs = st.executeQuery();
            
           
            if(rs.next()){
                restaurant = new Restaurant();
                restaurant.setIdRest(rs.getInt("id_rest"));
                restaurant.setNameRest(rs.getString("name_rest"));
                restaurant.setAddressRest(rs.getString("address_rest"));
                restaurant.setCiudad(rs.getString("ciudad"));
                restaurant.setPhoneRest(rs.getString("phone_rest"));
                restaurant.setTypeRest(rs.getString("typ_rest"));
                       
            }

        }

        return restaurant;
    } 

    public boolean isAdminOfRest(int id_user, int id_rest) throws SQLException{

        boolean istrue = false;
        String query = "SELECT * FROM Users INNER JOIN AdminRest ON Users.id_user = AdminRest.id_user_admin INNER JOIN Restaurants ON Restaurants.id_rest = AdminRest.id_rest WHERE Users.id_user = ? AND Restaurants.id_rest = ?";

        try(PreparedStatement st = connection.prepareStatement(query)){
            st.setInt(1, id_user);
            st.setInt(2, id_rest);
            ResultSet rs = st.executeQuery();
                   
            if(rs.next()){
                istrue = true;                       
            }
        }

        return istrue;
    }

    public Plato searchPlato(String nameplate) throws SQLException {
        
        String query = "SELECT * FROM Platos WHERE nameplate = ?";
        Plato plato = null;

        try(PreparedStatement st = connection.prepareStatement(query)){

            st.setString(1, nameplate);
            ResultSet rs = st.executeQuery();
          
            if(rs.next()){
                plato = new Plato();
                plato.setIdPlato(rs.getInt("id_plato"));
                plato.setNamePlate(rs.getString("nameplate"));
                plato.setPrecio(rs.getFloat("precio"));
                plato.setIdRest(rs.getInt("id_rest"));
                plato.setDescripcion(rs.getString("descripcion"));
                System.out.println("Plato encontrado");
                   
            }else{
                System.out.println("No existe el plato");
            
            }

        }
        return plato;
    }

    public Plato searchPlatoById(int id_plato) throws SQLException {
        
        String query = "SELECT * FROM Platos WHERE id_plato = ?";
        Plato plato = null;

        try(PreparedStatement st = connection.prepareStatement(query)){

            st.setInt(1, id_plato);
            ResultSet rs = st.executeQuery();
          
            if(rs.next()){
                plato = new Plato();
                plato.setIdPlato(rs.getInt("id_plato"));
                plato.setNamePlate(rs.getString("nameplate"));
                plato.setPrecio(rs.getFloat("precio"));
                plato.setIdRest(rs.getInt("id_rest"));
                plato.setDescripcion(rs.getString("descripcion"));
                System.out.println("Plato encontrado");
                   
            }else{
                System.out.println("No existe el plato");
            
            }

        }
        return plato;
    }

    public boolean insertPlate(String nameplate, Float precio, int id_rest, String descripcion) throws SQLException{

        Plato plato = new Plato();
        boolean success = false;
        connection.setTransactionIsolation(Connection. TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);

        plato = searchPlato(nameplate);

        if(plato == null){
            String query = "INSERT INTO Platos (Platos.nameplate, Platos.precio, Platos.id_rest, Platos.descripcion) VALUES (?, ?, ?, ?)";
            try(PreparedStatement st = connection.prepareStatement(query)){
                st.setString(1, nameplate);
                st.setFloat(2, precio);
                st.setInt(3, id_rest);
                st.setString(4,descripcion);        
                st.executeUpdate();

                success = true;
            
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


    public Restaurant searchRestByIdRest(int id_rest) throws SQLException{

        Restaurant restaurant = null;
        String query = "SELECT * FROM Restaurants WHERE id_rest=?";

        try(PreparedStatement st = connection.prepareStatement(query)){
            st.setInt(1, id_rest);
            ResultSet rs = st.executeQuery();
            
           
            if(rs.next()){
                restaurant = new Restaurant();
                restaurant.setIdRest(rs.getInt("id_rest"));
                restaurant.setNameRest(rs.getString("name_rest"));
                restaurant.setAddressRest(rs.getString("address_rest"));
                restaurant.setCiudad(rs.getString("ciudad"));
                restaurant.setPhoneRest(rs.getString("phone_rest"));
                restaurant.setTypeRest(rs.getString("typ_rest"));
                       
            }

        }

        return restaurant;
    } 

    public boolean deletePlate(int id_plate) throws SQLException{

        boolean success = false;
        connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);

        String query = "DELETE FROM Platos WHERE id_plato= ? ";
        try(PreparedStatement st = connection.prepareStatement(query)){
            st.setInt(1, id_plate);
            st.executeUpdate();
            success = true; 
        }
        
        if(success){
            connection.commit();

        }else{
            connection.rollback();
        }

        connection.setAutoCommit(true);
        return success;
    }

    public int insertPlatoOnOrder(int id_user, String ciudad, String address_order, Float precio_total, 
                                        int id_rest, int [] id_plato, int [] cantidad) throws SQLException{

        boolean success = false;
        int id_order = 0;
        connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);

        String query = "INSERT INTO Orders (Orders.state, Orders.id_user,Orders.address_order, Orders.ciudad, Orders.precio_total, Orders.fecha_hora, Orders.id_rest) VALUES (?,?,?,?,?,?,?)";

        try(PreparedStatement st = connection.prepareStatement(query)){

            st.setString(1,"inprogress");
            st.setInt(2,id_user);
            st.setString(3, address_order);
            st.setString(4,ciudad);
            st.setFloat(5, precio_total);
            st.setTimestamp(6, setTimestamp());
            st.setInt(7, id_rest);
            st.executeUpdate();
            success = true;
        }

        if(success){
            connection.commit();
        }else{
            connection.rollback();
        }

        connection.setAutoCommit(true);
        
        String queryOrder = "SELECT * FROM Orders ORDER BY id_order DESC LIMIT 1";
        try(PreparedStatement st1 = connection.prepareStatement(queryOrder)){
            ResultSet rs = st1.executeQuery(); 
            if(rs.next()){
                id_order = rs.getInt("id_order");          
            }
        }
        connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);
        success = false;
        String queryOrderPlate = "INSERT INTO OrderPlato VALUES (?,?,?)";
        for(int i = 0; i<id_plato.length; i++){
            try(PreparedStatement st2 = connection.prepareStatement(queryOrderPlate)){
                st2.setInt(1, id_order);
                st2.setInt(2, id_plato[i]);
                st2.setInt(3, cantidad[i]);
                st2.executeUpdate();
            }
            success = true;
        }
        if(success){
            connection.commit();
        }else{
            connection.rollback();
        }

        connection.setAutoCommit(true);
        return id_order;
    }

    public Order searchOrder(int id_order)throws SQLException{

        String query = "SELECT * FROM Orders WHERE id_order = ?";
        Order order = null;

        try(PreparedStatement st = connection.prepareStatement(query)){

            st.setInt(1, id_order);
            ResultSet rs = st.executeQuery();
          
            if(rs.next()){
                order = new Order();
                order.setIdOrder(rs.getInt("id_order"));
                order.setState(rs.getString("state"));
                order.setIdUser(rs.getInt("id_user"));
                order.setAddressOrder(rs.getString("address_order"));
                order.setPrecioTotal(rs.getFloat("precio_total"));
                order.setFechaHora(rs.getTimestamp("fecha_hora"));
                order.setIdRest(rs.getInt("id_rest"));                   
            }
            

        }
        return order;
    }

    public ArrayList<Plato> searchPlatesOfOrder(int id_order) throws SQLException{

        String query = "SELECT * FROM Platos INNER JOIN OrderPlato ON Platos.id_plato = OrderPlato.id_plato INNER JOIN Orders ON Orders.id_order = OrderPlato.id_order WHERE Orders.id_order = ?";
        ArrayList<Plato> platospedido = new ArrayList<Plato>();

        try(PreparedStatement st = connection.prepareStatement(query)){
            st.setInt(1,id_order);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Plato plato = new Plato();
                plato.setIdPlato(rs.getInt("id_plato"));
                plato.setNamePlate(rs.getString("nameplate"));
                plato.setPrecio(rs.getFloat("precio"));
                plato.setIdRest(rs.getInt("id_rest"));
                plato.setDescripcion(rs.getString("descripcion"));

                platospedido.add(plato);

            }
        }

        return platospedido;
    }

    public int getNumOfPlates(int id_plato, int id_order) throws SQLException{

        String query = "SELECT * FROM OrderPlato WHERE id_plato = ? AND id_order = ?";
        int cantidad = 0;

        try(PreparedStatement st = connection.prepareStatement(query)){
            st.setInt(1,id_plato);
            st.setInt(2,id_order);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                cantidad = rs.getInt("cantidad");
            }
        }

        return cantidad;
    }
    
}
