package tiquartet.ClientModule.ui.manageuserui;

import java.util.List;

import tiquartet.CommonModule.vo.*;
import tiquartet.ServerModule.bl.manageuserbl.*;
import tiquartet.CommonModule.blservice.manageuserblservice.ManageUserBLService;


public class ManageUserBL_drive {

	public void drive(ManageUserBLService stub){
		
		List<UserVO> userlist = stub.accurateSearch("great","张三");
		System.out.println("Searching for users...");
		
		List<HotelStaffVO> stafflist = stub.searchHotelStaff(4,4001);
		System.out.println("Searching for hotel staff...");
		
		UserVO staff = stub.getUser(34);
		System.out.println("Searching for hotel staff...");
		
		List<UserVO> marketerlist = stub.marketerList();
		System.out.println("Getting user list...");
		
		System.out.println(stub.creditRecharge(33425,255));
		
		System.out.println(stub.addHotel(1034,"九乡河滨招待所"));
		
		System.out.println(stub.addHotelStaff(40010052,"goodboy023","simpleaf"));
	}
	
	public static void main(String[] args){
		
		new ManageUserBL_drive().drive(new ManageUserBL_stub());
	}
}
