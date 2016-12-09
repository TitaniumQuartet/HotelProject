package tiquartet.CommonModule.vo;

import java.io.Serializable;
import java.sql.Date;

import tiquartet.CommonModule.util.MemberType;

public class MemberVO implements Serializable{
	//用户编号
	public int userID;
	//会员类型
	public MemberType memberType;
	//真实姓名
	public String realName;
	//生日
	public Date birthday;
	//公司名称
	public String companyName;
	//会员等级
	public int memberRank;
}
