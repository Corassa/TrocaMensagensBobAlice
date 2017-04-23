/**
 * Este objeto é a representação de uma mensagem com assinatura e texto claro.
 */
package trabalhoseg.audito;

/**
 *
 * @author Ricardo Corassa
 */
public class Mensagem {
    
    private static byte[] mensagem;
    private static byte[] assinatura;

    /**
     * @return a mensagem
     */
    public static byte[] getMsg() {
        return mensagem;
    }

    /**
     * @param aMensagem a mensagem a ser setada
     */
    public static void setMensagem(byte[] aMensagem) {
        mensagem = aMensagem;
    } 

    /**
     * @return a assinatura
     */
    public static byte[] getAssinatura() {
        return assinatura;
    }

    /**
     * @param aAssinatura a assinatura a ser setada
     */
    public static void setAssinatura(byte[] aAssinatura) {
        assinatura = aAssinatura;
    }
    
    
}
