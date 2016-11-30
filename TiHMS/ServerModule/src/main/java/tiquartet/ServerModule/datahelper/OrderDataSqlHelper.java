package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	ResultMessage success = new ResultMessage(true);
	
	ResultMessage fail = new ResultMessage(false);
	
	public ResultMessage insert(OrderPO order) {
		Connection conn = getConn();
	    String sql = "insert into order(orderId,latestTime,numberOfRoom,numberOfPeople,child,realName,hotelId) values(?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setLong(1, order.getorderId());
	        pstmt.setString(2, order.getlatestTime());
	        pstmt.setInt(3, order.getnumberOfRoom());
	        pstmt.setInt(4, order.getnumberOfPeople());
	        pstmt.setInt(5, order.getchild());
	        pstmt.setString(6, order.getrealName());
	        pstmt.setInt(7, order.gethotelId());
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
	    String sql = "update user set lastestTime='" + order.getlatestTime() +
	    		"set numberOfRoom='" + order.getnumberOfRoom() +
	    		"set numberOfPeople='" + order.getnumberOfPeople() +
	    		"set child='" + order.getchild() +
	    		"set realName='" + order.getrealName() +
	    		"set hotelId='" + order.gethotelId() +
	            "' where orderId='" + order.getorderId() + "'";
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

	public List<OrderPO> hasBeenOrdered(int hotelID, int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Long, OrderPO> getOrder() {
		Connection conn = getConn();
		Map<Long, OrderPO> map = new HashMap<Long, OrderPO>();
		String sql="select * from order";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	long orderId=rs.getLong(1);
	        	String latestTime=rs.getString(2);
			    int numberOfRoom=rs.getInt(3);
			    int numberOfPeople=rs.getInt(4);
			    int child=rs.getInt(5);
			    String realName=rs.getString(6);
			    int hotelId=rs.getInt(7);
			    OrderPO orderpo=new OrderPO(orderId,latestTime,numberOfRoom,numberOfPeople,child,realName,hotelId);
			    map.put(orderId,orderpo);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public int countOrder(int userID, OrderStatus status) {
		// TODO Auto-generated method stub
		return 0;
	}

}
