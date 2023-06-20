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
import java.util.HashMap;
import sanjeevaniapp.dbutil.DBConnection;
import sanjeevaniapp.pojo.EmpPojo;
import sanjeevaniapp.pojo.User;

/**
 *
 * @author User
 */
public class UserDao {
    public static String validateUser(User user)throws SQLException{
         String qry="Select username from Users where userid=? and password=? and usertype=?";
         PreparedStatement ps=DBConnection.getConnection().prepareStatement(qry);
         System.out.println(user);
         ps.setString(1, user.getUserId());
         ps.setString(2, user.getPassword());
         ps.setString(3, user.getUserType());
         ResultSet rs=ps.executeQuery();
         String username=null;
         if(rs.next())
         {
             username=rs.getString(1);
             
         }
         return username;
    }
    
      public static boolean updatePassword(String rId,String npwd)throws SQLException
    {
         PreparedStatement st=DBConnection.getConnection().prepareStatement("update users set password= ? where userid=?");
       st.setString(1, npwd);
       st.setString(2, rId);
       return (st.executeUpdate()!=0);
    }
      public static HashMap<String,String> getAllReceptionistIdName() throws SQLException{
     HashMap<String,String> hs=new HashMap<>();
     Statement st=DBConnection.getConnection().createStatement();
     ResultSet rs=st.executeQuery("select  all userid,username from users where usertype='RECEPTIONIST'");
  
   while(rs.next())
   {
     hs.put(rs.getString(1), rs.getString(2));
   }
   return hs;
    } 
        public static ArrayList<EmpPojo> getAllReceptionist() throws SQLException{
       ArrayList<EmpPojo>empList=new ArrayList<>();
     Statement st=DBConnection.getConnection().createStatement();
     ResultSet rs=st.executeQuery("select * from employees where  role='RECEPTIONIST'");
  
   while(rs.next())
   {
       EmpPojo e=new EmpPojo();
       e.setEmpid(rs.getString(1));
       e.setEmpname(rs.getString(2));
       e.setJob(rs.getString(3));
       e.setSal(rs.getDouble(4));
       empList.add(e);
   }
   return empList;
    }
         public static ArrayList<EmpPojo> getAllDoctors() throws SQLException{
       ArrayList<EmpPojo>empList=new ArrayList<>();
     Statement st=DBConnection.getConnection().createStatement();
     ResultSet rs=st.executeQuery("select * from employees where  role='DOCTOR'");
  
   while(rs.next())
   {
       EmpPojo e=new EmpPojo();
       e.setEmpid(rs.getString(1));
       e.setEmpname(rs.getString(2));
       e.setJob(rs.getString(3));
       e.setSal(rs.getDouble(4));
       empList.add(e);
   }
   return empList;
    }
}
