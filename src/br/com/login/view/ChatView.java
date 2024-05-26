package br.com.login.view;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.DefaultCaret;

import br.com.login.controller.Clients;

/**
 *
 * @author Ryan Paulo
 */
public class ChatView extends javax.swing.JFrame {

    Clients client;
  
    public ChatView() {
        initComponents();
        this.setResizable(false);
        getRootPane().setDefaultButton(jb_enviar);
        this.setLocationRelativeTo(null);
        DefaultCaret caret = (DefaultCaret) jt_message.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }



    public void conecta() {
        jl_cliete.setText("Chat Global " + LoginView.nomeUsuario);
        jt_message.setText(LoginView.nomeUsuario + " entrou no chat");
        try {
            Socket conexao = new Socket("127.0.0.1", 3333);
            this.client = new Clients(conexao);

            PrintStream saida = new PrintStream(conexao.getOutputStream());
            saida.println(LoginView.nomeUsuario);

            Thread t = this.client;
            t.start();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

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
                      
    private void initComponents() {

        jt_enviarMsg = new javax.swing.JTextField();
        jb_voltar = new javax.swing.JButton();
        jl_cliete = new javax.swing.JLabel();
        jb_enviar = new javax.swing.JButton();
        jt_message = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resouces/TelaChat.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void jb_voltarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }          
    
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jb_enviar;
    private javax.swing.JButton jb_voltar;
    private javax.swing.JLabel jl_cliete;
    public static javax.swing.JTextArea jt_message;
    private javax.swing.JTextField jt_enviarMsg;
    // End of variables declaration                   
}
