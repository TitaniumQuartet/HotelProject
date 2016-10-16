package tiquartet.stub_driver.blservice.manageorder;

import tiquartet.client.vo.*;
import tiquartet.common.util.CreditRestore;
import tiquartet.common.util.ResultMessage;

import java.sql.Time;
import java.util.*;
import tiquartet.client.blservice.manageorderblservice.*;

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
