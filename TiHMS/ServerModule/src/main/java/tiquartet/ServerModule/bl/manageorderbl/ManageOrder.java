package tiquartet.ServerModule.bl.manageorderbl;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tiquartet.CommonModule.blservice.manageorderblservice.ManageOrderBLService;
import tiquartet.CommonModule.util.CreditChange;
import tiquartet.CommonModule.util.CreditRestore;
import tiquartet.CommonModule.util.OrderSort;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderFilterVO;
import tiquartet.CommonModule.vo.OrderNumVO;
import tiquartet.CommonModule.vo.OrderVO;
import tiquartet.ServerModule.dataservice.creditdataservice.CreditDataService;
import tiquartet.ServerModule.dataservice.hotelinfodataservice.HotelInfoDataService;
import tiquartet.ServerModule.dataservice.impl.CreditDataImpl;
import tiquartet.ServerModule.dataservice.impl.HotelInfoDataImpl;
import tiquartet.ServerModule.dataservice.impl.OrderDataImpl;
import tiquartet.ServerModule.dataservice.impl.UserDataImpl;
import tiquartet.ServerModule.dataservice.orderdataservice.OrderDataService;
import tiquartet.ServerModule.dataservice.userdataservice.UserDataService;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.OrderPO;
import tiquartet.ServerModule.po.UserPO;

public class ManageOrder implements ManageOrderBLService {
	OrderDataService orderdataservice;
	HotelInfoDataService hoteldataservice;
	UserDataService userdataservice;
	CreditDataService creditdataservice;

