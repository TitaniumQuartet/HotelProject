package tiquartet.stub_driver.blservice.hotelinfo;


public class HotelInfoBL_driver {
   public void drive(HotelInfoBLService stub){
	   long hotelID=0;
	   Calendar start = new Calendar();
	   Calendar end = new Calendar();
	   HotelDetailsVO hdvo = stub.getHotelDetails(hotelID);
	   List<RoomTypeVO> li = stub.availableRoomType(start,end,hotelID);
	   System.out.println(stub.reviewHotel);
   }
   public static void main(String[] args){
	   new HotelInfoBL_driver().drive(new HotelInfoBL_stub());
   }
}
