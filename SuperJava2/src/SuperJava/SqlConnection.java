
//PPE 3: Supercar Java Version 3.0
//Created By: Aakash Chady
//Date Created:22/03/2021
//Date Modified (Version 3.0): 26/04/2021


package SuperJava;
import java.sql.Connection;
import java.sql.DriverManager;


public class SqlConnection {
   public static Connection Conn () {
	   try {
		   String driver = "com.mysql.jdbc.Driver";
		   String url = "jdbc:mysql://localhost/superjava";
		   Class.forName (driver);
		   return DriverManager.getConnection(url, "root", "");
	   } catch (Exception e) {
		   System.err.println("Connection Failed!!");
	   }
	  return null; 
	  
   }
   
  public static void main(String[] args) {
	  if (Conn() != null) {
		  System.out.println("Connection Successful!!");
	  }
  }
}
