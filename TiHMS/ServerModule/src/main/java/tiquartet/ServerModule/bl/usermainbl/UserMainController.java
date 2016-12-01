package tiquartet.ServerModule.bl.usermainbl;

import tiquartet.CommonModule.blservice.usermainblservice.UsermainBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;

public class UserMainController implements UsermainBLService{
	
	static UserMain usermain = new UserMain();
	
	/*
	 * 用户登录
	 */
	public UserVO login (String username, String password) {
		
		return usermain.login(username, password);
	}
	
	/*
	 * 用户登出
	 */
	public ResultMessage logout (int userID) {
		
		return usermain.logout(userID);
	}
	
	/*
	 * 用户注册
	 */
	public ResultMessage signUp(String username,String password) {
		
		return usermain.signUp(username, password);
	}
	
	/*
	 * 用户是否存在
	 */
	public boolean isUnregistered (String username) {
		
		return usermain.isUnregistered(username);
	}
	
}