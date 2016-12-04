package tiquartet.ServerModule.datahelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.util.UserType;
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
	        Class.forName(driver); //classLoader,鍔犺浇瀵瑰簲椹卞姩
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
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
	 * 楠岃瘉鐢ㄦ埛鏄惁瀛樺湪
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
	 * 楠岃瘉瀵嗙爜鏄惁姝ｇ‘
	 * @param 
	 */
	public UserPO checkPassword (String username, String password){
		Connection conn = getConn();
	    String sql = "select * from students where username ="+username;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        String name=rs.getString(1);
	        String pass=rs.getString(3);
	        if(username==name&&password==pass){
	        	UserPO userpo=createuserpo(rs);
	        	return userpo;
	        }else
	        	return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * 鏂板鐢ㄦ埛
	 * @param 
	 */
	public ResultMessage insert (UserPO user){
		Connection conn = getConn();
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
	 * 鍚戞暟鎹枃浠朵腑鍐欏叆鐢ㄦ埛鏁版嵁
	 * @param list
	 */
	public ResultMessage update (UserPO userPO){
		Connection conn = getConn();
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
	 *鎼滅储鐢ㄦ埛
	 * @param 
	 */
	public List<UserPO> searchUser (String username, String realName, UserType type){
		Connection conn = getConn();
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
	 * 寰楀埌淇＄敤璁板綍
	 * @param 
	 */
	public ResultMessage getCreditBalance (int userID){
		Connection conn = getConn();
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
	 * 澧炲姞淇＄敤璁板綍
	 * @param 
	 */
	public ResultMessage addCredit (int userID, double addition){
		Connection conn = getConn();
	    String sql = "select * from students where userId = " + userID;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        double credit = rs.getDouble(6) + addition;
	        String sqll = "update user set credit=" + credit + "' where userId = " + rs.getString(1)+"'";
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
	
	public UserPO accurateSearch (String username){
		Connection conn = getConn();
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


	@Override
	public UserPO getUser(int userID) {
		Connection conn = getConn();
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
	
	@Override
	public List<UserPO> hotelStaffList(int cityID, int distrcitID) {
		Connection conn = getConn();
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

	@Override
	public List<UserPO> marketerList() {
		Connection conn = getConn();
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
	

