package tiquartet.ServerModule.dataservice.impl;


import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.datahelper.service.DataFactoryService;
import tiquartet.ServerModule.datahelper.service.LocationDataHelper;
import tiquartet.ServerModule.dataservice.locationdataservice.LocationDataService;
import tiquartet.ServerModule.po.DistrictPO;

public class LocationDataImpl implements LocationDataService{

	private LocationDataHelper locationDataHelper;
	
	private DataFactoryService dataFactory;

	private static LocationDataImpl locationDataServiceImpl;

	public static LocationDataImpl getInstance(){
		if(locationDataServiceImpl == null){
			locationDataServiceImpl = new LocationDataImpl();
		}
		return locationDataServiceImpl;
	}
	
	public LocationDataImpl(){
		if(dataFactory == null){
			dataFactory = new DataFactory();
			locationDataHelper = dataFactory.getLocationDataHelper();
		}
	}
	
	ResultMessage success=new ResultMessage(true);
	
	ResultMessage fail=new ResultMessage(false);
	@Override
	public DistrictPO renewDistrict() {
		return locationDataHelper.renewDistrict();
	}

	@Override
	public ResultMessage insert(DistrictPO district) {
		return locationDataHelper.insert(district);
	}

}
