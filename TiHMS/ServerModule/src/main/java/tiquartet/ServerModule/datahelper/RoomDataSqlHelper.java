package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.service.RoomDataHelper;
import tiquartet.ServerModule.po.OrderPO;
import tiquartet.ServerModule.po.RoomPO;
import tiquartet.ServerModule.po.RoomTypePO;

public class RoomDataSqlHelper implements RoomDataHelper{

	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/samp_db";
	    String username = "root";
	    String password = "";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,鍔犺浇瀵瑰簲椹卞姩
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	public ResultMessage preOrder(OrderPO preOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage cancelPreOrder(OrderPO preOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RoomTypePO> availableRoomType(int hotelID, String startDate,
			String endDate, int numOfRoom) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Integer, RoomPO> getRoom(){
		return null;
	}
	
	public ResultMessage update(RoomPO room) {
		Connection conn = getConn();
	    String sql = "update roomType set roomTypeId='" + room.getroomTypeId() +
	    		"set typeIntro='" + room.getroomId() +
	    		"set price='" + room.getroomNumber() +
	    		"set roomType='" + room.getstate() +
	            "' where hotelId='" + room.gethotelId() + "'";
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

	public ResultMessage insert(RoomPO room) {
		Connection conn = getConn();
	    String sql = "insert into room(roomId,roomNumber,roomTypeId,state,hotelId) values(?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1,room.getroomId());
	        pstmt.setInt(2, room.getroomNumber());
	        pstmt.setInt(3,room.getroomTypeId());
	        pstmt.setString(4, room.getstate());
	        pstmt.setInt(5, room.gethotelId());
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return fail;
	    } 	
	    return success;
		
	}

	public ResultMessage delete(int roomID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage checkIn(int roomID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage checkOut(int roomID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage insertType(int hotelID, RoomTypePO room) {
		Connection conn = getConn();
	    String sql = "insert into roomType(roomTypeId,typeIntro,price,roomType) values(?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1, room.getroomTypeId());
	        pstmt.setString(2, room.gettypeIntroduction());
	        pstmt.setDouble(3, room.getprice());
	        pstmt.setString(4, room.getroomType()); 
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return fail;
	    } 	
	    return success;
	}

	public ResultMessage updateType(int hotelID, RoomTypePO room) {
		Connection conn = getConn();
	    String sql = "update roomType set roomTypeId='" + room.getroomTypeId() +
	    		"set typeIntro='" + room.gettypeIntroduction() +
	    		"set price='" + room.getprice() +
	    		"set roomType='" + room.getroomType() +
	            "' where hotelId='" + hotelID + "'";
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

}
