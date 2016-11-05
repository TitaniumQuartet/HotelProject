package tiquartet.ClientModule.bl.usermainbl;

import tiquartet.ClientModule.vo.UserVO;
import tiquartet.CommonModule.util.ResultMessage;

public class UserMain {
	
	
	public UserVO login (String username, String password){
		UserVO user=new UserVO();
		user.userName=username;
		user.password=password;
    	return user;
    }
	public ResultMessage logout (int userID){
		return ResultMessage.SUCCEED;
	}
    public ResultMessage signUp(String username,String password){
    	
    	return ResultMessage.SUCCEED;
    }
    public boolean isUnregistered (String username){
    	return true;
    }
    public  UserVO currentUser (){
    	UserVO user=this.login("Teki", "12345678");
    	return user;
    }

}
