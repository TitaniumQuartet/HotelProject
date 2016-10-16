package tiquartet.stub_driver.dataservice.creditdata;

import java.util.*;
import tiquartet.common.dataservice.creditdataservice.*;
import tiquartet.common.po.*;

public class CreditData_stub implements CreditDataService{
	public List<CreditPO> getRecord(long userID){
		System.out.println("yes");
		return new ArrayList<CreditPO>();
	}

}
