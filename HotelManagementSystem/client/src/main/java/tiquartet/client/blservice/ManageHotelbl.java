package tiquartet.client.blservice;

public interface ManageHotelbl {

	public HotelInfoVO getHotelInfo(long userID);
	
	public ResultMessage modifyHotelInfo(HotelInfoVO hotelInfo);
	
}
