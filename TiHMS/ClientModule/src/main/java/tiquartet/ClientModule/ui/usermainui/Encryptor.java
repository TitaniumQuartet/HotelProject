package tiquartet.ClientModule.ui.usermainui;

import java.security.MessageDigest;

/**
 * 用户密码的加密器类，提供利用MD5算法加密的静态方法.
 * 
 * @author greatlyr
 *
 */
public class Encryptor {

	/**
	 * 对密码用MD5算法加密，返回密文.
	 * 
	 * @param code
	 * @return
	 */
	public static String encriptMD5(String code) {
		if (code == null || code.isEmpty())
			return null;

		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f'};
		try {
			byte[] strTemp = code.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(strTemp);
			byte[] md = messageDigest.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(encriptMD5("software"));
	}
}
