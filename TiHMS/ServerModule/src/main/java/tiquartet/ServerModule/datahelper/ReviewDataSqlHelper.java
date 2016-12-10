package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.service.ReviewDataHelper;
import tiquartet.ServerModule.po.ReviewPO;

/**
 * 对user数据库的操作.
 * @author Teki
 */
public class ReviewDataSqlHelper implements ReviewDataHelper{
	
	/**
	 * 根据hotelID搜索酒店评论.
	 * @return
	 */
	@Override
	public List<ReviewPO> searchByHotel (int hotelID){
		Connection conn = Connect.getConn();
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
	
	/**
	 * 向review数据库中添加一条记录.
	 * @return
	 */
	@Override
	public ResultMessage insert(ReviewPO review) {
		Connection conn = Connect.getConn();
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
	    average(review.getscore(), review.gethotelId());	
	    return new ResultMessage(true);
	}
	
	/**
	 * 每新增一条评分，重新计算酒店的平均评分.
	 * @return
	 */
	public ResultMessage average(int addtion,int hotelId){
		Connection conn = Connect.getConn();
	    String sql = "select * for review where hotelId =" + hotelId;
	    double aver=addtion;
	    int number=0;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs=pstmt.executeQuery();
	        while(rs.next()){
	        	number++;
	        	aver+=rs.getInt(2);
	        }
	        aver=aver/(number+1);
	        String sqll="update hotelInfo set averageGrade = " + aver +
	        		"where hotelId = " + hotelId;
	        pstmt = (PreparedStatement) conn.prepareStatement(sqll);	        
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        return new ResultMessage(true);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResultMessage(false);
	    } 	
	}

}
