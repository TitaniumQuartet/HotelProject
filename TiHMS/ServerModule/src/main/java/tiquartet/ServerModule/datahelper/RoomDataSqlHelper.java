package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
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
	 * 判断客房在某时间段是否可预定
	 * @return
	 */
	public boolean timeConflict(String startDate, String endDate, String startTime, String leaveTime){
		if((Integer.valueOf(startTime)<=Integer.valueOf(startDate)&&Integer.valueOf(leaveTime)>=Integer.valueOf(startDate))||
				(Integer.valueOf(startTime)<=Integer.valueOf(endDate)&&Integer.valueOf(leaveTime)>=Integer.valueOf(endDate))){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据客房编号得到房间类型
	 * @return
	 */
	public RoomTypePO getRoomType(int roomID){
		Connection conn = Connect.getConn();
	    String sql = "SELECT * FROM room where roomId =" + roomID;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rst = pstmt.executeQuery();
	       	int roomTypeId=rst.getInt(3);
	       	String sqll = "SELECT * FROM roomType where roomTypeId =" + roomTypeId;
	       	pstmt = (PreparedStatement)conn.prepareStatement(sqll);
	        ResultSet rs = pstmt.executeQuery();
	       	String typeIntro=rs.getString(2);
	       	double price=rs.getDouble(3);
	       	String roomType=rs.getString(4);
	       	int hotelId=rs.getInt(5);
			RoomTypePO roomtypepo = new RoomTypePO(roomTypeId,roomType,typeIntro,price,hotelId);
			pstmt.close();
	        conn.close();
			return roomtypepo;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * 根据信息得到可住客房.
	 * @return
	 */
	@Override
	public List<RoomTypePO> availableRoomType(int hotelID, String startDate, String endDate, int numOfRoom) {
		Connection conn = Connect.getConn();
		List<RoomTypePO> rooms = new ArrayList<RoomTypePO>();//可用房间类型的po列表
		Map<Integer, Integer> roomtAn = new HashMap<Integer, Integer>();//可用客房房间类型和数量
		String sql="select * from order where hotelId =" + hotelID + "where state =" + "0";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	String roomId=rs.getString(5);
	        	String startTime=rs.getString(14);
	 		    String leaveTime=rs.getString(15);
	 		     if(!timeConflict(startDate, endDate, startTime, leaveTime)){//判断订单时间与预定时间是否冲突
	 		    	 String[] roomid=roomId.split(",");
	 		    	 int[] roomID=new int[roomid.length];//得到订单上的所有房间编号
	 		    	 for(int i=0;i<roomid.length;i++){
	 		    		 roomID[i]=Integer.valueOf(roomid[i]);	 		    		
	 		    		 if(!roomtAn.containsKey(roomID[i])){
	 		    			 roomtAn.put(roomID[i], 1);
	 		    		 }else{
	 		    			 roomtAn.put(roomID[i], roomtAn.get(roomID[i])+1);
	 		    		 }	 		    		   
	 		    	 }
	 		   	} 
	 	    } 
	        Iterator<Map.Entry<Integer, Integer>> iter = roomtAn.entrySet().iterator();//判断可用客房类型的数量是否足够
	        while (iter.hasNext()) {
	        	Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iter.next();
	        	int key = entry.getKey();
	        	int val = entry.getValue();
	        	if(val>=numOfRoom){
	        		RoomTypePO roomType = getRoomType(key);
	        		rooms.add(roomType);
	        	}	        	
	        }	        
			pstmt.close();
	        conn.close();
			return rooms;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
