package jsoft.ads.user;

import jsoft.*;
import jsoft.ads.basic.*;
import jsoft.library.*;
import jsoft.objects.*;
import java.sql.*;
import java.util.ArrayList;

import org.javatuples.*;

public interface User extends ShareControl{
	// Nhóm các phương thức - chức năng cập nhật cho modun User
	public boolean addUser(UserObject item);
	public boolean editUser(UserObject item, EDIT_TYPE et);
	public boolean delUser(UserObject item);
	
	
	// Nhóm các phương thức lấy thông tin
	public ResultSet getUser(int id);
	public ResultSet getUser(String username, String userpass);
	public ArrayList<ResultSet> getUsers(Quartet<UserObject, Integer, Byte, Boolean> infos, Pair<USER_ORDER, ORDER> so);

	
	
	
//	public ResultSet getUsers(UserObject similar, int at, byte total, boolean isTrash);
//	public ResultSet getUsers(UserObject similar, int at, byte total, USER_ORDER uso, ORDER o, boolean isTrash);
//	public ResultSet getUsers(UserObject similar, int at, byte total, Pair<USER_ORDER, ORDER> order, boolean isTrash);
}
