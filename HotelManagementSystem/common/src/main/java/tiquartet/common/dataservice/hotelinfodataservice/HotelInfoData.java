package tiquartet.common.dataservice.hotelinfodataservice;

public interface HotelInfoData {
	public HotelInfoPO getHotelInfo(long hotelID);
	public HotelInfoPO search(long hotelID);
	public HotelInfoPO updateRate(long HotelID, int newRate);
	public ResultMessage insert(HotelDetailsPO hotel);
	public ResultMessage update(HotelInfoPO hotelInfo);

}
