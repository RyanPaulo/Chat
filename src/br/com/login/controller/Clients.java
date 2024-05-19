package br.com.login.controller;

import static br.com.login.view.ChatView.jt_message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Clients extends Thread {

    private static boolean done = false;

    private Socket conexao; 

    public Clients(Socket s) {
        conexao = s;
    }

    public Socket getConexao() {
        return conexao;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean valor) {
        done = valor;
    }

    public void run() {        
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            while (true) {
                // Pega o que o servidor enviou.
                linha = entrada.readLine();
                // Verifica se é uma linha válida. Pode ser que a conexão
                // foi interrompida. Neste caso, a linha é null. Se isso
                // ocorrer, termina-se a execução saindo com break
                if (linha == null) {
                    System.out.println("Conexão encerrada!");
                    break;
                }
                // Caso a linha não seja nula, deve-se imprimi-la.
                jt_message.setText(jt_message.getText() + "\n" + linha);
            }
        } catch (IOException e) {
            // Caso ocorra alguma exceção de E/S, mostre qual foi.
            System.out.println("IOException: " + e);
        }
        // Sinaliza para o main que a conexão encerrou.
        done = true;
    }
}
