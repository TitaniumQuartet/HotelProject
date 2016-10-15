package tiquartet.client.blservice.hotelinfoblservice;

public interface HotelInfoBLService {
	public HotelDetailsVO getHotelDetails(long hotelID);
	public List<RoomTypeVO> availableRoomType(Calendar start, Calendar end, long hotelID);
	public ResultMessage reviewHotel(ReviewVO review);
}
