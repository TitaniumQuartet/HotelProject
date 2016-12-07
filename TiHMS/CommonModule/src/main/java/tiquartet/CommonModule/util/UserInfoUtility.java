package tiquartet.CommonModule.util;

/**
 * 提供用户信息相关的方法.
 * @author greatlyr
 *
 */
public class UserInfoUtility {
	
	/**
	 * 返回该用户名是否合法,不检查有无重复.
	 * @param username
	 * @return
	 */
	public static boolean checkUserName(String username){
		return !username.matches(".[^a-zA-Z0-9_].")&&username.length()>5&&username.length()<17;
	}
	
	/**
	 * 返回该密码是否合法.
	 * @param password
	 * @return
	 */
	public static boolean checkPassword(String password){
		return !password.contains(" ")&&password.length()>5&&password.length()<17;
	}
}
