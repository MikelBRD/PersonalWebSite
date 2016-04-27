package info.mikelberdufi.it;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
 
public class LoginDB {
 

    public static boolean validate(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;
 
        try {
            con = getConnection();
            ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, password);
 
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                //result found, means valid inputs
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
           close(con);
        }
        return false;
    }
	public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cardb", "pankaj", "pankaj123");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->"
                    + ex.getMessage());
            return null;
        }
    }
 
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
	
}