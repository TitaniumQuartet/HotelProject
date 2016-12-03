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
		Iterator<Map.Entry<Integer,UserPO>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Integer,UserPO> entry = iterator.next();
			UserPO userpo = entry.getValue();
			if(userpo.getuserName()==username){
				return fail;
			}
		}
		return success;
	}
	
	public UserPO checkPassword (String username, String password){
		Iterator<Map.Entry<Integer,UserPO>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Integer,UserPO> entry = iterator.next();
			UserPO userpo = entry.getValue();
			if(userpo.getuserName()==username&&userpo.getpassword()==password){
				return userpo;
			}
		}
		return null;
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
			userDataHelper.update(user);
			return success;
		}
		return fail;
	}
	
	public List<UserPO> searchUser(String username, String realName){
		return userDataHelper.searchUser(username, realName);
	}
	
	public double getCreditBalance (int userID){
		UserPO user=map.get(userID);
		double credit=user.getcredit();
		return credit;
	}
	
	public ResultMessage addCredit (int userID, double addition){
		UserPO userPO=map.get(userID);
		double credit=userPO.getcredit();
		credit=credit+addition;
		userPO.setcredit(credit);
		map.put(userID,userPO);
		return userDataHelper.update(userPO);
	}

	@Override
	public UserPO getUser(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserPO> searchHotelStaff(int cityID, int districtID) {
		// TODO Auto-generated method stub
		return null;
	}

}
