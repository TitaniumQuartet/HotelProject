package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import java.util.Map;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.CreditPO;

public interface CreditDataHelper {
	
	/**
	 * 增加信用记录
	 * @param 
	 */
	public ResultMessage insert (CreditPO creditItem);
	
	/**
	 * 获得信用值记录
	 * @param 
	 */
	public List<CreditPO> getRecord (int userID);

}
