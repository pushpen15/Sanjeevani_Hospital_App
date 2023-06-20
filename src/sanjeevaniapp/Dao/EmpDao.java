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

/**
 *
 * @author User
 */
public class EmpDao {
      public static String getnewid() throws SQLException
    {
     Statement st=DBConnection.getConnection().createStatement();
       ResultSet rs=st.executeQuery("select max(EmpId) from employees");
       int id=1;
     if(rs.next())
     {  
         String empid=rs.getString(1);
         int eno=Integer.parseInt(empid.substring(1));
         id=id+eno;
     }
     String sr="E"+id;
        System.out.println(sr);
        return sr;
}
    public static boolean addEmployee(EmpPojo e) throws SQLException
    {
        
        
       PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into employees values(?,?,?,?)");
        ps.setString(1,e.getEmpid());
        ps.setString(2,e.getEmpname());
        ps.setString(3,e.getJob().toUpperCase());
        ps.setDouble(4,e.getSal());
        int x=ps.executeUpdate();
        return x==1;
                
    }
    public static ArrayList<EmpPojo> getAllEmp() throws SQLException{
       ArrayList<EmpPojo>empList=new ArrayList<>();
     Statement st=DBConnection.getConnection().createStatement();
     ResultSet rs=st.executeQuery("select * from employees");
  
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
    public static EmpPojo findEmpById(String empId)throws SQLException{
    EmpPojo e=null;
     PreparedStatement ps=DBConnection.getConnection().prepareStatement("select * from employees where empid=?");
     ps.setString(1, empId);
     ResultSet rs=ps.executeQuery();
     if(rs.next()){
        e=new EmpPojo();
        e.setEmpid(rs.getString(1));
         e.setEmpname(rs.getString(2));
       e.setJob(rs.getString(3));
       e.setSal(rs.getDouble(4));
     }
     return e;
}
    public static boolean updateEmp(EmpPojo e) throws SQLException, InterruptedException{
    String empid=e.getEmpid();
    String ename=e.getEmpname();
    String job=e.getJob();
    double sal=e.getSal();
    Statement st=DBConnection.getConnection().createStatement();
   int x=st.executeUpdate("update employees set role='"+job+"',sal='"+sal+"',Empname='"+ename+"' where empid='"+empid+"'");
   return x==1;
}
public static boolean deleteEmp(String empid)throws SQLException, InterruptedException{
    
   PreparedStatement ps=DBConnection.getConnection().prepareStatement("Delete from employees where empid=?");
     ps.setString(1, empid);
    int result= ps.executeUpdate();
    return result==1;
}
 public static ArrayList<String> getAllId() throws SQLException{
    ArrayList<String>idList=new ArrayList<>();
    Statement st=DBConnection.getConnection().createStatement();
     ResultSet rs=st.executeQuery("select  empid from employees");
       while(rs.next())
   {
       String s;
       s=rs.getString(1);
       idList.add(s);
       //System.out.println(s);
          
   }
   return idList;
     
 }
 public static HashMap<String,String> getNonRegisteredReceptionistList()throws SQLException{
     String qry="Select Empid,empname from employees where role='RECEPTIONIST'and empid not in(Select empid from users where usertype='RECEPTIONIST')";
     Statement st=DBConnection.getConnection().createStatement();
     ResultSet rs=st.executeQuery(qry);
     HashMap<String,String> receptionist=new HashMap<>();
     while(rs.next()){
         String id=rs.getString(1);
         String name=rs.getString(2);
         receptionist.put(id, name);
     }
    return receptionist; 
 }
  public static HashMap<String,String> getNonRegisteredDoctorList()throws SQLException{
     String qry="Select Empid,empname from employees where role='DOCTOR'and empid not in(Select empid from users where usertype='DOCTOR')";
     Statement st=DBConnection.getConnection().createStatement();
     ResultSet rs=st.executeQuery(qry);
     HashMap<String,String> receptionist=new HashMap<>();
     while(rs.next()){
         String id=rs.getString(1);
         String name=rs.getString(2);
         receptionist.put(id, name);
     }
    return receptionist; 
 }
  public static HashMap<String,String> getRegisteredReceptionistList()throws SQLException{
     String qry="Select  empid,empname from employees where role='RECEPTIONIST'and empid in (Select empid from users where usertype='RECEPTIONIST')";
     Statement st=DBConnection.getConnection().createStatement();
     ResultSet rs=st.executeQuery(qry);
     HashMap<String,String> receptionist=new HashMap<>();
     while(rs.next()){
         String id=rs.getString(1);
         String name=rs.getString(2);
         receptionist.put(id, name);
     }
    return receptionist; 
 }
}
