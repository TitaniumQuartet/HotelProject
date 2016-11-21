package tiquartet.ServerModule.data.userdata;

import java.util.*;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.dataservice.userdataservice.*;
import tiquartet.ServerModule.po.*;


public class UserData_stub implements UserDataService{
	
	public ResultMessage insert(UserPO user){
		System.out.println("yes");
		return new ResultMessage(true);
	}
	
	public PersonalPO getUserInfo(long userID){
		System.out.println("yes");
		return new PersonalPO();
	}
	
	public ResultMessage update(PersonalPO personal){
		System.out.println("yes");
		return new ResultMessage(true);		
	}
	
	public ResultMessage insert(PersonalPO personal){
		System.out.println("yes");
		return new ResultMessage(true);
	}
	
	public CreditPO getCreditBalance(long userID){
		System.out.println("yes");
		return new CreditPO();
	}
	
	public List<UserPO> searchClient(String username, String realName){
		System.out.println("yes");
		return new ArrayList<UserPO>();
	}

	public ResultMessage userExist(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage checkPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserPO getUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage update(UserPO user) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserPO> searchUser(String username, String realName) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage getCreditBalance(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addCredit(int userID, double addition) {
		// TODO Auto-generated method stub
		return null;
	}

}
