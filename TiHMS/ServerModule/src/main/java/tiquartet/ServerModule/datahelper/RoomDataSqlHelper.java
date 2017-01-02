package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.RoomStatus;
import tiquartet.ServerModule.datahelper.service.RoomDataHelper;
import tiquartet.ServerModule.po.RoomPO;
import tiquartet.ServerModule.po.RoomTypePO;

/**
 * 对room数据库的操作.
 * 
 * @author Teki
 */
public class RoomDataSqlHelper implements RoomDataHelper {

	ResultMessage success = new ResultMessage(true);

	ResultMessage fail = new ResultMessage(false);

	/**
	 * 更新room数据库.
	 * 
	 * @return
	 */
	public ResultMessage updatetable(String sql) {
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
	 * 判断客房在某时间段是否可预定.
	 * 
	 * @return
	 */
	public boolean timeConflict(String startDate, String endDate,
			String startTime, String leaveTime) {
		if ((Integer.valueOf(startTime) <= Integer.valueOf(startDate)
				&& Integer.valueOf(endDate) >= Integer.valueOf(startTime))
				|| (Integer.valueOf(leaveTime) <= Integer.valueOf(endDate)
						&& Integer.valueOf(leaveTime) >= Integer
								.valueOf(startDate))) {
			return true;
		}
		return false;
	}

	/**
	 * 根据数据库里的数据创建roomtypepo.
	 * 
	 * @return
	 */
	public RoomTypePO createroomTypePO(ResultSet rs) {
		RoomTypePO roomtypepo = new RoomTypePO();
		try {
			if (rs.next()) {
				int roomTypeId = rs.getInt(1);
				String typeIntro = rs.getString(2);
				double price = rs.getDouble(3);
				String roomType = rs.getString(4);
				int hotelId = rs.getInt(5);
				int number = rs.getInt(6);
				roomtypepo = new RoomTypePO(roomTypeId, roomType, typeIntro,
						price, hotelId, number);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return roomtypepo;
	}

	/**
	 * 根据客房编号得到房间类型.
	 * 
	 * @return
	 */
	public RoomTypePO getRoomType(int roomID) {
		Connection conn = Connect.getConn();
		String sql = "SELECT * FROM room where roomId =" + roomID;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			int roomTypeId = rst.getInt(3);
			String sqll = "SELECT * FROM roomType where roomTypeId ="
					+ roomTypeId;
			pstmt = (PreparedStatement) conn.prepareStatement(sqll);
			ResultSet rs = pstmt.executeQuery();
			RoomTypePO roomtypepo = createroomTypePO(rs);
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
	 * 
	 * @return
	 */
	@Override
	public List<RoomTypePO> availableRoomType(int hotelID, String startDate,
			String endDate, int numOfRoom) {
		Connection conn = Connect.getConn();
		List<RoomTypePO> rooms = new ArrayList<RoomTypePO>();// 可用房间类型的po列表
		List<RoomTypePO> allroom = new ArrayList<RoomTypePO>();// 所有可用客房
		Map<Integer, Integer> roomtAn = new HashMap<Integer, Integer>();// 可用客房房间类型和数量
		String sql = "select * from ordertable where hotelId =" + hotelID
				+ " AND where orderStatus = " + 3 + " OR " + 1 + "OR" + 5;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String roomId = rs.getString(5);
				String startTime = rs.getString(14);
				String leaveTime = rs.getString(15);
				if (timeConflict(startDate, endDate, startTime, leaveTime)) {// 判断订单时间与预定时间是否冲突
					String[] roomid = roomId.split(",");
					int[] roomID = new int[roomid.length];// 得到订单上的所有房间编号
					for (int i = 0; i < roomid.length; i++) {
						roomID[i] = Integer.valueOf(roomid[i]);
						int roomTypeId = this.getRoomType(roomID[i])
								.getroomTypeId();
						if (!roomtAn.containsKey(roomTypeId)) {
							roomtAn.put(roomTypeId, 1);
						} else {
							roomtAn.put(roomTypeId,
									roomtAn.get(roomTypeId) + 1);
						}
					}
				}
			}
			String sqll = "select * from roomType where hotelId =" + hotelID;// 得到酒店所有房间类型
			pstmt = (PreparedStatement) conn.prepareStatement(sqll);
			rs = pstmt.executeQuery();
			RoomTypePO roomTypePO = new RoomTypePO();
			while (rs.next()) {
				roomTypePO = createroomTypePO(rs);
				allroom.add(roomTypePO);
			}
			Iterator<Map.Entry<Integer, Integer>> iter;
			for (int i = 0; i < allroom.size(); i++) {
				iter = roomtAn.entrySet().iterator();// 判断可用客房类型的数量是否足够
				while (iter.hasNext()) {
					Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iter
							.next();
					int key = entry.getValue();
					int val = entry.getValue();
					if ((allroom.get(i).getroomTypeId() == key)
							&& (allroom.get(i).getnumber()
									- val) >= numOfRoom) {// 用房间类型的总量减去被预定了的房间数量
						rooms.add(allroom.get(i));
					}
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
	 * 
	 * @return
	 */
	@Override
	public ResultMessage update(RoomPO room) {// hotelid不可修改
		String sql = "update room set roomTypeId=" + room.getroomTypeId()
				+ ", roomNumber='" + room.getroomNumber() + "', state="
				+ room.getstateAsInt() + " where roomId=" + room.getroomId();
		return updatetable(sql);
	}

	/**
	 * 向room数据表添加一条记录.
	 * 
	 * @return
	 */
	@Override
	public ResultMessage insert(RoomPO room) {
		Connection conn = Connect.getConn();
		String sql = "insert into room(roomNumber,roomTypeId,state,hotelId) values(?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, room.getroomNumber());
			pstmt.setInt(2, room.getroomTypeId());
			pstmt.setInt(3, room.getstateAsInt());
			pstmt.setInt(4, room.gethotelId());
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
	 * 
	 * @return
	 */
	@Override
	public ResultMessage delete(int roomID) {
		String sql = "DELETE FROM room where roomId = " + roomID;
		return updatetable(sql);
	}

	/**
	 * 修改房间状态为已入住
	 * 
	 * @return
	 */
	@Override
	public ResultMessage checkIn(int roomID) {
		String sql = "update room set state =" + 1 + " where roomId=" + roomID;
		return updatetable(sql);
	}

	/**
	 * 修改房间状态为空闲
	 * 
	 * @return
	 */
	@Override
	public ResultMessage checkOut(int roomID) {
		String sql = "update room set state =" + 0 + " where roomId=" + roomID;
		return updatetable(sql);
	}

	/**
	 * 向roomType数据表添加一条记录.
	 * 
	 * @return
	 */
	@Override
	public ResultMessage insertType(RoomTypePO room) {
		Connection conn = Connect.getConn();
		String sql = "insert into roomType(roomTypeId,typeIntro,price,roomType,hotelId,number) values(null,?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, room.gettypeIntroduction());
			pstmt.setDouble(2, room.getprice());
			pstmt.setString(3, room.getroomType());
			pstmt.setInt(4, room.gethotelId());
			pstmt.setInt(5, room.getnumber());
			pstmt.executeUpdate();
			updateLow(room.getprice(), room.gethotelId());
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return fail;
		}
		return success;
	}

	/**
	 * 修改酒店最低价格和最高价格
	 * 
	 * @return
	 */
	public void updateLow(double price, int hotelId) {
		Connection conn = Connect.getConn();
		String sql = "select* from hotelInfo where hotelId =" + hotelId;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			String sqll = "update hotelInfo set lowPrice =" + price
					+ "where hotelId =" + hotelId;
			String sqlll = "update hotelInfo set highPrice =" + price
					+ "where hotelId =" + hotelId;
			if (rs.next()) {
				if (rs.getInt(9) == -1) {
					PreparedStatement pstmt1 = (PreparedStatement) conn
							.prepareStatement(sqll);
					pstmt1.executeUpdate();
				} else {
					if (rs.getDouble(9) > price) {
						PreparedStatement pstmt1 = (PreparedStatement) conn
								.prepareStatement(sqll);
						pstmt1.executeUpdate();
					}
				}
				if (rs.getDouble(10) == -1) {
					PreparedStatement pstmt1 = (PreparedStatement) conn
							.prepareStatement(sqlll);
					pstmt1.executeUpdate();
				} else {
					if (rs.getDouble(10) < price) {
						PreparedStatement pstmt1 = (PreparedStatement) conn
								.prepareStatement(sqlll);
						pstmt1.executeUpdate();
					}
				}

			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	/**
	 * 更新roomType数据表.
	 * 
	 * @return
	 */
	@Override
	public ResultMessage updateType(RoomTypePO room) {
		String sql = "update roomType set roomTypeId=" + room.getroomTypeId()
				+ ", typeIntro='" + room.gettypeIntroduction() + "', price="
				+ room.getprice() + ", roomType='" + room.getroomType()
				+ "', number=" + room.getnumber() + " where roomTypeId="
				+ room.getroomTypeId();
		return updatetable(sql);
	}

	/**
	 * 删除roomType中的一条记录.
	 * 
	 * @return
	 */
	@Override
	public ResultMessage deleteType(RoomTypePO room) {
		String sql = "DELETE FROM roomType where roomTypeId = "
				+ room.getroomTypeId();
		return updatetable(sql);
	}

	/**
	 * 得到酒店的所有房间.
	 * 
	 * @return
	 */
	@Override
	public List<RoomPO> getRoomList(int hotelID) {
		Connection conn = Connect.getConn();
		List<RoomPO> rooms = new ArrayList<RoomPO>();
		String sql = "select * from room where hotelId =" + hotelID;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int roomId = rs.getInt(1);
				String roomNumber = rs.getString(2);
				int roomTypeId = rs.getInt(3);
				RoomStatus state = RoomStatus.values()[rs.getInt(4)];
				int hotelId = rs.getInt(5);
				RoomPO roompo = new RoomPO(roomId, roomNumber, roomTypeId,
						state, hotelId);
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
