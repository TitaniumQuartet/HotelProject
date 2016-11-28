package tiquartet.ServerModule.po;

import java.util.HashMap;

import tiquartet.CommonModule.vo.DistrictVO;

/**
 * 包含城市、商圈的编号和名称的完整列表.
 * @author greatlyr
 *
 */
public class DistrictPO {
	//Key是cityID，Value是城市名称的HashMap
	public HashMap<Integer, String> cityMap;
	//Key是districtID，Value是商圈名称的HashMap
	public HashMap<Integer, String> districtMap;
	
	/**
	 * 转换为相应的值对象.
	 * @return
	 */
	public DistrictVO toDistrictVO() {
		return new DistrictVO(cityMap,districtMap);
	}
	
	/**
	 * 使用两个HashMap来构造新的DistrictPO.
	 * @param cityMap
	 * @param districtMap
	 */
	public DistrictPO(HashMap<Integer, String> cityMap, HashMap<Integer, String> districtMap) {
		this.cityMap=cityMap;
		this.districtMap=districtMap;
	}
}
