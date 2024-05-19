package br.com.login.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.login.view.HomeView;

public class Server extends Thread{

    private static HashMap<String, PrintStream> clients = new HashMap<>();

    private static ArrayList clientsNome = new ArrayList<>();

    private Socket conexao;
    private String meuNome;
    private static HomeView home;

    public static void main(String[] args) {
        try {
             ServerSocket s = new ServerSocket(3333);

             System.out.println("Esperando alguem se conectar...");
             Socket conexao = s.accept();
             System.out.println("Conectou!...");

             Thread t = new Server(conexao, home);
             t.start();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }


    public Server(Socket s, HomeView home) {
        conexao = s;
        this.home = home;
    }


    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());

            meuNome = entrada.readLine();

            clients.put(meuNome, saida);

           // home.atualizarContatos(meuNome);

            sendToAll(saida, " entrou ", "no chat");

            saida.println("Número de usuários conectados: " + clients.size());

            String linha = entrada.readLine();
            while (linha != null && !(linha.trim().equals(""))) {
                sendToAll(saida, " disse > ", linha);
                linha = entrada.readLine(); 
            }
            sendToAll(saida, " saiu ", "do chat!");
            clients.remove(saida);
            conexao.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public void sendToAll(PrintStream saida, String acao, String linha) throws IOException {
        for (PrintStream chat : clients.values()) {
            if (chat != saida) {
                chat.println(meuNome + acao + linha);           
            }

        }
    }

    public void sendToOne(PrintStream nomeDestino, String acao, String linha) throws IOException {
        PrintStream chatDestino = clients.get(nomeDestino);
        if (chatDestino != null) {            
            chatDestino.println((meuNome + acao + linha));        
        }else {
            System.out.println("Usuario " + nomeDestino + "não encontrado");
        }
            
        
    } 

}
