package tiquartet.ServerModule.dataservice.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.RoomDataHelper;
import tiquartet.ServerModule.dataservice.roomdataservice.RoomDataService;
import tiquartet.ServerModule.po.*;

public class RoomDataImpl implements RoomDataService{

	private RoomDataHelper roomDataHelper;
	
	private DataFactoryService dataFactory;

	private static RoomDataImpl roomDataServiceImpl;
	
	private Connection con;
	
	public static RoomDataImpl getInstance(){
		if(roomDataServiceImpl == null){
			roomDataServiceImpl = new RoomDataImpl();
		}
		return roomDataServiceImpl;
	}
	
	public RoomDataImpl(){
		if(dataFactory == null){
			dataFactory = new DataFactory();
			roomDataHelper = dataFactory.getRoomDataHelper();
		}
	}

	@Override
	public List<RoomTypePO> availableRoomType(int hotelID, String startDate, String endDate, int numOfRoom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(RoomPO room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insert(RoomPO room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(int roomID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage checkIn(int roomID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage checkOut(int roomID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insertType(int hotelID, RoomTypePO room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateType(int hotelID, RoomTypePO room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteType(int hotelID, RoomTypePO room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomPO> getRoomList(int hotelID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
