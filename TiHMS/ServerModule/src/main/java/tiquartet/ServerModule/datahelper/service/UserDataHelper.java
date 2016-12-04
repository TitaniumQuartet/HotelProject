package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.omg.CORBA.INTERNAL;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserType;
import tiquartet.ServerModule.po.UserPO;

public interface UserDataHelper {

	public ResultMessage userExist (String username);
	public UserPO checkPassword (String username, String password);
	public ResultMessage insert (UserPO user);
	public UserPO getUser (int userID);
	public ResultMessage update (UserPO user);
	public List<UserPO> searchUser (String username, String realName,UserType type);
	public ResultMessage getCreditBalance (int userID);
	public ResultMessage addCredit (int userID, double addition);
	public List<UserPO> hotelStaffList(int cityID, int distrcitID);
	public List<UserPO> marketerList();
	public UserPO accurateSearch (String username);
	
}
