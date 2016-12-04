package tiquartet.CommonModule.blservice.manageroomblservice;

import tiquartet.CommonModule.vo.RoomTypeVO;
import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.CommonModule.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author 
 *
 */
public interface ManageRoomBLService extends Remote{

	/**获取酒店房间列表
	 * @param hotelID
	 * @return
	 * @throws RemoteException
	 */
	public List<RoomVO> getRoomList (int hotelID) throws RemoteException; 
	
	/**修改房间信息
	 * @param room
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage modifyRoomInfo (RoomVO room) throws RemoteException;
	
	/**添加房间
	 * @param room
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage addRoom (RoomVO room) throws RemoteException;
	
	/**删除房间
	 * @param roomID
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage deleteRoom (int roomID) throws RemoteException; 
	
	/**办理入住
	 * @param roomID
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage checkIn(int roomID) throws RemoteException;
	
	/**办理退房
	 * @param roomID
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage checkOut(int roomID) throws RemoteException;
	
	/**添加房间类型
	 * @param roomType
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage addRoomType(RoomTypeVO roomType) throws RemoteException;
	
	/**修改房间类型
	 * @param roomType
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage modifyRoomType(RoomTypeVO roomType) throws RemoteException;
	
	/**删除房间类型
	 * @param HotelID
	 * @param RoomTypeID
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage deleteRoomType(int HotelID,int RoomTypeID) throws RemoteException;
}
