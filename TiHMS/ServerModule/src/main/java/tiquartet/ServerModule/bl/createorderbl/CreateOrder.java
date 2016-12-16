package tiquartet.ServerModule.bl.createorderbl;

import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.ServerModule.dataservice.impl.OrderDataImpl;
import tiquartet.ServerModule.dataservice.impl.RoomDataImpl;
import tiquartet.ServerModule.dataservice.impl.StrategyDataImpl;
import tiquartet.ServerModule.dataservice.impl.UserDataImpl;
import tiquartet.ServerModule.po.OrderPO;
import tiquartet.ServerModule.po.StrategyPO;
import tiquartet.ServerModule.po.UserPO;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.vo.OrderStrategyVO;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tiquartet.CommonModule.blservice.createorderblservice.CreateOrderBLService;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.StrategyType;

public class CreateOrder implements CreateOrderBLService {

	/**
	 * @param hotelID
	 * @param roomTypeID
	 * @return
	 * @throws RemoteException
	 */
	StrategyDataImpl strategydataimpl;
	UserDataImpl userdataimpl;
	OrderDataImpl orderdataimpl;
	RoomDataImpl roomdataimpl;
	// 用户编号和初始订单的映射
	Map<Integer, PreOrderVO> map = new HashMap<Integer, PreOrderVO>();

	public CreateOrder() {
		strategydataimpl = StrategyDataImpl.getInstance();
		userdataimpl = UserDataImpl.getInstance();
		orderdataimpl = OrderDataImpl.getInstance();
		roomdataimpl = RoomDataImpl.getInstance();
	}

	public OrderStrategyVO getStrategy(int userID) throws RemoteException {
		PreOrderVO preorder = map.get(userID);
		List<OrderPO> orderlist = orderdataimpl.searchByUser(preorder.hotelID, userID);
		OrderPO order = new OrderPO();
		for (int i = 0; i < orderlist.size(); i++) {
			if (orderlist.get(i).getorderStatus() == OrderStatus.暂时预订) {
				order = orderlist.get(i);
			}
		}
		UserPO user = userdataimpl.getUser(userID);
		List<StrategyPO> polist = StrategyDataImpl.getInstance().searchByHotel(preorder.hotelID);
		List<OrderStrategyVO> orderStrategylist = new ArrayList<OrderStrategyVO>();
		OrderStrategyVO orderstrategy = new OrderStrategyVO();
		orderstrategy.orderID = order.getorderId();
		// 计算价格
		for (int i = 0; i < polist.size(); i++) {
			orderstrategy.strategyID = polist.get(i).getstrategyId();
			orderstrategy.strategyIntroduce = polist.get(i).getstrategyIntro();
			double price = preorder.price * preorder.numOfRoom;
			orderstrategy.orderPrice = price;
			if (polist.get(i).getStrategyType() == StrategyType.BIRTHDAY) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				if (user.getbirthday().equals(format.format(new Date()))) {
					price = price * polist.get(i).getdiscount();
					orderstrategy.orderPrice = price;
					orderStrategylist.add(orderstrategy);
				}
			} else if (polist.get(i).getStrategyType() == StrategyType.CIRCLE) {
				if (preorder.hotelID / 1000 != polist.get(i).getCircelID()) {
					double[] memberDiscount = polist.get(i).getMemberDiscount();
					price = price * memberDiscount[user.getmemberRank()];
					orderstrategy.orderPrice = price;
					orderStrategylist.add(orderstrategy);
				}
			} else if (polist.get(i).getStrategyType() == StrategyType.ROOMNUM) {
				if (preorder.numOfRoom >= polist.get(i).getnumOfRoom()) {
					price = price * polist.get(i).getdiscount();
					orderstrategy.orderPrice = price;
					orderStrategylist.add(orderstrategy);
				}
			} else {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date nowdate = new Date();
				try {
					Date startdate = format.parse(polist.get(i).getStartTime());
					Date enddate = format.parse(polist.get(i).getEndTime());
					if (startdate.before(nowdate) && enddate.after(nowdate)) {
						price = price * polist.get(i).getdiscount();
						orderstrategy.orderPrice = price;
						orderStrategylist.add(orderstrategy);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		// 取最小价格的策略
		for (int i = 0; i < orderStrategylist.size(); i++) {
			if (orderStrategylist.get(i).orderPrice < orderstrategy.orderPrice) {
				orderstrategy = orderStrategylist.get(i);
			}
		}

		return orderstrategy;
	}

	public ResultMessage preOrder(PreOrderVO preOrder) throws RemoteException {
		if (preOrder.userID == -1) {
			return new ResultMessage(false, "订单用户为空", "");
		}

		if (!userdataimpl.getCreditBalance(preOrder.userID).result) {
			return userdataimpl.getCreditBalance(preOrder.userID);
		}
		int credit = Integer.parseInt(userdataimpl.getCreditBalance(preOrder.userID).message);
		if (credit < 0) {
			return new ResultMessage(false, "用户信用值低于0", "");
		}
		map.put(preOrder.userID, preOrder);
		OrderPO order = new OrderPO(preOrder);
		order.setorderStatus(OrderStatus.暂时预订);
		OrderPO preorder = orderdataimpl.preOrder(order);
		if (preorder != null) {
			return new ResultMessage(true, "", String.valueOf(preorder.getorderId()));
		}
		return new ResultMessage(false, "存取订单失败", "");
	}

	public ResultMessage cancelPreOrder(int userID) throws RemoteException {
		int hotelID = map.get(userID).hotelID;
		List<OrderPO> orderlist = orderdataimpl.searchByUser(hotelID, userID);
		OrderPO preOrder = new OrderPO();
		for (int i = 0; i < orderlist.size(); i++) {
			if (orderlist.get(i).getorderStatus() == OrderStatus.暂时预订) {
				preOrder = orderlist.get(i);
				map.remove(userID);
				return orderdataimpl.cancelPreOrder(preOrder);
			}
		}
		return new ResultMessage(false, "找不到该订单", "");
	}

	public ResultMessage confirm(OrderInfoVO orderInfo) throws RemoteException {
		PreOrderVO preorder = map.get(orderInfo.userID);
		OrderPO order = new OrderPO(preorder);
		order.setorderId(orderInfo.orderID);
		order.setguestRealName(orderInfo.guestRealName);
		order.setnumberOfPeople(orderInfo.numOfGuest);
		order.setprice(orderInfo.price);
		order.setchild(orderInfo.kids);
		order.setlatestTime(orderInfo.lastTime);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		order.setorderTime(format.format(new Date()));
		order.setorderStatus(OrderStatus.未执行订单);
		return orderdataimpl.update(order);
	}

	public List<String> offLine(PreOrderVO preOrder) throws RemoteException {
		OrderPO order = new OrderPO(preOrder);
		order.setorderStatus(OrderStatus.线下已执行订单);
		OrderPO preorder = orderdataimpl.preOrder(order);
		Set<Integer> roomID = preorder.getRoomMap().keySet();
		List<String> realRoomIDList = new ArrayList<String>();
		List<Integer> roomIDList = new ArrayList<Integer>(roomID);
		for (int i = 0; i < roomIDList.size(); i++) {
			realRoomIDList.add(preorder.getRoomMap().get(roomIDList.get(i)));
		}
		return realRoomIDList;
	}

}
