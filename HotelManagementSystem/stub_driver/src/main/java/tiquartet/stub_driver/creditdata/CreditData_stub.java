package tiquartet.stub_driver.creditdata;
import java.util.List;
import tiquartet.common.dataservice.creditdataservice;

public class CreditData_stub implements CreditDataService{
	public List<CreditRecordPO> getRecord(long userID){
		System.out.println("yes");
		return new CreditRecordPO();
	}

}
