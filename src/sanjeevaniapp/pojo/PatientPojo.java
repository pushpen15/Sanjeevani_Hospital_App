/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.pojo;

import java.sql.Date;

/**
 *
 * @author User
 */
public class PatientPojo {

    public PatientPojo() {
        //To change body of generated methods, choose Tools | Templates.
    }

    public PatientPojo(String opd, String doctor_id, String f_name, String s_name, int age, String p_id, String gender, String m_status, String address, String city, String Mno, Date date) {
               this.opd = opd;
        this.doctor_id = doctor_id;
        this.f_name = f_name;
        this.s_name = s_name;
        this.age = age;
        this.p_id = p_id;
        this.gender = gender;
        this.m_status = m_status;
        this.address = address;
        this.city = city;
       this.Mno=Mno;
        this.date = date;

    }

    public String getOpd() {
        return opd;
    }

    public void setOpd(String opd) {
        this.opd = opd;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getM_status() {
        return m_status;
    }

    public void setM_status(String m_status) {
        this.m_status = m_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMno() {
        return Mno;
    }

    public void setMno(String Mno) {
        this.Mno = Mno;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
    private String opd;
    private String doctor_id;
    private String f_name;
    private String s_name;
    private int age;
    private String p_id;
    private String gender;
    private String m_status;
    private String address;
    private String city;
    private String Mno;
    private Date date;
    
}
