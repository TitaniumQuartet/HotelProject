package tiquartet.server;

import java.util.ArrayList;

public interface ManageUserbl {

	public ArrayList<UserPO> searchUser(String name, int userId);
	
	public ArrayList<HotelStaffPO> searchHotelStaff(String city, String area);
	
	public HotelStaffPO searchStaff(int staffId);
	
	public ArrayList<UserPO> getUserList();
	
	public ArrayList<UserPO> sortUserList(String way);
	
	public ResultMessage credit(double money, int userId);
	
	public ResultMessage addHotel(String hotelName);
	
	public ResultMessage add(int hotelId, int staffId);
	
}
