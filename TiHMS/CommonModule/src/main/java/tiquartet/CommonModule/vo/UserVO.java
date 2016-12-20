package tiquartet.CommonModule.vo;

import java.io.Serializable;

import tiquartet.CommonModule.util.MemberType;
import tiquartet.CommonModule.util.UserType;

/**
 * 包含用户信息的值对象
 * @author greatlyr
 *
 */
public class UserVO implements Serializable{
	//用户编号
    public int userID;
    //用户名
    public String userName;
    //用户类型
    public UserType userType;
    //密码
    public String password;
    //真实姓名
    public String realName;
    //联系方式
    public String phone;
    //会员类型
    public MemberType memberType;
    //当前信用值
    public double credit;
    //生日
    public String birthday;
    //是否会员
    public boolean isMember;
    //会员等级
    public int memberLevel;
    //公司名称
    public String company;
    //酒店编号
    public int hotelID;
    //是否已登录
    public boolean login;
    
    /**
     * 用注册信息建立简单的用户信息值对象.
     * @param username
     * @param password
     * @param realName
     * @return
     */
    public static UserVO getClientInstance(String username, String password, String realName){
    	UserVO userVO = new UserVO();
    	userVO.userName = username;
    	userVO.password = password;
    	userVO.realName = realName;
    	userVO.userType = UserType.客户;
    	userVO.login = false;
    	return userVO;
    }
}
