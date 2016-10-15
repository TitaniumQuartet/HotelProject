package tiquartet.common.dataservice.roomdataservice;

public interface RoomDataService {
	public ResultMessage update(RoomPO room);
	public ResultMessage insert(RoomPO room);
	public ResultMessage delete(int roomID);


}
