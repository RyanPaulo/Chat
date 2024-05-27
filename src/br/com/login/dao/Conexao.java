
package br.com.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ryan Paulo
 */
public class Conexao {
    
    private static String status = "Não conectou...";

    Connection connection = null;


    private String driverName = "com.mysql.cj.jdbc.Driver";
    private String serverName = "localhost:3306";
    private String myDataBase = "chat";
    private String url = "jdbc:mysql://" + serverName + "/" + myDataBase;
    //Parametro de login pessoal do MySql
    private String userName = "root"; 
    private String passWord = "Rps4303.";

    /**
     * Metodo para fazer a conexao com o Banco de dados, Mysql.
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException{
        

        try {
            Class.forName(driverName);
            //Endereço de conexao do MySql
            Connection conexao = (Connection) DriverManager.getConnection(url, userName, passWord);
            //Vericação da conexao
            if (conexao != null) {
                status = ("STATUS> Conectado com sucesso!");
            }else {
                status = ("STATUS> Não foi possivel realizar conexão");
            }


            return conexao;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");

            return null;
        }
    
    }

    /**
     * Método que retorna o status da conexão
     * 
     */
    public String statusConexao() {
        return status;
    }

    /**
     * Método que fecha a conexão 
     * 
     */
    public boolean FecharConexao() {
        try {
            getConnection().close();
           return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Método que reinicia a conexao
     * @throws SQLException 
     * 
     */
    public Connection ReiniciarConexao() throws SQLException {
        FecharConexao();
        return getConnection();
    }


}
