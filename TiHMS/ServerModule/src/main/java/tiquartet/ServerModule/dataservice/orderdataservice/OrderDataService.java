package tiquartet.ServerModule.dataservice.orderdataservice;

import java.util.List;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.OrderPO;

public interface OrderDataService {
	
	/**
	 * 初始的订单生成订单号.
	 * @return
	 */
	public OrderPO preOrder (OrderPO preOrder);
	
	/**
	 * 取消初始订单.
	 * @return
	 */
	public ResultMessage cancelPreOrder (OrderPO preOrder);
	
	/**
	 * 向数据库中插入一条新的订单记录.
	 * @return
	 */
	public ResultMessage insert(OrderPO order);
	
	/**
	 * 更新order数据库中的一条记录.
	 * @return
	 */
	public ResultMessage update(OrderPO order);
	
	/**
	 * 根据hotelID和订单状态搜索该酒店的订单.
	 * @return
	 */
	public List<OrderPO> searchByHotel (int hotelID, OrderStatus status);
	
	/**
	 * 根据userID搜索订单.
	 * @return
	 */
	public List<OrderPO> searchByUser (int userID);
	
	/**
	 * 根据订单号得到订单信息.
	 * @return
	 */
	public OrderPO getOrderByID (long orderID);
	
	/**
	 * 计算该用户某种状态的订单总数.
	 * @return
	 */
	public int countOrder (int userID, OrderStatus status);
	
	/**
	 * 判断酒店未执行订单是否过时，若是，则置为异常订单，并扣除信用值.
	 * @return
	 */
	public ResultMessage updateState();

}
