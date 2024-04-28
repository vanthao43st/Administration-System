package jsoft.ads.user;

import jsoft.*;
import jsoft.library.ORDER;
import jsoft.objects.*;
import java.util.*;
import org.javatuples.*;

public class UserControl {
	private UserModel um;

	public UserControl(ConnectionPool cp) {
		this.um = new UserModel(cp);
	}

	protected void finallize() throws Throwable {
		this.um = null;
	}

	public ConnectionPool getCP() {
		return this.um.getCP();
	}

	public void releaseConnection() {
		this.um.releaseConnection();
	}

	// **************************************************************************
	public boolean addUser(UserObject item) {
		return this.um.addUser(item);
	}

	public boolean editUser(UserObject item, EDIT_TYPE et) {
		return this.um.editUser(item, et);
	}

	public boolean delUser(UserObject item) {
		return this.um.delUser(item);
	}
	// ***************************************************************************

	// ***************************************************************************
	public UserObject getUserObject(int id) {
		return this.um.getUserObject(id);
	}

	public UserObject getUserObject(String username, String userpass) {
		return this.um.getUserObject(username, userpass);
	}

	// ***************************************************************************

	
	public Pair<String, String> viewUsers(Quartet<UserObject, Integer, Byte, Boolean> infos, Pair<USER_ORDER, ORDER> so) {
		Pair<ArrayList<UserObject>, Short> datas = this.um.getUserObjects(infos, so);

		if(infos.getValue3()) {
			return UserLibrary.viewTrashUsers(datas.getValue0());
		}else {
			return UserLibrary.viewUsers(datas, infos);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
//	public Pair<String, String> viewUsers(Triplet<UserObject, Short, Byte> infos, boolean isTrash) {
//		// Lấy danh sách đối tượng
//		ArrayList<UserObject> items = this.um.getUserObjects(infos.getValue0(), infos.getValue1(), infos.getValue2(),
//				isTrash);
//
//		if (isTrash) {
//			return UserLibrary.viewTrashUsers(items);
//		} else {
//			return UserLibrary.viewUsers(items);
//		}
//
//	}

//	public static void main(String[] args) {
//		ConnectionPool cp = new ConnectionPoolImpl();
//
//		// Tạo đối tượng thực thi chức năng mức control
//		UserControl uc = new UserControl(cp);
//
//		// Giả lập thông tin
//		Triplet<UserObject, Short, Byte> infos = new Triplet<>(null, (short) 1, (byte) 15);
//		Pair<USER_ORDER, ORDER> order = new Pair<USER_ORDER, ORDER>(USER_ORDER.NAME, ORDER.DESC);
//
//		// Lấy cấu trúc trình bày view
//		Pair<String, String> view = uc.viewUsers(infos, order);
//		
//		//Trả về kết nối
//		uc.releaseConnection();
//		
//		// Hiển thị màn hình ảo
//		System.out.println(view.getValue0());
//
//	}

//	public Pair<String, String> viewUsers(Quartet<UserObject, Short, Byte, Boolean> infos,
//			Pair<USER_ORDER, ORDER> order) {
//		// Lấy danh sách đối tượng
//		ArrayList<UserObject> items = this.um.getUserObjects1(infos, order);
//
//		boolean isTrash = infos.getValue3();
//		if (isTrash) {
//			return UserLibrary.viewTrashUsers(items);
//		} else {
//			return UserLibrary.viewUsers(datas, infos);
//		}
//	}
	
	
	// -------------------------------------------------------
		
}
