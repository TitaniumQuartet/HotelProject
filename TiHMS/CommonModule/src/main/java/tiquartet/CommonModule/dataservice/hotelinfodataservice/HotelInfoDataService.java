package tiquartet.CommonModule.dataservice.hotelinfodataservice;

import tiquartet.CommonModule.po.HotelDetailsPO;
import tiquartet.CommonModule.po.HotelInfoPO;
import tiquartet.CommonModule.po.HotelPO;
import tiquartet.CommonModule.po.RoomTypePO;
import tiquartet.CommonModule.util.ResultMessage;

import java.util.List;

public interface HotelInfoDataService {
	public HotelPO briefHotelInfo(long hotelID);
	public HotelDetailsPO searchHotelDetails(long hotelID);
	public HotelInfoPO updateRate(long HotelID, int newRate);
	public ResultMessage insert(HotelDetailsPO hotel);
	public ResultMessage update(HotelInfoPO hotelInfo);
	public RoomTypePO getRoomType(long hotelID, int roomTypeID);
	public List<RoomTypePO> getRoomTypes(long HotelID);
	public HotelPO getHotelList(int cityID, int districtID);

}
