/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.login.controller;

import br.com.login.dao.Conexao;
import br.com.login.dao.LoginDAO;
import br.com.login.view.CadastroView;
import br.com.login.view.HomeView;
import br.com.login.view.LoginView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;

/**
 *
 * @author Ryanp
 */
public class LoginController {
    
    MD5Controller md5 = new MD5Controller();

   


  

    public void cadastroUsuario(CadastroView view) throws SQLException{
        

        
        Connection conexao = new Conexao().getConnection(); 
        LoginDAO cadastro = new LoginDAO();
        String senha = md5.gerarMD5(view.getJpf_senha().getText());

        cadastro.cadastrarUsuario(view.getJt_nome().getText(), view.getJt_email().getText(), senha);
        
    }


    
    //   public void loginUsuario(LoginView view) throws SQLException{
    //     CadastroView cadastro = new CadastroView();
    //     HomeView home = new HomeView();

    //     conectaBD();

    //     PrintStream saida;
    //     try {
            
    
    //         BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
    //         MD5Controller md5 = new MD5Controller();
    //         String senha = new String(view.getJp_senha().getPassword());

    //         saida = new PrintStream(conexao.getOutputStream());
    //         saida.println("login");
    //         saida.println(view.getJt_login().getText());
    //         saida.println(md5.gerarMD5(senha));

    //         String nomeUsuario = entrada.readLine();
          

    //         if(!"null".equals(nomeUsuario)){
    //             view.setVisible(false);
    //             cadastro.setVisible(false);
    //             //home.conecta();
    //             home.setVisible(true);
    //             saida.close();
    //         }else {
    //             view.limpaCampos();
    //             view.getJl_aviso().setText("Usuario ou senha invalido.");
    //             view.getJl_aviso().setVisible(true);
    //         }
    //     } catch (IOException ex) {
    //         Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
    //     }

    // }

}
    
    

