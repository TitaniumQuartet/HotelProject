package tiquartet.CommonModule.vo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 包含城市、商圈的编号和名称的完整列表.
 * @author greatlyr
 *
 */
public class DistrictVO implements Serializable{
	//Key是cityID，Value是城市名称的HashMap
	public HashMap<Integer, String> cityMap;
	//Key是districtID，Value是商圈名称的HashMap
	public HashMap<Integer, String> districtMap;
	
	/**
	 * 使用两个HashMap来构造新的DistrictVO.
	 * @param cityMap
	 * @param districtMap
	 */
	public DistrictVO(HashMap<Integer, String> cityMap, HashMap<Integer, String> districtMap) {
		this.cityMap=cityMap;
		this.districtMap=districtMap;
	}
}
