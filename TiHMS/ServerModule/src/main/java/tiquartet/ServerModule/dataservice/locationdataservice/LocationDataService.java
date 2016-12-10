package tiquartet.ServerModule.dataservice.locationdataservice;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.DistrictPO;

/**
 * 提供更新城市和商圈数据功能的接口.
 * @author greatlyr
 *
 */
public interface LocationDataService {
	
	/**
	 * 返回当前的城市、商圈的编号及名称对应关系.
	 * @return
	 */
	public DistrictPO renewDistrict ();
	
	/**
	 * 新增商圈
	 * @return
	 */
	public ResultMessage insert(DistrictPO district);

}
