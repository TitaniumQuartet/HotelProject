package tiquartet.ServerModule.bl.usermainbl;

import tiquartet.CommonModule.blservice.usermainblservice.UsermainBLService;
import tiquartet.CommonModule.util.ResultMessage;

public class UserMainController implements UsermainBLService{
	
	static UserMain usermain = new UserMain();
	
	/*
	 * 用户登录
	 */
	public ResultMessage login (String username, String password) {
		
		return usermain.login(username, password);
	}
	
	/*
	 * 用户登出
	 */
	public ResultMessage logout (int userID) {
		
		return usermain.logout(userID);
	}
	
	/*
	 * 用户是否存在
	 */
	public boolean isUnregistered (String username) {
		
		return usermain.isUnregistered(username);
	}
	
}