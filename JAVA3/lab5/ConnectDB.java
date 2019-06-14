package baitap.lab5;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String userName = "hunga";
            String password = "ABCabc.12";
            String url = "jdbc:mysql://localhost:3306/";
            //Class.forName("com.mysql.jdbc.Driver");
            // driver register
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Da ket noi CSDL");
        } catch (Exception e) {
            System.err.println("Khong ket noi duoc");
            e.printStackTrace();
        }
        finally{
            if(conn != null) {
                try {
                    conn.close();
                    System.out.println("Dong ket noi");
                }
                catch(Exception e) {
                    
                }
            }
        }
        
    }
}
