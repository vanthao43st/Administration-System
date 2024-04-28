package jsoft.ads.user;

import jsoft.objects.*;
import jsoft.*;
import jsoft.library.ORDER;
import java.sql.*;
import java.util.*;

import org.javatuples.Pair;
import org.javatuples.Quartet;

public class UserModel {

	private User u;

	public UserModel(ConnectionPool cp) {
		this.u = new UserImpl(cp);
	}

	protected void finallize() throws Throwable {
		this.u = null;
	}

	public ConnectionPool getCP() {
		return this.u.getCP();
	}

	public void releaseConnection() {
		this.u.releaseConnection();
	}

	// **************************************************************************
	public boolean addUser(UserObject item) {
		return this.u.addUser(item);
	}

	public boolean editUser(UserObject item, EDIT_TYPE et) {
		return this.u.editUser(item, et);
	}

	public boolean delUser(UserObject item) {
		return this.u.delUser(item);
	}
	// ***************************************************************************

	public UserObject getUserObject(int id) {
		UserObject item = null;

		ResultSet rs = this.u.getUser(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_logined(rs.getShort("user_logined"));
					item.setUser_jobarea(rs.getString("user_jobarea"));
					item.setUser_job(rs.getString("user_job"));
					item.setUser_notes(rs.getString("user_notes"));
					item.setUser_position(rs.getString("user_position"));
					item.setUser_applyyear(rs.getShort("user_applyyear"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}

	public UserObject getUserObject(String username, String userpass) {
		UserObject item = null;

		ResultSet rs = this.u.getUser(username, userpass);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_officephone(rs.getString("user_officephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_logined(rs.getShort("user_logined"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
	
	
	public Pair<ArrayList<UserObject>, Short> getUserObjects(Quartet<UserObject, Integer, Byte, Boolean> infos, Pair<USER_ORDER, ORDER> so) {
		
		ArrayList<UserObject> items = new ArrayList<>();
		
		UserObject item = null;

		ArrayList<ResultSet> res = this.u.getUsers(infos, so);
		ResultSet rs = res.get(1);
		if (rs != null) {
			try {
				while(rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_created_date(rs.getString("user_created_date"));
					item.setUser_last_modified(rs.getString("user_last_modified"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_trash_id(rs.getInt("user_trash_id"));
					item.setUser_last_logined(rs.getString("user_last_logined"));
					item.setUser_birthday(rs.getString("user_birthday"));
					
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_logined(rs.getShort("user_logined"));
					
					items.add(item);
					
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		rs = res.get(0);
		short total = 0;
		if(rs!=null) {
			try {
				if(rs.next()) {
					total = rs.getShort("total");
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return new Pair<>(items, total);
	}
	
	
	
	
	
	
	
	
	
	
	
	

//	public ArrayList<UserObject> getUserObjects(UserObject similar, short page, byte total, boolean isTrash) {
//
//		ArrayList<UserObject> items = new ArrayList<UserObject>();
//
//		UserObject item = null;
//
//		int at = (page - 1) * total; // 1 2 3
//
//		ResultSet rs = this.u.getUsers(similar, at, total, isTrash);
//		if (rs != null) {
//			try {
//				while (rs.next()) {
//					item = new UserObject();
//					item.setUser_id(rs.getInt("user_id"));
//					item.setUser_name(rs.getString("user_name"));
//					item.setUser_fullname(rs.getString("user_fullname"));
//					item.setUser_email(rs.getString("user_email"));
//					item.setUser_address(rs.getString("user_address"));
//					item.setUser_homephone(rs.getString("user_homephone"));
//					item.setUser_officephone(rs.getString("user_officephone"));
//					item.setUser_permission(rs.getByte("user_permission"));
//					item.setUser_logined(rs.getShort("user_logined"));
//					item.setUser_last_modified(rs.getString("user_last_modified"));
//					item.setUser_last_logined(rs.getString("user_last_logined"));
//					item.setUser_trash_id(rs.getInt("user_trash_id"));
//
//					items.add(item);
//
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return items;
//	}

//	public static void main(String[] args) {
//		ConnectionPool cp = new ConnectionPoolImpl();
//		
//		UserModel um = new UserModel(cp);
//		
//		ArrayList<UserObject> items = um.getUserObjects(null, (short)1, (byte)20, USER_ORDER.LOGIN, ORDER.DESC);
//		items.forEach(uso -> System.out.println(uso));
//	}

//	public ArrayList<UserObject> getUserObjects(UserObject similar, short page, byte total, USER_ORDER uso, ORDER o,
//			boolean isTrash) {
//
//		ArrayList<UserObject> items = new ArrayList<UserObject>();
//
//		UserObject item = null;
//
//		int at = (page - 1) * total; // 1 2 3
//
//		ResultSet rs = this.u.getUsers(similar, at, total, uso, o, isTrash);
//		if (rs != null) {
//			try {
//				while (rs.next()) {
//					item = new UserObject();
//					item.setUser_id(rs.getInt("user_id"));
//					item.setUser_name(rs.getString("user_name"));
//					item.setUser_fullname(rs.getString("user_fullname"));
//					item.setUser_email(rs.getString("user_email"));
//					item.setUser_address(rs.getString("user_address"));
//					item.setUser_homephone(rs.getString("user_homephone"));
//					item.setUser_officephone(rs.getString("user_officephone"));
//					item.setUser_permission(rs.getByte("user_permission"));
//					item.setUser_logined(rs.getShort("user_logined"));
//					item.setUser_jobarea(rs.getString("user_jobarea"));
//					item.setUser_job(rs.getString("user_job"));
//					item.setUser_trash_id(rs.getInt("user_trash_id"));
//					item.setUser_last_logined(rs.getString("user_last_logined"));
//					item.setUser_last_modified(rs.getString("user_last_modified"));
//
//					items.add(item);
//
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return items;
//	}

//	public ArrayList<UserObject> getUserObjects1(Quartet<UserObject, Short, Byte, Boolean> infos,
//			Pair<USER_ORDER, ORDER> order) {
//
//		ArrayList<UserObject> items = new ArrayList<UserObject>();
//		
//
//		UserObject item = null;
//		
//		short page = infos.getValue1();
//		byte total = infos.getValue2();
//		int at = (page - 1) * total; // 1 2 3
//
//		ResultSet rs = this.u.getUsers(infos.getValue0(), at, infos.getValue2(), order, infos.getValue3());
//		if (rs != null) {
//			try {
//				while (rs.next()) {
//					item = new UserObject();
//					item.setUser_id(rs.getInt("user_id"));
//					item.setUser_name(rs.getString("user_name"));
//					item.setUser_fullname(rs.getString("user_fullname"));
//					item.setUser_email(rs.getString("user_email"));
//					item.setUser_address(rs.getString("user_address"));
//					item.setUser_homephone(rs.getString("user_homephone"));
//					item.setUser_officephone(rs.getString("user_officephone"));
//					item.setUser_mobilephone(rs.getString("user_mobilephone"));
//					item.setUser_permission(rs.getByte("user_permission"));
//					item.setUser_logined(rs.getShort("user_logined"));
//					item.setUser_last_logined(rs.getString("user_last_logined"));
//					item.setUser_last_modified(rs.getString("user_last_modified"));
//					item.setUser_trash_id(rs.getInt("user_trash_id"));
//					item.setUser_birthday(rs.getString("user_birthday"));
//					item.setUser_created_date(rs.getString("user_created_date"));
//
//					items.add(item);
//
//				}
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		return items;
//	}
	
}
