package tiquartet.ServerModule.dataservice.impl;

import tiquartet.CommonModule.util.ResultMessage;

import java.sql.*;
import java.util.*;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.UserDataHelper;
import tiquartet.ServerModule.dataservice.userdataservice.UserDataService;
import tiquartet.ServerModule.po.*;

public class UserDataImpl implements UserDataService{

	private Map<Integer,UserPO> map;
	
	private UserDataHelper userDataHelper;
	
	private DataFactoryService dataFactory;

	private static UserDataImpl userDataServiceImpl;
	
	private Connection con;
	
	public static UserDataImpl getInstance(){
		if(userDataServiceImpl == null){
			userDataServiceImpl = new UserDataImpl();
		}
		return userDataServiceImpl;
	}
	
	public UserDataImpl(){
		if(map == null){
			dataFactory = new DataFactory();
			userDataHelper = dataFactory.getUserDataHelper();
			map = userDataHelper.getUser();
		}  
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	public ResultMessage userExist (String username){
		return userDataHelper.userExist(username);
	}
	
	public ResultMessage checkPassword (String username, String password){
		return userDataHelper.checkPassword(username, password);
	}
	
	public ResultMessage insert (UserPO user){
		return userDataHelper.insert(user);
	}
	
	public UserPO getUser (int userID){
		return map.get(userID);
	}
	
	public ResultMessage update (UserPO user){
		int userId = user.getuserId();
		if(map.get(userId) != null){
			map.put(userId,user);
			userDataHelper.update(map);
			return success;
		}
		return fail;
	}
	
	public List<UserPO> searchUser(String username, String realName){
		return userDataHelper.searchUser(username, realName);
	}
	
	public ResultMessage getCreditBalance (int userID){
		return userDataHelper.getCreditBalance(userID);
	}
	
	public ResultMessage addCredit (int userID, double addition){
		return userDataHelper.addCredit(userID, addition);
	}

	public UserPO getUser(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}


}
