package tiquartet.ServerModule.bl.manageorderbl;

import java.rmi.RemoteException;
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

public class ManageOrder implements ManageOrderBLService {
	static DataFactory dataFactory=new DataFactory();

	public List<OrderVO> orderHistory(OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) throws RemoteException{
		List<OrderVO> volist=new ArrayList<OrderVO>();
		List<OrderPO> polist=dataFactory.getOrderDataHelper().searchByUser(filter.userId, filter.userId);
		for(int i=0;i<polist.size();i++){
			if(filter.circleId!=-1){
				if(polist.get(i).gethotelId()/1000!=filter.circleId){
					continue;
				}
			}
			if(filter.highprice!=-1){
				if(polist.get(i).getprice()>filter.highprice){
					continue;
				}
			}
			if(filter.lowprice!=-1){
				if(polist.get(i).getprice()<filter.lowprice){
					continue;
				}
			}
			if(filter.star!=-1){
				HotelInfoPO hotelpo=dataFactory.getHotelInfoDataHelper().getHotelInfo(polist.get(i).gethotelId());
				if(hotelpo.getstar()!=filter.star){
					continue;
				}
			}
			if(filter.endTime!=null){
				int filterEndTime=Integer.parseInt(filter.endTime);
				int orderEndTime=Integer.parseInt(polist.get(i).getleaveTime());				
				if(orderEndTime>filterEndTime){
					continue;
				}
			}
			if(filter.startTime!=null){
				int filterStartTime=Integer.parseInt(filter.startTime);
				int orderStartTime=Integer.parseInt(polist.get(i).getstartTime());
				if(orderStartTime<filterStartTime){
					continue;
				}
			}
			if(filter.hotelName!=null){
				HotelInfoPO hotelinfopo=dataFactory.getHotelInfoDataHelper().getHotelInfo(polist.get(i).gethotelId());
				if(hotelinfopo.gethotelName()!=filter.hotelName){
					continue;
				}
			}
			if(filter.orderState!=null){
				if(polist.get(i).getorderStatus()!=filter.orderState){
					continue;
				}
			}
			if(filter.clientRealName!=null){
				if(polist.get(i).getclientRealName()!=filter.clientRealName){
					continue;
				}
            }
			if(filter.orderManName!=null){
				if(polist.get(i).getOrderManName()!=filter.orderManName){
					continue;
				}
			}
			volist.add(polist.get(i).toOrderVO());
		}
		//此方法可能需要修改
	    while(volist.size()>rank2){
	    	volist.remove(rank2);
	    }
	    for(int i=1;i<rank1;i++){
	    	volist.remove(0);
	    }
		return volist;
	}

	public OrderVO getOrderByID(long orderID) throws RemoteException{
		OrderPO po=dataFactory.getOrderDataHelper().getOrderByID(orderID);
		OrderVO vo=new OrderVO();
		vo=po.toOrderVO();
		return vo;//未完成的返回内容，需要补充po转化为vo的方法。
	}

