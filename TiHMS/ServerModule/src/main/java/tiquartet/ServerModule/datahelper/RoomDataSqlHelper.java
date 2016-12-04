package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.RoomStatus;
import tiquartet.ServerModule.datahelper.service.RoomDataHelper;
import tiquartet.ServerModule.po.OrderPO;
import tiquartet.ServerModule.po.RoomPO;
import tiquartet.ServerModule.po.RoomTypePO;
import tiquartet.ServerModule.po.UserPO;

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
	
	public ResultMessage updatetable(String sql){
		Connection conn = getConn();
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
	
	public List<RoomTypePO> availableRoomType(int hotelID, String startDate, String endDate, int numOfRoom) {
		
		return null;
	}

	public ResultMessage update(RoomPO room) {
	    String sql = "update roomType set roomTypeId='" + room.getroomTypeId() +
	    		"set typeIntro='" + room.getroomId() +
	    		"set price='" + room.getroomNumber() +
	    		"set roomType='" + room.getstateAsInt() +
	            " where hotelId='" + room.gethotelId();
	    return updatetable(sql);
	}

	public ResultMessage insert(RoomPO room) {
		Connection conn = getConn();
	    String sql = "insert into room(roomId,roomNumber,roomTypeId,state,hotelId) values(?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1,room.getroomId());
	        pstmt.setString(2, room.getroomNumber());
	        pstmt.setInt(3,room.getroomTypeId());
	        pstmt.setInt(4, room.getstateAsInt());
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
	    String sql = "DELETE FROM room where roomId = " + roomID;
	    return updatetable(sql);
	}

	public ResultMessage checkIn(int roomID) {
		String sql = "update room set state =" + "1" +
	            " where roomId='" + roomID;
	    return updatetable(sql);
	}

	public ResultMessage checkOut(int roomID) {
		String sql = "update room set state =" + "0" +
	            " where roomId='" + roomID;
	    return updatetable(sql);
	}

	@Override
	public ResultMessage insertType(RoomTypePO room) {
		Connection conn = getConn();
	    String sql = "insert into roomType(roomTypeId,typeIntro,price,roomType,hotelId) values(?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1, room.getroomTypeId());
	        pstmt.setString(2, room.gettypeIntroduction());
	        pstmt.setDouble(3, room.getprice());
	        pstmt.setString(4, room.getroomType()); 
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

	@Override
	public ResultMessage updateType(RoomTypePO room) {
	    String sql = "update roomType set roomTypeId='" + room.getroomTypeId() +
	    		"set typeIntro='" + room.gettypeIntroduction() +
	    		"set price='" + room.getprice() +
	    		"set roomType='" + room.getroomType() +
	            " where hotelId='" + room.gethotelId();
	    return updatetable(sql);
	}

	@Override
	public ResultMessage deleteType(RoomTypePO room) {
	    String sql = "DELETE FROM roomType where roomTypeId = " + room.getroomTypeId();
	    return updatetable(sql);
	}

	@Override
	public List<RoomPO> getRoomList(int hotelID) {
		Connection conn = getConn();
		List<RoomPO> rooms = new ArrayList<RoomPO>();
		String sql="select * from room where hotelId =" + hotelID;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	int roomId=rs.getInt(1);
	        	String roomNumber=rs.getString(2);
	        	int roomTypeId=rs.getInt(3);
	        	RoomStatus state=RoomStatus.values()[rs.getInt(4)];
	        	int hotelId=rs.getInt(5);
				RoomPO roompo = new RoomPO(roomId,roomNumber,roomTypeId,state,hotelId);
				rooms.add(roompo);
			}
			pstmt.close();
	        conn.close();
			return rooms;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
