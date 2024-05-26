
package br.com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ryan Paulo
 */
public class LoginDAO {
    
    public boolean cadastrarUsuario(String nome, String email, String senha) throws SQLException{
        Connection conexao = new Conexao().getConnection();
        String sql = "INSERT INTO login (nome, email, senha) VALUES ( ?, ?, ?) ";
        //System.out.println(sql);
        try (PreparedStatement statment = conexao.prepareStatement(sql)) {
            statment.setString(1, nome);
            statment.setString(2, email);
            statment.setString(3, senha);
            statment.execute();
            return true;
        }
    
    }
  
    
    public boolean login(String email, String senha) throws SQLException {
        Connection conexao = new Conexao().getConnection();


        String sql = "SELECT nome, email, senha from login WHERE email = '"+email+"' AND senha = '"+senha+"' ";
        System.out.println(sql);
        Statement statment = conexao.createStatement();
        ResultSet rs = statment.executeQuery(sql);
        
        if(rs != null && rs.next()){
            System.out.println("Possui");
            ServidorBD.nome = rs.getString("nome");
            
            return true;
        }else {
            System.out.println("Não possui");
        }
        
        return false;
    
    }
}
