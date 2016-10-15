package tiquartet.common.dataservice.hotelinfodataservice;

public class HotelInfoDataService {
	public HotelInfoPO getHotelInfo(long hotelID);
	public HotelInfoPO search(long hotelID);
	public HotelInfoPO updateRate(long HotelID, int newRate);
	public ResultMessage insert(HotelDetailsPO hotel);
	public ResultMessage update(HotelInfoPO hotelInfo);

}
