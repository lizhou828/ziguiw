package com.zigui.utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class EncryptUtil {
	private static final String UTF8 = "utf-8";   
    //定义 加密算法,可用 DES,DESede,Blowfish   
    private static final String ALGORITHM_DESEDE = "DESede";   
  
    /**  
     * MD5数字签名  
     *   
     * @param src  
     * @return  
     * @throws Exception  
     */  
    public static String md5Digest(String src) throws Exception {   
        // 定义数字签名方法, 可用：MD5, SHA-1   
        MessageDigest md = MessageDigest.getInstance("MD5");   
        byte[] b = md.digest(src.getBytes(UTF8));   
           
        return byte2HexStr(b);   
    }   
       
    /**  
     * BASE64 加密  
     *   
     * @param src  
     * @return  
     * @throws Exception  
     */  
    public static String base64Encoder(String src) throws Exception {   
        BASE64Encoder encoder = new BASE64Encoder();   
        return encoder.encode(src.getBytes(UTF8));   
    }   
       
    /**  
     * BASE64解密  
     *   
     * @param dest  
     * @return  
     * @throws Exception  
     */  
    public static String base64Decoder(String dest) throws Exception {   
        BASE64Decoder decoder = new BASE64Decoder();   
        return new String(decoder.decodeBuffer(dest), UTF8);   
    }   
       
    /**  
     * 3DES加密  
     *   
     * @param src  
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static String desedeEncoder(String src, String key) throws Exception {   
        SecretKey secretKey = new SecretKeySpec(build3DesKey(key), ALGORITHM_DESEDE);   
        Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);   
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);   
        byte[] b = cipher.doFinal(src.getBytes(UTF8));   
           
        return byte2HexStr(b);   
    }   
       
    /**  
     * 3DES解密  
     *   
     * @param dest  
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static String desedeDecoder(String dest, String key) throws Exception {   
        SecretKey secretKey = new SecretKeySpec(build3DesKey(key), ALGORITHM_DESEDE);   
        Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);   
        cipher.init(Cipher.DECRYPT_MODE, secretKey);   
        byte[] b = cipher.doFinal(str2ByteArray(dest));   
           
        return new String(b, UTF8);   
       
    }   
       
    /**  
     * 字节数组转化为大写16进制字符串  
     *   
     * @param b  
     * @return  
     */  
    private static String byte2HexStr(byte[] b) {   
        StringBuilder sb = new StringBuilder();   
        for (int i = 0; i < b.length; i++) {   
            String s = Integer.toHexString(b[i] & 0xFF);   
            if (s.length() == 1) {   
                sb.append("0");   
            }   
               
            sb.append(s.toUpperCase());   
        }   
           
        return sb.toString();   
    }   
       
    /**  
     * 字符串转字节数组  
     *   
     * @param s  
     * @return  
     */  
    private static byte[] str2ByteArray(String s) {   
        int byteArrayLength = s.length()/2;   
        byte[] b = new byte[byteArrayLength];   
        for (int i = 0; i < byteArrayLength; i++) {   
            byte b0 = (byte) Integer.valueOf(s.substring(i*2, i*2+2), 16).intValue();   
            b[i] = b0;   
        }   
           
        return b;   
    }   
       
    /**  
     * 构造3DES加解密方法key  
     *   
     * @param keyStr  
     * @return  
     * @throws Exception  
     */  
    private static byte[] build3DesKey(String keyStr) throws Exception {   
        byte[] key = new byte[24];   
        byte[] temp = keyStr.getBytes(UTF8);   
        if (key.length > temp.length) {
            System.arraycopy(temp, 0, key, 0, temp.length);   
        } else {   
            System.arraycopy(temp, 0, key, 0, key.length);   
        }   
           
        return key;   
    }
    
    public static void main(String[] args) {
    	String str = "123456";
		try {
			//加密
			str = EncryptUtil.base64Encoder(str);
			System.out.println(str);
			//解密
			System.out.println(EncryptUtil.base64Decoder(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
