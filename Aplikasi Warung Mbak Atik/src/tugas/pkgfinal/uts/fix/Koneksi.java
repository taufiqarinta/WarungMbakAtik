package tugas.pkgfinal.uts.fix;

import java.sql.*;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Koneksi {    
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    
    public static Connection BukaKoneksi(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4306/iqbal","root","");
            return con;
        }catch (Exception e){
            JOptionPane.showInternalMessageDialog(null, e);
            return null;
        }
    }
    
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:4306/mydatabase", "root", "password");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        
    public boolean validateUser(String username, String password) {
        boolean isValid = false;
        try {
            stmt = conn.createStatement();
            String query = "SELECT * FROM admin WHERE username='" + username + "' AND password='" + password + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                isValid = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isValid;
    }

}
