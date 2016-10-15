package tiquartet.stub_driver.roomdata;

import tiquartet.common.dataservice.roomdataservice;

public class RoomData_stub {
	public ResultMessage update(RoomPO room){
		return ResultMessage.SUCCEED;
	}
	public ResultMessage insert(RoomPO room){
		return ResultMessage.SUCCEED;
	}
	public ResultMessage delete(int roomID){
		return ResultMessage.SUCCEED;
	}


}
