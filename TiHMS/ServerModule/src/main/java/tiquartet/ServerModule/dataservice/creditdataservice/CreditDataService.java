package tiquartet.ServerModule.dataservice.creditdataservice;

import java.util.List;

import tiquartet.ServerModule.po.CreditPO;

public interface CreditDataService {
	public List<CreditPO> getRecord(long userID);

}
