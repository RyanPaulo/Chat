/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.login.dao;


import java.sql.*;

import br.com.login.controller.LoginController;

/**
 *
 * @author Ryanp
 */
public class LoginDAO {
    
    public boolean cadastrarUsuario(String nome, String email, String senha) throws SQLException{
        Connection conexao = new Conexao().getConnection();
        String sql = "INSERT INTO login (nome, email, senha) VALUES ('"+nome+"', '"+email+"', '"+senha+"') ";
        System.out.println(sql);
        try (PreparedStatement statment = conexao.prepareStatement(sql)) {
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
