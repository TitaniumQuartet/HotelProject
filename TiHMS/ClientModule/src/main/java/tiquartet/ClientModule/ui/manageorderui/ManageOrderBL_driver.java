package tiquartet.ClientModule.ui.manageorderui;

import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.*;
import tiquartet.ServerModule.bl.manageorderbl.*;
import tiquartet.ServerModule.blservice.manageorderblservice.*;

import java.sql.Time;
import java.util.*;

public class ManageOrderBL_driver {

	public void drive(ManageOrderBLService stub){
		
		List<OrderVO> ordervo = stub.clientOrderList(1,225);
		System.out.println(ordervo);
		ResultMessage message;
		
		ordervo = stub.hotelOrders(245,new OrderFilterVO(),3);
		System.out.println("Searching for order list...");
		
		message = stub.clientCancel("353452");
		System.out.println(message);
		
		message = stub.marketerCancel("8976986",CreditRestore.FULL);
		System.out.println(message);
		
		message = stub.checkIn("302",new Time(System.nanoTime()),"54245");
		System.out.println(message);
		
		message = stub.checkOut(23);
		System.out.println(message);
		
		ordervo = stub.getOrderList("","",new Date(),34252,11,'1');
		System.out.println("Searching for order list...");
	}
	
	public static void main(String[] args){
		
		new ManageOrderBL_driver().drive(new ManageOrderBL_stub());
	}
}