	public ManageOrder() {
		orderdataservice = OrderDataImpl.getInstance();
		hoteldataservice = HotelInfoDataImpl.getInstance();
		userdataservice = UserDataImpl.getInstance();
		creditdataservice = CreditDataImpl.getInstance();
	}
    //用户的订单筛选排序
	public List<OrderVO> orderHistory(OrderFilterVO filter, OrderSort sort, int rank1, int rank2)
			throws RemoteException {
		List<OrderVO> volist = new ArrayList<OrderVO>();
		List<OrderPO> polist = orderdataservice.searchByUser( filter.userId);
		for(int i=0;i<polist.size();i++){
			if(polist.get(i).gethotelId()!=filter.hotelID){
				polist.remove(i);
			}
		}
		for (int i = 0; i < polist.size(); i++) {
			//筛选订单
			if (filter.cityId != -1) {
				if (polist.get(i).gethotelId() / 100000 != filter.cityId) {
					continue;
				}
			}
			if (filter.highprice != -1) {
				if (polist.get(i).getprice() > filter.highprice) {
					continue;
				}
			}
			if (filter.lowprice != -1) {
				if (polist.get(i).getprice() < filter.lowprice) {
					continue;
				}
			}
			if (filter.star != -1) {
				HotelInfoPO hotel = hoteldataservice.getHotelInfo(filter.hotelID);
				if (hotel.getstar() != filter.star) {
					continue;
				}
			}
			if (filter.endTime != null) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date endDate = format.parse(filter.endTime);
					Date orderEndDate = format.parse(polist.get(i).getleaveTime());
					if (!orderEndDate.after(endDate)) {
						continue;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (!filter.startTime.equals("")) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date startDate = format.parse(filter.startTime);
					Date orderStartDate = format.parse(polist.get(i).getstartTime());
					if (startDate.before(orderStartDate)) {
						continue;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (!filter.hotelName.equals("")) {
				HotelInfoPO hotelinfopo = hoteldataservice.getHotelInfo(filter.hotelID);
				if (!hotelinfopo.gethotelName().contains(filter.hotelName)) {
					continue;
				}
			}
			if (filter.orderState!=null) {
				if (polist.get(i).getorderStatus() != filter.orderState) {
					continue;
				}
			}
			if (!filter.clientRealName.equals("")) {
				if (!polist.get(i).getclientRealName().contains(filter.clientRealName)) {
					continue;
				}
			}
			if (!filter.guestRealName.equals("")) {
				if (!polist.get(i).getguestRealName().contains(filter.guestRealName)) {
					continue;
				}
			}
			volist.add(polist.get(i).toOrderVO());
		}
		//订单排序
		if (sort == OrderSort.生成日期升序) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < volist.size(); i++) {
				for (int j = 0; j < volist.size() - 1; j++) {
					try {
						Date time1 = format.parse(volist.get(j).orderTime);
						Date time2 = format.parse(volist.get(j + 1).orderTime);
						if (time1.after(time2)) {
							OrderVO ordertemp = volist.get(j);
							volist.add(j, volist.get(j + 1));
							volist.add(j + 1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (sort == OrderSort.生成日期降序) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < volist.size(); i++) {
				for (int j = 0; j < volist.size() - 1; j++) {
					try {
						Date time1 = format.parse(volist.get(j).orderTime);
						Date time2 = format.parse(volist.get(j + 1).orderTime);
						if (time1.before(time2)) {
							OrderVO ordertemp = volist.get(j);
							volist.add(j, volist.get(j + 1));
							volist.add(j + 1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (sort == OrderSort.入住日期升序) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < volist.size(); i++) {
				for (int j = 0; j < volist.size() - 1; j++) {
					try {
						Date time1 = format.parse(volist.get(j).startTime);
						Date time2 = format.parse(volist.get(j + 1).startTime);
						if (!time1.before(time2)) {
							OrderVO ordertemp = volist.get(j);
							volist.add(j, volist.get(j + 1));
							volist.add(j + 1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (sort == OrderSort.入住日期降序) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < volist.size(); i++) {
				for (int j = 0; j < volist.size() - 1; j++) {
					try {
						Date time1 = format.parse(volist.get(j).startTime);
						Date time2 = format.parse(volist.get(j + 1).startTime);
						if (time1.before(time2)) {
							OrderVO ordertemp = volist.get(j);
							volist.add(j, volist.get(j + 1));
							volist.add(j + 1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (sort == OrderSort.订单总价升序) {
			for (int i = 0; i < volist.size(); i++) {
				for (int j = 0; j < volist.size() - 1; j++) {
					if (volist.get(j).price > volist.get(j + 1).price) {
						OrderVO ordertemp = volist.get(j);
						volist.add(j, volist.get(j + 1));
						volist.add(j + 1, ordertemp);
					}
				}
			}
		} else {
			for (int i = 0; i < volist.size(); i++) {
				for (int j = 0; j < volist.size() - 1; j++) {
					if (volist.get(j).price < volist.get(j + 1).price) {
						OrderVO ordertemp = volist.get(j);
						volist.add(j, volist.get(j + 1));
						volist.add(j + 1, ordertemp);
					}
				}
			}
		}
		//把rank1之前的设为null
		for(int i=0;i<rank1-1;i++){
			volist.remove(i);
			volist.add(i,null);
		}
		//判断rank2是否出界
		if(rank2>volist.size()){
			rank2=volist.size();
		}
		//把rank2后面的也设为null
		for(int i=rank2;i<volist.size();i++){
			volist.remove(i);
			volist.add(null);
		}
		return volist;
	}

	public OrderVO getOrderByID(long orderID) throws RemoteException {
		OrderPO po = orderdataservice.getOrderByID(orderID);
		OrderVO vo = new OrderVO();
		vo = po.toOrderVO();
		return vo;
	}

	public List<OrderVO> hotelOrders(OrderFilterVO filter, OrderSort sort, int rank1, int rank2)
			throws RemoteException {
		// TODO Auto-generated method stub
		List<OrderVO> volist = new ArrayList<OrderVO>();
		List<OrderPO> polist = orderdataservice.searchByHotel(filter.hotelID, null);
		// 根据条件筛选订单
		for (int i = 0; i < polist.size(); i++) {
			if (filter.highprice != -1) {
				if (polist.get(i).getprice() > filter.highprice) {
					continue;
				}
			}
			if (filter.lowprice != -1) {
				if (polist.get(i).getprice() < filter.lowprice) {
					continue;
				}
			}
			if (!filter.endTime.equals("")) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date endDate = format.parse(filter.endTime);
					Date orderEndDate = format.parse(polist.get(i).getleaveTime());
					if (orderEndDate.after(endDate)) {
						continue;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (!filter.startTime.equals("")) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date startDate = format.parse(filter.startTime);
					Date orderStartDate = format.parse(polist.get(i).getstartTime());
					if (startDate.before(orderStartDate)) {
						continue;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (filter.orderState!=null) {
				if (polist.get(i).getorderStatus() != filter.orderState) {
					continue;
				}
			}
			if (!filter.clientRealName.equals("")) {
				if (!polist.get(i).getclientRealName().contains(filter.clientRealName)) {
					continue;
				}
			}
			if (!filter.guestRealName.equals("")) {
				if (!polist.get(i).getguestRealName().contains(filter.guestRealName)) {
					continue;
				}
			}
			if (!filter.userName.equals("")) {
				if (!polist.get(i).getuserName().contains(filter.userName)) {
					continue;
				}
			}
			volist.add(polist.get(i).toOrderVO());
		}
		//对订单进行排序
		if (sort == OrderSort.生成日期升序) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < volist.size(); i++) {
				for (int j = 0; j < volist.size() - 1; j++) {
					try {
						Date time1 = format.parse(volist.get(j).orderTime);
						Date time2 = format.parse(volist.get(j + 1).orderTime);
						if (!time1.before(time2)) {
							OrderVO ordertemp = volist.get(j);
							volist.add(j, volist.get(j + 1));
							volist.add(j + 1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (sort == OrderSort.生成日期降序) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < volist.size(); i++) {
				for (int j = 0; j < volist.size() - 1; j++) {
					try {
						Date time1 = format.parse(volist.get(j).orderTime);
						Date time2 = format.parse(volist.get(j + 1).orderTime);
						if (time1.before(time2)) {
							OrderVO ordertemp = volist.get(j);
							volist.add(j, volist.get(j + 1));
							volist.add(j + 1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (sort == OrderSort.入住日期升序) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < volist.size(); i++) {
				for (int j = 0; j < volist.size() - 1; j++) {
					try {
						Date time1 = format.parse(volist.get(j).startTime);
						Date time2 = format.parse(volist.get(j + 1).startTime);
						if (!time1.before(time2)) {
							OrderVO ordertemp = volist.get(j);
							volist.add(j, volist.get(j + 1));
							volist.add(j + 1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (sort == OrderSort.入住日期降序) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < volist.size(); i++) {
				for (int j = 0; j < volist.size() - 1; j++) {
					try {
						Date time1 = format.parse(volist.get(j).startTime);
						Date time2 = format.parse(volist.get(j + 1).startTime);
						if (time1.before(time2)) {
							OrderVO ordertemp = volist.get(j);
							volist.add(j, volist.get(j + 1));
							volist.add(j + 1, ordertemp);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (sort == OrderSort.订单总价升序) {
			for (int i = 0; i < volist.size(); i++) {
				for (int j = 0; j < volist.size() - 1; j++) {
					if (volist.get(j).price > volist.get(j + 1).price) {
						OrderVO ordertemp = volist.get(j);
						volist.add(j, volist.get(j + 1));
						volist.add(j + 1, ordertemp);
					}
				}
			}
		} else {
			for (int i = 0; i < volist.size(); i++) {
				for (int j = 0; j < volist.size() - 1; j++) {
					if (volist.get(j).price < volist.get(j + 1).price) {
						OrderVO ordertemp = volist.get(j);
						volist.add(j, volist.get(j + 1));
						volist.add(j + 1, ordertemp);
					}
				}
			}
		}
		//把rank1之前的设为null
		for(int i=0;i<rank1-1;i++){
			volist.remove(i);
			volist.add(i,null);
		}
		//判断rank2是否出界
		if(rank2>volist.size()){
			rank2=volist.size();
		}
		for(int i=rank2;i<volist.size();i++){
			volist.remove(i);
			volist.add(i,null);
		}
		return volist;
	}

	public ResultMessage clientCancel(long orderID) throws RemoteException {
		// 客户撤销订单
		OrderPO order = orderdataservice.getOrderByID(orderID);
		if (order == null) {
			return new ResultMessage(false, "找不到该订单", "");
		}
		// 如果订单不为异常
		if (order.getorderStatus() != OrderStatus.异常订单) {
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowtime=new Date();
			try {
				Date orderstartdate=format.parse(order.getstartTime());
				if(orderstartdate.before(nowtime)){
					return new ResultMessage(false,"已经过了订单开始时间","");
				}
				long timeInterval=orderstartdate.getTime()-nowtime.getTime();
				timeInterval=timeInterval/1000/60/60;				
				if(timeInterval<6){
					UserPO user=userdataservice.getUser(order.getuserId());
					user.setcredit(user.getcredit()-order.getprice()/2);
					CreditPO credit=new CreditPO();
					credit.setuserID(user.getuserId());
					credit.setbalance(user.getcredit());
					credit.setchange(order.getprice()/2);
					credit.setorderId(order.getorderId());
					credit.setchangeType(CreditChange.客户较晚撤销订单时扣除信用值);
					userdataservice.update(user);
					creditdataservice.insert(credit);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			order.setorderStatus(OrderStatus.已撤销订单);
			orderdataservice.update(order);
			return new ResultMessage(true);
		} else {
			return new ResultMessage(false);
			// 如果订单为异常则返回错误
		}

	}
    //网站营销人员取消异常订单
	public ResultMessage marketerCancel(long orderID, CreditRestore restore) throws RemoteException {
		// 网站营销人员撤销异常订单，并恢复一定信用值；
		OrderPO order = orderdataservice.getOrderByID(orderID);
		if (order == null) {
			return new ResultMessage(false, "找不到该订单", "");
		}
		// 订单为异常
		if (order.getorderStatus() == OrderStatus.异常订单 && order.getuserId() != -1) {
			order.setorderStatus(OrderStatus.已撤销订单);
			UserPO user = userdataservice.getUser(order.getuserId());
			CreditPO credit = new CreditPO();
			if (restore == CreditRestore.一半) {
				user.setcredit(user.getcredit() + order.getprice() / 2);
				credit.setchangeType(CreditChange.撤销异常订单时恢复一半信用值);
				credit.setchange(order.getprice() / 2);
			} else {
				user.setcredit(user.getcredit() + order.getprice());
				credit.setchangeType(CreditChange.撤销异常订单时恢复全部信用值);
				credit.setchange(order.getprice());
			}
			credit.setbalance(user.getcredit());
			credit.setorderId(order.getorderId());
			userdataservice.update(user);
			creditdataservice.insert(credit);
			return new ResultMessage(true);

		}
		return new ResultMessage(false);
	}
    //入住
	public ResultMessage checkIn(long orderID, String estLeaveTime) throws RemoteException {
		OrderPO order = orderdataservice.getOrderByID(orderID);
		if (order == null) {
			return new ResultMessage(false, "此订单不存在", "");
		}
		order.setleaveTime(estLeaveTime);
		order.setorderStatus(OrderStatus.已执行订单);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		try {
			Date latestDate = format.parse(order.getlatestTime());
			Date startDate = format.parse(order.getstartTime());
			if (nowDate.before(latestDate) && nowDate.after(startDate)) {
				order.setstartTime(format.format(new Date()));
				UserPO user = userdataservice.getUser(order.getuserId());
				user.setcredit(user.getcredit() + order.getprice());
				CreditPO credit = new CreditPO();
				credit.setbalance(user.getcredit());
				credit.setchange(order.getprice());
				credit.setchangeType(CreditChange.订单执行时自动增加信用值);
				credit.setorderId(order.getorderId());
				orderdataservice.update(order);
				userdataservice.update(user);
				creditdataservice.insert(credit);
				return new ResultMessage(true);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResultMessage(false, "订单已过期", "");
	}
    //离店
	public ResultMessage checkOut(long orderID, String leaveTime) throws RemoteException {
		OrderPO order = orderdataservice.getOrderByID(orderID);
		if (order == null) {
			return new ResultMessage(false, "此订单不存在", "");
		}
		order.setleaveTime(leaveTime);
		orderdataservice.update(order);
		return new ResultMessage(true);
	}

	public List<Integer> orderedHotelID(int userID) throws RemoteException {
		// 返回用户预订过的酒店编号列表
		List<Integer> hotelIdlist = new ArrayList<Integer>();
		List<OrderPO> orderlist = orderdataservice.searchByUser(userID);
		if(orderlist.size()==0){
			return null;
		}
		hotelIdlist.add(orderlist.get(0).gethotelId());
		for(int i=0;i<orderlist.size();i++){
			for(int j=0;j<hotelIdlist.size();j++){
				if(orderlist.get(i).gethotelId()==hotelIdlist.get(j)){
					break;
				}
				if(j==hotelIdlist.size()-1){
					hotelIdlist.add(orderlist.get(i).gethotelId());
				}
			}
		}
		return hotelIdlist;
	}

	public List<OrderVO> clientAtHotel(int userID, int hotelID) throws RemoteException {
		// 返回该用户在该酒店预定过得订单列表
		List<OrderVO> volist = new ArrayList<OrderVO>();
		List<OrderPO> polist = orderdataservice.searchByUser(userID);
		for(int i =0;i<polist.size();i++){
			if(polist.get(i).gethotelId()!=hotelID){
				polist.remove(i);
			}
		}
		for (int i = 0; i < polist.size(); i++) {
			volist.add(polist.get(i).toOrderVO());
		}
		return volist;
	}

	public OrderNumVO numAtHotel(int hotelID, int userID) throws RemoteException {
		// 返回用户在该酒店的各类订单数目；
		List<OrderPO> polist = orderdataservice.searchByUser( userID);
		for(int i =0;i<polist.size();i++ ){
			if(polist.get(i).gethotelId()!=hotelID){
				polist.remove(i);
			}
		}
		OrderNumVO ordernumvo = new OrderNumVO();
		ordernumvo.uesrID = userID;
		ordernumvo.hotelID = hotelID;
		for (int i = 0; i < polist.size(); i++) {
			switch (polist.get(i).getorderStatus()) {
			case 已撤销订单:
				ordernumvo.canceledOrder = ordernumvo.canceledOrder + 1;
				break;
			case 未执行订单:
				ordernumvo.unexecutedOrder = ordernumvo.unexecutedOrder + 1;
				break;
			case 已执行订单:
				ordernumvo.executedOrder = ordernumvo.executedOrder + 1;
				break;
			case 异常订单:
				ordernumvo.abnormalOrder = ordernumvo.abnormalOrder + 1;
				break;
			default:
				break;
			}
		}
		ordernumvo.allOrder = polist.size();
		return ordernumvo;
	}

}
