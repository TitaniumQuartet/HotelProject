/**
 * @author Yolanda151250080
 */
package tiquartet.ServerModule.bl.manageroombl;

import java.util.List;
import tiquartet.CommonModule.util.ResultMessage;
import tiquartet.CommonModule.vo.RoomVO;
import tiquartet.CommonModule.vo.RoomTypeVO;


public class ManageRoomController {
	
	static ManageRoom manageroom = new ManageRoom();
	
	/*
	 * ���þƵ��Ż�ȡ�Ƶ귿���б�
	 */
	public static List<RoomVO> getRoomList (long hotelID) {
		
		return manageroom.getRoomList(hotelID);
	}
	
	/*
	 * �޸ľƵ���Ӧ�������Ϣ
	 */
	public static ResultMessage modifyRoomInfo (RoomVO room) {
		
		return manageroom.modifyRoomInfo(room);
	}
	
	/*
	 * �����Ӧ������Ϣ
	 */
	public static ResultMessage addRoom (RoomVO room) {
		
		return manageroom.addRoom(room);
	}
	
	/*
	 * ͨ��������ɾ���Ƶ�ĳһ������Ϣ
	 */
	public static ResultMessage deleteRoom (int roomID) {
		
		return manageroom.deleteRoom(roomID);
	}
	
	/*
	 * ͨ���Ƶ귿���Ű�����ס����,���ÿͷ�״̬��Ϊ����ס��
	 */
	public static ResultMessage checkIn (int roomID) {
		
		return manageroom.checkIn(roomID);
	}
	
	/*
	 * ͨ���Ƶ귿���Ű����˷����������ÿͷ�״̬��Ϊ�����С�
	 */
	public static ResultMessage checkOut (int roomID) {
		
		return manageroom.checkOut(roomID);
	}
	
	/*
	 * �޸ķ�������
	 */
	public static ResultMessage modifyRoomType (int hotelID, RoomTypeVO roomType) {
		
		return manageroom.modifyRoomType(hotelID, roomType);
	}
	
	/*
	 * ɾ����������
	 */
	public static ResultMessage deleteRoomType (int hotelID, int roomTypeID) {
		
		return manageroom.deleteRoomType(hotelID, roomTypeID);
	}

}