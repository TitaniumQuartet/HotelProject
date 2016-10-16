package tiquartet.stub_driver.userdata;

import java.util.List;

import tiquartet.common.dataservice.userdataservice;


public class UserData_stub implements UserDataService{
	
	public ResultMessage insert(UserPO user){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	
	public PersonalPO getUserInfo(long userID){
		System.out.println("yes");
		return new PersonalPO();
	}
	
	public ResultMessage update(PersonalPO personal){
		System.out.println("yes");
		return ResultMessage.SUCCEED;		
	}
	
	public ResultMessage insert(PersonalPO personal){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	
	public CreditPO getCreditBalance(long userID){
		System.out.println("yes");
		return new CreditPO();
	}
	
	public List<UserPO> searchClient(String username, String realName){
		System.out.println("yes");
		return new List<UerPO>();
	}

}
