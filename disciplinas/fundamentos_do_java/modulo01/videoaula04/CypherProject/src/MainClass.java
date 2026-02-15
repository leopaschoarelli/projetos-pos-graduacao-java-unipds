import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class MainClass  {

    public static void main(String[] args) throws Exception {
        String texto = "Hello World!";

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); //128 bytes
        SecretKey secretKey = keyGen.generateKey();

        System.out.println("Criptografando...");
        String textoCriptografado = criptografar(texto, secretKey);
        System.out.println(textoCriptografado);

        System.out.println("-------------------------------------------");

        System.out.println("Descriptografando...");
        String textoDescriptografado = descriptografar(textoCriptografado, secretKey);
        System.out.println(textoDescriptografado);
    }

    public static String criptografar(String textoOriginal, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] textoByte = cipher.doFinal(textoOriginal.getBytes());
        return Base64.getEncoder().encodeToString(textoByte);
    }

    public static String descriptografar(String textoCripto, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, chave);
        byte[] cryptoByte = Base64.getDecoder().decode(textoCripto.getBytes());
        byte[] textoByte = cipher.doFinal(cryptoByte);
        return new String(textoByte);
    }

}
