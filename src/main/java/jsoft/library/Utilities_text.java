package jsoft.library;

public class Utilities_text {
	
	/**
	 * Phương thức kiểm tra xác nhật mật khẩu
	 * 
	 * @param pass1 - Mật khẩu chính
	 * @param pass2 - Mật khẩu xác nhận
	 * @return - Trả về đúng nếu tồn tại mật khẩu và xác nhận thành công.
	 */
	public static boolean checkPass(String pass1, String pass2) {
		if(pass1!=null && pass2!=null) {
			if (!pass1.equalsIgnoreCase("") && pass1.length()> 5) {
				if (pass1.equals(pass2)) {
					return true;
				}
			}
		}
		
		
		
		
		return false;
	}
}
