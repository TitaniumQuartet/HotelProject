package tiquartet.ServerModule.dataservice.userdataservice;

import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserType;
import tiquartet.ServerModule.po.UserPO;


public interface UserDataService {
	
	public ResultMessage userExist (String username);
	public ResultMessage checkPassword (String username, String password);
	public ResultMessage insert (UserPO user);
	public UserPO getUser (int userID);
	public ResultMessage update (UserPO user);
	public List<UserPO> searchUser (String username, String realName,UserType type);
	public ResultMessage getCreditBalance (int userID);
	public ResultMessage addCredit (int userID, double addition);
	public List<UserPO> hotelStaffList(int cityID, int districtID);
	public List<UserPO> marketerList();
	public UserPO accurateSearch (String username);
	
}
