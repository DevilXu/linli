package org.common.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class EncrypAES {
	private final KeyGenerator kgen;
	private final SecretKey secretKey;
	private final SecretKeySpec key;
	private final Cipher cipher;
	private final String password="123456789";
	
	public EncrypAES() throws Exception{
		kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(password.getBytes()));  
        secretKey = kgen.generateKey();  
        byte[] enCodeFormat = secretKey.getEncoded();  
        key = new SecretKeySpec(enCodeFormat, "AES"); 
        cipher = Cipher.getInstance("AES");// 创建密码器   
	}
	/** 
	 * 加密 
	 *  
	 * @param content 需要加密的内容 
	 * @param password  加密密码 
	 * @return 
	 */  
	public String encrypt(String content) {  
        try {             
            byte[] byteContent = content.getBytes("utf-8");  
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
            byte[] result = cipher.doFinal(byteContent);  
            return byte2Hex(result); // 加密   
        } catch (Exception e) {  
        	e.printStackTrace();  
        }
        return null;  
	}  
	/**解密 
	 * @param content  待解密内容 
	 * @param password 解密密钥 
	 * @return 
	 */  
	public String decrypt(String content) {  
        try {  
	        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
	        byte[] result = cipher.doFinal(hex2Byte(content));  
            return new String(result); // 加密   
        } catch (Exception e) {  
        	e.printStackTrace();  
        }  
        return null;  
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
	public static void main(String[] args) throws Exception{
		EncrypAES aes=new EncrypAES();
		String content = "123";   
		//加密   
		System.out.println("加密前：" + content);  
		String encryptResult = aes.encrypt(content);  
		String decryptResult = aes.decrypt(encryptResult);  
		System.out.println("解密后：" + new String(decryptResult));  
	}
}
