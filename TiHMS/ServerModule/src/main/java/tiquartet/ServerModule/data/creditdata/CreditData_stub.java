package tiquartet.ServerModule.data.creditdata;

import java.util.*;

import tiquartet.ServerModule.dataservice.creditdataservice.*;
import tiquartet.ServerModule.po.*;

public class CreditData_stub implements CreditDataService{
	public List<CreditPO> getRecord(long userID){
		System.out.println("yes");
		return new ArrayList<CreditPO>();
	}

}
