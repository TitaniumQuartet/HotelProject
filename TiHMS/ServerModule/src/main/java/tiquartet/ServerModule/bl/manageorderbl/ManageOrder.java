package tiquartet.ServerModule.bl.manageorderbl;

import java.util.ArrayList;
import java.util.List;

import tiquartet.CommonModule.blservice.manageorderblservice.ManageOrderBLService;
import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderNumVO;
import tiquartet.CommonModule.vo.OrderVO;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.OrderPO;
import tiquartet.ServerModule.po.UserPO;

public class ManageOrder implements ManageOrderBLService{
	static DataFactory dataFactory=new DataFactory();

	public List<OrderVO> orderHistory(int userID, OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) {
		List<OrderVO> volist=new ArrayList<OrderVO>();
		List<OrderPO> polist=dataFactory.getOrderDataHelper().searchByUser(0, userID);
		
		//此方法可能需要修改
	
		return null;
	}

	public OrderVO getOrderByID(long orderID) {
		OrderPO po=dataFactory.getOrderDataHelper().getOrderByID(orderID);
		OrderVO vo=new OrderVO();
		vo=po.toOrderVO();
		return vo;//未完成的返回内容，需要补充po转化为vo的方法。
	}

	public List<OrderVO> hotelOrders(int hotelID, OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage clientCancel(long orderID) {
		//客户撤销订单
		OrderPO po=dataFactory.getOrderDataHelper().getOrderByID(orderID);
		//如果订单不为异常
		if(po.getorderStatus()!=OrderStatus.ABNORMAL){
			po.setorderStatus(OrderStatus.UNEXECUTED);
			return new ResultMessage(true);
		}else{
			return new ResultMessage(false);
		}
		
		
	}

	public ResultMessage marketerCancel(long orderID, CreditRestore restore) {
		//网站营销人员撤销异常订单，并恢复一定信用值；
		OrderPO po=dataFactory.getOrderDataHelper().getOrderByID(orderID);
		//订单为异常
		if(po.getorderStatus()==OrderStatus.ABNORMAL&&po.getuserId()!=-1){
			po.setorderStatus(OrderStatus.CANCELED);
			//user的更新没有写
			return new ResultMessage(true);
			
		}
		return new ResultMessage(false);
	}

	public ResultMessage checkIn(long orderID, String leaveTime) {
		OrderPO po=dataFactory.getOrderDataHelper().getOrderByID(orderID);
		po.setleaveTime(leaveTime);
		dataFactory.getOrderDataHelper().update(po);
		return new ResultMessage(true);
	}

	public ResultMessage checkOut(long orderID) {
		OrderPO po=dataFactory.getOrderDataHelper().getOrderByID(orderID);
		po.setorderStatus(OrderStatus.EXECUTED);// TODO Auto-generated method stub
		return null;
	}

	public List<Integer> getHotelList(int userID) {
		// 返回用户预订过的酒店编号列表
		List<Integer> hotelIdlist=new ArrayList<Integer>();
		List<HotelInfoPO> polist=dataFactory.getHotelInfoDataHelper().getHotelList(userID);
		for(int i=0;i<polist.size();i++){
			hotelIdlist.add(polist.get(i).gethotelId());
		}
		return hotelIdlist;
	}

	public List<OrderVO> clientAtHotel(int userID, int hotelID) {
		//返回该用户在该酒店预定过得订单列表
		List<OrderVO> volist=new ArrayList<OrderVO>();
		List<OrderPO> polist=dataFactory.getOrderDataHelper().searchByUser(hotelID, userID);
		for(int i=0;i<polist.size();i++){
			volist.add(polist.get(i).toOrderVO());
		}
		return null;
	}

	public OrderNumVO numAtHotel(int hotelID,int userID) {
		//返回用户在该酒店的各类订单数目；
		List<OrderPO> polist=dataFactory.getOrderDataHelper().searchByUser(hotelID, userID);
		OrderNumVO ordernumvo=new OrderNumVO();
		for(int i=0;i<polist.size();i++){
			switch(polist.get(i).getorderStatus()){
			case CANCELED:
				ordernumvo.canceled=ordernumvo.canceled+1;
				break;
			case UNEXECUTED:
				ordernumvo.unexecuted=ordernumvo.unexecuted+1;
				break;
			case EXECUTED:
				ordernumvo.executed=ordernumvo.executed+1;
				break;
			case ABNORMAL:
				ordernumvo.abnormal=ordernumvo.abnormal+1;
				break;
			default:
				break;
			}
		}
		return ordernumvo;
	}

	
}
