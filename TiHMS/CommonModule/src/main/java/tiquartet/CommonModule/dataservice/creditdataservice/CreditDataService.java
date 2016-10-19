package tiquartet.CommonModule.dataservice.creditdataservice;

import java.util.List;

import tiquartet.CommonModule.po.CreditPO;

public interface CreditDataService {
	public List<CreditPO> getRecord(long userID);

}
