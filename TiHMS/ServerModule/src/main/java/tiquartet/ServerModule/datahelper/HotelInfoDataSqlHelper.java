package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.service.HotelInfoDataHelper;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.RoomTypePO;

public class HotelInfoDataSqlHelper implements HotelInfoDataHelper{

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
	
	@Override
	public HotelInfoPO getHotelInfo(int hotelID) {
		Connection conn = getConn();
	    String sql = "SELECT * FROM hotelInfo where hotelId =" + hotelID;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
        	HotelInfoPO hotelInfoPO=createhotel(rs);
        	pstmt.close();
	        conn.close();
        	return hotelInfoPO;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public ResultMessage insert(HotelInfoPO hotelInfo) {
		Connection conn = getConn();
	    String sql = "insert into hotelInfo(hotelId,hotelName,star,address,hotelIntroduction,serviceIntrduction,circleId,circleName,lowPrice,highPrice,averageGrade,cityName) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1, hotelInfo.gethotelId());
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
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return fail;
	    } 	
	    return success;
	}

	@Override
	public ResultMessage update(HotelInfoPO hotelInfo) {
		Connection conn = getConn();
	    String sql = "update hotelInfo set hotelName='" + hotelInfo.gethotelName() +
	    		"set star='" + hotelInfo.getstar() +
	    		"set address='" + hotelInfo.getaddress() +
	    		"set hotelIntroduction='" + hotelInfo.gethotelIntroduction() +
	    		"set serviceIntroduction='" + hotelInfo.getserviceIntroduction() +
	    		"set circleId='" + hotelInfo.getcircleId() +
	    		"set circleName='" + hotelInfo.getcircleName() +
	    		"set lowPrice='" + hotelInfo.getlowprice() +
	    		"set highPrice='" + hotelInfo.gethighprice() +
	    		"set averageGrade='" + hotelInfo.getaverageGrade() +
	    		"set cityName='" + hotelInfo.getcityName() +
	            " where hotelId = " + hotelInfo.gethotelId() ;
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

	@Override
	public List<RoomTypePO> getRoomTypes(int hotelID) {
		Connection conn = getConn();
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
				RoomTypePO roomtypepo = new RoomTypePO(roomTypeId,roomType,typeIntro,price,hotelId);
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

	@Override
	public List<HotelInfoPO> getHotelList(int districtID) {
		Connection conn = getConn();
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
