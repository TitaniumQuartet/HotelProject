package tiquartet.stub_driver.blservice.usermain;

import tiquartet.client.blservice.usermainblservice.*;
import tiquartet.client.vo.*;
import tiquartet.common.util.ResultMessage;

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
