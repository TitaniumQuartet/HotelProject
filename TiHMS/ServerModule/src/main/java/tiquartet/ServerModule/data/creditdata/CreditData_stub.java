package tiquartet.ServerModule.data.creditdata;

import java.util.*;
import tiquartet.CommonModule.dataservice.creditdataservice.*;
import tiquartet.CommonModule.po.*;

public class CreditData_stub implements CreditDataService{
	public List<CreditPO> getRecord(long userID){
		System.out.println("yes");
		return new ArrayList<CreditPO>();
	}

}
