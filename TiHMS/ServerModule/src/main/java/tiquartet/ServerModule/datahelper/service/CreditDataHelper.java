package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;

import tiquartet.ServerModule.po.CreditPO;

public interface CreditDataHelper {
	
	/**
	 * 增加信用记录
	 * @param 
	 */
	public void insert (CreditPO creditItem);
	
	/**
	 * 获得信用值记录
	 * @param 
	 */
	public Map<Integer,CreditPO> getRecord (int userId);

}
