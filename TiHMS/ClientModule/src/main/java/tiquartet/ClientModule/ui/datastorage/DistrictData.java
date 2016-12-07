package tiquartet.ClientModule.ui.datastorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.DistrictVO;

/**
 * 用于管理本地以二进制文件存储的DistrictVO.
 * @author greatlyr
 *
 */
public class DistrictData {
	
	private static DistrictVO districtVO = null;
	
	private static ArrayList<Integer> cityIDList;
	
	private static ArrayList<String> cityNameList;
	
	private static HashMap<Integer, ArrayList<Integer>> districtCityList;
	
	private static HashMap<Integer, ArrayList<String>> districtCityNameList;
	
	/**
	 * 初始化各个静态的列表实例
	 * 
	 */
	private static void setList(){
		cityIDList = new ArrayList<>();
		cityNameList = new ArrayList<>();
		districtCityList = new HashMap<>();
		districtCityNameList = new HashMap<>();
		for(int i:districtVO.cityMap.keySet()){
			districtCityList.put(i, new ArrayList<Integer>());
			districtCityNameList.put(i, new ArrayList<String>());
			cityIDList.add(i);
			cityNameList.add(districtVO.cityMap.get(i));
		}
		for(int i:districtVO.districtMap.keySet()){
			ArrayList<Integer> listID = districtCityList.get(i/100);
			if(listID!=null) listID.add(i);
			ArrayList<String> listName = districtCityNameList.get(i/100);
			if(listName!=null) listName.add(districtVO.districtMap.get(i));
		}
	}
	
	/**
	 * 返回初始的城市商圈列表，测试用.
	 * @return
	 */
	private static DistrictVO initialData(){
		HashMap<Integer, String> cityMap = new HashMap<>();
		HashMap<Integer, String> districtMap = new HashMap<>();
		cityMap.put(1, "北京");
		cityMap.put(2, "广州");
		cityMap.put(3, "南京");
		districtMap.put(101, "中南海");
		districtMap.put(102, "中关村");
		districtMap.put(201, "中山大学");
		districtMap.put(202, "广州大学");
		districtMap.put(301, "仙林");
		districtMap.put(302, "鼓楼");
		return new DistrictVO(cityMap, districtMap);
	}
	
	/**
	 * 从本地的district文件加载DistrictVO值对象.
	 * @return
	 */
	public static ResultMessage loadLocalData(){
		try{
			//读取district文件
			File binFile = new File("src/main/java/tiquartet/ClientModule/data/district");
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(binFile));
			districtVO = (DistrictVO) inputStream.readObject();
			setList();
			inputStream.close();
			return new ResultMessage(true);
		}catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ResultMessage(false,"数据文件读取失败",null);
		}
		
	}
	
	/**
	 * 用传入的值对象更新本地数据文件，再更新静态DistrictVO成员.
	 * @param newData
	 * @return
	 */
	public static ResultMessage updateData(DistrictVO newData){
		try{
			//将传入额VO保存到本地数据文件
			File binFile = new File("src/main/java/tiquartet/ClientModule/data/district");
			binFile.createNewFile();
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(binFile, false));
			outputStream.writeObject(newData);
			outputStream.close();
			districtVO = newData;
			setList();
			ResultMessage loadResult = loadLocalData();
			return loadResult;
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(false,"数据文件写入失败",null);
		}
	}
	
	/**
	 * 获取城市编号与名称的HashMap.
	 * @return
	 */
	public static HashMap<Integer, String> getCityMap(){
		return districtVO.cityMap;
	}
	
	/**
	 * 获取商圈编号与名称的HashMap.
	 * @return
	 */
	public static HashMap<Integer, String> getDistrictMap(){
		return districtVO.cityMap;
	}
	
	/**
	 * 返回该城市的所有商圈的编号的列表.
	 * @param cityID
	 * @return
	 */
	public static ArrayList<Integer> districtIDListOfCity(int cityID){
		return districtCityList.get(cityID);
	}
	
	/**
	 * 返回该城市的所有商圈的名称的列表.
	 * @param cityID
	 * @return
	 */
	public static ArrayList<String> districtNameListOfCity(int cityID){
		return districtCityNameList.get(cityID);
	}
	
	/**
	 * 传入城市名称，返回城市编号.
	 * @param cityName
	 * @return
	 */
	public static int getCityIDOf(String cityName){
		return cityIDList.get(cityNameList.indexOf(cityName));
	}
	
	/**
	 * 使用初始数据生成数据文件.
	 * @param args
	 */
	public static void main(String[] args){
		updateData(initialData());
	}
	
}
