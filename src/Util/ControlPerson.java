/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Thinh
 */
public class ControlPerson {
    ConnectBanco conecta = new ConnectBanco();
    public void inserir(ModelPerson mod){
    try{
        conecta.conexao();
        PreparedStatement pst = conecta.conn.prepareStatement("INSERT INTO person (id,first_name,last_name,dob,office) value (?,?,?,?,?)");
        pst.setInt(1,mod.getId());
        pst.setString(2,mod.getFisrt_name());
        pst.setString(3,mod.getLast_name());
         pst.setString(4,mod.getDob());
          pst.setString(5,mod.getOffice());
          pst.executeUpdate();
          System.out.println("Datos do (a):" + mod.getLast_name()+ "cadastrados");
          conecta.desconnectta();
          
               
    }
    catch (SQLException ex){
    
    System.out.println("Erorr:" +ex);
    }
    
    }
}
