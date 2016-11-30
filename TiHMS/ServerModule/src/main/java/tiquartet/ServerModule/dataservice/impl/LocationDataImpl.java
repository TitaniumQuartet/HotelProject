package tiquartet.ServerModule.dataservice.impl;

import java.sql.Connection;
import java.util.Map;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.LocationDataHelper;
import tiquartet.ServerModule.datahelper.service.OrderDataHelper;
import tiquartet.ServerModule.dataservice.locationdataservice.LocationDataService;
import tiquartet.ServerModule.po.DistrictPO;
import tiquartet.ServerModule.po.OrderPO;

public class LocationDataImpl implements LocationDataService{

	
	
	private LocationDataHelper locationDataHelper;
	
	private DataFactoryService dataFactory;

	private static LocationDataImpl locationDataServiceImpl;
	
	private Connection con;
	
	public static LocationDataImpl getInstance(){
		if(locationDataServiceImpl == null){
			locationDataServiceImpl = new LocationDataImpl();
		}
		return locationDataServiceImpl;
	}
	
	public LocationDataImpl(){
		
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	@Override
	public DistrictPO renewDistrict() {
		// TODO Auto-generated method stub
		return null;
	}

}
