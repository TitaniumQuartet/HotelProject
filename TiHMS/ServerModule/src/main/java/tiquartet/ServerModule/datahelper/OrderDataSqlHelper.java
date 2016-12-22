package tiquartet.ServerModule.datahelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import tiquartet.CommonModule.util.OrderStatus;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.service.OrderDataHelper;
import tiquartet.ServerModule.po.OrderPO;

/**
 * 对order数据库的操作.
 * @author Teki
 */
public class OrderDataSqlHelper implements OrderDataHelper{

	/**
	 * 将order数据库中对应的房间编号和房间号解析出来.
	 * @return
	 */
	public HashMap<Integer, String> transform(String roomNumber,String roomId){//订单的房间号和房间编号合为一个字符串保存在数据库中
		String[] roomn=roomNumber.split(",");
		String[] roomi=roomId.split(",");
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		int l=0;
		l=roomn.length;
		int[] num = new int[l];
		for(int i=0;i<l;i++){
			num[i]=Integer.valueOf(roomi[i]);
			map.put(num[i], roomn[i]);
		}
		return map;
	}
	
	/**
	 * 通过数据库的数据生成OrderPO.
	 * @return
	 */
	public OrderPO createorderpo(ResultSet rs){
		OrderPO orderpo=new OrderPO();
		try{
			long orderId=rs.getLong(1);
			OrderStatus orderStatus=OrderStatus.values()[rs.getInt(2)];
        	String latestTime=rs.getString(3);
        	String roomNumber=rs.getString(4);
        	String roomId=rs.getString(5);
		    int numberOfRoom=rs.getInt(6);
		    int numberOfPeople=rs.getInt(7);
		    int child=rs.getInt(8);
		    String guestRealName=rs.getString(9);
		    String clientRealName=rs.getString(10);
		    String hotelName=rs.getString(11);
		    int userId=rs.getInt(12);
		    String userName=rs.getString(13);
		    String startTime=rs.getString(14);
		    String leaveTime=rs.getString(15);
		    String orderTime=rs.getString(16);
		    double price=rs.getDouble(17);
		    int hotelId=rs.getInt(18);	
		    String phone=rs.getString(19);
		    String roomType=rs.getString(20);
		    HashMap<Integer, String> roomMap;
		    if(roomNumber.length()==0){
		    	roomMap=new HashMap<Integer, String>();
		    }else
		    	roomMap=transform(roomNumber, roomId);
		    orderpo=new OrderPO(orderId,orderStatus,latestTime,roomMap,numberOfRoom,numberOfPeople,child,guestRealName,clientRealName,hotelName,userId,userName,startTime,leaveTime,orderTime,price,hotelId,phone,roomType);
	    	return orderpo;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	ResultMessage success = new ResultMessage(true);
	
	ResultMessage fail = new ResultMessage(false);
	
	/**
	 * 初始的订单生成订单号.
	 * @return
	 */
	@Override
	public OrderPO preOrder(OrderPO preOrder){
		ResultMessage rMessage=insert(preOrder);
		long orderId=Long.valueOf(rMessage.message);
		Connection conn = Connect.getConn();
        String sql="select * from ordertable where orderId = "+ orderId;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();			
			OrderPO orderpo=new OrderPO();
			if(rs.next())
				orderpo=createorderpo(rs);
			pstmt.close();
	        conn.close();
			return orderpo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 向数据库中插入一条新的订单记录.
	 * @return
	 */
	@Override
	public ResultMessage insert(OrderPO order) {
		Connection conn = Connect.getConn();
	    String sql = "insert into ordertable(orderId,orderStatus,latestTime,roomNumber,roomId,numberOfRoom,numberOfPeople,child,guestRealName,clientRealName,hotelName,userId,userName,startTime,leaveTime,orderTime,price,hotelId,phone,roomType) values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    long orderId=0;
	    try {
	    	String[] room=order.getroom().split(";");
	        pstmt = (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	        pstmt.setInt(1,order.getorderStatusAsInt());
	        pstmt.setString(2, order.getlatestTime());
	        pstmt.setString(3, room[1]);
	        pstmt.setString(4, room[0]);
	        pstmt.setInt(5, order.getnumberOfRoom());
	        pstmt.setInt(6, order.getnumberOfPeople());
	        pstmt.setInt(7, order.getchild());
	        pstmt.setString(8, order.getguestRealName());
	        pstmt.setString(9, order.getclientRealName());
	        pstmt.setString(10, order.gethotelName());
	        pstmt.setInt(11, order.getuserId());
	        pstmt.setString(12, order.getuserName());
	        pstmt.setString(13, order.getstartTime());
	        pstmt.setString(14, order.getleaveTime());
	        pstmt.setString(15, order.getorderTime());
	        pstmt.setDouble(16, order.getprice());
	        pstmt.setInt(17, order.gethotelId());
	        pstmt.setString(18, order.getphone());
	        pstmt.setString(19, order.getRoomTypeName());
	        pstmt.executeUpdate();
	        ResultSet rs = pstmt.getGeneratedKeys(); //获取结果  	        
	        if (rs.next()) {
	        	orderId = rs.getInt(1);//取得ID
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return fail;
	    } 	
	    return new ResultMessage(true,null,String.valueOf(orderId));
	}

	/**
	 * 更新order数据库中的一条记录.
	 * @return
	 */
	@Override
	public ResultMessage update(OrderPO order) {
		Connection conn = Connect.getConn();
		String[] room=order.getroom().split(";");
	    String sql = "update ordertable set latestTime='" + order.getlatestTime() +
	    		"', numberOfRoom=" + order.getnumberOfRoom() +
	    		", numberOfPeople=" + order.getnumberOfPeople() +
	    		", child=" + order.getchild() +
	    		", realName='" + order.getclientRealName() +
	    		"', hotelId=" + order.gethotelId() +
	    		", guestRealName='" + order.getguestRealName() +
	    		"', hotelName='" + order.gethotelName() +
	    		"', orderStatus=" + order.getorderStatusAsInt() +
	    		", price=" + order.getprice() +
	    		", startTime='" + order.getstartTime() +
	    		"', leaveTime='" + order.getleaveTime() +
	    		"', userName='" + order.getuserName() +
	    		"', userId=" + order.getuserId() +
	    		", roomNumber='" + room[1] +
	    		"', roomId='" + room[0] +
	    		"', orderTime='" + order.getorderTime() +
	    		"', phone='" + order.getphone() +
	    		"', roomType='" + order.getRoomTypeName() +
	            "' where orderId = " + order.getorderId() ;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return fail;
	    }
	    return success;
	}
	
	/**
	 * 计算该用户某种状态的订单总数.
	 * @return
	 */
	@Override
	public int countOrder(int userID, OrderStatus status) {
		Connection conn = Connect.getConn();
		int i=0;
		String sql="select * from ordertable where userId = " + userID + "AND orderStatus = " + status.ordinal();
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				i++;
			}
			pstmt.close();
	        conn.close();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 取消初始订单.
	 * @return
	 */
	@Override
	public ResultMessage cancelPreOrder(OrderPO preOrder) {
		Connection conn = Connect.getConn();
		String sql="DELETE FROM ordertable where orderId = " + preOrder.getorderId();
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
			return success;
		} catch (Exception e) {
			e.printStackTrace();
			return fail;
		}
	}

	/**
	 * 根据hotelID和订单状态搜索该酒店的订单.
	 * @return
	 */
	@Override
	public List<OrderPO> searchByHotel(int hotelID, OrderStatus status) {
		Connection conn = Connect.getConn();
		List<OrderPO> orders=new ArrayList<OrderPO>();
		String sql="select * from ordertable where hotelId = " + hotelID + "AND orderStatus = " + status.ordinal();
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				OrderPO orderpo=createorderpo(rs);
				orders.add(orderpo);
			}
			pstmt.close();
	        conn.close();
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据hotelID和userID搜索订单.
	 * @return
	 */
	@Override
	public List<OrderPO> searchByUser(int userID) {
		Connection conn = Connect.getConn();
		List<OrderPO> orders=new ArrayList<OrderPO>();
		String sql="select * from ordertable where userId =" + userID;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				OrderPO orderpo=createorderpo(rs);
				orders.add(orderpo);
			}
			pstmt.close();
	        conn.close();
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据订单号得到订单信息.
	 * @return
	 */
	@Override
	public OrderPO getOrderByID(long orderID) {
		Connection conn = Connect.getConn();
		String sql="select * from ordertable where orderId = " + orderID;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        OrderPO orderpo=new OrderPO();
			if(rs.next())
				orderpo=createorderpo(rs);
			return orderpo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 判断酒店未执行订单是否过时，若是，则置为异常订单，并扣除信用值.
	 * @return
	 */
	@Override
	public ResultMessage updateState(int hotelId) {
		Connection conn = Connect.getConn();
		String sql="select * from ordertable where hotelId = " + hotelId + "AND where orderStatus = " + 3;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				if(!isvalid(rs.getString(3))){
					changeState(rs.getLong(1));
					UserDataSqlHelper userDataSqlHelper=new UserDataSqlHelper();
					userDataSqlHelper.addCredit(rs.getInt(12),rs.getDouble(17));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fail;
		}
		return success;
	}
	
	/**
	 * 判断订单时间是否过时.
	 * @return
	 */
	public boolean isvalid(String latestTime) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date latest=df.parse(latestTime);
		Date now=new Date();
		if(latest.getTime()<now.getTime())
			return false;
		return true;
	}

	/**
	 * 若置为异常订单则扣除信用值.
	 * @return
	 */
	public void changeState(long orderId){
		Connection conn = Connect.getConn();
		String sql="update ordertable set orderStatus = " + 2 + "where orderId = " + orderId;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	

}
