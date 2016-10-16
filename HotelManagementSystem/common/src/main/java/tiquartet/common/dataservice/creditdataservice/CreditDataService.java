package tiquartet.common.dataservice.creditdataservice;

import java.util.List;
import tiquartet.common.po.creditpo.CreditPO;

public interface CreditDataService {
	public List<CreditPO> getRecord(long userID);

}
