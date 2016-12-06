package tiquartet.ServerModule.datahelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		int l=roomn.length;
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
		    double price=rs.getDouble(17);
		    int hotelId=rs.getInt(18);
		    String orderTime=rs.getString(16);
		    HashMap<Integer, String> roomMap=transform(roomNumber, roomId);
		    OrderPO orderpo=new OrderPO(orderId,orderStatus,latestTime,roomMap,numberOfRoom,numberOfPeople,child,guestRealName,clientRealName,hotelName,userId,userName,startTime,leaveTime,orderTime,price,hotelId);
		    return orderpo;
		}catch (Exception e) {
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
		insert(preOrder);
		Connection conn = Connect.getConn();
		String sql="select * from order where userId = " ;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			OrderPO orderpo =new OrderPO();
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
	    String sql = "insert into order(orderStatus,latestTime,roomNumber,roomId,numberOfRoom,numberOfPeople,child,guestRealName,clientRealName,hotelName,userId,userName,startTime,leaveTime,price,hotelId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	    	String[] room=order.getroom().split(";");
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(2,order.getorderStatusAsInt());
	        pstmt.setString(3, order.getlatestTime());
	        pstmt.setString(4, room[1]);
	        pstmt.setString(5, room[0]);
	        pstmt.setInt(6, order.getnumberOfRoom());
	        pstmt.setInt(7, order.getnumberOfPeople());
	        pstmt.setInt(8, order.getchild());
	        pstmt.setString(9, order.getguestRealName());
	        pstmt.setString(10, order.getclientRealName());
	        pstmt.setString(11, order.gethotelName());
	        pstmt.setInt(12, order.getuserId());
	        pstmt.setString(13, order.getuserName());
	        pstmt.setString(14, order.getstartTime());
	        pstmt.setString(15, order.getleaveTime());
	        pstmt.setString(16, order.getorderTime());
	        pstmt.setDouble(17, order.getprice());
	        pstmt.setInt(18, order.gethotelId());
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
	 * 更新order数据库中的一条记录.
	 * @return
	 */
	@Override
	public ResultMessage update(OrderPO order) {
		Connection conn = Connect.getConn();
		String[] room=order.getroom().split(";");
	    String sql = "update user set lastestTime='" + order.getlatestTime() +
	    		"set numberOfRoom='" + order.getnumberOfRoom() +
	    		"set numberOfPeople='" + order.getnumberOfPeople() +
	    		"set child='" + order.getchild() +
	    		"set realName='" + order.getclientRealName() +
	    		"set hotelId='" + order.gethotelId() +
	    		"set guestRealName='" + order.getguestRealName() +
	    		"set hotelName='" + order.gethotelName() +
	    		"set orderStatus='" + order.getorderStatusAsInt() +
	    		"set price='" + order.getprice() +
	    		"set startTime='" + order.getstartTime() +
	    		"set leaveTime='" + order.getleaveTime() +
	    		"set userName='" + order.getuserName() +
	    		"set userId='" + order.getuserId() +
	    		"set roomNumber='" + room[1] +
	    		"set roomId='" + room[0] +
	    		"set orderTime='" + order.getorderTime() +
	            " where orderId = " + order.getorderId() ;
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
		String sql="select * from order where userId = " + userID + "AND orderStatus = " + status;
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
		String sql="DELETE FROM order where orderId = " + preOrder.getorderId();
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
		String sql="select * from order where hotelId = " + hotelID + "AND orderStatus = " + status;
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
	public List<OrderPO> searchByUser(int hotelID, int userID) {
		Connection conn = Connect.getConn();
		List<OrderPO> orders=new ArrayList<OrderPO>();
		String sql="select * from order where hotelId = " + hotelID + "AND userId = " + userID;
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
		String sql="select * from order where orderId = " + orderID;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
			OrderPO orderpo=createorderpo(rs);
			return orderpo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
