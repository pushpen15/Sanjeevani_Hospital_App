/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sanjeevaniapp.dbutil.DBConnection;
import sanjeevaniapp.pojo.EmpPojo;
import sanjeevaniapp.pojo.PatientPojo;

/**
 *
 * @author User
 */
public class PatientDao {
  public static boolean addPatient(PatientPojo p)throws SQLException
    {
       System.out.print(p.getP_id()+" "+p.getF_name()+" "+p.getS_name()+" "+p.getAge()+" "+p.getOpd()+" "+p.getAddress()+" "+p.getCity()+" "+p.getMno()+" "+p.getDoctor_id()+" "+p.getM_status()+" "+p.getGender()+" "+p.getDate());
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into patients (P_ID,F_NAME,S_NAME,AGE,OPD,GENDER,M_STATUS,P_DATE,ADDRESS,CITY,PHONE_NO,DOCTORID) values (?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, p.getP_id());
        ps.setString(2, p.getF_name());
        ps.setString(3, p.getS_name());
        ps.setInt(4, p.getAge());
        ps.setString(5, p.getOpd());
        ps.setString(6, p.getGender());
        ps.setString(7, p.getM_status());
        ps.setDate(8,p.getDate());
        ps.setString(9, p.getAddress());
        ps.setString(10, p.getCity());
        ps.setString(11,p.getMno());
        ps.setString(12,p.getDoctor_id());
        return (ps.executeUpdate()!=0);    
    }

  public static String getNewId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select max(p_id) from patients");
        int id = 1;
        if(rs.next())
        {
        String empid=rs.getString(1);
        System.out.println(empid.substring(1));
	int eno=Integer.parseInt(empid.substring(1));
	id = id + eno;
        String sr = "P" + id;
        System.out.println(sr);
        return sr;
        }
        else 
        return "P101";
    }

     public static PatientPojo findPatientById(String PId)throws SQLException{
    PatientPojo e=null;
     PreparedStatement ps=DBConnection.getConnection().prepareStatement("select * from patients where p_id=?");
     ps.setString(1, PId);
     ResultSet rs=ps.executeQuery();
     if(rs.next()){
        e=new PatientPojo();
       e.setP_id(rs.getString(1));
         e.setF_name(rs.getString(2));
       e.setS_name(rs.getString(3));
       e.setAge(rs.getInt(4));
       e.setOpd(rs.getString(5));
     
       e.setGender(rs.getString(6));
         e.setM_status(rs.getString(7));
       e.setAddress(rs.getString(9));
       e.setCity(rs.getString(10));
       e.setMno(rs.getString(11));
       e.setDoctor_id(rs.getString(12));
     }
     return e;
}
    public static boolean updateEmp(PatientPojo p) throws SQLException, InterruptedException{
    
    
    String opd=p.getOpd();
    String f_name=p.getF_name();
    String s_name=p.getS_name();
     int age=p.getAge();
    String p_id=p.getP_id();
    String gender=p.getGender();
    String m_status=p.getM_status();
    String address=p.getAddress();
    String city=p.getCity();
    String mno=p.getMno();
     String doctorid=p.getDoctor_id();
   
   
    Statement st=DBConnection.getConnection().createStatement();
   int x=st.executeUpdate("update patients set F_name='"+f_name+"',s_name='"+s_name+"',opd='"+opd+"',gender='"+gender+"',m_status='"+m_status+"',address='"+address+"',doctorid='"+doctorid+"',city='"+city+"',phone_no='"+mno+"',age="+age+"where p_id='"+p_id+"'");
   return x==1;
}
     public static ArrayList<String> getPatientsId() throws SQLException{
         ArrayList<String>idList=new ArrayList<>();
    Statement st=DBConnection.getConnection().createStatement();
     ResultSet rs=st.executeQuery("select  p_id from patients");
       while(rs.next())
   {
       String s;
       s=rs.getString(1);
       idList.add(s);
       //System.out.println(s);
          
   }
        System.out.println(idList);
   return idList;
      
    }
       public static ArrayList<PatientPojo> getAllPatients() throws SQLException{
       ArrayList<PatientPojo>empList=new ArrayList<>();
     Statement st=DBConnection.getConnection().createStatement();
     ResultSet rs=st.executeQuery("select * from patients");
  
   while(rs.next())
   {
       PatientPojo e=new PatientPojo();
         e.setP_id(rs.getString(1));
         e.setF_name(rs.getString(2));
       e.setS_name(rs.getString(3));
       e.setAge(rs.getInt(4));
       e.setOpd(rs.getString(5));
     
       e.setGender(rs.getString(6));
         e.setM_status(rs.getString(7));
         e.setDate(rs.getDate(8));
       e.setAddress(rs.getString(9));
       e.setCity(rs.getString(10));
       e.setMno(rs.getString(11));
       e.setDoctor_id(rs.getString(12));
       empList.add(e);
   }
   return empList;
    }
       public static boolean deletePatient(String empid)throws SQLException, InterruptedException{
    
   PreparedStatement ps=DBConnection.getConnection().prepareStatement("Delete from patientss where p_id=?");
     ps.setString(1, empid);
    int result= ps.executeUpdate();
    return result==1;
}
     public static PatientPojo findPatient(String PId)throws SQLException{
    PatientPojo e=null;
     PreparedStatement ps=DBConnection.getConnection().prepareStatement("select * from patients where p_id=?");
     ps.setString(1, PId);
     ResultSet rs=ps.executeQuery();
     if(rs.next()){
        e=new PatientPojo();
       e.setP_id(rs.getString(1));
       e.setF_name(rs.getString(2));
       e.setS_name(rs.getString(3));
     }
     return e;
        }     
}
