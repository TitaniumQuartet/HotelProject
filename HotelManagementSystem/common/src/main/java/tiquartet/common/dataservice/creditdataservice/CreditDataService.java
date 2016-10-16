package tiquartet.common.dataservice.creditdataservice;

import java.util.List;

import tiquartet.common.po.CreditPO;

public interface CreditDataService {
	public List<CreditPO> getRecord(long userID);

}
