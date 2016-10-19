package tiquartet.StubDriver.blservice.manageuser;

import java.util.List;

import tiquartet.ClientModule.blservice.manageuserblservice.ManageUserBLService;
import tiquartet.ClientModule.vo.*;


public class ManageUserBL_drive {

	public void drive(ManageUserBLService stub){
		
		List<UserVO> userlist = stub.searchUser("ane",0);
		System.out.println("Searching for users...");
		
		List<HotelStaffVO> staffpo = stub.searchHotelStaff("","");
		System.out.println("Searching for hotel staff...");
		
		HotelStaffVO staff = stub.searchStaff(5);
		System.out.println("Searching for hotel staff...");
		
		userlist = stub.getUserList();
		System.out.println("Getting user list...");
		
		System.out.println(stub.credit(200, 33425));
		
		System.out.println(stub.addHotel("Big hotel"));
		
		System.out.println(stub.add(25235,23423));
	}
	
	public static void main(String[] args){
		
		new ManageUserBL_drive().drive(new ManageUserBL_stub());
	}
}
