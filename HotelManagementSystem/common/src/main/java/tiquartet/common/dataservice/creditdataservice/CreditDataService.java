package tiquartet.common.dataservice.creditdataservice;

import java.util.List;

public interface CreditDataService {
	public List<CreditRecordPO> getRecord(long userID);

}
