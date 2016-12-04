package tiquartet.ServerModule.dataservice.impl;

import java.util.List;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.HotelInfoDataHelper;
import tiquartet.ServerModule.dataservice.hotelinfodataservice.HotelInfoDataService;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.RoomTypePO;

public class HotelInfoDataImpl implements HotelInfoDataService{
	
	private HotelInfoDataHelper hotelinfoDataHelper;
	
	private DataFactoryService dataFactory;

	private static HotelInfoDataImpl hotelinfoDataServiceImpl;
	
	
	public static HotelInfoDataImpl getInstance(){
		if(hotelinfoDataServiceImpl == null){
			hotelinfoDataServiceImpl = new HotelInfoDataImpl();
		}
		return hotelinfoDataServiceImpl;
	}
	
	public HotelInfoDataImpl(){
		if(dataFactory == null){
			dataFactory = new DataFactory();
			hotelinfoDataHelper = dataFactory.getHotelInfoDataHelper();
		}
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	@Override
	public HotelInfoPO getHotelInfo(int hotelID) {
		return hotelinfoDataHelper.getHotelInfo(hotelID);
	}

	@Override
	public ResultMessage insert(HotelInfoPO hotelInfo) {
		return hotelinfoDataHelper.insert(hotelInfo);
	}

	@Override
	public ResultMessage update(HotelInfoPO hotelInfo) {
		return hotelinfoDataHelper.update(hotelInfo);
	}

	@Override
	public List<RoomTypePO> getRoomTypes(int hotelID) {
		return hotelinfoDataHelper.getRoomTypes(hotelID);
	}

	@Override
	public List<HotelInfoPO> getHotelList(int districtID) {
		return hotelinfoDataHelper.getHotelList(districtID);
	}

	

}
