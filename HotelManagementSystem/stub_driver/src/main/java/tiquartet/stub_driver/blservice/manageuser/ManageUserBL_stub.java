package tiquartet.stub_driver.blservice.manageuser;

import java.util.ArrayList;

import tiquartet.client.blservice.HotelStaffPO;
import tiquartet.client.blservice.ResultMessage;
import tiquartet.client.blservice.UserPO;

public class ManageUserBL_stub {

	public ArrayList<UserPO> searchUser(String name, int userId){
		return new ArrayList<UserPO>();
	}
	
	public ArrayList<HotelStaffPO> searchHotelStaff(String city, String area){
		return new ArrayList<HotelStaffPO>();
	}
	
	public HotelStaffPO searchStaff(int staffId){
		return new HotelStaffPO();
	}
	
	public ArrayList<UserPO> getUserList(){
		return new ArrayList<UserPO>();
	}
	
	public ArrayList<UserPO> sortUserList(String way){
		return new ArrayList<UserPO>();
	}
	
	public ResultMessage credit(double money, int userId){
		return ResultMessage.succeed;
	}
	
	public ResultMessage addHotel(String hotelName){
		return ResultMessage.succeed;
	}
	
	public ResultMessage add(int hotelId, int staffId){
		return ResultMessage.succeed;
	}
}
