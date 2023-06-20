/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DBConnection {
    private static Connection conn;
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/myhms","root","student");
             JOptionPane.showMessageDialog(null,"Connection Done Successfully!","Success!",JOptionPane.INFORMATION_MESSAGE);
        }
         catch(ClassNotFoundException cnfe)
        {
             JOptionPane.showMessageDialog(null,"Cannot Load the Driver","Error!"+cnfe,JOptionPane.ERROR_MESSAGE);
             cnfe.printStackTrace();
        }
        catch (SQLException sqle) {
           JOptionPane.showMessageDialog(null,"Problem in DB","Error!"+sqle,JOptionPane.ERROR_MESSAGE);
           sqle.printStackTrace();
        } 
    }
  public static Connection getConnection(){
      return conn;
  }  
  public static void closeConnection()
          {
             try{
                 if(conn!=null)
                 conn.close();
                 JOptionPane.showMessageDialog(null,"Connection Closed Successfully!","Success!",JOptionPane.INFORMATION_MESSAGE);
             } 
               catch (SQLException sqle) {
           JOptionPane.showMessageDialog(null,"Problem in Closing Connection","Error!"+sqle,JOptionPane.ERROR_MESSAGE);
           sqle.printStackTrace();
        } 
          }
}
