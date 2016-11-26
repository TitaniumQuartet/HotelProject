package tiquartet.ServerModule.dataservice.userdataservice;

import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.PersonalPO;
import tiquartet.ServerModule.po.UserPO;
import tiquartet.ServerModule.po.MemberPO;
import tiquartet.ServerModule.po.HotelStaffPO;

public interface UserDataService {
	
	public ResultMessage userExist (String username);
	public ResultMessage checkPassword (String username, String password);
	public ResultMessage insert (UserPO user);
	public ResultMessage memberSignIn (MemberPO member);
	public UserPO getUser (int userID);
	public UserPO getUser (String username, String password);
	public ResultMessage update (UserPO user);
	public List<UserPO> searchUser (String username, String realName);
	public List<UserPO> userList();
	public List<UserPO> marketerList ();
	public List<HotelStaffPO> searchHotelStaff(int cityID, int districtID);
	public ResultMessage getCreditBalance (int userID);
	public List<CreditPO> getCreditRecord (int userID);
	public ResultMessage addCredit (int userID, double addition);
	public List<CreditPO> addCreditItem (CreditPO creditItem);
	public ResultMessage addHotel (int districtID, String hotelName);
	public ResultMessage addHotelStaff (int hotelID, String username, String password);

}
