package org.encryptor.aes;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AesUtil {

    /**
    * 키 생성
     * 16바이트의 SecretKey를 생성한다.
    * */
    private static Key makeAES16Key(String key) throws UnsupportedEncodingException {


        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if(len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        return keySpec;
    }

    /**
     * 암호화
     * @param key
     * @param originStr
     * @return enStr 암호화 후 base64로 인코딩 하여 리턴한다.
     */
    public static String aseEncrypt(String key, String originStr) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String iv = key.substring(0, 16);
        Key keySpec = makeAES16Key(key);
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

        byte[] encrypted = c.doFinal(originStr.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));

        return enStr;
    }

    /**
     * 복호화
     * @param key
     * @param encStr
     * @return enStr 복호화 후 base64로 인코딩 하여 리턴한다.
     */
    public static String aseDecrypt(String key, String encStr) throws UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {
        String iv = key.substring(0, 16);
        Key keySpec = makeAES16Key(key);

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));

        byte[] byteStr = Base64.decodeBase64(encStr.getBytes());

        return new String(c.doFinal(byteStr),"UTF-8");
    }
}
