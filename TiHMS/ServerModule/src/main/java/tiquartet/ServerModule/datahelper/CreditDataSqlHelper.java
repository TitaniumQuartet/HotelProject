package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tiquartet.CommonModule.util.CreditChange;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.service.CreditDataHelper;
import tiquartet.ServerModule.po.CreditPO;

/**
 * 对credit数据库的操作.
 * @author Teki
 */
public class CreditDataSqlHelper implements CreditDataHelper{
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	/**
	 * 向credit数据库中插入一条记录.
	 * @return
	 */
	@Override
	public ResultMessage insert(CreditPO creditItem) {
		Connection conn = Connect.getConn();
	    String sql = "insert into credit(changeType,change,balance,orderId,creditRecordId) values(?,?,?,?,null)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1,creditItem.getchangeType().ordinal());
	        pstmt.setDouble(2, creditItem.getchange());
	        pstmt.setDouble(3,creditItem.getbalance());
	        pstmt.setLong(4, creditItem.getorderId());
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
	 * 根据userId从数据库得到用户的信用记录.
	 * @return
	 */
	@Override
	public List<CreditPO> getRecord(int userID) {
		Connection conn = Connect.getConn();
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
	        	int creditRecordId=rs.getInt(5);
	        	CreditPO creditpo = new CreditPO(CreditChange.values()[changeType],change,balance,orderId,creditRecordId);
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
