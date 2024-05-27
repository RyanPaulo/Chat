package br.com.login.view;


import br.com.login.controller.MD5Controller;
import br.com.login.dao.LoginDAO;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.SQLException;

// import javax.swing.JOptionPane;
// import br.com.login.view.LoginView;
// import java.awt.Color;


/**
 * Class que representa a interface de cadastro
 * @author Ryan Paulo
 */
public class CadastroView extends javax.swing.JFrame {

    /**
     * Construtor para receber os parametros da inteface de cadastro
     */
    public CadastroView() {
        initComponents();
        this.setResizable(false);
    }
                   
    /**
     * Define a disposição dos componentes na interface.
     */
    private void initComponents() {

        jt_nome = new javax.swing.JTextField();
        jt_email = new javax.swing.JTextField();
        jp_senha = new javax.swing.JPasswordField();
        jb_cadastrar = new javax.swing.JButton();
        jl_aviso = new javax.swing.JLabel();
        jl_imagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jt_nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 300, 40));

        jt_email.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        getContentPane().add(jt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 300, 40));

        jp_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp_senhaActionPerformed(evt);
            }
        });
        getContentPane().add(jp_senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 300, 40));

        jb_cadastrar.setContentAreaFilled(false);
        jb_cadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(jb_cadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, 190, 40));

        // Aviso para preencher todos os campos
        jl_aviso.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jl_aviso.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(jl_aviso, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 280, 40));

        //Imagem de fundo
        jl_imagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resouces/TelaCadastro.png"))); // NOI18N
        getContentPane().add(jl_imagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }                       

    private void jp_senhaActionPerformed(java.awt.event.ActionEvent evt) {                                         
    
    }                                        

    /**
     * Função do botao para realizar o cadastro
     * @param evt
     */
    @SuppressWarnings("deprecation")
    private void jb_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        //Instancia da class responsavel de fazer a criptografica da senha
        MD5Controller md5 = new MD5Controller();
        //para fazer a verificação dos campos
        if (jt_nome.getText().matches("") || jt_email.getText().matches("") || jp_senha.getText().matches("")) {
            jl_aviso.setText("Preencha todos os campos");
        
        }else {
        
            try{
                LoginDAO cadastro = new LoginDAO();
                String senha = md5.gerarMD5(this.getJpf_senha().getText());

                cadastro.cadastrarUsuario(this.getJt_nome().getText(), this.getJt_email().getText(), senha);
        
                
            } catch (SQLException sql) {
                 System.err.println("ERRO -> " + sql);
            }
            this.setVisible(false);
            LoginView login = new LoginView();
            login.setVisible(true);
              
            login.getJl_aviso2().setText("Cadastro realizado com sucesso!");
        }
     
    }                                                                                    

    public JPasswordField getJpf_senha() {
        return jp_senha;
    }

    public void setJpf_senha(JPasswordField jpf_senha) {
        this.jp_senha = jpf_senha;
    }

    public JTextField getJt_email() {
        return jt_email;
    }

    public void setJt_email(JTextField jt_email) {
        this.jt_email = jt_email;
    }

    public JTextField getJt_nome() {
        return jt_nome;
    }

    public void setJt_nome(JTextField jt_nome) {
        this.jt_nome = jt_nome;
    }

          
    private javax.swing.JLabel jl_imagem;
    private javax.swing.JButton jb_cadastrar;
    private javax.swing.JLabel jl_aviso;
    private javax.swing.JPasswordField jp_senha;
    private javax.swing.JTextField jt_email;
    private javax.swing.JTextField jt_nome;                 

  
}