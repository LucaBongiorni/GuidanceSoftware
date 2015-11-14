package com.guidepointsecurity.crypto;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class DESCrypter {

    /**
     * Local objects and variables
     */
    private static Cipher dec;
    private static byte[] key;
    private static String decryptedResult;
    private static DESKeySpec desKeySpec;

    public DESCrypter() {}

    public String decrypt(String s, String command) {


        try {
            /** *
             * Decode inbound command
             */
            key = s.getBytes();
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decodedCommand = decoder.decode(command);

            /**
             * Crypto
             */
            desKeySpec = new DESKeySpec(key);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            dec = Cipher.getInstance("DES");
            dec.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decrypted = dec.doFinal(decodedCommand);
            decryptedResult = new String(decrypted, "UTF-8");

            /**
             * Print StackTrace for debugging
             */
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /**
         * Return the results
         */
        return decryptedResult;
    }

}


