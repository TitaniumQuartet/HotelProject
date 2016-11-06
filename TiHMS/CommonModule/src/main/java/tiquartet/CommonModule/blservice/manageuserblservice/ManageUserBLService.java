package tiquartet.CommonModule.blservice.manageuserblservice;

import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserSort;
import tiquartet.CommonModule.vo.*;

public interface ManageUserBLService {
	
	public List<UserVO> accurateSearch (String username, String realName);

	public List<HotelStaffVO> searchHotelStaff(int cityID, int districtID);
	
	public UserVO getUser (int userID);
	
	public List<UserVO> search (UserFilterVO filter, UserSort sort, int rank1, int rank2);
	
	public ResultMessage creditRecharge (int userID, double amount);
	
	public ResultMessage addHotel(int districtID, String hotelName);
	
	public ResultMessage memberSignIn (MemberVO member);
	
	public List<CreditVO> addCreditItem (CreditVO creditItem);
	
	public List<CreditVO> getCreditRecord (int userID);
	
	public ResultMessage addHotelStaff(int hotelID, String username, String password);
	
	public List<UserVO> marketerList();
	
}
