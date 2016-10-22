package tiquartet.StubDriver.blservice.manageuser;

import java.util.ArrayList;

import tiquartet.ClientModule.blservice.manageuserblservice.*;
import tiquartet.ClientModule.vo.*;
import tiquartet.CommonModule.util.ResultMessage;

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
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage addHotel(String hotelName){
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage add(int hotelId, int staffId){
		return ResultMessage.SUCCEED;
	}
}