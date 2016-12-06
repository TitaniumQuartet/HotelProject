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
import tiquartet.CommonModule.util.StrategyType;
import tiquartet.ServerModule.datahelper.service.StrategyDataHelper;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.StrategyPO;

/**
 * 对strategy数据库的操作.
 * @author Teki
 */
public class StrategyDataSqlHelper implements StrategyDataHelper{
	
	/**
	 * 处理存储在数据库中的会员等级判断标准和折扣信息.
	 * @return
	 */
	public double[][] transform(String member){
		String[] str=member.split(";");//等级判断标准和折扣信息合并成一个字符串存储在数据库中，以分号隔开
		String[] memberThreShold=str[0].split(",");
		String[] memberDiscount=str[1].split(",");
		double[][] mem=new double[2][str.length];
		for(int i=0;i<str.length;i++){
			mem[0][i]=Double.valueOf(memberThreShold[i]);
			mem[1][i]=Double.valueOf(memberDiscount[i]);
		}
		return null;
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	/**
	 * 根据酒店ID搜索策略.
	 * @return
	 */
	@Override
	public List<StrategyPO> searchByHotel(int hotelID) {
		Connection conn = Connect.getConn();
		List<StrategyPO> strategy = new ArrayList<StrategyPO>();
		String sql="select * from credit where hotelId =" + hotelID + "OR where hotelId = '-1'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	int strategyId=rs.getInt(1);
	        	String strategyIntro=rs.getString(2);
	        	int hotelId=rs.getInt(3);
	        	double discount=rs.getDouble(4);
	        	int circleId=rs.getInt(5);
	        	String member=rs.getString(6);
	        	String startTime=rs.getString(7);
	        	String endTime=rs.getString(8);
	        	StrategyType strategyType=StrategyType.values()[rs.getInt(9)];
	        	int numOfRoom=rs.getInt(10);
	        	double[] memberThreShold=transform(member)[0];
	        	double[] memberDiscount=transform(member)[1];
	        	StrategyPO strategyPO=new StrategyPO(strategyId,strategyIntro,hotelId,discount,circleId, memberThreShold,memberDiscount,startTime,endTime,strategyType,numOfRoom);
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

	/**
	 * 在数据库中添加一条新的策略.
	 * @return
	 */
	@Override
	public ResultMessage insert(StrategyPO strategy) {
		Connection conn = Connect.getConn();
	    String sql = "insert into strategy(strategyId,strategyIntro,hotelId,discount) values(?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1, strategy.getstrategyId());
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

	/**
	 * 根据策略ID在数据库中删除相关策略.
	 * @return
	 */
	@Override
	public ResultMessage delete(int strategyID) {
		Connection conn = Connect.getConn();
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

	/**
	 * 更新一条策略信息.
	 * @return
	 */
	@Override
	public ResultMessage update(StrategyPO strategy) {
		Connection conn = Connect.getConn();
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
