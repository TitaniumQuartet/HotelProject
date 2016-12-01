package tiquartet.ServerModule.dataservice.userdataservice;

import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.UserPO;


public interface UserDataService {
	
	public ResultMessage userExist (String username);
	public ResultMessage checkPassword (String username, String password);
	public ResultMessage insert (UserPO user);
	public UserPO getUser (String username, String password);
	public UserPO getUser (int userID);
	public ResultMessage update (UserPO user);
	public List<UserPO> searchUser (String username, String realName);
	public ResultMessage getCreditBalance (int userID);
	public ResultMessage addCredit (int userID, double addition);
	public ResultMessage addHotel (int districtID, String hotelName);
	public List<CreditPO> getCreditRecord (int userID);
	public List<CreditPO> addCreditItem (CreditPO creditItem);
	public List<UserPO> userList ();
	public List<UserPO> searchHotelStaff(int cityID, int districtID);
	
}
