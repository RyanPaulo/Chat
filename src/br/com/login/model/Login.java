
package br.com.login.model;

/**
 *
 * @author Ryan Paulo
 */
public class Login {
    
    String nome, email, senha;

    public Login(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    
    public void cadatrar(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
}
