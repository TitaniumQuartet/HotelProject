package tiquartet.stub_driver.creditdata;
import tiquartet.common.dataservice.creditdataservice;

public class CreditData_stub implements CreditDataService{
	public CreditRecordPO getRecord(long userID){
		return new CreditRecordPO();
	}

}
