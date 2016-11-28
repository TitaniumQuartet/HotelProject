package tiquartet.CommonModule.blservice.hotelinfoblservice;

import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.*;

public interface HotelInfoBLService extends Remote{
	/**
	 * 获得酒店简略信息
	 * @param hotelID
	 * @param userID
	 * @return
	 * @throws RemoteException
	 */
	public HotelBriefVO getHotelBrief (int hotelID,int userID) throws RemoteException;
	/**
	 * 获得酒店详细信息
	 * @param hotelID
	 * @param userID
	 * @return
	 * @throws RemoteException
	 */
	public HotelDetailsVO getHotelDetails (int hotelID,int userID) throws RemoteException;
	/**
	 * 获得可用房型
	 * @param preOrder
	 * @return
	 * @throws RemoteException
	 */
	public List<RoomTypeVO> availableRoomType(PreOrderVO preOrder) throws RemoteException;
	/**评论酒店
	 * @param review
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage reviewHotel(ReviewVO review) throws RemoteException;
	/**修改酒店信息
	 * @param hotelInfo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage modifyHotelInfo (HotelInfoVO hotelInfo) throws RemoteException;
	/**返回客户预定过得酒店列表
	 * @param userID
	 * @return
	 * @throws RemoteException
	 */
	public List<HotelBriefVO> clientHotelList(int userID) throws RemoteException;
}
