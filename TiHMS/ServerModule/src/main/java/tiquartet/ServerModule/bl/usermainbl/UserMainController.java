package tiquartet.ServerModule.bl.usermainbl;

import tiquartet.CommonModule.blservice.usermainblservice.UsermainBLService;
import tiquartet.CommonModule.util.ResultMessage;

public class UserMainController implements UsermainBLService{
	
	static UserMain usermain = new UserMain();
	
	/*
	 * 用户登录.
	 */
	public ResultMessage login (String username, String password) {
		
		return usermain.login(username, password);
	}
	
	/*
	 * 用户退出登录.
	 */
	public ResultMessage logout (int userID) {
		
		return usermain.logout(userID);
	}
	
	/*
	 * 判断该用户名是否已经被注册.
	 */
	public boolean isUnregistered (String username) {
		
		return usermain.isUnregistered(username);
	}
	
}