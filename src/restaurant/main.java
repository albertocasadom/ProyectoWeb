package restaurant;

import java.sql.SQLException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class main {

	public static void main(String[] args) {
            if (args.length < 1) {
                error();
            }
	    try (DBManager manager = new DBManager()) {
	        if (args[0].equals("search")) {
	            if (args.length < 2) {
	                error();
	            }
	            //String isbn = args[1];
	            String name = args[1];
	            String surname = args[2];
	            String mail = args[3];
	            String pass = args[4];
	            String addr = args[5];
	            boolean bool = manager.insertUser(name, surname, mail, pass, addr);
	            if (bool == true) {
	                System.out.println("Funciona");
	            } else {
	                System.out.println("Book not found: ");
	            }
	        } 
	    } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        catch (NamingException exN){

          exN.printStackTrace();

        }
	}

	public static void error() {
		System.err.println("Wrong command-line arguments. Use:");
		System.err.println("java bookshop.BookShop search <isbn>");
		System.err.println("java bookshop.BookShop stock <isbn>");
		System.err.println("java bookshop.BookShop sell "
                                   + "<isbn> <units>");
		System.err.println("java bookshop.BookShop list");
		System.exit(1);
	}
}
