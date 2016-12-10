package tiquartet.ServerModule.po;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import tiquartet.CommonModule.vo.DistrictVO;

/**
 * 包含城市、商圈的编号和名称的完整列表.
 * @author greatlyr
 *
 */
public class DistrictPO {
	//Key是cityID，Value是城市名称的HashMap
	public HashMap<Integer, String> cityMap;
	//Key是districtID，Value是商圈名称的HashMap
	public HashMap<Integer, String> districtMap;
	
	/**
	 * 转换为相应的值对象.
	 * @return
	 */
	public DistrictVO toDistrictVO() {
		return new DistrictVO(cityMap,districtMap);
	}
	
	/**
	 * 使用两个HashMap来构造新的DistrictPO.
	 * @param cityMap
	 * @param districtMap
	 */
	public DistrictPO(HashMap<Integer, String> cityMap, HashMap<Integer, String> districtMap) {
		this.cityMap=cityMap;
		this.districtMap=districtMap;
	}
	
	public void setcity(HashMap<Integer, String> cityMap){
		this.cityMap=cityMap;
	}
	
	public HashMap<Integer, String> getcity(){
		return cityMap;
	}
	
	public String getcityAsString(){
		String city = "";
		java.util.Iterator<Entry<Integer, String>> i = cityMap.entrySet().iterator();  
		while(i.hasNext()){ 
			Map.Entry entry = (Map.Entry) i.next();  
		    Object key = entry.getKey();  
		    city = city + key.toString() + ",";		  
		}  
		city = city + ";" ;
        i = cityMap.entrySet().iterator();  
		while(i.hasNext()){ 
			Map.Entry entry = (Map.Entry) i.next();  
		    Object val = entry.getValue();
		    city = city + val.toString() + ",";		  
		}  
		return city;
	}
	
	public void setdistrict(HashMap<Integer, String> districtMap){
		this.districtMap=districtMap;
	}
	
	public HashMap<Integer, String> getdistrict(){
		return districtMap;
	}
	
	public String getdistrictAsString(){
		String district = "";
		java.util.Iterator<Entry<Integer, String>> i = districtMap.entrySet().iterator();  
		while(i.hasNext()){ 
			Map.Entry entry = (Map.Entry) i.next();  
		    Object key = entry.getKey();  
		    district = district + key.toString() + ",";		  
		}  
		
		district = district.substring(0,district.length()-1) + ";" ;
        i = cityMap.entrySet().iterator();  
		while(i.hasNext()){ 
			Map.Entry entry = (Map.Entry) i.next();  
		    Object val = entry.getValue();
		    district =district + val.toString() + ",";		  
		}  
		district=district.substring(0,district.length()-1);
		return district;
	}
}
