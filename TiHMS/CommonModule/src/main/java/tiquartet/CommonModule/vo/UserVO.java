package tiquartet.CommonModule.vo;


/**
 * 包含用户信息的值对象
 * @author greatlyr
 *
 */
public class UserVO {
	//用户编号
    public int userID;
    //用户名
    public String userName;
    //用户类型
    public String userType;
    //密码
    public String password;
    //真实姓名
    public String realName;
    //是否会员
    public boolean isMember;
    //当前信用值
    public double credit;
    //生日
    public String birthday;
    //会员等级
    public int memberLevel;
    //公司名称
    public String company;
    //酒店编号
    public int hotelID;
    
    /**
     * 用注册信息建立简单的用户信息值对象.
     * @param username
     * @param password
     * @param realName
     * @return
     */
    public UserVO getClientInstance(String username, String password, String realName){
    	UserVO userVO = new UserVO();
    	userVO.userName = username;
    	userVO.password = password;
    	userVO.realName = realName;
    	return userVO;
    }
}
