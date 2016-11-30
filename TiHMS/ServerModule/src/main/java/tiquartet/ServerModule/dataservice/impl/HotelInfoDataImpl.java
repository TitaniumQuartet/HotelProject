package tiquartet.ServerModule.dataservice.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.HotelInfoDataHelper;
import tiquartet.ServerModule.datahelper.service.OrderDataHelper;
import tiquartet.ServerModule.dataservice.hotelinfodataservice.HotelInfoDataService;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.HotelPO;
import tiquartet.ServerModule.po.OrderPO;
import tiquartet.ServerModule.po.RoomTypePO;

public class HotelInfoDataImpl implements HotelInfoDataService{

	private Map<Integer, HotelInfoPO> map;
	
	private HotelInfoDataHelper hotelinfoDataHelper;
	
	private DataFactoryService dataFactory;

	private static HotelInfoDataImpl hotelinfoDataServiceImpl;
	
	private Connection con;
	
	public static HotelInfoDataImpl getInstance(){
		if(hotelinfoDataServiceImpl == null){
			hotelinfoDataServiceImpl = new HotelInfoDataImpl();
		}
		return hotelinfoDataServiceImpl;
	}
	
	public HotelInfoDataImpl(){
		if(map == null){
			dataFactory = new DataFactory();
			hotelinfoDataHelper = dataFactory.getHotelInfoDataHelper();
			map = hotelinfoDataHelper.getHotelInfo();
		}
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	@Override
	public HotelInfoPO getHotelInfo(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateRate(int HotelID, double newRate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insert(HotelInfoPO hotelInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(HotelInfoPO hotelInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomTypePO> getRoomTypes(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomTypePO> availableRoomType(PreOrderVO preOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HotelInfoPO> getHotelList(int districtID) {
		// TODO Auto-generated method stub
		return null;
	}

}
