package tiquartet.stub_driver.hotelinfodata;


import tiquartet.common.dataservice.hotelinfodataservice;

public class HotelInfoData_stub {
	public HotelInfoPO getHotelInfo(long hotelID){
		return new HotelInfoPO();
	}
	public HotelInfoPO search(long hotelID){
		return new HotelInfoPO();
	}
	public HotelInfoPO updateRate(long HotelID, int newRate){
		return new HotelInfoPO();
	}
	public ResultMessage insert(HotelDetailsPO hotel){
		return ResultMessage.SUCCEED;
	}
	public ResultMessage update(HotelInfoPO hotelInfo){
		return ResultMessage.SUCCEED;
	}

}
