package tiquartet.ClientModule.ui.manageorderui;

import tiquartet.ClientModule.vo.*;
import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.ResultMessage;
import java.sql.Time;
import java.util.*;
import tiquartet.ClientModule.blservice.manageorderblservice.*;
import tiquartet.ClientModule.bl.manageorderbl.*;

public class ManageOrderBL_driver {

	public void drive(ManageOrderBLService stub){
		
		List<OrderVO> ordervo = stub.clientOrderList(1,225);
		System.out.println(ordervo);
		ResultMessage message;
		
		ordervo = stub.hotelOrderList(245,new OrderFilterVO(),3);
		System.out.println("Searching for order list...");
		
		message = stub.clientCancelOrder("353452");
		System.out.println(message);
		
		message = stub.marketerCancelOrder("8976986",CreditRestore.FULL);
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
