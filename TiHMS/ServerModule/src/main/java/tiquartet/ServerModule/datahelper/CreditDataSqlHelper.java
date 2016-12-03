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
import tiquartet.ServerModule.datahelper.service.CreditDataHelper;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.RoomPO;

public class CreditDataSqlHelper implements CreditDataHelper{

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
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	@Override
	public ResultMessage insert(CreditPO creditItem) {
		Connection conn = getConn();
	    String sql = "insert into credit(changeType,change,balance,orderId,creditRecordId) values(?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1,creditItem.getchangeType());
	        pstmt.setDouble(2, creditItem.getchange());
	        pstmt.setDouble(3,creditItem.getbalance());
	        pstmt.setLong(4, creditItem.getorderId());
	        pstmt.setLong(5, creditItem.getcreditRecordId());
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
	public List<CreditPO> getRecord(int userID) {
		Connection conn = getConn();
		List<CreditPO> credit = new ArrayList<CreditPO>();
		String sql="select * from credit where userId =" + userID;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	int changeType=rs.getInt(1);
	        	double change=rs.getDouble(2);
	        	double balance=rs.getDouble(3);
	        	long orderId=rs.getLong(4);
	        	long creditRecordId=rs.getLong(5);
	        	CreditPO creditpo = new CreditPO(changeType,change,balance,orderId,creditRecordId);
				credit.add(creditpo);
			}
			pstmt.close();
	        conn.close();
			return credit;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
