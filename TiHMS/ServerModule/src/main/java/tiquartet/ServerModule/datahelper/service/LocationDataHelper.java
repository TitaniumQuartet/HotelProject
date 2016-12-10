package tiquartet.ServerModule.datahelper.service;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.po.DistrictPO;

public interface LocationDataHelper {

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
