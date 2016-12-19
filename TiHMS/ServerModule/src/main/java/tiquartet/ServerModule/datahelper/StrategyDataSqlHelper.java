package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.StrategyType;
import tiquartet.ServerModule.datahelper.service.StrategyDataHelper;
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
	public double[] transform(String member){
		String[] memberto=member.split(",");
		double[] mem=new double[memberto.length];
		for(int i=0;i<memberto.length;i++){
			mem[i]=Double.valueOf(memberto[i]);
		}
		return mem;
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
		String sql="select * from strategy where hotelId =" + hotelID + "OR where hotelId = -1";
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
	        	String memberT=rs.getString(6);
	        	String memberD=rs.getString(7);
	        	String startTime=rs.getString(8);
	        	String endTime=rs.getString(9);
	        	StrategyType strategyType=StrategyType.values()[rs.getInt(10)];
	        	int numOfRoom=rs.getInt(11);
	        	double[] memberThreShold=transform(memberT);
	        	double[] memberDiscount=transform(memberD);
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
	    String sql = "insert into strategy(strategyId,strategyIntro,hotelId,discount,circleId,memberThreShold,memberDiscount,startTime,endTime,strategyType,numOfRoom) values(null,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, strategy.getstrategyIntro());
	        pstmt.setInt(2,strategy.gethotelId());
	        pstmt.setDouble(3, strategy.getdiscount());
	        pstmt.setInt(4,strategy.getCircelID());
	        pstmt.setString(5, strategy.getMemberThresholdAsString());
	        pstmt.setString(6, strategy.getMemberDiscountAsString());
	        pstmt.setString(7, strategy.getStartTime());
	        pstmt.setString(8, strategy.getEndTime());
	        pstmt.setInt(9,strategy.getStrategyType().ordinal());
	        pstmt.setInt(10,strategy.getnumOfRoom());
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
	    		"', hotelId=" + strategy.gethotelId() +
	    		", discount=" + strategy.getdiscount() +
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
