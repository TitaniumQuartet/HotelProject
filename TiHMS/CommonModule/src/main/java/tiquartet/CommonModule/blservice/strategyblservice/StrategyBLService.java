package tiquartet.CommonModule.blservice.strategyblservice;

import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;


import tiquartet.CommonModule.vo.StrategyVO;

public interface StrategyBLService {
	//添加策略
	public ResultMessage addStrategy(StrategyVO s);
	//删除策略
	public ResultMessage deleteStrategy(int strategyID);
	//获得酒店策略
	public List<StrategyVO> getStrategy(int hotelID);
	//改变策略
	public ResultMessage changeStrategy(StrategyVO s);
	
}
