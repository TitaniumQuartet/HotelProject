package tiquartet.stub_driver.blservice.hotelinfo;

import java.util.*;
import tiquartet.client.vo.*;
import tiquartet.client.blservice.hotelinfoblservice.*;

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
