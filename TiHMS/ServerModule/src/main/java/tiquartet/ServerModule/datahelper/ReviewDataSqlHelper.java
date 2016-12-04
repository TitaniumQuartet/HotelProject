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
import tiquartet.ServerModule.datahelper.service.ReviewDataHelper;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.ReviewPO;

public class ReviewDataSqlHelper implements ReviewDataHelper{

	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/samp_db";
	    String username = "root";
	    String password = "";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	public List<ReviewPO> searchByHotel (int hotelID){
		Connection conn = getConn();
		List<ReviewPO> reviews=new ArrayList<ReviewPO>();
	    String sql = "SELECT * FROM review where hotelId =" + hotelID;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	int hotelId=rs.getInt(1);
	        	int score=rs.getInt(2);
	        	String review=rs.getString(3);
	        	int userId=rs.getInt(4);
	        	String userName=rs.getString(5);
	        	String time=rs.getString(6);
	        	ReviewPO reviewPO=new ReviewPO(hotelId,score,review,userId,userName,time);
	        	reviews.add(reviewPO);
	        } 
	        pstmt.close();
	        conn.close();
		    return reviews;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public ResultMessage insert(ReviewPO review) {
		Connection conn = getConn();
	    String sql = "insert into review(hotelId,score,review,userId,userName,time) values (?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1, review.gethotelId());
	        pstmt.setInt(2, review.getscore());
	        pstmt.setString(3, review.getreview());
	        pstmt.setInt(4, review.getuserId());
	        pstmt.setString(5, review.getuserName());
	        pstmt.setString(6, review.gettime());
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResultMessage(false);
	    } 	
	    return new ResultMessage(true);
	}

}
