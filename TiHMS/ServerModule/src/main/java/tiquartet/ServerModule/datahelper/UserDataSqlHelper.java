package tiquartet.ServerModule.datahelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.service.UserDataHelper;
import tiquartet.ServerModule.po.UserPO;

public class UserDataSqlHelper implements UserDataHelper{

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
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	/**
	 * 验证用户是否存在
	 * @param 
	 */
	public ResultMessage userExist (String username){
		Connection conn = getConn();
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
	 * 验证密码是否正确
	 * @param 
	 */
	public ResultMessage checkPassword (String username, String password){
		Connection conn = getConn();
	    String sql = "select * from students where username ="+username;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        String name=rs.getString(1);
	        String pass=rs.getString(3);
	        if(username==name&&password==pass)
	        	return success;
	        else
	        	return fail;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return fail;
	    }
	}
	
	/**
	 * 新增用户
	 * @param 
	 */
	public ResultMessage insert (UserPO user){
		Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into user(userId,userName,password,userType,realName,credit,birthday,memberRank,isMember,company,hotelId) values(?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setInt(1, user.getuserId());
	        pstmt.setString(2, user.getuserName());
	        pstmt.setString(3, user.getpassword());
	        pstmt.setString(4, user.getuserType());
	        pstmt.setString(5, user.getrealName());
	        pstmt.setInt(6, user.getcredit());
	        pstmt.setDate(7, user.getbirthday());
	        pstmt.setInt(8, user.getmemberRank());
	        pstmt.setBoolean(9, user.getisMember());  
	        pstmt.setString(10, user.getcompany());  
	        pstmt.setInt(11, user.gethotelId());  
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return fail;
	    } 	
	    return success;
	}
	
	/**
	 * @return	从数据库中读取用户数据
	 */
	public Map<Integer, UserPO> getUser(){
		Connection conn = getConn();
		Map<Integer, UserPO> map = new HashMap<Integer, UserPO>();
		String sql="select * from user";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	int userId=rs.getInt(1);
	        	String userName=rs.getString(2);
			    String password=rs.getString(3);
			    String userType=rs.getString(4);
			    String realName=rs.getString(5);
			    int credit=rs.getInt(6);
			    Date birthday=rs.getDate(7);
			    int memberRank=rs.getInt(8);
			    boolean isMember=rs.getBoolean(9);
			    String company=rs.getString(10);
			    int hotelId=rs.getInt(11);
			    UserPO userPO=new UserPO(userId,userName,password,userType,realName,credit,birthday,memberRank,isMember,company,hotelId);
			    map.put(userId,userPO);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 向数据文件中写入用户数据
	 * @param list
	 */
	public ResultMessage update (UserPO userPO){
		Connection conn = getConn();
	    String sql = "update user set userName='" + userPO.getuserName() +
	    		"set password='" + userPO.getpassword() +
	    		"set userType='" + userPO.getuserType() +
	    		"set realName='" + userPO.getrealName() +
	    		"set credit='" + userPO.getcredit() +
	    		"set birthday='" + userPO.getbirthday() +
	    		"set memberRank='" + userPO.getmemberRank() +
	    		"set isMember='" + userPO.getisMember() +
	    		"set company='" + userPO.getcompany() +
	    		"set hotelId='" + userPO.gethotelId() +
	            "' where userId='" + userPO.getuserId() + "'";
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
	 *搜索用户
	 * @param 
	 */
	public List<UserPO> searchUser (String username, String realName){
		Connection conn = getConn();
		List<UserPO> users=new ArrayList<UserPO>();
	    String sql = "select * from students where realName =" + realName +" where userName = " + username;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	int userId=rs.getInt(1);
	        	String userName=rs.getString(2);
			    String password=rs.getString(3);
			    String userType=rs.getString(4);
			    int credit=rs.getInt(6);
			    Date birthday=rs.getDate(7);
			    int memberRank=rs.getInt(8);
			    boolean isMember=rs.getBoolean(9);
			    String company=rs.getString(10);
			    int hotelId=rs.getInt(11);
			    UserPO userpo=new UserPO(userId,userName,password,userType,realName,credit,birthday,memberRank,isMember,company,hotelId);
			    users.add(userpo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return users;
	}
	
	/**
	 * 得到信用记录
	 * @param 
	 */
	public ResultMessage getCreditBalance (int userID){
		
		return success;
	}
	
	/**
	 * 增加信用记录
	 * @param 
	 */
	public ResultMessage addCredit (int userID, double addition){
		Connection conn = getConn();
	    String sql = "select * from students where userId =ewwrz	 " + userID;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        double credit = rs.getInt(6) + (int) addition;
	        String sqll = "update user set credit='" + credit + " where userId = " + rs.getString(1);
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

	@Override
	public ResultMessage update(Map<Integer, UserPO> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
	

