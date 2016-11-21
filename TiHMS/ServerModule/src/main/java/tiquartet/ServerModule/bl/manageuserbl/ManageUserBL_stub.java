package tiquartet.ServerModule.bl.manageuserbl;

import java.util.ArrayList;
import java.util.List;

import tiquartet.CommonModule.blservice.manageuserblservice.*;
import tiquartet.CommonModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserSort;

public class ManageUserBL_stub implements ManageUserBLService{

	public ArrayList<UserVO> searchUser(String name, int userId){
		return new ArrayList<UserVO>();
	}
	
	public ArrayList<HotelStaffVO> searchHotelStaff(String city, String area){
		return new ArrayList<HotelStaffVO>();
	}
	
	public HotelStaffVO searchStaff(int staffId){
		return new HotelStaffVO();
	}
	
	public ArrayList<UserVO> getUserList(){
		return new ArrayList<UserVO>();
	}
	
	public ArrayList<UserVO> sortUserList(String way){
		return new ArrayList<UserVO>();
	}
	
	public ResultMessage credit(double money, int userId){
		return new ResultMessage(true);
	}
	
	public ResultMessage addHotel(String hotelName){
		return new ResultMessage(true);
	}
	
	public ResultMessage add(int hotelId, int staffId){
		return new ResultMessage(true);
	}

	public List<UserVO> accurateSearch(String username, String realName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<HotelStaffVO> searchHotelStaff(int cityID, int districtID) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserVO getUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserVO> search(UserFilterVO filter, UserSort sort, int rank1,
			int rank2) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage creditRecharge(int userID, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addHotel(int districtID, String hotelName) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage memberSignIn(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CreditVO> addCreditItem(CreditVO creditItem) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CreditVO> getCreditRecord(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addHotelStaff(int hotelID, String username,
			String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserVO> marketerList() {
		// TODO Auto-generated method stub
		return null;
	}
}
