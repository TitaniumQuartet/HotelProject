package tiquartet.ServerModule.dataservice.userdataservice;

import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.PersonalPO;
import tiquartet.ServerModule.po.UserPO;
import tiquartet.ServerModule.po.MemberPO;
import tiquartet.ServerModule.po.HotelStaffPO;
import tiquartet.ServerModule.datahelper.service.UserDataHelper;

public class UserDataController{
	
	UserDataHelper helper;
	
	public static ResultMessage userExist (String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ResultMessage checkPassword (String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ResultMessage insert (UserPO user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ResultMessage memberSignIn (MemberPO member) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static UserPO getUser (int userID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static UserPO getUser (String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ResultMessage update (UserPO user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static List<UserPO> searchUser (String username, String realName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static List<UserPO> userList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static List<UserPO> marketerList () {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static List<HotelStaffPO> searchHotelStaff(int cityID, int districtID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ResultMessage getCreditBalance (int userID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static List<CreditPO> getCreditRecord (int userID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ResultMessage addCredit (int userID, double addition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static List<CreditPO> addCreditItem (CreditPO creditItem) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ResultMessage addHotel (int districtID, String hotelName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ResultMessage addHotelStaff (int hotelID, 
			String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}