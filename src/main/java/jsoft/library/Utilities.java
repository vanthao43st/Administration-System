package jsoft.library;

import javax.servlet.*;
import net.htmlparser.jericho.*;

public class Utilities {

	/**
	 * Phương thức lấy giá trị của một tham số theo kiểu Byte
	 * 
	 * @param request - Đối tượng lưu trữ
	 * @param name    - Tên tham số
	 * 
	 * @return - Giá trị nguyên Byte, nếu không tồn tại thì trả về -1
	 */

	public static byte getByteParam(ServletRequest request, String name) {
		byte value = -1;

		try {
			String str_value = request.getParameter(name);

			if (str_value != null && !str_value.equalsIgnoreCase("")) {
				value = Byte.parseByte(str_value);
			}
		} catch (NumberFormatException ex) {
			System.out.print("Co loi trong gia tri cua tham so");
			ex.printStackTrace();
		}

		return value;
	}

	/**
	 * Phương thức lấy giá trị của một tham số theo kiểu short
	 * 
	 * @param request - Đối tượng lưu trữ
	 * @param name    - Tên tham số
	 * 
	 * @return - Giá trị nguyên short, nếu không tồn tại thì trả về -1
	 */
	public static short getShortParam(ServletRequest request, String name) {
		short value = -1;

		try {
			String str_value = request.getParameter(name);

			if (str_value != null && !str_value.equalsIgnoreCase("")) {
				value = Short.parseShort(str_value);
			}
		} catch (NumberFormatException ex) {
			System.out.print("Co loi trong gia tri cua tham so");
			ex.printStackTrace();
		}

		return value;
	}

	/**
	 * Phương thức lấy giá trị của một tham số theo kiểu Int
	 * 
	 * @param request - Đối tượng lưu trữ
	 * @param name    - Tên tham số
	 * 
	 * @return - Giá trị nguyên Int, nếu không tồn tại thì trả về -1
	 */
	public static int getIntParam(ServletRequest request, String name) {
		int value = -1;

		try {
			String str_value = request.getParameter(name);

			if (str_value != null && !str_value.equalsIgnoreCase("")) {
				value = Integer.parseInt(str_value);
			}
		} catch (NumberFormatException ex) {
			System.out.print("Co loi trong gia tri cua tham so");
			ex.printStackTrace();
		}

		return value;
	}
	
	
	
	/**
	 * Lấy tham số phân trang
	 * @param request
	 * @return
	 */
	public static short getPageParam(ServletRequest request) {
		short page = 1;

		try {
			String str_value = request.getParameter("p");

			if (str_value != null && !str_value.equalsIgnoreCase("")) {
				page = Short.parseShort(str_value);
			}
		} catch (NumberFormatException ex) {
			System.out.print("Có lỗi khi lấy tham số phân trang");
			ex.printStackTrace();
		}

		return page;
	}

	public static String encode(String strUnicode) {
		return CharacterReference.encode(strUnicode);
	}

	public static String decode(String strHtml) {
		return CharacterReference.decode(strHtml);
	}
}
