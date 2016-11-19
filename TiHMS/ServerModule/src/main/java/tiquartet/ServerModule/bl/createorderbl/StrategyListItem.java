package tiquartet.ServerModule.bl.createorderbl;
/**
 * 
 * @author Yolanda
 *
 */
import tiquartet.CommonModule.vo.OrderStrategyVO;

public class StrategyListItem{
	
	OrderStrategyVO vo = new OrderStrategyVO();
	public String intro="20% dicount";
	
	public OrderStrategyVO getOrderStrategyVO(){
		return vo;
	}
}