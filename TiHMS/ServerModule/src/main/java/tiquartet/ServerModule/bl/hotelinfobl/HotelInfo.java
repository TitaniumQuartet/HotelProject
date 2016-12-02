package tiquartet.ServerModule.bl.hotelinfobl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import tiquartet.ServerModule.datahelper.DataFactory;
import tiquartet.ServerModule.dataservice.impl.HotelInfoDataImpl;
import tiquartet.ServerModule.dataservice.impl.ReviewDataImpl;
import tiquartet.ServerModule.dataservice.impl.RoomDataImpl;
import tiquartet.ServerModule.po.HotelInfoPO;
import tiquartet.ServerModule.po.ReviewPO;
import tiquartet.ServerModule.po.RoomTypePO;
import tiquartet.ServerModule.bl.manageorderbl.ManageOrderController;
import tiquartet.CommonModule.vo.*;
import tiquartet.CommonModule.blservice.hotelinfoblservice.HotelInfoBLService;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.OrderNumVO;
public class HotelInfo implements HotelInfoBLService{
	HotelInfoDataImpl hoteldataimpl;
	ReviewDataImpl reviewdataimpl;
	RoomDataImpl roomdataimpl;
	ManageOrderController manageordercontroller;
	public HotelInfo(){
		hoteldataimpl=HotelInfoDataImpl.getInstance();
		reviewdataimpl=ReviewDataImpl.getInstance();
		roomdataimpl=RoomDataImpl.getInstance();
		manageordercontroller=new ManageOrderController();
	}
	public HotelBriefVO getHotelBrief (int hotelID,int userID)throws RemoteException{
	    //浼犲叆閰掑簵ID杩斿洖閰掑簵鐨勭畝鐣ヤ俊鎭�.
		HotelBriefVO hotelbrief=new HotelBriefVO();
		HotelInfoPO hp=new HotelInfoPO();//姝ゅ 搴旇淇敼
		hotelbrief.averageGrade=hp.getaverageGrade();
		hotelbrief.circleName=hp.getcircleName();
		hotelbrief.cityName=hp.getcityName();
		hotelbrief.hotelID=hp.gethotelId();
		hotelbrief.hotelName=hp.gethotelName();
		hotelbrief.star=hp.getstar();
		OrderNumVO onp=manageordercontroller.numAtHotel(hotelID,userID);
		hotelbrief.numOfAllOrder=onp.allOrder;
		hotelbrief.numOfEndOrder=onp.executedOrder;
		return hotelbrief;
	}
	
		
	public HotelDetailsVO getHotelDetails (int hotelID,int userID)throws RemoteException{	
		//浼犲叆閰掑簵缂栧彿锛岃繑鍥為厭搴楄缁嗕俊鎭�
	    HotelDetailsVO hoteldetails=new HotelDetailsVO();
	    HotelInfoPO hp=hoteldataimpl.getHotelInfo(hotelID);//姝ゅ搴旇淇敼
	    hoteldetails.address=hp.getaddress();
	    hoteldetails.averageg=hp.getaverageGrade();
	    hoteldetails.circleName=hp.getcircleName();
	    hoteldetails.cityName=hp.getcityName();
	    hoteldetails.hotelID=hp.gethotelId();
	    hoteldetails.introduction=hp.gethotelIntroduction();
	    hoteldetails.lowprice=hp.getlowprice();
	    hoteldetails.hotelName=hp.gethotelName();
	    hoteldetails.serviceintro=hp.getserviceIntroduction();
	    hoteldetails.star=hp.getstar();
	    List<ReviewPO> list=reviewdataimpl.searchByHotel(hotelID);//姝ゅ搴旇淇敼
	    for(int i=0;i<list.size();i++){
	    	ReviewVO rv=new ReviewVO();
	    	rv=list.get(i).toReviewvo();
	    	hoteldetails.list.add(rv);
	    	
	    }
		return hoteldetails;
	}
	
	public List<RoomTypeVO> availableRoomType (PreOrderVO preOrder)throws RemoteException{
		//浼犲叆鍏ヤ綇鏃ユ湡锛岄厭搴楋紝杩斿洖鍙敤瀹㈡埧绫诲瀷鍙婅绫诲瀷鏁伴噺
		List<RoomTypePO> list=new ArrayList<RoomTypePO>();
		List<RoomTypeVO> listvo=new ArrayList<RoomTypeVO>();
		for(int i=0;i<list.size();i++){
			RoomTypeVO rtv=new RoomTypeVO();
			rtv.typeIntroduction=list.get(i).gettypeIntroduction();
			rtv.roomType=list.get(i).getroomType();
			rtv.price=list.get(i).getprice();
			rtv.roomTypeId=list.get(i).getroomTypeId();
			listvo.add(rtv);
		}
		return listvo;
		
	}
	
	public ResultMessage reviewHotel(ReviewVO review)throws RemoteException{
		//浼犲叆涓�涓瘎浠凤紝鎶婁粬瀛樺叆鏁版嵁搴撲腑
		ReviewPO reviewpo=new ReviewPO(review);
		return reviewdataimpl.insert(reviewpo);
	}
	
	public ResultMessage modifyHotelInfo (HotelInfoVO hotelInfo)throws RemoteException{
		//淇敼閰掑簵淇℃伅
		HotelInfoPO hip=new HotelInfoPO(hotelInfo);		
		return hoteldataimpl.update(hip);
	}
   public List<HotelBriefVO> clientHotelList(int userId)throws RemoteException{
	   //杩斿洖鐢ㄦ埛棰勮杩囩殑閰掑簵鍒楄〃
	   List<HotelBriefVO> listvo=new ArrayList<HotelBriefVO>();	   
	   ManageOrderController manageordercontroller=new ManageOrderController();
	   List<Integer>  hotelID=manageordercontroller.orderedHotelID(userId);
	   for(int i=0;i<hotelID.size();i++){
		   HotelInfoPO hip=hoteldataimpl.getHotelInfo(hotelID.get(i));
		   HotelBriefVO hotelbriefvo =new HotelBriefVO();
		   hotelbriefvo.circleName=hip.getcircleName();
		   hotelbriefvo.cityName=hip.getcityName();
		   hotelbriefvo.hotelID=hip.gethotelId();
		   hotelbriefvo.star=hip.getstar();
		   hotelbriefvo.averageGrade=hip.getaverageGrade();
		   hotelbriefvo.hotelName=hip.gethotelName();
		   listvo.add(hotelbriefvo);
	   }
	   return listvo;
   }
}
