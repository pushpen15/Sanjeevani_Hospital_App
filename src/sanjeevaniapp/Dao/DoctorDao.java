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
import sanjeevaniapp.pojo.DoctorPojo;
import sanjeevaniapp.pojo.DoctorUpdatePojo;
import sanjeevaniapp.pojo.EmpPojo;
import sanjeevaniapp.pojo.UserPojo;

/**
 *
 * @author User
 */
public class DoctorDao {
    public static boolean addDoctor(DoctorPojo user)throws SQLException{
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,'Y')");
        ps.setString(1,user.getUserId());
        ps.setString(2,user.getDocId());
        ps.setString(3,user.getQualification());
        ps.setString(4,user.getSpecialist());
         ps.setString(5, String.valueOf(user.getActive()));
        int x=ps.executeUpdate();
        return x>0;
    }

   
    public static ArrayList<String> getAllDoctorsId()throws SQLException
    {
        ArrayList<String> docId = new ArrayList<>();
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery("select doctorid from doctors where Active='Y'");
        while(rs.next())
        {
            docId.add(rs.getString(1));
        }
        return docId;
    }
   

      public static String getNewId()throws SQLException{
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("Select max(DOCTORID) from DOCTORS");
        int id=1;
        if(rs.next()){
            String empid=rs.getString(1);
            System.out.println(empid.substring(1));
            int eno=Integer.parseInt(empid.substring(1));
            id=id+eno;
            String str="DOC"+id;
            return str;
        }
        else
       return "DOC101";
    }
        public static boolean updateQS(DoctorPojo e,double sal) throws SQLException, InterruptedException{
    String doctorid=e.getDocId();
    String ename=e.getUserId();
    String Q=e.getQualification();
    String S=e.getSpecialist();
    
  
    Statement st=DBConnection.getConnection().createStatement();
   int x=st.executeUpdate("update doctors set Qualification ='"+Q+"',Specialist='"+S+"'where doctorid='"+doctorid+"'");
   if( x==1){
       x=st.executeUpdate("update employees set sal="+sal+"where userid=(select userid from doctors where doctorid='"+doctorid+"')");
   }
   return x==1;
}
     public static boolean DeleteDoctor(String doctorid) throws SQLException, InterruptedException{
  
    Statement st=DBConnection.getConnection().createStatement();
   int x=st.executeUpdate("update doctors set active='N'where doctorid='"+doctorid+"'");
   return x==1;
}   
  public static DoctorUpdatePojo  getDoctorInfo(String doctorid )throws SQLException {
      DoctorUpdatePojo d=null;
      PreparedStatement ps=DBConnection.getConnection().prepareStatement("select qualification,specialist from doctors where doctorid=?");
     ps.setString(1, doctorid);
     ResultSet rs=ps.executeQuery();
     if(rs.next()){
        d=new DoctorUpdatePojo();
      
      d.setQualification(rs.getString(1));
    
      d.setSpecialization(rs.getString(2));
     }
     ps=DBConnection.getConnection().prepareStatement("select name,sal from employees where empid=(select empid from users where userid=(select userid from doctors where doctorid='"+doctorid+"'");
    
     rs=ps.executeQuery();
     if(rs.next()){
        d=new DoctorUpdatePojo();
      
  d.setName(rs.getString(1));
    
      d.setSal(rs.getDouble(2));
     }
     
      return d;
      
  }    
}
