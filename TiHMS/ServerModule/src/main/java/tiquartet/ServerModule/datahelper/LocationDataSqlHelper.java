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

	/**
	 * 返回当前的城市、商圈的编号及名称对应关系.
	 * @return
	 */
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
	        	for(int i=0;i<cityName.length;i++){
	        		cityMap.put(Integer.valueOf(cityId[i]), cityName[i]);
	        	}
	        	String[] districtId=districts[0].split(",");
	        	String[] districtName=districts[1].split(",");
	        	for(int i=0;i<districtName.length;i++){
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

	/**
	 * 更新商圈
	 * @return
	 */
	@Override
	public ResultMessage update(DistrictPO district) {
		Connection conn = Connect.getConn();
	    
	    String cityMap=district.getcityAsString();
	    String districtMap=district.getdistrictAsString();
	    String sql = "update location set city =" + cityMap +"set district ="+districtMap + "where Id ="+1;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
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
