package tiquartet.client.blservice.manageuserblservice;

import java.util.List;
import tiquartet.client.vo.*;

public interface ManageUserBLService {

	public List<UserPO> searchUser(String name, int userId);
	
	public List<HotelStaffPO> searchHotelStaff(String city, String area);
	
	public HotelStaffPO searchStaff(int staffId);
	
	public List<UserPO> getUserList();
	
	public List<UserPO> sortUserList(String way);
	
	public ResultMessage credit(double money, int userId);
	
	public ResultMessage addHotel(String hotelName);
	
	public ResultMessage add(int hotelId, int staffId);
	
}
