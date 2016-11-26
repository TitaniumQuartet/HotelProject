package tiquartet.ServerModule.bl.usermainbl;

import tiquartet.CommonModule.blservice.usermainblservice.UsermainBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.dataservice.userdataservice.UserDataController;
import org.apache.commons.beanutils.BeanUtils; 
import tiquartet.CommonModule.vo.UserVO;
import tiquartet.ServerModule.po.UserPO;

public class UserMain implements UserMainBLService{
	
	/*
	 * 用户登录
	 */
	public UserVO login (String username, String password){
		
		//先判断用户是否存在
		ResultMessage res = UserDataContrller.userExist(username);
		//用户存在
		if(res.result == true){
			//验证用户名和密码是否匹配
			ResultMessage match = UserDataController.checkPassword(username, password);
			//匹配返回用户信息
			if(match.result == true){
				UserPO userpo = UserDataController.getUser(username, password);
				UserVO uservo = new UserVO();
				BeanUtils.copyProperties(uservo, userpo);
				
				return uservo;
			}
			//不匹配返回空
			else{
				return null;
			}
		}
		//不存在返回空
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
    	newUser.username = username;
    	
    	//放入数据库
    	UserPO userpo = new UserPO();
    	BeanUtils.copyProperties(userpo, newUser);
    	
    	return UserDataController.insert(userpo);
    }
    
    /*
     * 判断用户名是否已存在
     */
    public boolean isUnregistered (String username){
    	
    	boolean exist = UserDataController.userExist(username).result;
    	
    	return exist;
    }
    
    //先不考虑这个方法
    public UserVO currentUser (){
    	UserVO user=this.login("Teki", "12345678");
    	return user;
    }

}
