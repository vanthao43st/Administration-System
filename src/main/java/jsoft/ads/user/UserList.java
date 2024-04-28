package jsoft.ads.user;

import java.io.IOException;
import jsoft.library.*;
import java.io.PrintWriter;

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
@WebServlet("/user/list")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	public UserList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Tìm thông tin tài khoản trong phiên làm việc
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		

		if (user == null) {
			response.sendRedirect("/adv/user/login");
		} else {
			view(request, response, user);
		}
	}

	protected void view(HttpServletRequest request, HttpServletResponse response, UserObject user)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Xác định tập ký tự làm việc
		request.setCharacterEncoding("UTF-8");
		
		
		response.setContentType(CONTENT_TYPE);

		// Tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		UserControl uc = new UserControl(cp);
		if (cp == null) {
			getServletContext().setAttribute("CPool", uc.getCP());
		}

		String trash = request.getParameter("trash"); // Tham số xác định xem thùng rác
		boolean isTrash = (trash != null) ? true : false;
		
		//Tìm từ khoá nếu có
		String key = request.getParameter("keyword");
		String saveKey = (key!=null && !key.equalsIgnoreCase("")) ? key.trim() : "";

		// Lấy cấu trúc hiển thị danh sách
		UserObject similar = new UserObject();
		similar.setUser_permission(user.getUser_permission());
		similar.setUser_name(Utilities.encode(saveKey));
		
		int p = Utilities.getPageParam(request);

