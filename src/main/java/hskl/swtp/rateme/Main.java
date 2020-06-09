package hskl.swtp.rateme;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

       String a = test("password","fred");
       String v = test("password","freddy");
        System.out.println(a);
        System.out.println(a.length());

        System.out.println(v);
        System.out.println(a.contentEquals(v));
    }



    private static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }


    private static String test(String password,String username) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        char[] chars = password.toCharArray();
        byte[] salt = username.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, 1000, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();

        return  toHex(salt) + ":" + toHex(hash);
    }


}
