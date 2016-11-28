package tiquartet.ServerModule.dataservice.userdataservice;

import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.UserPO;


public interface UserDataService {
	
	public ResultMessage userExist (String username);
	public ResultMessage checkPassword (String username, String password);
	public ResultMessage insert (UserPO user);
	public UserPO getUser (String username, String password);
	public ResultMessage update (UserPO user);
	public List<UserPO> searchUser (String username, String realName);
	public ResultMessage getCreditBalance (int userID);
	public ResultMessage addCredit (int userID, double addition);
	
}