//		Triplet<UserObject, Short, Byte> infors = new Triplet<UserObject, Short, Byte>(similar, (short) 1, (byte) 10);
		Quartet<UserObject, Integer, Byte, Boolean> infos = new Quartet<>(similar, p, (byte) 10, isTrash);
		Pair<USER_ORDER, ORDER> order = new Pair<USER_ORDER, ORDER>(USER_ORDER.ID, ORDER.ASC);
		Pair<String, String> view = uc.viewUsers(infos, order);

		uc.releaseConnection();

		
		
		
		PrintWriter out = response.getWriter();

		// Tham chiếu tải nội dung của header
		RequestDispatcher h = request.getRequestDispatcher("/header?pos=urlist");
		if (h != null) {
			h.include(request, response);
		}

		out.append("<main id=\"main\" class=\"main\">");

		RequestDispatcher error = request.getRequestDispatcher("/error");
		if (error != null) {
			error.include(request, response);
		}

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách người sử dụng</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Người sử dụng</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");

		out.append("<div class=\"card\">\n");
		out.append("<div class=\"card-body pt-3\">\n");

		if (!isTrash) {

			out.append("<button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#addUser\">");
			out.append("<i class=\"bi bi-person-add\"></i> Thêm mới");
			out.append("</button>");

			out.append("<div class=\"modal fade\" id=\"addUser\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
			out.append("<div class=\"modal-dialog modal-lg\">");
			out.append("<div class=\"modal-content\">");
			out.append("<form method=\"post\" action=\"/adv/user/list\" class=\"needs-validation\" novalidate>");
			out.append("<div class=\"modal-header text-bg-primary\">");
			out.append("<h1 class=\"modal-title fs-5\" id=\"staticBackdropLabel\">Thêm người sử dụng mới</h1>");
			out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			out.append("</div>");
			out.append("<div class=\"modal-body\">");

			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"name\" class=\"form-label\">Tên đăng nhập</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"name\" name=\"txtName\" required>");
			out.append("<div class=\"invalid-feedback\">Cần có tên cho tài khoản</div>");
			out.append("</div>"); // col-lg-6
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"fullName\" class=\"form-label\">Tên đầy đủ</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"fullName\" name=\"txtFullname\">");
			out.append("</div>"); // col-lg-6
			out.append("</div>"); // row mb-3

			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"pass1\" class=\"form-label\">Mật khẩu</label>");
			out.append("<input type=\"password\" class=\"form-control\" id=\"pass1\" name=\"txtPass1\" required>");
			out.append("<div class=\"invalid-feedback\">Nhập mật khẩu</div>");
			out.append("</div>"); // col-lg-6
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"pass2\" class=\"form-label\">Xác nhận</label>");
			out.append("<input type=\"password\" class=\"form-control\" id=\"pass2\" name=\"txtPass2\" required>");
			out.append("<div class=\"invalid-feedback\">Xác nhận mật khẩu</div>");
			out.append("</div>"); // col-lg-6
			out.append("</div>");

			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"phone\" class=\"form-label\">Điện thoại</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"phone\" name=\"txtPhone\" >");
			out.append("</div>"); // col-lg-6
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"mail\" class=\"form-label\">Hộp thư</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"mail\" name=\"txtEmail\" >");
			out.append("</div>"); // col-lg-6
			out.append("</div>");

			out.append("<div class=\"row mb-3\">");
			out.append("<div class=\"col-lg-6\">");
			out.append("<label for=\"permis\" class=\"form-label\">Quyền thực thi</label>");
			out.append("<select id=\"permis\" class=\"form-select\" name=\"slcPermis\" required>");
			out.append("<option value=\"\"> ---------------------Chọn quyền---------------------- </option>");
			out.append("<option value=\"1\">Thành viên</option>");
			out.append("<option value=\"2\">Tác giả</option>");
			out.append("<option value=\"3\">Quản lý</option>");
			out.append("<option value=\"4\">Quản trị</option>");
			out.append("<option value=\"5\">Quản trị cấp cao</option>");
			out.append("</select>");
			out.append("<div class=\"invalid-feedback\">Chọn quyền thực thi cho tài khoản</div>");
			out.append("</div>"); // col-lg-6
			out.append("</div>");

			out.append("<div class=\"mb-3 form-check\">");
			out.append("<div class=\"col-lg-12\">");
			out.append("<input type=\"checkbox\" class=\"form-check-input\" id=\"exampleCheck1\">");
			out.append("<label class=\"form-check-label\" for=\"exampleCheck1\">Bạn có đồng ý với quy định sử dụng tài khoản?</label>");
			out.append("</div>");
			out.append("</div>");

			out.append("</div>");
			out.append("<div class=\"modal-footer text-bg-light\">");

			out.append("<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-person-add\"></i>Thêm mới</button>");
			out.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Thoát</button>");

			out.append("</div>");// modal-footer

			out.append("</form>");
			out.append("</div>");// modal-content
			out.append("</div>");// modal-dialog
			out.append("</div>");// modal
		}

		out.append(view.getValue0());

		out.append("</div>");// card-body
		out.append("</div>");// card
		
		// Biểu đồ
		out.append(view.getValue1());

		out.append("</div>");// col-lg-12
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

		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");

		// Thiết lập ký tự cần lấy
		request.setCharacterEncoding("utf-8");

		// Lấy thông tin bắt buộc
		String name = request.getParameter("txtName");
		String pass1 = request.getParameter("txtPass1");
		String pass2 = request.getParameter("txtPass2");
		String email = request.getParameter("txtEmail");

		byte permis = Utilities.getByteParam(request, "slcPermis");

		if (name != null && !name.equalsIgnoreCase("") && Utilities_text.checkPass(pass1, pass2) && email != null
				&& !email.equalsIgnoreCase("") && permis > 0) {

			// Lấy thông tin không bắt buộc
			String fullname = request.getParameter("txtFullname");
			String phone = request.getParameter("txtPhone");

			// Tạo đối tượng lưu trữ
			UserObject nUser = new UserObject();
			nUser.setUser_name(name);
			nUser.setUser_pass(pass1);
			nUser.setUser_email(email);
			nUser.setUser_permission(permis);
			nUser.setUser_created_date(Utilities_date.getDate());
			nUser.setUser_parent_id(user.getUser_id());

			nUser.setUser_fullname(Utilities.encode(fullname));
			nUser.setUser_homephone(phone);

			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			UserControl uc = new UserControl(cp);

			if (cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}

			// Thực hiện thêm mới
			boolean result = uc.addUser(nUser);

			// Yêu cầu trả về kết nối
			uc.releaseConnection();

			if (result) {
				response.sendRedirect("/adv/user/list");
			} else {
				response.sendRedirect("/adv/user/list?err=add");
			}

		} else {
			response.sendRedirect("/adv/user/list?err=param");
		}
	}

}
