package tiquartet.ServerModule.datahelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserType;
import tiquartet.ServerModule.datahelper.service.UserDataHelper;
import tiquartet.ServerModule.po.UserPO;

/**
 * 对user数据库的操作.
 * @author Teki
 */
public class UserDataSqlHelper implements UserDataHelper{
	
	/**
	 * 通过数据库的数据生成UserPO.
	 * @return
	 */
	public UserPO createuserpo(ResultSet rs){
		try{
			int userId=rs.getInt(1);
        	String userName=rs.getString(2);
		    String password=rs.getString(3);
		    UserType userType=UserType.values()[rs.getInt(4)];
		    String realName=rs.getString(5);
		    double credit=rs.getDouble(6);
		    String birthday=rs.getString(7);
		    int memberRank=rs.getInt(8);
		    boolean isMember=rs.getBoolean(9);
		    String company=rs.getString(10);
		    int hotelId=rs.getInt(11);
		    boolean login=rs.getBoolean(12);
		    UserPO userpo=new UserPO(userId,userName,password,userType,realName,credit,birthday,memberRank,isMember,company,hotelId,login);
	    	return userpo;
		}catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
		
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	/**
	 * 验证用户名是否已存在.
	 * @return
	 */
	@Override
	public ResultMessage userExist (String username){
		Connection conn = Connect.getConn();
	    String sql = "select * from students where username ="+username;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        String name=rs.getString(1);
	        if(username==name)
	        	return success;
	        else
	        	return fail;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return fail;
	    }
	}
	
	/**
	 * 验证密码是否正确.
	 * @return
	 */
	@Override
	public ResultMessage checkPassword (String username, String password){
		Connection conn = Connect.getConn();
	    String sql = "select * from students where username ="+username;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        String name=rs.getString(1);
	        String pass=rs.getString(3);
	        if(username==name&&password==pass){
	        	return success;
	        }else
	        	return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * 向user数据表添加一条记录.
	 * @return
	 */
	@Override
	public ResultMessage insert (UserPO user){
		Connection conn = Connect.getConn();
	    String sql = "insert into user(userId,userName,password,userType,realName,credit,birthday,memberRank,isMember,company,hotelId,login) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1, user.getuserId());
	        pstmt.setString(2, user.getuserName());
	        pstmt.setString(3, user.getpassword());
	        pstmt.setInt(4, user.getTypeAsInt());
	        pstmt.setString(5, user.getrealName());
	        pstmt.setDouble(6, user.getcredit());
	        pstmt.setString(7, user.getbirthday());
	        pstmt.setInt(8, user.getmemberRank());
	        pstmt.setBoolean(9, user.getisMember());  
	        pstmt.setString(10, user.getcompany());  
	        pstmt.setInt(11, user.gethotelId());  
	        pstmt.setBoolean(12, user.getLogin());  
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
	 * 更新user数据表里的一条记录.
	 * @return
	 */
	@Override
	public ResultMessage update (UserPO userPO){
		Connection conn = Connect.getConn();
	    String sql = "update user set userName='" + userPO.getuserName() +
	    		"set password='" + userPO.getpassword() +
	    		"set userType='" + userPO.getTypeAsInt() +
	    		"set realName='" + userPO.getrealName() +
	    		"set credit='" + userPO.getcredit() +
	    		"set birthday='" + userPO.getbirthday() +
	    		"set memberRank='" + userPO.getmemberRank() +
	    		"set isMember='" + userPO.getisMember() +
	    		"set company='" + userPO.getcompany() +
	    		"set hotelId='" + userPO.gethotelId() +
	    		"set login='" + userPO.getLogin() +
	            " where userId='" + userPO.getuserId();
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
	 * 根据username和realName的部分信息以及用户类型搜索相关用户.
	 * @return
	 */
	@Override
	public List<UserPO> searchUser (String username, String realName, UserType type){
		Connection conn = Connect.getConn();
		List<UserPO> users=new ArrayList<UserPO>();
	    String sql = "select * from students where username REGEXP '" + realName +"' AND userName REGEXP '" + username +"' AND userType =";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	UserPO userpo=createuserpo(rs);
	        	users.add(userpo);
	        } 
	        pstmt.close();
			conn.close();
		    return users;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	    
	}
	
	/**
	 * 得到用户的信用值.
	 * @return
	 */
	@Override
	public ResultMessage getCreditBalance (int userID){
		Connection conn = Connect.getConn();
	    String sql = "select * from students where userId = " + userID;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        double credit = rs.getDouble(6);
	        ResultMessage result=new ResultMessage(true,null,String.valueOf(credit));      
	        pstmt.close();
	        conn.close();
	        return result;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return fail;
	    }
	}
	
	/**
	 * 更新用户的信用值.
	 * @return
	 */
	@Override
	public ResultMessage addCredit (int userID, double addition){
		Connection conn = Connect.getConn();
	    String sql = "select * from students where userId = " + userID;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        double credit = rs.getDouble(6) + addition;
	        String sqll = "update user set credit=" + credit + " where userId = " + rs.getString(1);
	        pstmt = (PreparedStatement) conn.prepareStatement(sqll);
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
	 * 根据用户名搜索用户.
	 * @return
	 */
	@Override
	public UserPO accurateSearch (String username){
		Connection conn = Connect.getConn();
		String sql="select * from user where userName ="+username;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        UserPO userpo=createuserpo(rs);
	        return userpo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据用户ID搜索用户.
	 * @return
	 */
	@Override
	public UserPO getUser(int userID) {
		Connection conn = Connect.getConn();
		String sql="select * from user where userId ="+userID;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        UserPO userpo=createuserpo(rs);
	        return userpo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 通过城市和商圈得到酒店工作人员的列表.
	 * @return
	 */
	@Override
	public List<UserPO> hotelStaffList(int cityID, int distrcitID) {
		Connection conn = Connect.getConn();
		List<UserPO> users=new ArrayList<UserPO>(); 
		String sql;
		if(distrcitID!=-1)
	        sql = "SELECT * FROM user where user.hotelId = hotel.hotelId AND hotel.districtId = " + distrcitID;
		else {
			sql = "SELECT * FROM user where user.hotelId = hotel.hotelId AND hotel.cityId = " + cityID;
		}
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	UserPO userpo=createuserpo(rs);
	        	users.add(userpo);
	        } 
		    return users;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * 得到网站管理人员的列表.
	 * @return
	 */
	@Override
	public List<UserPO> marketerList() {
		Connection conn = Connect.getConn();
		List<UserPO> users=new ArrayList<UserPO>(); 
	    String sql = "SELECT * FROM user where userType =" + "2";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	UserPO userpo=createuserpo(rs);
			    users.add(userpo);
	        } 
		    return users;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
}
	

