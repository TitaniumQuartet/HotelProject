package tiquartet.ClientModule.ui.manageorderui;

import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.*;
import tiquartet.ServerModule.bl.manageorderbl.*;
import tiquartet.CommonModule.blservice.manageorderblservice.*;

import java.sql.Time;
import java.util.*;

public class ManageOrderBL_driver {

	public void drive(ManageOrderBLService stub){
		
		List<OrderVO> ordervo = stub.orderHistory(1,new OrderFilterVO(),OrderSort.DATEDESCEND,1,10);
		System.out.println(ordervo);
		ResultMessage message;
		
		ordervo = stub.hotelOrders(245,new OrderFilterVO(),OrderSort.DATEDESCEND,1,10);
		System.out.println("Searching for order list...");
		
		message = stub.clientCancel((long) 353452);
		System.out.println(message);
		
		message = stub.marketerCancel(8976986,CreditRestore.FULL);
		System.out.println(message);
		
		message = stub.checkIn(54245,"2015/03/01 15:03:45");
		System.out.println(message);
		
		message = stub.checkOut(54245);
		System.out.println(message);
		
	}
	
	public static void main(String[] args){
		
		new ManageOrderBL_driver().drive(new ManageOrder());
	}
}
