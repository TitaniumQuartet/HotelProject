package tiquartet.ServerModule.dataservice.creditdataservice;

import java.util.List;

import tiquartet.ServerModule.po.CreditPO;

public interface CreditDataService {

	public ResultMessage insert (CreditPO creditItem);
	public List<CreditPO> getRecord (int userID);

}
