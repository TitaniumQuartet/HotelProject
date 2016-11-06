package tiquartet.ServerModule.bl.createorderbl;

import java.util.ArrayList;
import java.util.List;

import tiquartet.CommonModule.vo.OrderStrategyVO;

/**
 * 
 * @author Yolanda
 *
 */

public class StrategyList{
	
	StrategyListItem listItem = new StrategyListItem();
	
	public StrategyListItem getStrategy(long hotelID, long roomTypeID){
		return listItem;
	}
	
	public List<OrderStrategyVO> getList(){
		return new ArrayList<OrderStrategyVO>();
	}
}