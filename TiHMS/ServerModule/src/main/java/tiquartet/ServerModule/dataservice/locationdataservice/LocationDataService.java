package tiquartet.ServerModule.dataservice.locationdataservice;

import java.util.List;

import tiquartet.ServerModule.po.DistrictPO;
import tiquartet.ServerModule.po.HotelPO;
import tiquartet.ServerModule.po.HotelBriefPO;

public interface LocationDataService {
	
	public List<DistrictPO> renewDistrict ();
	public List<HotelPO> getHotel ();
	public List<HotelBriefPO> getHotelBrief();
	
}
