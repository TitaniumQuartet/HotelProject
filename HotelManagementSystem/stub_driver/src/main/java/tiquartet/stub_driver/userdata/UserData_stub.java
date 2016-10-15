package tiquartet.stub_driver.userdata;

import tiquartet.common.dataservice.userdataservice;

public class UserData_stub {
	public ResultMessage insert(UserPO user){
		return ResultMessage.SUCCEED;
	}
	
	public PersonalPO getUserInfo(long userID){
		return new PersonalPO();
	}
	
	public ResultMessage update(PersonalPO personal){
		return ResultMessage.SUCCEED;		
	}
	
	public ResultMessage insert(PersonalPO personal){
		return ResultMessage.SUCCEED;
	}
	
	public CreditPO getCreditBalance(long userID){
		return new CreditPO();
	}

}
