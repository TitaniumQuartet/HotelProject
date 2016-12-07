package tiquartet.ServerModule.bl.manageorderbl;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tiquartet.CommonModule.blservice.manageorderblservice.ManageOrderBLService;
import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderNumVO;
import tiquartet.CommonModule.vo.OrderVO;
import tiquartet.ServerModule.dataservice.impl.HotelInfoDataImpl;
import tiquartet.ServerModule.dataservice.impl.OrderDataImpl;
import tiquartet.ServerModule.dataservice.impl.UserDataImpl;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.OrderPO;
import tiquartet.ServerModule.po.UserPO;

public class ManageOrder implements ManageOrderBLService {
	 OrderDataImpl orderdataimpl;
	 HotelInfoDataImpl hoteldataimpl;
	 UserDataImpl userdataimpl;
    public ManageOrder(){
    	orderdataimpl=new OrderDataImpl();
    	hoteldataimpl=new HotelInfoDataImpl();
    	userdataimpl=new UserDataImpl();
    }
	public List<OrderVO> orderHistory(OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) throws RemoteException{
		List<OrderVO> volist=new ArrayList<OrderVO>();
		List<OrderPO> polist=orderdataimpl.searchByUser(filter.hotelID, filter.userId);
		for(int i=0;i<polist.size();i++){
			if(filter.districtId!=-1){
				if(polist.get(i).gethotelId()/1000!=filter.districtId){
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
				//需添加
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
				HotelInfoPO hotelinfopo=new HotelInfoPO();
				//需修改
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
			if(filter.guestRealName!=null){
				if(polist.get(i).getguestRealName()!=filter.guestRealName){
					continue;
				}
			}
			volist.add(polist.get(i).toOrderVO());
		}
		if(sort==OrderSort.DATEASCEND){
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0;i<volist.size();i++){
				for(int j=0;j<volist.size()-1;j++){
					try {
						Date time1=format.parse(volist.get(j).orderTime);
						Date time2=format.parse(volist.get(j+1).orderTime);
						if(!time1.before(time2)){
							OrderVO ordertemp=volist.get(j);
							volist.add(j,volist.get(j+1));
							volist.add(j+1, ordertemp);
						}
					} catch (ParseException e) {				
						e.printStackTrace();
					}
				}
			}
		}else if(sort==OrderSort.DATEDESCEND){
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0;i<volist.size();i++){
				for(int j=0;j<volist.size()-1;j++){
					try {
						Date time1=format.parse(volist.get(j).orderTime);
						Date time2=format.parse(volist.get(j+1).orderTime);
						if(time1.before(time2)){
							OrderVO ordertemp=volist.get(j);
							volist.add(j,volist.get(j+1));
							volist.add(j+1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}else if(sort==OrderSort.CHECKINASCEND){
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0;i<volist.size();i++){
				for(int j=0;j<volist.size()-1;j++){
					try {
						Date time1=format.parse(volist.get(j).startTime);
						Date time2=format.parse(volist.get(j+1).startTime);
						if(!time1.before(time2)){
							OrderVO ordertemp=volist.get(j);
							volist.add(j,volist.get(j+1));
							volist.add(j+1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}else if(sort==OrderSort.CHECKINDESCEND){
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0;i<volist.size();i++){
				for(int j=0;j<volist.size()-1;j++){
					try {
						Date time1=format.parse(volist.get(j).startTime);
						Date time2=format.parse(volist.get(j+1).startTime);
						if(time1.before(time2)){
							OrderVO ordertemp=volist.get(j);
							volist.add(j,volist.get(j+1));
							volist.add(j+1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}else if(sort==OrderSort.PRICEASCEND){
			for(int i=0;i<volist.size();i++){
				for(int j=0;j<volist.size()-1;j++){
					if(volist.get(j).price>volist.get(j+1).price){
					      OrderVO ordertemp=volist.get(j);
					      volist.add(j, volist.get(j+1));
					      volist.add(j+1, ordertemp);
					}
				}
			}
		}else{
			for(int i=0;i<volist.size();i++){
				for(int j=0;j<volist.size()-1;j++){
					if(volist.get(j).price<volist.get(j+1).price){
					      OrderVO ordertemp=volist.get(j);
					      volist.add(j, volist.get(j+1));
					      volist.add(j+1, ordertemp);
					}
				}
			}
		}
		OrderVO orderrank2=volist.get(rank2);
		volist=volist.subList(rank1, rank2);
		volist.add(orderrank2);
		return volist;
	}

	public OrderVO getOrderByID(long orderID) throws RemoteException{
		OrderPO po=orderdataimpl.getOrderByID(orderID);
		OrderVO vo=new OrderVO();
		vo=po.toOrderVO();
		return vo;
	}

	public List<OrderVO> hotelOrders( OrderFilterVO filter,
			OrderSort sort, int rank1, int rank2) throws RemoteException{
		// TODO Auto-generated method stub
		List<OrderVO> volist=new ArrayList<OrderVO>();
		List<OrderPO> polist=orderdataimpl.searchByHotel(filter.hotelID, null);
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
			if(filter.guestRealName!=null){
				if(polist.get(i).getguestRealName()!=filter.guestRealName){
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
		if(sort==OrderSort.DATEASCEND){
			DateFormat format=new SimpleDateFormat("yyyy/MM/dd//HH/mm/ss");
			for(int i=0;i<volist.size();i++){
				for(int j=0;j<volist.size()-1;j++){
					try {
						Date time1=format.parse(volist.get(j).orderTime);
						Date time2=format.parse(volist.get(j+1).orderTime);
						if(!time1.before(time2)){
							OrderVO ordertemp=volist.get(j);
							volist.add(j,volist.get(j+1));
							volist.add(j+1, ordertemp);
						}
					} catch (ParseException e) {				
						e.printStackTrace();
					}
				}
			}
		}else if(sort==OrderSort.DATEDESCEND){
			DateFormat format=new SimpleDateFormat("yyyy/MM/dd//HH/mm/ss");
			for(int i=0;i<volist.size();i++){
				for(int j=0;j<volist.size()-1;j++){
					try {
						Date time1=format.parse(volist.get(j).orderTime);
						Date time2=format.parse(volist.get(j+1).orderTime);
						if(time1.before(time2)){
							OrderVO ordertemp=volist.get(j);
							volist.add(j,volist.get(j+1));
							volist.add(j+1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}else if(sort==OrderSort.CHECKINASCEND){
			DateFormat format=new SimpleDateFormat("yyyy/MM/dd//HH/mm/ss");
			for(int i=0;i<volist.size();i++){
				for(int j=0;j<volist.size()-1;j++){
					try {
						Date time1=format.parse(volist.get(j).startTime);
						Date time2=format.parse(volist.get(j+1).startTime);
						if(!time1.before(time2)){
							OrderVO ordertemp=volist.get(j);
							volist.add(j,volist.get(j+1));
							volist.add(j+1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}else if(sort==OrderSort.CHECKINDESCEND){
			DateFormat format=new SimpleDateFormat("yyyy/MM/dd//HH/mm/ss");
			for(int i=0;i<volist.size();i++){
				for(int j=0;j<volist.size()-1;j++){
					try {
						Date time1=format.parse(volist.get(j).startTime);
						Date time2=format.parse(volist.get(j+1).startTime);
						if(time1.before(time2)){
							OrderVO ordertemp=volist.get(j);
							volist.add(j,volist.get(j+1));
							volist.add(j+1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}else if(sort==OrderSort.PRICEASCEND){
			for(int i=0;i<volist.size();i++){
				for(int j=0;j<volist.size()-1;j++){
					if(volist.get(j).price>volist.get(j+1).price){
					      OrderVO ordertemp=volist.get(j);
					      volist.add(j, volist.get(j+1));
					      volist.add(j+1, ordertemp);
					}
				}
			}
		}else{
			for(int i=0;i<volist.size();i++){
				for(int j=0;j<volist.size()-1;j++){
					if(volist.get(j).price<volist.get(j+1).price){
					      OrderVO ordertemp=volist.get(j);
					      volist.add(j, volist.get(j+1));
					      volist.add(j+1, ordertemp);
					}
				}
			}
		}
		OrderVO orderrank2=volist.get(rank2);
		volist=volist.subList(rank1, rank2);
		volist.add(orderrank2);
		return volist;
	}

	public ResultMessage clientCancel(long orderID) throws RemoteException{
		//客户撤销订单
		OrderPO po=orderdataimpl.getOrderByID(orderID);
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
		OrderPO po=orderdataimpl.getOrderByID(orderID);
		//订单为异常
		if(po.getorderStatus()==OrderStatus.ABNORMAL&&po.getuserId()!=-1){
			po.setorderStatus(OrderStatus.CANCELED);
			UserPO userpo=userdataimpl.getUser(po.getuserId());
			userdataimpl.update(userpo);
			return new ResultMessage(true);
			
		}
		return new ResultMessage(false);
	}

	public ResultMessage checkIn(long orderID, String estLeaveTime) throws RemoteException{
		OrderPO order=orderdataimpl.getOrderByID(orderID);
		if(order==null){
			return new ResultMessage(false,"此订单不存在","");
		}
		order.setlatestTime(estLeaveTime);
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate=new Date();
		try {
			Date leaveDate=format.parse(order.getleaveTime());
			Date startDate=format.parse(order.getstartTime());
			if(nowDate.before(leaveDate)&&nowDate.after(startDate)){
				order.setstartTime(format.format(new Date()));
				UserPO user=userdataimpl.getUser(order.getuserId());
				user.setcredit(user.getcredit()+order.getprice());
				orderdataimpl.update(order);
				userdataimpl.update(user);
				return new ResultMessage(true);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResultMessage(false,"订单已过期","");
	}

	public ResultMessage checkOut(long orderID,String leaveTime) throws RemoteException{
		OrderPO order=orderdataimpl.getOrderByID(orderID);
		if(order==null){
			return new ResultMessage(false,"此订单不存在","");
		}
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date leaveDate=format.parse(leaveTime);
			Date estLeaveDate=format.parse(order.getlatestTime());
			if(leaveDate.before(estLeaveDate)){
				order.setleaveTime(leaveTime);
				order.setorderStatus(OrderStatus.EXECUTED);// TODO Auto-generated method stub
				return new ResultMessage(true);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResultMessage(false,"超过订单最晚离开时间","");
	}

	public List<Integer> orderedHotelID(int userID) throws RemoteException{
		// 返回用户预订过的酒店编号列表
		List<Integer> hotelIdlist=new ArrayList<Integer>();
		List<HotelInfoPO> polist=new ArrayList<HotelInfoPO>();//此处应为根据userID搜索酒店列表；
		for(int i=0;i<polist.size();i++){
			hotelIdlist.add(polist.get(i).gethotelId());
		}
		return hotelIdlist;
	}

	public List<OrderVO> clientAtHotel(int userID, int hotelID) throws RemoteException{
		//返回该用户在该酒店预定过得订单列表
		List<OrderVO> volist=new ArrayList<OrderVO>();
		List<OrderPO> polist=orderdataimpl.searchByUser(hotelID, userID);//此处应该修改；
		for(int i=0;i<polist.size();i++){
			volist.add(polist.get(i).toOrderVO());
		}
		return volist;
	}

	public OrderNumVO numAtHotel(int hotelID,int userID) throws RemoteException{
		//返回用户在该酒店的各类订单数目；
		List<OrderPO> polist=orderdataimpl.searchByUser(hotelID, userID);//此处应该修改
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
