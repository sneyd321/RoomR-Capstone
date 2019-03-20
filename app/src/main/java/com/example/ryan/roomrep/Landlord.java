package com.example.ryan.roomrep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Landlord {

    String res = "";




    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public Landlord(){
        firstName = "";
        lastName = "";
        password = "";
        email = "";
    }

    public Landlord(String firstName, String lastName, String password, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String addValues(){
        String url = "jdbc:mysql://146.148.82.166:3306/RoomRDB";
        String user = "sneyd321";
        String pass = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            String result = "Database Connection Successful\n";
            Statement st = con.createStatement();
            String sql = "INSERT INTO Landlords VALUES ('"
                    + this.firstName + "','"
                    + this.lastName + "','"
                    + "" + this.password + "','"
                    + this.email + "')";
            st.executeUpdate(sql);

            res = result;
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            res = e.toString();
        }
        return res;
    }


}