	public List<OrderVO> hotelOrders( OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) throws RemoteException{
		// TODO Auto-generated method stub
		List<OrderVO> volist=new ArrayList<OrderVO>();
		List<OrderPO> polist=dataFactory.getOrderDataHelper().searchByHotel(filter.hotelID, null);
		for(int i=0;i<polist.size();i++){
			if(filter.highprice!=-1){
				if(polist.get(i).getprice()>filter.highprice){
					continue;
				}
			}
			if(filter.lowprice!=-1){
				if(polist.get(i).getprice()<filter.lowprice){
					continue;
				}
			}
			if(filter.endTime!=null){
				int filterEndTime=Integer.parseInt(filter.endTime);
				int orderEndTime=Integer.parseInt(polist.get(i).getleaveTime());				
				if(orderEndTime>filterEndTime){
					continue;
				}
			}
			if(filter.startTime!=null){
				int filterStartTime=Integer.parseInt(filter.startTime);
				int orderStartTime=Integer.parseInt(polist.get(i).getstartTime());
				if(orderStartTime<filterStartTime){
					continue;
				}
			}
			if(filter.orderState!=null){
				if(polist.get(i).getorderStatus()!=filter.orderState){
					continue;
				}
			}
			if(filter.clientRealName!=null){
				if(polist.get(i).getclientRealName()!=filter.clientRealName){
					continue;
				}
            }
			if(filter.orderManName!=null){
				if(polist.get(i).getOrderManName()!=filter.orderManName){
					continue;
				}
			}
			if(filter.userName!=null){
				if(polist.get(i).getuserName()!=filter.userName){
					continue;
				}
			}
			volist.add(polist.get(i).toOrderVO());
		}
		while(volist.size()>rank2){
			volist.remove(rank2);
		}
		for(int i=1;i<rank1;i++){
			volist.remove(0);
		}
		return volist;
	}

	public ResultMessage clientCancel(long orderID) throws RemoteException{
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

	public ResultMessage marketerCancel(long orderID, CreditRestore restore) throws RemoteException{
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

	public ResultMessage checkIn(long orderID, String estLeaveTime) throws RemoteException{
		OrderPO po=dataFactory.getOrderDataHelper().getOrderByID(orderID);
		po.setlatestTime(estLeaveTime);
		dataFactory.getOrderDataHelper().update(po);
		return new ResultMessage(true);
	}

	public ResultMessage checkOut(long orderID,String leaveTime) throws RemoteException{
		OrderPO po=dataFactory.getOrderDataHelper().getOrderByID(orderID);
		po.setleaveTime(leaveTime);
		po.setorderStatus(OrderStatus.EXECUTED);// TODO Auto-generated method stub
		return null;
	}

	public List<Integer> orderedHotelID(int userID) throws RemoteException{
		// 返回用户预订过的酒店编号列表
		List<Integer> hotelIdlist=new ArrayList<Integer>();
		List<HotelInfoPO> polist=dataFactory.getHotelInfoDataHelper().getHotelList(userID);
		for(int i=0;i<polist.size();i++){
			hotelIdlist.add(polist.get(i).gethotelId());
		}
		return hotelIdlist;
	}

	public List<OrderVO> clientAtHotel(int userID, int hotelID) throws RemoteException{
		//返回该用户在该酒店预定过得订单列表
		List<OrderVO> volist=new ArrayList<OrderVO>();
		List<OrderPO> polist=dataFactory.getOrderDataHelper().searchByUser(hotelID, userID);
		for(int i=0;i<polist.size();i++){
			volist.add(polist.get(i).toOrderVO());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see tiquartet.CommonModule.blservice.manageorderblservice.ManageOrderBLService#numAtHotel(int, int)
	 */
	public OrderNumVO numAtHotel(int hotelID,int userID) throws RemoteException{
		//返回用户在该酒店的各类订单数目；
		List<OrderPO> polist=dataFactory.getOrderDataHelper().searchByUser(hotelID, userID);
		OrderNumVO ordernumvo=new OrderNumVO();
		ordernumvo.uesrID=userID;
		ordernumvo.hotelID=hotelID;
		for(int i=0;i<polist.size();i++){
			switch(polist.get(i).getorderStatus()){
			case CANCELED:
				ordernumvo.canceledOrder=ordernumvo.canceledOrder+1;
				break;
			case UNEXECUTED:
				ordernumvo.unexecutedOrder=ordernumvo.unexecutedOrder+1;
				break;
			case EXECUTED:
				ordernumvo.executedOrder=ordernumvo.executedOrder+1;
				break;
			case ABNORMAL:
				ordernumvo.abnormalOrder=ordernumvo.abnormalOrder+1;
				break;
			default:
				break;
			}
		}
		ordernumvo.allOrder=polist.size();
		return ordernumvo;
	}


	
}
