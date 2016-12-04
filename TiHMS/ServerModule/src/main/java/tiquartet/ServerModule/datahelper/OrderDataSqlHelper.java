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

public class OrderDataSqlHelper implements OrderDataHelper{

	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/samp_db";
	    String username = "root";
	    String password = "";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	public HashMap<Integer, String> transform(String roomNumber,String roomId){
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
	
	public OrderPO preOrder(OrderPO preOrder){
		return null;
	}
	
	public ResultMessage insert(OrderPO order) {
		Connection conn = getConn();
	    String sql = "insert into order(orderId,orderStatus,latestTime,roomNumber,roomId,numberOfRoom,numberOfPeople,child,guestRealName,clientRealName,hotelName,userId,userName,startTime,leaveTime,price,hotelId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	    	String[] room=order.getroom().split(";");
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setLong(1, order.getorderId());
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

	public ResultMessage update(OrderPO order) {
		Connection conn = getConn();
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
	
	public int countOrder(int userID, OrderStatus status) {
		Connection conn = getConn();
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

	@Override
	public ResultMessage cancelPreOrder(OrderPO preOrder) {
		Connection conn = getConn();
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

	@Override
	public List<OrderPO> searchByHotel(int hotelID, OrderStatus status) {
		Connection conn = getConn();
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

	@Override
	public List<OrderPO> searchByUser(int hotelID, int userID) {
		Connection conn = getConn();
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

	@Override
	public OrderPO getOrderByID(long orderID) {
		Connection conn = getConn();
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
