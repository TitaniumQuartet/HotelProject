package tiquartet.common.dataservice.hotelinfodataservice;

public interface HotelInfoData {
	public HotelPO briefHotelInfo(long hotelID);
	public HotelDetailsPO searchHotelDetails(long hotelID);
	public HotelInfoPO updateRate(long HotelID, int newRate);
	public ResultMessage insert(HotelDetailsPO hotel);
	public ResultMessage update(HotelInfoPO hotelInfo);
	public RoomTypePO getRoomType(long hotelID, int roomTypeID);
	public List<RoomTypePO> getRoomTypes(long HotelID);
	public HotelPO getHotelList(int cityID, int districtID);

}
