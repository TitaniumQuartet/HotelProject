package tiquartet.CommonModule.vo;

import java.io.Serializable;
import java.sql.Date;

import tiquartet.CommonModule.util.MemberType;

public class MemberVO implements Serializable{
	//�û����
	public int userID;
	//��Ա����
	public MemberType memberType;
	//��ʵ����
	public String realName;
	//����
	public Date birthday;
	//��˾����
	public String companyName;
	//��Ա�ȼ�
	public int memberRank;
}
