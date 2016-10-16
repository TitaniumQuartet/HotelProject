package tiquartet.stub_driver.blservice.hotelinfo;



public class HotelInfoBL_stub implements HotelInfoBLService{
	public HotelDetailsVO getHotelDetails(long hotelID){
		return new HotelDetailsVO;
	}
	public List<RoomTypeVO> availableRoomType(Calendar start, Calendar end, long hotelID){
		return new List<RoomTypeVO>;
	}
	public ResultMessage reviewHotel(ReviewVO review){
		return ResultMessage.SUCCEED;
	}
}
