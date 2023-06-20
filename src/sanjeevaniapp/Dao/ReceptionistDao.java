/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sanjeevaniapp.dbutil.DBConnection;
import sanjeevaniapp.pojo.EmpPojo;
import sanjeevaniapp.pojo.UserPojo;

/**
 *
 * @author User
 */
public class ReceptionistDao {
    public static boolean addReceptionist(UserPojo user)throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?)");
        ps.setString(1,user.getUserid());
        ps.setString(2,user.getUsername());
        ps.setString(3,user.getEmpId());
        ps.setString(4,user.getPassword());
         ps.setString(5,user.getUsertype());
        int x=ps.executeUpdate();
        return x>0;
    }
    
  
    public static String getpwd(String rId)throws SQLException{
         PreparedStatement ps=DBConnection.getConnection().prepareStatement("select * from users where empid=?");
     ps.setString(1, rId);
     ResultSet rs=ps.executeQuery();
     rs.next();
     String spwd =rs.getString("password");
     return spwd;
    }
  
     public static ArrayList<String> getAllRecepId() throws SQLException{
    ArrayList<String>idList=new ArrayList<>();
    Statement st=DBConnection.getConnection().createStatement();
     ResultSet rs=st.executeQuery("select  empid from employees where role='RECEPTIONIST'");
       while(rs.next())
   {
       String s;
       s=rs.getString(1);
       idList.add(s);
       //System.out.println(s);
          
   }
   return idList;
     
 }
}
