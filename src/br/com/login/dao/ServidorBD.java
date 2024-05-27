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


/**
 *
 * @Thread indicando que ela será executada como uma thread separada.
 * @author Ryan Paulo
 */
public class ServidorBD extends Thread{

    //Socket para as conexoes com os clientes
    private Socket conexao;
    //Instancia para gerenciar a conexão com o banco de dados.
    Conexao conectaBD = new Conexao();
    // Nome deste cliente
    public static String nome;
    //Instancia para lida com operações de banco de dados
    LoginDAO loginDAO = new LoginDAO();
 
    /**
     * Metodo para iniciar o servido do Banco de dados, ouvindo na porta 4444.
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        try (ServerSocket s = new ServerSocket(4444);){

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

    /**
     *  Construtor que inicializa a conexao com o Socket
     * @param s
     * @throws SQLException
     */
    public ServidorBD(Socket s) throws SQLException {
        conexao = s;
        conectaBD.getConnection();
        System.out.println(conectaBD.statusConexao());
    }

    /**
     * Metodo que de entrada para a thread. Le a mensagens do cliente e realiza o login ou o registro do usuário
     */
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

    /**
     * Metodo que fecha a conexão do socket.
     */
    public void fecharConexao() {
        try {
            conexao.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
