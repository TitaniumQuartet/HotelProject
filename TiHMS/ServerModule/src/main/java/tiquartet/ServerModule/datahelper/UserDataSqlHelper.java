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
	    String sql = "select * from user where username ='" + username + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        String name=null;
	        if(rs.next())
	        	name=rs.getString(2);
	        pstmt.close();
	        conn.close();
	        if(username.equals(name))
	        	return success;
	        else
	        	return fail;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return fail;
	    }
	}
	
	/**
	 * 验证密码是否正确以及用户是否在其他地方已登陆.
	 * @return
	 */
	@Override
	public ResultMessage checkPassword (String username, String password){
		Connection conn = Connect.getConn();
	    String sql = "select * from user where username = '" + username + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        String name=null;
	        String pass=null;
	        boolean login=false;
	        if(rs.next()){
	        	name=rs.getString(2);
	        	pass=rs.getString(3);
	        	login=rs.getBoolean(12);
	        }
	        if(username.equals(name)&&password.equals(pass)&&login==false){//若登陆成功，修改login的值为真.
	        	String sqll="update user set login =" + 1 +
	        			" where username = '" + username + "'";
	        	pstmt = (PreparedStatement) conn.prepareStatement(sqll);
	 	        pstmt.executeUpdate();
	 	        pstmt.close();
		        conn.close();
	        	return success;
	        }else if(!password.equals(pass)){
	        	pstmt.close();
	            conn.close();
	        	return new ResultMessage(false,"密码不正确",null);
	        }else if(login==true){
	        	pstmt.close();
		        conn.close();
	        	return new ResultMessage(false,"用户在其他地方已登陆",null);
	        }else {
	        	pstmt.close();
		        conn.close();
				return fail;
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return fail;
	    }
	}
	
	/**
	 * 向user数据表添加一条记录.
	 * @return
	 */
	@Override
	public ResultMessage insert (UserPO user){
		Connection conn = Connect.getConn();
	    String sql = "insert into user(userId,userName,password,userType,realName,credit,birthday,memberRank,isMember,company,hotelId,login) values(null,?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, user.getuserName());
	        pstmt.setString(2, user.getpassword());
	        pstmt.setInt(3, user.getTypeAsInt());
	        pstmt.setString(4, user.getrealName());
	        pstmt.setDouble(5, user.getcredit());
	        pstmt.setString(6, user.getbirthday());
	        pstmt.setInt(7, user.getmemberRank());
	        pstmt.setBoolean(8, user.getisMember());  
	        pstmt.setString(9, user.getcompany());  
	        pstmt.setInt(10, user.gethotelId());  
	        pstmt.setBoolean(11, user.getLogin());  
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
	    		"', password='" + userPO.getpassword() +
	    		"', userType=" + userPO.getTypeAsInt() +
	    		", realName='" + userPO.getrealName() +
	    		"', credit=" + userPO.getcredit() +
	    		", birthday='" + userPO.getbirthday() +
	    		"', memberRank=" + userPO.getmemberRank() +
	    		", isMember=" + userPO.getisMember() +
	    		", company='" + userPO.getcompany() +
	    		"', hotelId=" + userPO.gethotelId() +
	    		", login=" + userPO.getLogin() +
	            " where userId='" + userPO.getuserId() + "'";
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
	    String sql = "select * from user where realName REGEXP '" + realName +"' AND userName REGEXP '" + username +"' AND userType ='" + type.ordinal() + "'";
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
	    String sql = "select * from user where userId = " + userID;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        double credit = 0;
	        if(rs.next())
	        	credit = rs.getDouble(6);
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
	 * 若是会员，判断等级是否有变.
	 * @return
	 */
	public int memberRank(double credit,int memberRank){
		int rank=-1;
		String level=null;
		if(memberRank!=-1){
			Connection conn = Connect.getConn();
			String sql = "select * from member";
			PreparedStatement pstmt;
			try {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);	
				ResultSet rs = pstmt.executeQuery();				
				if(rs.next()){
					level=rs.getString(1);
				}				
				pstmt.close();
				conn.close();
				} catch (SQLException e) {
					e.printStackTrace();	 
				}
			String[] memberThreShold=level.split(",");
			double[] memberlevel=new double[memberThreShold.length];//保存从每个等级的上限，最低等级无下限，最高等级无上限.
			for(int i=0;i<memberThreShold.length;i++){
				memberlevel[i]=Double.valueOf(memberThreShold[i]);
			}
			if(credit<=memberlevel[0]){
				rank=1;
     		}else if(credit>memberlevel[memberlevel.length-1]){
     			rank=memberlevel.length+1;     			
     		}else{
				for(int i=1;i<memberlevel.length;i++){
					if(credit<=memberlevel[i]&&credit>memberlevel[i-1]){
						rank=i+1;
						break;
				    }
			    }				
			}
	    }	  
		return rank;
	}
	
	/**
	 * 更新用户的信用值.
	 * @return
	 */
	@Override
	public ResultMessage addCredit (int userID, double addition){
		Connection conn = Connect.getConn();
	    String sql = "select * from user where userId = " + userID;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        double credit = 0;
	        int memberRank=0;
	        if(rs.next()){
	        	credit = rs.getDouble(6) + addition;
	        	memberRank=rs.getInt(8);
	        }
	        memberRank=memberRank(credit,memberRank);	        
	        String sqll = "update user set credit=" + credit + ",set memberRank="+memberRank+" where userId = " + rs.getInt(1);
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
		String sql="select * from user where userName ='"+username+"'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        UserPO userpo=new UserPO();
	        if(rs.next())
	        	userpo=createuserpo(rs);
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
	        UserPO userpo=new UserPO();
	        if(rs.next())
	        	userpo=createuserpo(rs);
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
	public List<UserPO> hotelStaffList(int cityID, int districtID) {//还未测试！！！！！！
		Connection conn = Connect.getConn();
		List<UserPO> users=new ArrayList<UserPO>(); 
		String sql;
		if(districtID!=-1)
	        sql = "SELECT * FROM user where user.hotelId = hotel.hotelId AND hotel.districtId = " + districtID;
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
	    String sql = "SELECT * FROM user where userType =" + 2;
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
	

