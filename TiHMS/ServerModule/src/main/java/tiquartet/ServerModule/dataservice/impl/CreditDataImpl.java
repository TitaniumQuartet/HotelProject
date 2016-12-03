package tiquartet.ServerModule.dataservice.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.INTERNAL;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.CreditDataHelper;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.OrderDataHelper;
import tiquartet.ServerModule.dataservice.creditdataservice.CreditDataService;
import tiquartet.ServerModule.po.CreditPO;
import tiquartet.ServerModule.po.OrderPO;

public class CreditDataImpl implements CreditDataService{
	
	private CreditDataHelper creditDataHelper;
	
	private DataFactoryService dataFactory;

	private static CreditDataImpl creditDataServiceImpl;
	
	
	public static CreditDataImpl getInstance(){
		if(creditDataServiceImpl == null){
			creditDataServiceImpl = new CreditDataImpl();
		}
		return creditDataServiceImpl;
	}
	
	public CreditDataImpl(){
		if(dataFactory == null){
			dataFactory = new DataFactory();
			creditDataHelper = dataFactory.getCreditDataHelper();
		}
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	
	public ResultMessage insert(CreditPO creditItem) {
		return creditDataHelper.insert(creditItem);
	}

	@Override
	public List<CreditPO> getRecord(int userID) {
		return creditDataHelper.getRecord(userID);
	}

}
