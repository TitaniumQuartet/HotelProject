package tiquartet.stub_driver.hotelinfo;


public class HotelInfoBL_driver {
   public void drive(HotelInfoBLService stub){
	   
   }
   public static void main(String[] args){
	   new HotelInfoBL_driver().drive(new HotelInfoBL_stub());
   }
}
