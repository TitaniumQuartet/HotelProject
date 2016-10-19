package tiquartet.StubDriver.blservice.usermain;

import tiquartet.ClientModule.blservice.usermainblservice.*;
import tiquartet.ClientModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;

public class UsermainBL_stub implements UsermainBLService{
	 public UserVO login(long userID,String password){
		 return new UserVO();
	 }
	    public  ResultMessage logout(long userID){
	    	return ResultMessage.SUCCEED;
	    }
	    public ResultMessage signUp(String username,String password){
	    	return ResultMessage.SUCCEED;
	    }
}
