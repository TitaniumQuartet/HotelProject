package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.RoomStatus;
import tiquartet.ServerModule.datahelper.service.RoomDataHelper;
import tiquartet.ServerModule.po.RoomPO;
import tiquartet.ServerModule.po.RoomTypePO;

/**
 * 对room数据库的操作.
 * @author Teki
 */
public class RoomDataSqlHelper implements RoomDataHelper{

	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	/**
	 * 更新room数据库.
	 * @return
	 */
	public ResultMessage updatetable(String sql){
		Connection conn = Connect.getConn();
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
	 * 根据信息得到可住客房.
	 * @return
	 */
	@Override
	public List<RoomTypePO> availableRoomType(int hotelID, String startDate, String endDate, int numOfRoom) {
		
		return null;
	}

	/**
	 * 更新room数据表的一条记录.
	 * @return
	 */
	@Override
	public ResultMessage update(RoomPO room) {
	    String sql = "update roomType set roomTypeId='" + room.getroomTypeId() +
	    		"set typeIntro='" + room.getroomId() +
	    		"set price='" + room.getroomNumber() +
	    		"set roomType='" + room.getstateAsInt() +
	            " where hotelId='" + room.gethotelId();
	    return updatetable(sql);
	}

	/**
	 * 向room数据表添加一条记录.
	 * @return
	 */
	@Override
	public ResultMessage insert(RoomPO room) {
		Connection conn = Connect.getConn();
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

	/**
	 * 在room数据表删除一条记录.
	 * @return
	 */
	@Override
	public ResultMessage delete(int roomID) {
	    String sql = "DELETE FROM room where roomId = " + roomID;
	    return updatetable(sql);
	}

	/**
	 * 修改房间状态为已入住
	 * @return
	 */
	@Override
	public ResultMessage checkIn(int roomID) {
		String sql = "update room set state =" + "1" +
	            " where roomId='" + roomID;
	    return updatetable(sql);
	}

	/**
	 * 修改房间状态为空闲
	 * @return
	 */
	@Override
	public ResultMessage checkOut(int roomID) {
		String sql = "update room set state =" + "0" +
	            " where roomId='" + roomID;
	    return updatetable(sql);
	}

	/**
	 * 向roomType数据表添加一条记录.
	 * @return
	 */
	@Override
	public ResultMessage insertType(RoomTypePO room) {
		Connection conn = Connect.getConn();
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

	/**
	 * 更新roomType数据表.
	 * @return
	 */
	@Override
	public ResultMessage updateType(RoomTypePO room) {
	    String sql = "update roomType set roomTypeId='" + room.getroomTypeId() +
	    		"set typeIntro='" + room.gettypeIntroduction() +
	    		"set price='" + room.getprice() +
	    		"set roomType='" + room.getroomType() +
	            " where hotelId='" + room.gethotelId();
	    return updatetable(sql);
	}

	/**
	 * 删除roomType中的一条记录.
	 * @return
	 */
	@Override
	public ResultMessage deleteType(RoomTypePO room) {
	    String sql = "DELETE FROM roomType where roomTypeId = " + room.getroomTypeId();
	    return updatetable(sql);
	}

	/**
	 * 得到酒店的所有房间.
	 * @return
	 */
	@Override
	public List<RoomPO> getRoomList(int hotelID) {
		Connection conn = Connect.getConn();
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
