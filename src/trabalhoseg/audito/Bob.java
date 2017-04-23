/**
 * Este objeto representa o Bob, ele possui as funções para criptografar, decriptografar e gerar as chaves pública e privada
 */

package trabalhoseg.audito;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;

/**
 *
 * @author Ricardo Corassa
 */
public class Bob {

    public static final String ALGORITHM = "RSA";

    //Chave pública
    private PublicKey chavePublic;
    //Chave privada a ser comptartilhada somente com Alice
    private PrivateKey chavePrivada;
    //Bob possui a chave privada de Alice
    private PrivateKey chavePrivadaAlice;    
    
    public Bob() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);
            final KeyPair key = keyGen.generateKeyPair();
         
            this.chavePrivada = key.getPrivate();
            this.chavePublic = key.getPublic();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Bob.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Criptografa o texto puro usando chave pública.
     */
    public byte[] criptografa(String texto, PublicKey chave) {
        byte[] cipherText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Criptografa o texto puro usando a chave Púlica
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            cipherText = cipher.doFinal(texto.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     * Decriptografa o texto puro usando chave privada.
     */
    public String decriptografa(byte[] texto, PrivateKey chave) {
        byte[] dectyptedText = null;

        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Decriptografa o texto puro usando a chave Privada
            cipher.init(Cipher.DECRYPT_MODE, chave);
            dectyptedText = cipher.doFinal(texto);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new String(dectyptedText);
    }

    /**
     * @return a chave pública
     */
    public PublicKey getChavePublic() {
        return chavePublic;
    }

    /**
     * @param chavePublic a chave púbica para ser setada
     */
    public void setChavePublic(PublicKey chavePublic) {
        this.chavePublic = chavePublic;
    }

    /**
     * @return a chave privada
     */
    public PrivateKey getChavePrivada() {
        return chavePrivada;
    }

    /**
     * @param chavePrivada a chave privada a ser setada
     */
    public void setChavePrivada(PrivateKey chavePrivada) {
        this.chavePrivada = chavePrivada;
    }  

    /**
     * @return a chave privada de Alice
     */
    public PrivateKey getChavePrivadaAlice() {
        return chavePrivadaAlice;
    }

    /**
     * @param chavePrivadaAlice a chave privada de Alice a ser setada
     */
    public void setChavePrivadaAlice(PrivateKey chavePrivadaAlice) {
        this.chavePrivadaAlice = chavePrivadaAlice;
    }

}
