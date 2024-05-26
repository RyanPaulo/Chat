package br.com.login.view;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

/**
 *
 * @author Ryan Paulo
 */
public class HomeView extends javax.swing.JFrame {

 
    public HomeView() {
        initComponents();
        this.setResizable(false);        
        
    }
                     
    private void initComponents() {

        jb_atualizar = new javax.swing.JButton();
        jb_voltar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jl_contatos = new javax.swing.JList<>();
        jl_usuario = new javax.swing.JLabel();
        jb_conversa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jb_atualizar.setContentAreaFilled(false);
        jb_atualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(jb_atualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 43, 150, 50));

        jb_voltar.setContentAreaFilled(false);
        jb_voltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_voltarActionPerformed(evt);
            }
        });
        getContentPane().add(jb_voltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 590, 120, 40));

        jl_contatos.setBackground(new java.awt.Color(46, 115, 155));
        jl_contatos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18));
        jl_contatos.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(jl_contatos);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 490, 450));

        jl_usuario.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jl_usuario.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jl_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 36, 310, 60));

        jb_conversa.setBackground(new java.awt.Color(46, 100, 155));
        getContentPane().add(jb_conversa, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 583, 100, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resouces/TelaHome.png")));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -4, 560, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void jb_voltarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.setVisible(false);
        
        LoginView login = new LoginView();
        login.setVisible(true);
    }              
    
    public void conecta() {
        try {
            Socket conexao = new Socket("127.0.0.1", 3333);

        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
    public void atualizarContatos(ArrayList<String> meuNome) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for(String nome : meuNome) {
            model.addElement(nome);
        }
        jl_contatos.setModel(model);
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
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jb_atualizar;
    private javax.swing.JButton jb_conversa;
    private javax.swing.JButton jb_voltar;
    private javax.swing.JList<String> jl_contatos;
    private javax.swing.JLabel jl_usuario;
    // End of variables declaration                   
}
