package tiquartet.ServerModule.datahelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.ServerModule.datahelper.service.LocationDataHelper;
import tiquartet.ServerModule.po.DistrictPO;

public class LocationDataSqlHelper implements LocationDataHelper{

	@Override
	public DistrictPO renewDistrict() {
		Connection conn = Connect.getConn();
		String sql="select * from district ";
		PreparedStatement pstmt;
		HashMap<Integer, String> cityMap=new HashMap<Integer, String>();
		HashMap<Integer, String> districtMap=new HashMap<Integer, String>();
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        if(rs.next()){
	        	String city=rs.getString(1);
	        	String distrcit=rs.getString(2);
	        	String[] citys=city.split(";");
	        	String[] districts=distrcit.split(";");
	        	
	        	String[] cityId=citys[0].split(",");
	        	String[] cityName=citys[1].split(",");
	        	for(int i=0;i<citys.length;i++){
	        		cityMap.put(Integer.valueOf(cityId[i]), cityName[i]);
	        	}
	        	String[] districtId=districts[0].split(",");
	        	String[] districtName=districts[1].split(",");
	        	for(int i=0;i<districts.length;i++){
	        		districtMap.put(Integer.valueOf(districtId[i]), districtName[i]);
	        	}	        	
			}
	        DistrictPO districtPO=new DistrictPO(cityMap, districtMap);				
			pstmt.close();
	        conn.close();
			return districtPO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultMessage insert(DistrictPO district) {
		Connection conn = Connect.getConn();
	    String sql = "insert into district(city,district) values(?,?)";
	    String cityMap=district.getcityAsString();
	    String districtMap=district.getdistrictAsString();
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, cityMap);
	        pstmt.setString(2,districtMap);
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ResultMessage(false);
	    } 	
	    return new ResultMessage(true);
	}

}
