package tiquartet.ServerModule.bl.usermainbl;

import tiquartet.CommonModule.blservice.usermainblservice.UsermainBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;
import tiquartet.ServerModule.dataservice.impl.UserDataImpl;
import tiquartet.ServerModule.dataservice.userdataservice.UserDataService;
import tiquartet.ServerModule.po.UserPO;

public class UserMain implements UsermainBLService{
	
	private UserDataService userDataService;
	
	public UserMain(){
		userDataService = UserDataImpl.getInstance();
	}
	
	/*
	 * 用户登录
	 */
	public UserVO login (String username, String password){
		
		//先检查用户是否存在
		ResultMessage exist = userDataService.userExist(username);
		//存在则继续登录
		if(exist.result){
			//检查密码是否正确
			ResultMessage isRight = userDataService.checkPassword(username, password);
			//密码正确返回用户信息
			if(isRight.result){
				UserPO userPO = userDataService.getUser(username, password);
				UserVO userVO = userPO.getVO();
		
				return userVO;
			}
			//不正确返回空
			else{
				return null;
			}
		}
		//用户不存在返回空
		else{
			return null;
		}
    }
	
	/*
	 * 用户登出
	 */
	public ResultMessage logout (int userID){
		
		return new ResultMessage(true);
	}
	
	/*
	 * 用户注册
	 */
    public ResultMessage signUp(String username,String password){
    	
    	UserVO newUser = new UserVO();
    	newUser.password = password;
    	newUser.userName = username;
    	
    	//vo转po并且添加
    	UserPO userPO = new UserPO(newUser);
    	ResultMessage result = userDataService.insert(userPO);
    	
    	return result;
    }
    
    /*
     * 判断用户是否存在
     */
    public boolean isUnregistered (String username){
    	
    	boolean exist = userDataService.userExist(username).result;
    	
    	return exist;
    }

}
