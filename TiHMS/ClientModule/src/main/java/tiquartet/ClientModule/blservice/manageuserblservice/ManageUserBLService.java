package tiquartet.ClientModule.blservice.manageuserblservice;

import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;

import tiquartet.ClientModule.vo.*;

public interface ManageUserBLService {

	public List<UserVO> searchUser(String name, int userId);
	
	public List<HotelStaffVO> searchHotelStaff(String city, String area);
	
	public HotelStaffVO searchStaff(int staffId);
	
	public List<UserVO> getUserList();
	
	public List<UserVO> sortUserList(String way);
	
	public ResultMessage credit(double money, int userId);
	
	public ResultMessage addHotel(String hotelName);
	
	public ResultMessage add(int hotelId, int staffId);
	
}