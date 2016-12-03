package tiquartet.ServerModule.dataservice.impl;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserType;

import java.sql.*;
import java.util.*;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.UserDataHelper;
import tiquartet.ServerModule.dataservice.userdataservice.UserDataService;
import tiquartet.ServerModule.po.*;

public class UserDataImpl implements UserDataService{
	
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
		if(dataFactory == null){
			dataFactory = new DataFactory();
			userDataHelper = dataFactory.getUserDataHelper();
		}  
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	public ResultMessage userExist (String username){
		return userDataHelper.userExist(username);
	}
	
	public UserPO checkPassword (String username, String password){
		return userDataHelper.checkPassword(username, password);
	}
	
	public ResultMessage insert (UserPO user){
		return userDataHelper.insert(user);
	}
	
	public UserPO getUser (int userID){
		return userDataHelper.getUser(userID);
	}
	
	public ResultMessage update (UserPO user){
		return	userDataHelper.update(user);
	}
	
	public List<UserPO> searchUser(String username, String realName, UserType type){
		return userDataHelper.searchUser(username, realName, type);
	}
	
	public ResultMessage getCreditBalance (int userID){
		return userDataHelper.getCreditBalance(userID);
	}
	
	public ResultMessage addCredit (int userID, double addition){
		return userDataHelper.addCredit(userID, addition);
	}

	@Override
	public List<UserPO> hotelStaffList(int cityID, int districtID) {
		    return userDataHelper.hotelStaffList(cityID, districtID);
	}

	@Override
	public List<UserPO> marketerList() {
		return userDataHelper.marketerList();
	}

}
