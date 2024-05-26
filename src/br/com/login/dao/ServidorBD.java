package br.com.login.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorBD extends Thread{

    private Socket conexao;
    
    Conexao conectaBD = new Conexao();
    
    public static String nome;

    LoginDAO loginDAO = new LoginDAO();
 

    public static void main(String[] args) throws SQLException {
        try (ServerSocket s = new ServerSocket(4444);){
            // ServerSocket s = new ServerSocket(4444);

            while (true) {
                System.out.println("Esperando alguem se conectar...");
                Socket conexao = s.accept();
                System.out.println("Conectou...");

                Thread t = new ServidorBD(conexao);

                t.start();
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e);
        }
    }

       public ServidorBD(Socket s) throws SQLException {
        conexao = s;
        conectaBD.getConnection();
        System.out.println(conectaBD.statusConexao());
    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            while (true) {
                String msgUsuario = entrada.readLine();

                if ("login".equals(msgUsuario)) {
                    String email = entrada.readLine();
                    String senha = entrada.readLine();

                    if (loginDAO.login(email, senha)) {
                        saida.println(nome);
                        System.out.println("Login efetuado com sucesso.");
                        break;
                    } else {
                        saida.println("null");
                        System.out.println("Não foi possível fazer o login.");
                    }
                } else {
                    String nomeUsuario = entrada.readLine();
                    String email = entrada.readLine();
                    String senha = entrada.readLine();

                    if (loginDAO.cadastrarUsuario(nomeUsuario, email, senha)) {
                        saida.println("true");
                        System.out.println("Cadastro efetuado com sucesso.");
                    } else {
                         saida.println("false");
                        System.out.println("Erro ao fazer o cadastro.");
                    }
                }
            }            
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (SQLException ex) {
            Logger.getLogger(ServidorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
