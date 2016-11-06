package tiquartet.ServerModule.data.userdata;

import java.util.*;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.dataservice.userdataservice.*;
import tiquartet.ServerModule.po.*;


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
		return new ArrayList<UserPO>();
	}

}
