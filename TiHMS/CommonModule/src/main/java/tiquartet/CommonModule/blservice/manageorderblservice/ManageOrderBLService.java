package tiquartet.CommonModule.blservice.manageorderblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import tiquartet.CommonModule.util.ResultMessage;
import java.util.List;
import tiquartet.CommonModule.vo.OrderVO;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderNumVO;
import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;;

/**
 * @author 李珍鸿
 *
 */
public interface ManageOrderBLService extends Remote{
	
	/**订单的排序
	 * @param filter
	 * @param sort
	 * @param rank1
	 * @param rank2
	 * @return
	 * @throws RemoteException
	 */
	public List<OrderVO> orderHistory( OrderFilterVO filter, OrderSort sort, int rank1, int rank2) throws RemoteException;

	/**获得订单
	 * @param orderID
	 * @return
	 * @throws RemoteException
	 */
	public OrderVO getOrderByID (long orderID) throws RemoteException;
	
	/**酒店订单排序
	 * @param filter
	 * @param sort
	 * @param rank1
	 * @param rank2
	 * @return
	 * @throws RemoteException
	 */
	public List<OrderVO> hotelOrders(OrderFilterVO filter, OrderSort sort, int rank1, int rank2) throws RemoteException;
		
	/**网站营销人员撤销异常订单
	 * @param orderID
	 * @param restore
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage marketerCancel (long orderID, CreditRestore restore) throws RemoteException;
	
	/**客户取消
	 * @param orderID
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage clientCancel(long orderID) throws RemoteException;
	
	/**入住的时候保存订单
	 * @param orderID
	 * @param estLeaveTime
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage checkIn(long orderID, String estLeaveTime) throws RemoteException;
	
	/**离店的时候保存订单
	 * @param orderID
	 * @param leaveTime
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage checkOut(long orderID,String leaveTime) throws RemoteException;
	
	/**用户预订过的酒店列表
	 * @param userID
	 * @return
	 * @throws RemoteException
	 */
	public List<Integer> orderedHotelID (int userID) throws RemoteException;
	
	/**客户在该酒店的订单列表
	 * @param userID
	 * @param hotelID
	 * @return
	 * @throws RemoteException
	 */
	public List<OrderVO> clientAtHotel (int userID,int hotelID) throws RemoteException;
	
	/**返回客户在酒店各类订单数目
	 * 
	 * @param userID
	 * @param hotelID
	 * @return
	 * @throws RemoteException
	 */
	public OrderNumVO numAtHotel (int userID,int hotelID) throws RemoteException;
	
}
