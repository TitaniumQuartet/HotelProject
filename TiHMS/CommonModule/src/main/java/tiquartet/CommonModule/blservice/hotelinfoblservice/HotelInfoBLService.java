package tiquartet.CommonModule.blservice.hotelinfoblservice;

import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.*;

public interface HotelInfoBLService extends Remote{
	public HotelBriefVO getHotelBrief (int hotelID,int userID) throws RemoteException;
	public HotelDetailsVO getHotelDetails (int hotelID,int userID) throws RemoteException;
	public List<RoomTypeVO> availableRoomType(PreOrderVO preOrder) throws RemoteException;
	public ResultMessage reviewHotel(ReviewVO review) throws RemoteException;
	public ResultMessage modifyHotelInfo (HotelInfoVO hotelInfo) throws RemoteException;
	public List<HotelBriefVO> clientHotelList(int userID) throws RemoteException;
}
