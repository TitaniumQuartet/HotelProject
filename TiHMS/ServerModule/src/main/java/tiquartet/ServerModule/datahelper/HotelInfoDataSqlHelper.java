package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.service.HotelInfoDataHelper;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.RoomTypePO;

/**
 * 对hotelInfo数据库的操作.
 * @author Teki
 */
public class HotelInfoDataSqlHelper implements HotelInfoDataHelper{

	/**
	 * 通过数据库的数据生成HotelInfoPO.
	 * @return
	 */
	public HotelInfoPO createhotel(ResultSet rs){
		try{
			int hotelId=rs.getInt(1);
        	String hotelName=rs.getString(2);
        	int star=rs.getInt(3);
        	String address=rs.getString(4);
        	String hotelIntroduction=rs.getString(5);
        	String serviceIntrduction=rs.getString(6);
        	int circleId=rs.getInt(7);
        	String circleName=rs.getString(8);
        	double lowPrice=rs.getDouble(9);
        	double highPrice=rs.getDouble(10);
        	double averageGrade=rs.getDouble(11);
        	String cityName=rs.getString(12);
        	HotelInfoPO hotelInfoPO=new HotelInfoPO(hotelId,hotelName,star,address,hotelIntroduction,serviceIntrduction,circleId,circleName,lowPrice,highPrice,averageGrade,cityName); 
	        return hotelInfoPO;
		} catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    } 
	}

	ResultMessage success = new ResultMessage(true);
	
	ResultMessage fail = new ResultMessage(false);
	
	/**
	 * 根据hotelID得到酒店的信息.
	 * @return
	 */
	@Override
	public HotelInfoPO getHotelInfo(int hotelID) {
		Connection conn = Connect.getConn();
	    String sql = "SELECT * FROM hotelInfo where hotelId =" + hotelID;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        HotelInfoPO hotelInfoPO=new HotelInfoPO();
	        if(rs.next())
	        	hotelInfoPO=createhotel(rs);
        	pstmt.close();
	        conn.close();
        	return hotelInfoPO;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * 根据某个商圈的酒店编号得到新酒店的酒店编号，酒店编号前7位为商圈编号，后三位为该商圈内部编号.
	 * @return
	 */
	public int getHotelId(HotelInfoPO hotel){
		Connection conn=Connect.getConn();
		int hotelId=0;
		String sql="select * from hotelInfo where circleId =" + hotel.getcircleId();
		PreparedStatement pstmt;
		try{
			pstmt=(PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				hotelId++;
			}
			hotelId=hotelId+hotel.getcircleId()*1000;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return hotelId;
	}

	/**
	 * 向hotelInfo数据库中插入一条记录.
	 * @return
	 */
	@Override
	public ResultMessage insert(HotelInfoPO hotelInfo) {
		Connection conn = Connect.getConn();
	    String sql = "insert into hotelInfo(hotelId,hotelName,star,address,hotelIntroduction,serviceIntroduction,circleId,circleName,lowPrice,highPrice,averageGrade,cityName) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        int hotelId=getHotelId(hotelInfo);
	        pstmt.setInt(1, hotelId);
	        pstmt.setString(2, hotelInfo.gethotelName());
	        pstmt.setInt(3, hotelInfo.getstar());
	        pstmt.setString(4, hotelInfo.getaddress());
	        pstmt.setString(5, hotelInfo.gethotelIntroduction());
	        pstmt.setString(6, hotelInfo.getserviceIntroduction());
	        pstmt.setInt(7, hotelInfo.getcircleId());
	        pstmt.setString(8, hotelInfo.getcircleName());
	        pstmt.setDouble(9, hotelInfo.getlowprice());
	        pstmt.setDouble(10, hotelInfo.gethighprice());
	        pstmt.setDouble(11, hotelInfo.getaverageGrade());
	        pstmt.setString(12, hotelInfo.getcityName());
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        return new ResultMessage(true,null,String.valueOf(hotelId));
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return fail;
	    } 	
	}

	/**
	 * 更新相关的酒店信息.
	 * @return
	 */
	@Override
	public ResultMessage update(HotelInfoPO hotelInfo) {
		Connection conn = Connect.getConn();
	    String sql = "update hotelInfo set hotelName='" + hotelInfo.gethotelName() +
	    		"', star=" + hotelInfo.getstar() +
	    		", address='" + hotelInfo.getaddress() +
	    		"', hotelIntroduction='" + hotelInfo.gethotelIntroduction() +
	    		"', serviceIntroduction='" + hotelInfo.getserviceIntroduction() +
	    		"', circleId=" + hotelInfo.getcircleId() +
	    		", circleName='" + hotelInfo.getcircleName() +
	    		"', lowPrice=" + hotelInfo.getlowprice() +
	    		", highPrice=" + hotelInfo.gethighprice() +
	    		", averageGrade=" + hotelInfo.getaverageGrade() +
	    		", cityName='" + hotelInfo.getcityName() +
	            "' where hotelId = " + hotelInfo.gethotelId() ;
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
	 * 通过hotelID得到该酒店的所有房间类型.
	 * @return
	 */
	@Override
	public List<RoomTypePO> getRoomTypes(int hotelID) {
		Connection conn = Connect.getConn();
		List<RoomTypePO> roomtypes=new ArrayList<RoomTypePO>();
	    String sql = "SELECT * FROM roomType where hotelId =" + hotelID;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	int roomTypeId=rs.getInt(1);
	        	String typeIntro=rs.getString(2);
	        	double price=rs.getDouble(3);
	        	String roomType=rs.getString(4);
	        	int hotelId=rs.getInt(5);
	        	int number=rs.getInt(6);
				RoomTypePO roomtypepo = new RoomTypePO(roomTypeId,roomType,typeIntro,price,hotelId,number);
				roomtypes.add(roomtypepo);
			}
			pstmt.close();
	        conn.close();
			return roomtypes;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	/**
	 * 得到该商圈的所有酒店列表.
	 * @return
	 */
	@Override
	public List<HotelInfoPO> getHotelList(int districtID) {
		Connection conn = Connect.getConn();
		List<HotelInfoPO> hotels=new ArrayList<HotelInfoPO>();
	    String sql = "SELECT * FROM hotelInfo where districtId =" + districtID;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	HotelInfoPO hotelInfoPO=createhotel(rs);
	        	hotels.add(hotelInfoPO);
	        } 
	        pstmt.close();
	        conn.close();
		    return hotels;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	
}
