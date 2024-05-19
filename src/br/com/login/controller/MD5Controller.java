package br.com.login.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author Ryanp
 */
public class MD5Controller {


    /**
     * Recebe uma String e converte para MD5.
     * @param senha String que será convertida.
     * @return A String convertida para MD5.
     */
    public String gerarMD5(String senha) {

        MessageDigest md;

        try {
            md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes(), 0, senha.length());
            BigInteger i = new BigInteger(1, md.digest());

            //Formatando o resuldado em uma cadeia de 32 caracteres, completando com 0 caso falte 
            senha = String.format("%1$032X", i);
            return senha;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
