package jsoft.ads.user;

import java.io.IOException;
import jsoft.library.*;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.*;

import jsoft.objects.*;
import jsoft.*;

/**
 * Servlet implementation class view
 */
@WebServlet("/user/profiles")
public class UserProfiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	public UserProfiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Tìm thông tin tài khoản trong phiên làm việc
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");

		if (user == null) {
			response.sendRedirect("/adv/user/login");
		} else {
			view(request, response);
		}
	}

	protected void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType(CONTENT_TYPE);

		// Tìm tham số phục hồi người sử dụng đã bị xoá (dr: delete restore)
		String dr = request.getParameter("dr");

		PrintWriter out = response.getWriter();

		// Xác định tap làm việc
		String tap = request.getParameter("t");
		HashMap<String, String> tap_active = new HashMap<>();
		HashMap<String, String> tap_show = new HashMap<>();
		if (tap != null) {

			tap_active.put(tap, "active");
			tap_show.put(tap, "show active");
		} else {
			tap_active.put("over", "active");
			tap_show.put("over", "show active");
		}

		// Tìm id của người sử dụng để xem chi tiết/chỉnh sửa
		int id = Utilities.getIntParam(request, "id");
		UserObject eUser = null;
		if (id > 0) {
			// Tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			UserControl uc = new UserControl(cp);
			if (cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}

			if (dr!=null && dr.equalsIgnoreCase("res")) {
				UserObject nUser = new UserObject();
				nUser.setUser_id(id);
				nUser.setUser_last_modified(Utilities_date.getDate());
				nUser.setUser_trash_id(0);

				boolean result = uc.editUser(nUser, EDIT_TYPE.TRASH);
				uc.releaseConnection();
				if (result) {
					response.sendRedirect("/adv/user/list?trash");
				} else {
					response.sendRedirect("/adv/user/list?trash&err");
				}
			} else {
				eUser = uc.getUserObject(id);
				uc.releaseConnection();
			}

		} else {
			response.sendRedirect("/adv/user/list");
		}


		// Tham chiếu tải nội dung của header
		RequestDispatcher h = request.getRequestDispatcher("/header?pos=urprofiles");
		if (h != null) {
			h.include(request, response);
		}

		out.append("<main id=\"main\" class=\"main\">");

		RequestDispatcher error = request.getRequestDispatcher("/error");
		if (error != null) {
			error.include(request, response);
		}

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Chi tiết người sử dụng</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Người sử dụng</li>");
		out.append("<li class=\"breadcrumb-item active\">Chi tiết</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section profile\">");

		out.append("<div class=\"row\">");

		// --------------------------------------------------------

		out.append("<div class=\"col-xl-4\">");

		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body profile-card pt-4 d-flex flex-column align-items-center\">");

		String name = "", fullname = "", image = "";
		if (eUser != null) {
			name = eUser.getUser_name();
			fullname = eUser.getUser_fullname();
		}

		out.append("<img src=\"/adv/img/profile-img.jpg\" alt=\"Profile\" class=\"rounded-circle\">");
		out.append("<h2>" + fullname + "</h2>");
		out.append("<h3>" + name + "</h3>");
		out.append("<div class=\"social-links mt-2\">");
		out.append("<a href=\"#\" class=\"twitter\"><i class=\"bi bi-twitter\"></i></a>");
		out.append("<a href=\"#\" class=\"facebook\"><i class=\"bi bi-facebook\"></i></a>");
		out.append("<a href=\"#\" class=\"instagram\"><i class=\"bi bi-instagram\"></i></a>");
		out.append("<a href=\"#\" class=\"linkedin\"><i class=\"bi bi-linkedin\"></i></a>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");

		out.append("</div>");
		out.append("<div class=\"col-xl-8\">");

		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body pt-3\">");
		out.append("<!-- Bordered Tabs -->");
		out.append("<ul class=\"nav nav-tabs nav-tabs-bordered\">");

		out.append("<li class=\"nav-item\">");
		out.append("<button class=\"nav-link " + tap_active.getOrDefault("over", "")
				+ "\" data-bs-toggle=\"tab\" data-bs-target=\"#profile-overview\"><i class=\"bi bi-info-square-fill\"></i> Thông tin chung</button>");
		out.append("</li>");

		out.append("<li class=\"nav-item\">");
		out.append("<button class=\"nav-link " + tap_active.getOrDefault("edit", "")
				+ "\" data-bs-toggle=\"tab\" data-bs-target=\"#profile-edit\"><i class=\"bi bi-pen-fill\"></i> Chỉnh sửa</button>");
		out.append("</li>");

		out.append("<li class=\"nav-item\">");
		out.append("<button class=\"nav-link" + tap_active.getOrDefault("sett", "")
				+ "\" data-bs-toggle=\"tab\" data-bs-target=\"#profile-settings\"><i class=\"bi bi-gear-fill\"></i> Thiết lập</button>");
		out.append("</li>");

		out.append("<li class=\"nav-item\">");
		out.append("<button class=\"nav-link " + tap_active.getOrDefault("pass", "")
				+ "\" data-bs-toggle=\"tab\" data-bs-target=\"#profile-change-password\"><i class=\"bi bi-key-fill\"></i> Đổi mật khẩu</button>");
		out.append("</li>");

		out.append("</ul>");
		out.append("<div class=\"tab-content pt-2\">");

		String notes = "", job = "", jobarea = "", hphone = "", mphone = "", ophone = "", email = "", address = "",
				date = "";
		String position = "", birthday = "";
		short logined = 0, apply = 0;
		if (eUser != null) {
			notes = eUser.getUser_notes();
			job = eUser.getUser_job();
			jobarea = eUser.getUser_jobarea();
			email = eUser.getUser_email();
			hphone = eUser.getUser_homephone();
			mphone = eUser.getUser_mobilephone();
			ophone = eUser.getUser_officephone();
			address = eUser.getUser_address();
			logined = eUser.getUser_logined();
			date = eUser.getUser_created_date() + " (" + eUser.getUser_last_logined() + ")";
			apply = eUser.getUser_applyyear();
			birthday = eUser.getUser_birthday();
			position = eUser.getUser_position();
		}

		
		
		//****************************************FORM OVERVIEW********************************//
		out.append("<div class=\"tab-pane fade " + tap_show.getOrDefault("over", "")
				+ " profile-overview\" id=\"profile-overview\">");
		out.append("<h5 class=\"card-title\">Giới thiệu</h5>");
		out.append("<p class=\"small fst-italic\">" + notes + "</p>");

		out.append("<h5 class=\"card-title\">Chi tiết</h5>");

		out.append("<div class=\"row\">");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Ngày tạo / Đăng nhập</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + date + "</div>");
		out.append("</div>");

		out.append("<div class=\"col-lg-3 col-md-4 label \">Tên đầy đủ</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + fullname + " (" + name + ")</div>");
		out.append("</div>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Nghề nghiệp</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + job + " (" + jobarea + ")" + "</div>");
		out.append("</div>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Địa chỉ</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + address + "</div>");
		out.append("</div>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Điện thoại</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + hphone + "</div>");
		out.append("</div>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Hộp thư</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + email + "</div>");
		out.append("</div>");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-3 col-md-4 label\">Số lần đăng nhập</div>");
		out.append("<div class=\"col-lg-9 col-md-8\">" + logined + "</div>");
		out.append("</div>");

		out.append("</div>");
		
		//*************************************END (FORM OVERVIEW)*************************************//
		
		
		
		
		
		//*****************************************FORM EDIT******************************************//
		out.append("<div class=\"tab-pane fade " + tap_show.getOrDefault("edit", "")
				+ " profile-edit pt-3\" id=\"profile-edit\">");

		out.append("<!-- Profile Edit Form -->");
		out.append("<form method=\"post\" action=\"/adv/user/profiles?id=" + id
				+ "\" class=\"needs-validation\" novalidate>");
		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"profileImage\" class=\"col-md-4 col-lg-3 col-form-label\">Ảnh</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<img src=\"/adv/img/profile-img.jpg\" alt=\"Profile\">");
		out.append("<div class=\"pt-2\">");
		out.append(
				"<a href=\"#\" class=\"btn btn-primary btn-sm\" title=\"Tải hình ảnh mới cho người sử dụng\"><i class=\"bi bi-upload\"></i></a>");
		out.append(
				"<a href=\"#\" class=\"btn btn-danger btn-sm\" title=\"Loại bỏ hình ảnh\"><i class=\"bi bi-trash\"></i></a>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"fullName\" class=\"col-md-4 col-lg-3 col-form-label\">Tên đầy đủ</label>");
		out.append("<div class=\"col-md-5 col-lg-6\">");
		out.append("<input name=\"txtFullname\" type=\"text\" class=\"form-control\" id=\"fullName\" value=\""
				+ fullname + "\">");
		out.append("<div class=\"invalid-feedback\">Thiếu tên đầy đủ</div>");
		out.append("</div>");
		out.append("<div class=\"col-md-3 col-lg-3\">");
		out.append("<input type=\"text\" class=\"form-control\" name=\"txtName\" readonly value=\"" + name + "\" >");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"notes\" class=\"col-md-4 col-lg-3 col-form-label\">Giới thiệu</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<textarea name=\"txtNotes\" class=\"form-control\" id=\"notes\" style=\"height: 100px\">" + notes
				+ "</textarea>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"jobarea\" class=\"col-md-4 col-lg-3 col-form-label\">Lĩnh vực nghề</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"txtJobarea\" type=\"text\" class=\"form-control\" id=\"jobarea\" value=\"" + jobarea
				+ "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"job\" class=\"col-md-4 col-lg-3 col-form-label\">Nghề nghiệp</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"txtJob\" type=\"text\" class=\"form-control\" id=\"job\" value=\"" + job + "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"address\" class=\"col-md-4 col-lg-3 col-form-label\">Địa chỉ</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"txtAddress\" type=\"text\" class=\"form-control\" id=\"address\" value=\"" + address
				+ "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"Phone\" class=\"col-md-4 col-lg-3 col-form-label\">Điện thoại</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<div class=\"input-group\">");
		out.append("<input name=\"txtHphone\" type=\"text\" class=\"form-control\" id=\"Hphone\" placeholder=\"Home Phone\" value=\"" + hphone
				+ "\">");
		out.append("<input name=\"txtMphone\" type=\"text\" class=\"form-control\" id=\"Mphone\" placeholder=\"Mobile Phone\" value=\"" + mphone
				+ "\">");
		out.append("<input name=\"txtOphone\" type=\"text\" class=\"form-control\" id=\"Ophone\" placeholder=\"Office Phone\" value=\"" + ophone
				+ "\">");
		out.append("</div>"); // input-group
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"Email\" class=\"col-md-4 col-lg-3 col-form-label\">Email</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"txtEmail\" type=\"email\" class=\"form-control\" id=\"Email\" required value=\""
				+ email + "\">");
		out.append("<div class=\"invalid-feedback\">Cần phải có email</div>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"apply\" class=\"col-md-4 col-lg-3 col-form-label\">Năm làm việc</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append(
				"<input name=\"txtApply\" type=\"text\" class=\"form-control\" id=\"apply\" value=\"" + apply + "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"position\" class=\"col-md-4 col-lg-3 col-form-label\">Vị trí công việc</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"txtPosition\" type=\"text\" class=\"form-control\" id=\"position\" value=\""
				+ position + "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"birthday\" class=\"col-md-4 col-lg-3 col-form-label\">Ngày sinh</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"txtBirthday\" type=\"date\" class=\"form-control\" id=\"birthday\" value=\""
				+ birthday + "\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-save-fill\"></i> Lưu thay đổi</button>");
		out.append("</div>");
		out.append("</form><!-- End Profile Edit Form -->");

		out.append("</div>");
		
		//*********************************************END (FORM EDIT)**********************************//

		
		
		
		
		//*********************************************FORM SETTING*****************************************//
		out.append("<div class=\"tab-pane fade pt-3\" id=\"profile-settings\">");

		out.append("<!-- Settings Form -->");
		out.append("<form>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"fullName\" class=\"col-md-4 col-lg-3 col-form-label\">Email Notifications</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"changesMade\" checked>");
		out.append("<label class=\"form-check-label\" for=\"changesMade\">");
		out.append("Changes made to your account");
		out.append("</label>");
		out.append("</div>");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"newProducts\" checked>");
		out.append("<label class=\"form-check-label\" for=\"newProducts\">");
		out.append("Information on new products and services");
		out.append("</label>");
		out.append("</div>");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"proOffers\">");
		out.append("<label class=\"form-check-label\" for=\"proOffers\">");
		out.append("Marketing and promo offers");
		out.append("</label>");
		out.append("</div>");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"securityNotify\" checked disabled>");
		out.append("<label class=\"form-check-label\" for=\"securityNotify\">");
		out.append("Security alerts");
		out.append("</label>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\">Save Changes</button>");
		out.append("</div>");
		out.append("</form><!-- End settings Form -->");

		out.append("</div>");
		
		//*********************************************END (FORM SETTING)**********************************//
		
		
		
		

		
		//******************************************FORM CHANGE PASSWORD***********************************//
		out.append("<div class=\"tab-pane fade pt-3\" id=\"profile-change-password\">");
		out.append("<!-- Change Password Form -->");
		out.append("<form>");

		out.append("<div class=\"row mb-3\">");
		out.append(
				"<label for=\"currentPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Current Password</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"password\" type=\"password\" class=\"form-control\" id=\"currentPassword\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"newPassword\" class=\"col-md-4 col-lg-3 col-form-label\">New Password</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"newpassword\" type=\"password\" class=\"form-control\" id=\"newPassword\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"row mb-3\">");
		out.append(
				"<label for=\"renewPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Re-enter New Password</label>");
		out.append("<div class=\"col-md-8 col-lg-9\">");
		out.append("<input name=\"renewpassword\" type=\"password\" class=\"form-control\" id=\"renewPassword\">");
		out.append("</div>");
		out.append("</div>");

		out.append("<div class=\"text-center\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\">Change Password</button>");
		out.append("</div>");
		out.append("</form><!-- End Change Password Form -->");

		out.append("</div>");
		
		//*****************************************END (FORM CHANGE PASSWORD)*******************************//

		
		
		
		out.append("</div><!-- End Bordered Tabs -->");

		out.append("</div>");
		out.append("</div>");

		out.append("</div>");

		// -----------------------------------------------------

		out.append("</div>");// row

		out.append("</section>");

		out.append("</main><!-- End #main -->");

		// Tham chiếu tải nội dung của footer
		RequestDispatcher f = request.getRequestDispatcher("/footer");
		if (f != null) {
			f.include(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Thiết lập ký tự cần lấy
		request.setCharacterEncoding("utf-8");

		int id = Utilities.getIntParam(request, "id");// Lấy id để cập nhật
		if (id > 0) {
			// Tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			UserControl uc = new UserControl(cp);

			if (cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}

			UserObject nUser = new UserObject();
			nUser.setUser_id(id);
			nUser.setUser_last_modified(Utilities_date.getDate());

			boolean result = false;

			// Tìm thông tin nếu chỉnh sửa
			String name = request.getParameter("txtName");
			String email = request.getParameter("txtEmail");

			if (name != null && !name.equalsIgnoreCase("") && email != null && !email.equalsIgnoreCase("")) {

				// Lấy thông tin không bắt buộc
				String fullname = request.getParameter("txtFullname");
				String notes = request.getParameter("txtNotes");
				String address = request.getParameter("txtAddress");
				String hphone = request.getParameter("txtHphone");
				String mphone = request.getParameter("txtMphone");
				String ophone = request.getParameter("txtOphone");
				String jobarea = request.getParameter("txtJobarea");
				String job = request.getParameter("txtJob");
				String position = request.getParameter("txtPosition");
				short apply = Utilities.getShortParam(request, "txtApply");
				String birthday = request.getParameter("txtBirthday");


				// Thiết lập thông tin cho đối tượng chỉnh sửa
				nUser.setUser_name(name);
				nUser.setUser_address(Utilities.encode(address));
				nUser.setUser_email(email);
				nUser.setUser_fullname(Utilities.encode(fullname));
				nUser.setUser_homephone(hphone);
				nUser.setUser_mobilephone(mphone);
				nUser.setUser_officephone(ophone);
				nUser.setUser_notes(Utilities.encode(notes));
				nUser.setUser_job(Utilities.encode(job));
				nUser.setUser_jobarea(Utilities.encode(jobarea));
				nUser.setUser_birthday(birthday);
				nUser.setUser_applyyear(apply);
				nUser.setUser_position(Utilities.encode(position));

				result = uc.editUser(nUser, EDIT_TYPE.NORMAL);
			} else {
				String dr = request.getParameter("dr");
				if (dr != null) {
					if (dr.equalsIgnoreCase("del")) {
//						UserObject user = (UserObject) request.getSession().getAttribute("userLogined");

						nUser.setUser_trash_id(id);

						// Cập nhật xoá lưu thùng rác hoặc phục hồi
						result = uc.editUser(nUser, EDIT_TYPE.TRASH);
					} else if (dr.equalsIgnoreCase("delabs")) {
						result = uc.delUser(nUser); // Xoá tuyệt đối
					}

				}

			}

			// Yêu cầu trả về kết nối
			uc.releaseConnection();

			if (result) {
				String dr = request.getParameter("dr");
				if (dr != null && dr.equalsIgnoreCase("delabs")) {
					response.sendRedirect("/adv/user/list?trash");
				} else {
					response.sendRedirect("/adv/user/list");
				}

			} else {
				response.sendRedirect("/adv/user/list?err=notok");
			}

		} // id > 0
	}

}
