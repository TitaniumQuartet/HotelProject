package tiquartet.stub_driver.hotelinfodata;


import tiquartet.common.dataservice.hotelinfodataservice;
import java.util.List;
public class HotelInfoData_stub implements HotelInfoDtaService{
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
		return new List<RoomTypePO>();
	}
	public HotelPO getHotelList(int cityID, int districtID){
		System.out.println("yes");
		return new HotelPO();
	}

}
