package al.weapon;



import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;



public class DES3Encrypt {

    private final static String secretKey = "freexzkj888@lx100$#365#$";
    private final static String iv        = "01234567";
    private final static String encoding  = "utf-8";


    public static String Encode(String text) throws Exception {
    	
        Key deskey = null;
        
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(text.getBytes(encoding));
        
        return Base64.encode(encryptData);
    }


    public static String Decode(String text) throws Exception {
    	
        Key deskey = null;
        
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] decryptData = cipher.doFinal(Base64.decode(text));

        return new String(decryptData, encoding);
    }
    
}
