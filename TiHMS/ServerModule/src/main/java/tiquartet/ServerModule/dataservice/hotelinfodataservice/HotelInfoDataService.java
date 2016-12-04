package tiquartet.ServerModule.dataservice.hotelinfodataservice;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.*;
import tiquartet.CommonModule.vo.*;

import java.util.List;

public interface HotelInfoDataService {
	
	public HotelInfoPO getHotelInfo (int hotelID);
	public ResultMessage insert (HotelInfoPO hotelInfo);
	public ResultMessage update (HotelInfoPO hotelInfo);
	public List <RoomTypePO> getRoomTypes (int hotelID);
	public List <HotelInfoPO> getHotelList (int districtID);

}
