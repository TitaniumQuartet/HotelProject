package tiquartet.ServerModule.dataservice.hotelinfodataservice;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.HotelDetailsPO;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.HotelPO;
import tiquartet.ServerModule.po.RoomTypePO;

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
