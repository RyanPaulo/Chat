package br.com.login.view;

import br.com.login.controller.MD5Controller;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;


/**
 *
 * @author Ryan Paulo
 */
public class LoginView extends javax.swing.JFrame {


    public static Socket conexao;
    public static String nomeUsuario;

    /**
     * Creates new form LoginView
     */
    public LoginView() {
        initComponents();
        getRootPane().setDefaultButton(jb_entrar);
        jt_login.grabFocus();
        this.setResizable(false);
        conectaBD();
        
    }

    private void initComponents() {

        jt_login = new javax.swing.JTextField();
        jp_senha = new javax.swing.JPasswordField();
        jb_entrar = new javax.swing.JButton();
        jb_cadastro = new javax.swing.JButton();
        jl_aviso2 = new javax.swing.JLabel();
        jl_aviso = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jt_login.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        getContentPane().add(jt_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 300, 40));

        jp_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp_senhaActionPerformed(evt);
            }
        });
        getContentPane().add(jp_senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 300, 40));

        jb_entrar.setContentAreaFilled(false);
        jb_entrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            jb_entrarActionPerformed(evt);
                        } catch (IOException | SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                  
            }
        });
        getContentPane().add(jb_entrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 413, 150, 50));

        jb_cadastro.setContentAreaFilled(false);
        jb_cadastro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_cadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cadastroActionPerformed(evt);
            }
        });
        getContentPane().add(jb_cadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, 200, 30));

        jl_aviso2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jl_aviso2.setForeground(new java.awt.Color(0, 204, 51));
        getContentPane().add(jl_aviso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 370, 40));

        jl_aviso.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jl_aviso.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(jl_aviso, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 280, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resouces/TelaLogin.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void limpaCampos() {
        jt_login.setText("");
        jt_login.grabFocus();
        jp_senha.setText("");
    }

    public void conectaBD() {
        try {
            // criando conexão com o servidor
            LoginView.conexao = new Socket("127.0.0.1", 4444);
        } catch (IOException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    private void jp_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jp_senhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jp_senhaActionPerformed

    private void jb_cadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cadastroActionPerformed
        CadastroView telaDeCadastro = new CadastroView();
        telaDeCadastro.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jb_cadastroActionPerformed

    private void jb_entrarActionPerformed(java.awt.event.ActionEvent evt) throws IOException, SQLException {//GEN-FIRST:event_jb_entrarActionPerformed
        
        HomeView telaHome = new HomeView();
        CadastroView cadastro = new CadastroView();
        ChatView chat = new ChatView();

        jl_aviso2.setVisible(false);

        if (jt_login.getText().matches("") || jp_senha.getText().matches("")) {//se um dos campos estiver vazio retorna aviso
            jl_aviso.setText("Preencha todos os campos");
        
        }else {

            PrintStream saida;
            try{
                BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                MD5Controller md5 = new MD5Controller();
                String senha = new String(jp_senha.getPassword());

                saida = new PrintStream(conexao.getOutputStream());
                saida.println("login");
                saida.println(jt_login.getText());
                saida.println(md5.gerarMD5(senha));

                nomeUsuario = entrada.readLine();

                if (!"null".equals(nomeUsuario)) {

                    this.setVisible(false);
                    chat.conecta();
                    chat.setVisible(true);
                    cadastro.setVisible(false);
                    telaHome.setVisible(true);
                    saida.close();
                }else {
                    limpaCampos();
                    jl_aviso.setText("Usuario ou senha invalido.");
                    jl_aviso.setVisible(true);
                }

            
            }catch(IOException ex) {
                Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
                
            }

        }
       
       
        
    }//GEN-LAST:event_jb_entrarActionPerformed

    public JLabel getJl_aviso2() {
        return jl_aviso2;
    }

    public JLabel getJl_aviso() {
        return jl_aviso;
    }


    public JPasswordField getJp_senha() {
        return jp_senha;
    }

    public void setJp_senha(JPasswordField jp_senha) {
        this.jp_senha = jp_senha;
    }


   

    public JTextField getJt_login() {
        return jt_login;
    }

    public void setJt_login(JTextField jt_login) {
        this.jt_login = jt_login;
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
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jb_cadastro;
    private javax.swing.JButton jb_entrar;
    private javax.swing.JLabel jl_aviso;
    private javax.swing.JLabel jl_aviso2;
    private javax.swing.JPasswordField jp_senha;
    private javax.swing.JTextField jt_login;
    // End of variables declaration//GEN-END:variables
}
