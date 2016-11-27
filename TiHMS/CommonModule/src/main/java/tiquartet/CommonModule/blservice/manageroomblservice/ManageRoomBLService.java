package tiquartet.CommonModule.blservice.manageroomblservice;

import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.CommonModule.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ManageRoomBLService extends Remote{

	public List<RoomVO> getRoomList (long hotelID) throws RemoteException; 
	
	public ResultMessage modifyRoomInfo (RoomVO room) throws RemoteException;
	
	public ResultMessage addRoom (RoomVO room) throws RemoteException;
	
	public ResultMessage deleteRoom (int roomID) throws RemoteException; 
	
	public ResultMessage checkIn(int roomID) throws RemoteException;
	
	public ResultMessage checkOut(int roomID) throws RemoteException;
	
	public ResultMessage addRoomType(RoomTypeVO roomType) throws RemoteException;
	
	public ResultMessage modifyRoomType(RoomTypeVO roomType) throws RemoteException;
	
	public ResultMessage deleteRoomType(int HotelID,int RoomTypeID) throws RemoteException;
}
