package tiquartet.ServerModule.bl.usermainbl;

import tiquartet.CommonModule.blservice.usermainblservice.UsermainBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;

public class UserMainController implements UsermainBLService{
	
	static UserMain usermain = new UserMain();
	
	/*
	 * �û���¼
	 */
	public UserVO login (String username, String password) {
		
		return usermain.login(username, password);
	}
	
	/*
	 * �û��ǳ�
	 */
	public ResultMessage logout (int userID) {
		
		return usermain.logout(userID);
	}
	
	/*
	 * �û�ע��
	 */
	public ResultMessage signUp(String username,String password) {
		
		return usermain.signUp(username, password);
	}
	
	/*
	 * �û��Ƿ����
	 */
	public boolean isUnregistered (String username) {
		
		return usermain.isUnregistered(username);
	}
	
}