package tiquartet.ServerModule.data.hotelinfodata;


import tiquartet.CommonModule.dataservice.hotelinfodataservice.*;
import java.util.*;
import tiquartet.CommonModule.po.*;
import tiquartet.CommonModule.util.ResultMessage;


public class HotelInfoData_stub implements HotelInfoDataService{
	public HotelPO getHotelInfo(long hotelID){
		System.out.println("yes");
		return new HotelPO();
	}
	public HotelDetailsPO search(long hotelID){
		System.out.println("yes");
		return new HotelDetailsPO();
	}
	public HotelInfoPO updateRate(long HotelID, int newRate){
		System.out.println("yes");
		return new HotelInfoPO();
	}
	public ResultMessage insert(HotelDetailsPO hotel){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	public ResultMessage update(HotelInfoPO hotelInfo){
		System.out.println("yes");
		return ResultMessage.SUCCEED;
	}
	public RoomTypePO getRoomType(long hotelID, int roomTypeID){
		System.out.println("yes");
		return new RoomTypePO();
	}
	public List<RoomTypePO> getRoomTypes(long HotelID){
		System.out.println("yes");
		return new ArrayList<RoomTypePO>();
	}
	public HotelPO getHotelList(int cityID, int districtID){
		System.out.println("yes");
		return new HotelPO();
	}
	public HotelPO briefHotelInfo(long hotelID){
		System.out.println("Getting brief info...");
		return new HotelPO();
	}
	public HotelDetailsPO searchHotelDetails(long hotelID){
		System.out.println("Getting detailed info...");
		return new HotelDetailsPO();
	}

}
