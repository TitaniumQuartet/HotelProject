package tiquartet.CommonModule.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 用户密码的加密器类，提供利用MD5算法加密的静态方法.
 * @author greatlyr
 *
 */
public class Encryptor {
	
	/**
	 * 对密码用MD5算法加密，返回密文.
	 * @param code
	 * @return
	 */
	public static String encript(String code) {
		if(code==null||code.isEmpty()) return null;
		
		byte[] bytes = code.getBytes(Charset.forName("UTF-8"));
		try {
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			//使用MD5算法进行转换，用UTF-8编码
			String result = new String(md5Digest.digest(bytes),Charset.forName("UTF-8"));
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
