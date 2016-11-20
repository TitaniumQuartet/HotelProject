package tiquartet.ServerModule.datahelper.service;

import java.util.List;
import tiquartet.ServerModule.po.StrategyPO;

public interface StrategyDataHelper {

	/**
	 * 从数据文件中读取用户数据
	 * @return	
	 */
	public List<StrategyPO> searchByHotel (int hotelID);
	
	/**
	 * 增加策略
	 * @param 
	 */
	public void insert (StrategyPO strategy);
	
	/**
	 * 删除策略
	 * @param 
	 */
	public void delete (int strategyID);
	
	/**
	 * 更新策略
	 * @param 
	 */
	public void update (StrategyPO strategy);

}
