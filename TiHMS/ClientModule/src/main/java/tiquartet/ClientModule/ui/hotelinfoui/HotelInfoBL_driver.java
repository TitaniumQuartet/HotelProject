package tiquartet.ClientModule.ui.hotelinfoui;

import java.util.*;

import tiquartet.CommonModule.blservice.hotelinfoblservice.HotelInfoBLService;
import tiquartet.CommonModule.vo.*;
import tiquartet.ServerModule.bl.hotelinfobl.*;

public class HotelInfoBL_driver {
   public void drive(HotelInfoBLService stub){
	   int hotelID=0;
	   Calendar start = Calendar.getInstance();
	   Calendar end = Calendar.getInstance();
	   HotelDetailsVO hdvo = stub.getHotelDetails(hotelID);
	   List<RoomTypeVO> list = stub.availableRoomType(new PreOrderVO());
	   stub.reviewHotel(new ReviewVO());
	   System.out.println("Reviewing.");
   }
   public static void main(String[] args){
	   new HotelInfoBL_driver().drive(new HotelInfoBL_stub());
   }
}
