package tiquartet.ClientModule.ui.hotelinfoui;

import java.util.*;
import tiquartet.ClientModule.vo.*;
import tiquartet.ClientModule.blservice.hotelinfoblservice.*;
import tiquartet.ClientModule.bl.hotelinfobl.*;

public class HotelInfoBL_driver {
   public void drive(HotelInfoBLService stub){
	   long hotelID=0;
	   Calendar start = Calendar.getInstance();
	   Calendar end = Calendar.getInstance();
	   HotelDetailsVO hdvo = stub.getHotelDetails(hotelID);
	   List<RoomTypeVO> list = stub.availableRoomType(start,end,hotelID);
	   stub.reviewHotel(new ReviewVO());
	   System.out.println("Reviewing.");
   }
   public static void main(String[] args){
	   new HotelInfoBL_driver().drive(new HotelInfoBL_stub());
   }
}
