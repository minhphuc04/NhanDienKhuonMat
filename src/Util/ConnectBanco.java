/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Thinh
 */
public class ConnectBanco {
    public Statement stm;
    public ResultSet rs;
    public Connection conn;
     private final  String driver = "org.mysql.Driver";
    private final  String root = "jdbc.mysql://127.0.0.1/database";
    private final  String user = "root";
    private final  String pass = "";
    public void conecta(){
        try{
     System.setProperty("jdbc.Driver ", driver);
    conn = DriverManager.getConnection(root, user , pass);
    System.out.print("OK");
    
        }catch(SQLException e){
        System.out.println("Error: " + e);
        
        }
 
    
    }
    public void desconnectta(){
    try{
        conn.close();
    
    }catch (SQLException e)
    {
      System.out.println("Error: " + e);
        
    
    }
    
    
    }
    public void executeSQL(String SQL){
    
     try{
         stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
         rs = stm.executeQuery(SQL);
    }catch (SQLException e)
    {
      System.out.println("Error: " + e);
        
    
    }
    
    }
    
    
}
