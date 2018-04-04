package restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


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

    public User searchUser(String mail) throws SQLException {
        
        String query = "SELECT * FROM Users WHERE mail='" + mail + "'";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if(rs.next()){

            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String mailuser = rs.getString("mail");
            String pass = rs.getString("pass");
            String address = rs.getString("address");
            int id = rs.getInt("id");
            int idOrder = rs.getInt("id_order");
            int idType = rs.getInt("id_type");

            User user = new User();

            user.setName(name);
            user.setSurname(surname);
            user.setMail(mailuser);
            user.setPass(pass);
            user.setAddress(address);
            user.setId(id);
            user.setIdOrder(idOrder);
            user.setIdType(idType);

            return user;

        }else{

            return null;

        }

    }

    public boolean insertUser(String name, String surname, String mail, String pass, String address)throws SQLException{

        User checkuser = searchUser(mail);
        boolean success = false; 

        if(checkuser == null){

            String query = "INSERT INTO Users (Users.name, Users.surname, Users.mail, Users.pass, Users.address, Users.id_type) VALUES (" +"'" + name + "'," + "'" + surname + "'," + "'" + mail + "'," + "'" + pass + "'," + "'" + address + "'," + 1 + ")";
            PreparedStatement stuser = connection.prepareStatement(query);
            stuser.executeUpdate();
            success = true;

        }

        return success;

    }

}
