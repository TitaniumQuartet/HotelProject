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
import tiquartet.ServerModule.datahelper.service.StrategyDataHelper;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.StrategyPO;

public class StrategyDataSqlHelper implements StrategyDataHelper{

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
	public List<StrategyPO> searchByHotel(int hotelID) {
		Connection conn = getConn();
		List<StrategyPO> strategy = new ArrayList<StrategyPO>();
		String sql="select * from credit where hotelId =" + hotelID + "OR where hotelId = '-1'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	long strategyId=rs.getInt(1);
	        	String strategyIntro=rs.getString(2);
	        	int hotelId=rs.getInt(3);
	        	double discount=rs.getDouble(4);
	        	StrategyPO strategyPO=new StrategyPO(strategyId,strategyIntro,hotelId,discount);
				strategy.add(strategyPO);
			}
			pstmt.close();
	        conn.close();
			return strategy;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultMessage insert(StrategyPO strategy) {
		Connection conn = getConn();
	    String sql = "insert into strategy(strategyId,strategyIntro,hotelId,discount) values(?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setLong(1, strategy.getstrategyId());
	        pstmt.setString(2, strategy.getstrategyIntro());
	        pstmt.setInt(3,strategy.gethotelId());
	        pstmt.setDouble(4, strategy.getdiscount());
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
	public ResultMessage delete(int strategyID) {
		Connection conn = getConn();
		String sql="DELETE FROM strategy where strategyId = " + strategyID;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
			return success;
		} catch (Exception e) {
			e.printStackTrace();
			return fail;
		}
	}

	@Override
	public ResultMessage update(StrategyPO strategy) {
		Connection conn = getConn();
	    String sql = "update strategy set strategyIntro='" + strategy.getstrategyIntro() +
	    		"set hotelId='" + strategy.gethotelId() +
	    		"set discount='" + strategy.getdiscount() +
	            " where strategyId = " + strategy.getstrategyId() ;
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

	

}
