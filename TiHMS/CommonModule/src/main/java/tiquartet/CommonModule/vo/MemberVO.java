package tiquartet.CommonModule.vo;

import java.io.Serializable;
import java.sql.Date;

import tiquartet.CommonModule.util.MemberType;

public class MemberVO implements Serializable{
	//�û����
	public int userID = -1;
	//��Ա����
	public MemberType memberType;
	//��ʵ����
	public String realName = "";
	//����
	public String birthday = "";
	//��˾����
	public String companyName = "";
	//��Ա�ȼ�
	public int memberRank = -1;
}
