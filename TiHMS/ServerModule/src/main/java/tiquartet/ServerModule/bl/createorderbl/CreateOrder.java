package tiquartet.ServerModule.bl.createorderbl;

import tiquartet.CommonModule.vo.PreOrderVO;
import tiquartet.CommonModule.vo.StrategyVO;
import tiquartet.ServerModule.dataservice.impl.OrderDataImpl;
import tiquartet.ServerModule.dataservice.impl.RoomDataImpl;
import tiquartet.ServerModule.dataservice.impl.StrategyDataImpl;
import tiquartet.ServerModule.dataservice.impl.UserDataImpl;
import tiquartet.ServerModule.po.OrderPO;
import tiquartet.ServerModule.po.StrategyPO;
import tiquartet.CommonModule.vo.OrderInfoVO;
import tiquartet.CommonModule.vo.OrderStrategyVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.blservice.createorderblservice.CreateOrderBLService;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;

public class CreateOrder implements CreateOrderBLService{	
	
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
	Map<Integer,PreOrderVO> map=new HashMap<Integer,PreOrderVO>();
	public CreateOrder(){
		strategydataimpl=StrategyDataImpl.getInstance();
		userdataimpl=UserDataImpl.getInstance();
		orderdataimpl=OrderDataImpl.getInstance();
		roomdataimpl=RoomDataImpl.getInstance();
	}
	public OrderStrategyVO getStrategy(int userID) throws RemoteException{
		OrderStrategyVO orderStrategy=new OrderStrategyVO();
		List<StrategyPO> polist=StrategyDataImpl.getInstance().searchByHotel(map.get(userID).hotelID);
		

		
		return orderStrategy;
	}
	
	public ResultMessage preOrder(PreOrderVO preOrder) throws RemoteException{
		int credit=Integer.parseInt(userdataimpl.getCreditBalance(preOrder.userID).message);
		if(credit<0){
			return new ResultMessage(false,"用户信用值低于0","");
		}
		map.put(preOrder.userID,preOrder);
		OrderPO order=new OrderPO(preOrder);
		order.setorderStatus(OrderStatus.PREORDER);
		return orderdataimpl.preOrder(order);
	}
	
	public ResultMessage cancelPreOrder(int userID)throws RemoteException{
		int hotelID=map.get(userID).hotelID;
        List<OrderPO> orderlist=orderdataimpl.searchByUser(hotelID, userID);
        OrderPO preOrder=new OrderPO();
        for(int i=0;i<orderlist.size();i++){
        	if(orderlist.get(i).getorderStatus()==OrderStatus.PREORDER){
        	   preOrder=orderlist.get(i);
        	   map.remove(userID);
        	   return orderdataimpl.cancelPreOrder(preOrder);
        	}
        }
		return new ResultMessage(false,"找不到该订单","");
	}
	
	public ResultMessage confirm(OrderInfoVO orderInfo)throws RemoteException{
		PreOrderVO preorder=map.get(orderInfo.userID);
		OrderPO order=new OrderPO(preorder);
		order.setorderId(orderInfo.orderID);
		order.setguestRealName(orderInfo.guestRealName);
		order.setnumberOfPeople(orderInfo.numOfGuest);
		order.setprice(orderInfo.price);
		order.setchild(orderInfo.kids);
		order.setlatestTime(orderInfo.lastTime);
		//加一个把订单时间设置为系统时间
		return orderdataimpl.update(order);
	}
	public List<String> offLine(PreOrderVO preOrder) throws RemoteException {
		
		return null;
	}

	
}
