package tiquartet.common.dataservice.hotelinfodataservice;

import tiquartet.common.po.HotelDetailsPO;
import tiquartet.common.po.HotelInfoPO;
import tiquartet.common.po.HotelPO;
import tiquartet.common.po.RoomTypePO;
import tiquartet.common.util.ResultMessage;

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
