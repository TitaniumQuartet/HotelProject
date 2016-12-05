package tiquartet.ServerModule.bl.usermainbl;

import tiquartet.CommonModule.blservice.usermainblservice.UsermainBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.UserVO;
import tiquartet.ServerModule.bl.manageuserbl.ManageUserController;
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
	public ResultMessage login (String username, String password){
		
		//先检查用户是否存在
		ResultMessage exist = userDataService.userExist(username);
		//存在则继续登录
		if(exist.result){
			//检查密码是否正确
			ResultMessage result = userDataService.checkPassword(username, password);
			
			return result;
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
		
		//先利用编号找到用户
		UserPO user = userDataService.getUser(userID);
		//修改用户登录状态
		user.setLogin(false);
		//更新用户信息
		UserVO userVO = user.getVO();
		ManageUserController manageUserController = new ManageUserController();
		ResultMessage result = manageUserController.update(userVO);
		
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
