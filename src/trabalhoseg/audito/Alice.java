/**
 * Este objeto representa a Alice, ele possui as funções para criptografar, decriptografar e gerar as chaves pública e privada
 */

package trabalhoseg.audito;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
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
public class Alice {

    public static final String ALGORITHM = "RSA";
    
    //Chave pública
    public PublicKey chavePublic;
    //Chave privada a ser comptartilhada somente com Bob
    private PrivateKey chavePrivada;
    //Alice possui a chave privada de Bob
    private PrivateKey chavePrivadaBob;

    public Alice() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);
            final KeyPair key = keyGen.generateKeyPair();

            this.chavePrivada = key.getPrivate();
            this.chavePublic = key.getPublic();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Alice.class.getName()).log(Level.SEVERE, null, ex);
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
    public String decriptografa(byte[] texto) {
        byte[] dectyptedText = null;

        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Decriptografa o texto puro usando a chave Privada
            cipher.init(Cipher.DECRYPT_MODE, chavePrivadaBob);
            dectyptedText = cipher.doFinal(texto);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new String(dectyptedText);
    }

    /**
     * Fução com objetivo de simular o canal de recebimento por parte de Alice
     * @param mensagem 
     */
    public void recebe(Mensagem mensagem) {

        try {
            //decriptografa a mensagem e a assinatura
            String msg = decriptografa(mensagem.getMsg());
            String assinatura = decriptografa(mensagem.getAssinatura());

            //gera um novo hash apartir da assinatura
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            byte messageDigest[] = algorithm.digest(msg.getBytes("UTF-8"));

            StringBuilder hexAssinatura = new StringBuilder();
            for (byte b : messageDigest) {
                hexAssinatura.append(String.format("%02X", 0xFF & b));
            }
            String assinatura2 = hexAssinatura.toString();

            //compara para ver se os dois são iguais
            if(assinatura2.equals(assinatura)){
                System.out.println("Bob é valido");
                System.out.println(msg);
            } else {
                System.out.println("Bob não é valido");
            }
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Alice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Alice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param chavePublic a chave pública a ser setada
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
     * @return a chave privada de Bob
     */
    public PrivateKey getChavePrivadaBob() {
        return chavePrivadaBob;
    }

    /**
     * @param chavePrivadaBob a chave de Bob a ser setada
     */
    public void setChavePrivadaBob(PrivateKey chavePrivadaBob) {
        this.chavePrivadaBob = chavePrivadaBob;
    }

}
