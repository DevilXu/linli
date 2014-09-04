package org.common.DESede;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
/**
 * 3des加密技术的实现
 * @author hegang
 *
 */
public class EncrypDES3 {
	// KeyGenerator 提供对称密钥生成器的功能，支持各种算法  
    private KeyGenerator keygen;  
    // SecretKey 负责保存对称密钥  
    private SecretKey deskey;  
    // Cipher负责完成加密或解密工作  
    private Cipher c;  
    // 该字节数组负责保存加密的结果  
    private byte[] cipherByte;  
  
    public EncrypDES3() throws NoSuchAlgorithmException, NoSuchPaddingException {  
        Security.addProvider(new com.sun.crypto.provider.SunJCE());  
        // 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)  
        keygen = KeyGenerator.getInstance("DESede");  
        // 生成密钥  
        deskey = keygen.generateKey();  
        // 生成Cipher对象,指定其支持的DES算法  
        c = Cipher.getInstance("DESede");  
    }  
  
    /** 
     * 对字符串加密 
     *  
     * @param str 
     * @return 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */  
    public String Encrytor(String str) throws InvalidKeyException,  
            IllegalBlockSizeException, BadPaddingException {  
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式  
        c.init(Cipher.ENCRYPT_MODE, deskey);  
        byte[] src = str.getBytes();  
        // 加密，结果保存进cipherByte  
        cipherByte = c.doFinal(src);  
        return byte2Hex(cipherByte);  
    }  
  
    /** 
     * 对字符串解密 
     *  
     * @param buff 
     * @return 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */  
    public String Decryptor(String str) throws InvalidKeyException,  
            IllegalBlockSizeException, BadPaddingException {  
        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式  
        c.init(Cipher.DECRYPT_MODE, deskey);  
        cipherByte = c.doFinal(hex2Byte(str));  
        return new String(cipherByte);  
    }  
    /**
     * 将返回的字节数组转化为16进制字符串
     * @param b
     * @return
     */
    public String byte2Hex(byte[] b){
    	String hs="";
    	String stmp="";
    	for(int n=0;n<b.length;n++){
    		stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
    		if(stmp.length()==1){
    			hs=hs+"0"+stmp;
    		}else{
    			hs=hs+stmp;
    		}
    	}
    	return hs.toUpperCase();
    }
    /**
     * 将16进制字符串转化为字节数组
     * @param str
     * @return
     */
    public byte[] hex2Byte(String str){
    	if(str==null){
    		return null;
    	}
    	str=str.trim();
    	int len=str.length();
    	if(len==0||len%2==1){
    		return null;
    	}
    	byte[] b=new byte[len/2];
    	try{
    		for(int i=0;i<str.length();i+=2){
    			b[i/2]=(byte)Integer.decode("0x"+str.substring(i,i+2)).intValue();
    		}
    		return b;
    	}
    	catch(Exception e){
    		return null;
    	}
    }
    /** 
     * @param args 
     * @throws NoSuchPaddingException  
     * @throws NoSuchAlgorithmException  
     * @throws BadPaddingException  
     * @throws IllegalBlockSizeException  
     * @throws InvalidKeyException  
     */  
    public static void main(String[] args) throws Exception {  
        EncrypDES3 des = new EncrypDES3();  
        String msg ="明文是明文是明文是明文是明文是明文是明文是明文是明文是";  
        String encontent = des.Encrytor(msg);  
        String decontent = des.Decryptor(encontent);  
        System.out.println("明文是:" + msg);  
        System.out.println("加密后:" + encontent);  
        System.out.println("解密后:" + decontent);  
        int i = 70;
    }  
}
