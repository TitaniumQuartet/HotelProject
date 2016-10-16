package searchHotelbl.stub_driver;

public class SearchHotelbl_driver {

public void drive(SearchHotelbl stub){
		
		HotelVO hotelvo = stub.getHotelList();
		System.out.println(hotelvo);
		
		hotelvo = stub.filter();
		System.out.println(hotelvo);
	}
	
	public static void main(String[] args){
		
		new SearchHotelbl_driver().drive(new SearchHotelbl_stub());
	}
}
