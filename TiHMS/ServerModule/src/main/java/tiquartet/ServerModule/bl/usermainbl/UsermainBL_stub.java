package tiquartet.ServerModule.bl.usermainbl;

import tiquartet.CommonModule.blservice.usermainblservice.*;
import tiquartet.CommonModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;

public class UsermainBL_stub implements UsermainBLService{

	    public UserVO login (String username, String password){
	    	return new UserVO();
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
	    public UserVO currentUser (){
	    	return new UserVO();
	    }
}
