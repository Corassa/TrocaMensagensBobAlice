/**
 * Este arquivo tem como objetivo similar a troca de mensagens entre Bob e Alice
 */
package trabalhoseg.audito;

/**
 *
 * @author Ricardo Corassa
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrocaMensagens {

    public static void main(String[] args) {

        try {
            // cria os dois objetos que farão a troca das mensagens
            Bob bob = new Bob();
            Alice alice = new Alice();

            //alice recebe a chave privada de Bob, essa troca em um software deve ser feita de maneira segura
            //como trata-se de uma simulação não foi feita
            alice.setChavePrivadaBob(bob.getChavePrivada());
           
            // mensagem
            String textoClaro = "Essa mensagem é um teste";
            //cria o objeto que será enviado para Alice
            Mensagem mensagem = new Mensagem();
            //recebe o texto claro
            mensagem.setMensagem(bob.criptografa(textoClaro, bob.getChavePublic()));

            //funcao hash para gerar a assinatura
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            byte messageDigest[] = algorithm.digest(textoClaro.getBytes("UTF-8"));

            StringBuilder hexAssinatura = new StringBuilder();
            for (byte b : messageDigest) {
                hexAssinatura.append(String.format("%02X", 0xFF & b));
            }
            String assinatura = hexAssinatura.toString();

            //assinatura encriptografada
            mensagem.setAssinatura(bob.criptografa(assinatura, bob.getChavePublic()));

            //envia para Alice a mensagem
            alice.recebe(mensagem);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TrocaMensagens.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TrocaMensagens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
