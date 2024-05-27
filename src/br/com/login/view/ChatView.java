package br.com.login.view;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.DefaultCaret;

import br.com.login.controller.Clients;

/**
 * Class que representa a interface do chat
 * @author Ryan Paulo
 */
public class ChatView extends javax.swing.JFrame {

    Clients client;

    public static LoginView loginView = new LoginView();

    /**
    * Construtor para receber os parametros da inteface do chat
    */
    public ChatView() {
        initComponents();
        this.setResizable(false);
        getRootPane().setDefaultButton(jb_enviar);
        this.setLocationRelativeTo(null);
        DefaultCaret caret = (DefaultCaret) jt_message.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }


    /**
     * Metodo para estabelecer a conexao com servido principal
     */
    public void conecta() {
        jl_cliete.setText("Chat Global " + loginView.nomeUsuario);
        jt_message.setText(LoginView.nomeUsuario + " entrou no chat");
        try {
            Socket conexao = new Socket("127.0.0.1", 3333);
            this.client = new Clients(conexao);

            PrintStream saida = new PrintStream(conexao.getOutputStream());
            saida.println(loginView.nomeUsuario);

            Thread t = this.client;
            t.start();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    /**
     * Metodo que envia uma mensagem para o servidor, que será enviada aos outros clientes.
     * @param msg
     * @throws IOException
     */
    public void sendMsg(String msg) throws IOException {
        jt_message.setText(jt_message.getText() + "\nVocê disse > " + jt_enviarMsg.getText());
        jt_enviarMsg.setText("");
        PrintStream saida = new PrintStream(client.getConexao().getOutputStream());
        // Antes de enviar, verifica se a conexão não foi fechada.
        if (client.getDone()) {
            System.exit(0);
        }
        saida.println(msg);
    }

    /**
     * Define a disposição dos componentes na interface.
     */
    private void initComponents() {

        jt_enviarMsg = new javax.swing.JTextField();
        jb_voltar = new javax.swing.JButton();
        jl_cliete = new javax.swing.JLabel();
        jb_enviar = new javax.swing.JButton();
        jt_message = new javax.swing.JTextArea();
        jl_imagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jt_enviarMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 612, 440, 40));

        jb_voltar.setContentAreaFilled(false);
        jb_voltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_voltarActionPerformed(evt);
            }
        });
        getContentPane().add(jb_voltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 60, 40));

        jl_cliete.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 25)); // NOI18N
        jl_cliete.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jl_cliete, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 440, 50));

        jb_enviar.setContentAreaFilled(false);
        jb_enviar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_enviarActionPerformed(evt);
            }
        });
        getContentPane().add(jb_enviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 610, 50, 40));

        jt_message.setEditable(false);
        jt_message.setBackground(new java.awt.Color(46, 115, 155));
        jt_message.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18));
        jt_message.setForeground(new java.awt.Color(255, 255, 255));
        jt_message.setColumns(20);
        jt_message.setRows(5);
        getContentPane().add(jt_message, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 550, 470));

        jl_imagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resouces/TelaChat.png"))); // NOI18N
        getContentPane().add(jl_imagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }                     
    /**
     * Função do botao voltar para a tela de login
     * @param evt
     */
    private void jb_voltarActionPerformed(java.awt.event.ActionEvent evt) {  
        this.setVisible(false);
        loginView.setVisible(true);

    }          
    
    /**
     * Função do botao para fazer o envio da mensagem
     * @param evt
     */
    private void jb_enviarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        if (!"".equals(jt_enviarMsg.getText())) {
            try {
                sendMsg(jt_enviarMsg.getText());
            } catch (IOException ex) {
                Logger.getLogger(ChatView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            jt_enviarMsg.grabFocus();
        }
    }
                  
    private javax.swing.JLabel jl_imagem;
    private javax.swing.JButton jb_enviar;
    private javax.swing.JButton jb_voltar;
    private javax.swing.JLabel jl_cliete;
    public static javax.swing.JTextArea jt_message;
    private javax.swing.JTextField jt_enviarMsg;                  
}
