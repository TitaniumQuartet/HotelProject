package tiquartet.ServerModule.dataservice.impl;

import java.sql.Connection;
import java.util.List;
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
		return roomDataHelper.availableRoomType(hotelID, startDate, endDate, numOfRoom);
	}

	@Override
	public ResultMessage update(RoomPO room) {
		return roomDataHelper.update(room);
	}

	@Override
	public ResultMessage insert(RoomPO room) {
		return roomDataHelper.insert(room);
	}

	@Override
	public ResultMessage delete(int roomID) {
		return roomDataHelper.delete(roomID);
	}

	@Override
	public ResultMessage checkIn(int roomID) {
		return roomDataHelper.checkIn(roomID);
	}

	@Override
	public ResultMessage checkOut(int roomID) {
		return roomDataHelper.checkOut(roomID);
	}

	@Override
	public ResultMessage insertType(RoomTypePO room) {
		return roomDataHelper.insertType(room);
	}

	@Override
	public ResultMessage updateType(RoomTypePO room) {
		return roomDataHelper.updateType(room);
	}

	@Override
	public ResultMessage deleteType(RoomTypePO room) {
		return roomDataHelper.deleteType(room);
	}

	@Override
	public List<RoomPO> getRoomList(int hotelID) {
		return roomDataHelper.getRoomList(hotelID);
	}
	
	
	
}
