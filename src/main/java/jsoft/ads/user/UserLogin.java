package jsoft.ads.user;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import jsoft.*;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/user/login")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Khai báo kiểu nội dung xuất về trình khách
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * Phương thức thường được sử dụng để cung câp một giao diện (GUI) <br>
	 * 
	 * Nó được gọi trong 2 trường hợp sau: <br>
	 * - Thông qua đường dẫn url của trình duyệt (https://www.jsoft.com/tin-tuc/?id=123) <br>
	 * - Thông qua sự kiện của form (method="get") <br>
	 * <br>
	 * 
	 * <b>Tham số request</b> - chứa các yêu cầu và dữ liệu cần xử lý từ trình khách
	 * gửi lên <br>
	 * <b>Tham số reponse</b> - lưu trữ các kết quả xử lý cần gửi về trình khách (client)
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);

		// Tạo đối tượng xuất nội dung về trình khách
		PrintWriter out = response.getWriter();
		
		
		
		// Tìm tham số báo lỗi nếu có
		String error = request.getParameter("err");
		String message = "";
		if (error!=null) {
			switch(error) {
			case "param":
				message = "Tham số lấy giá trị không chính xác, hoặc không tồn tại giá trị!";
				break;
			case "notok":
				message = "Tài khoản đăng nhập không thành công!";
				break;
			default:
				message = "Không thành công!";
			}
		}
		

        out.append("<!-- doctype html -->");
		out.append("<html lang=\"en\">");
		out.append("<head>");
		out.append("<meta charset=\"utf-8\">");
		out.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.append("<title>Login</title>");
		out.append("<link href=\"/adv/css/all.min.css\" rel=\"stylesheet\">");
		out.append("<link href=\"/adv/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.append("<link href=\"/adv/css/loginV3.css\" rel=\"stylesheet\">");
		out.append("<script language=\"javascript\" src=\"/adv/js/loginv3.js\"></script>");
		out.append("</head>");
		out.append("  ");
		out.append("<body>");
		out.append("");
		out.append("<div class=\"container-lg\">");
		out.append("<div class=\"row my-5\">");
		out.append("<div class=\"col-lg-6 offset-lg-3 text-bg-light\">");
		
		
		if (!message.equalsIgnoreCase("")) {
			out.append("<div class=\"row my-5 \">");
			out.append("<div class=\"col-md-12 text-center\">");
			out.append("<div class=\"fs-5 py-3 text-bg-danger\">"+message+"</div>");
			out.append("</div>");
			out.append("</div>");
		}
		
		
		out.append("<!-- form -->");
		out.append("<form action=\"\" class=\"loginview\" name=\"\" method=\"\">");
		out.append("<!-- Login -->");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-12 text-bg-primary py-3 text-center fw-bolder text-uppercase\">");
		out.append("<i class=\"fa-solid fa-user\"></i> &nbsp;&nbsp;login");
		out.append("</div>");
		out.append("</div>");

		out.append("<!-- UserName / Email -->");
		out.append("<div class=\"row mb-3 mt-3\">");
		out.append(
				"<label for=\"name\" class=\"col-sm-4 col-form-label text-end fw-semibold\">Username / Email</label>");
		out.append("<div class=\"col-sm-6\">");
		out.append(
				"<input type=\"email\" class=\"form-control\" id=\"name\" name=\"username\" onKeyup=\"checkValidLogin()\">");
		out.append("<div id=\"errName\"></div>");
		out.append("</div>");
		out.append("</div>");

		out.append("<!-- Password -->");
		out.append("<div class=\"row mb-3\">");
		out.append("<label for=\"pass\" class=\"col-sm-4 col-form-label text-end fw-semibold\">Password</label>");
		out.append("<div class=\"col-sm-6\">");
		out.append(
				"<input type=\"password\" class=\"form-control\" id=\"pass\" name=\"userpass\" onKeyup=\"checkValidLogin()\">");
		out.append("<div id=\"errPass\"></div>");
		out.append("</div>");
		out.append("</div>");

		out.append("<!-- check save account -->");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-sm-6 offset-sm-4\">");
		out.append("<div class=\"form-check\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\" id=\"chkSave\" onclick=\"checkValidLogin()\">");
		out.append("<label class=\"form-check-label\" for=\"chkSave\">");
		out.append("Save the account information?");
		out.append("</label>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");

		out.append("<!-- Forget Password, help, sign up -->");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-12 text-center\">");
		out.append("<a href=\"#\">Forget password</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
		out.append("<a href=\"#\">Help!</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
		out.append("<a href=\"#\">Sign up!</a>");
		out.append("</div>");
		out.append("</div>");

		out.append("<!-- Button -->");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-12 text-center\">");
		out.append(
				"<button type=\"button\" class=\"btn btn-primary\" disabled  id=\"btnReg\" onclick=\"login(this.form)\">"
						+ "<i class=\"fa-solid fa-right-to-bracket\"></i>&nbsp;Sign in</button>&nbsp;&nbsp;");
		out.append("<button type=\"button\" class=\"btn btn-secondary\" onclick=\"window.close()\">Cancel</button>");
		out.append("</div>");
		out.append("</div>");

		out.append("<!-- Language -->");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-12 text-end\">");
		out.append("<a href=\"#\"><i class=\"fa-solid fa-language\"></i> &nbsp;Vietnamese</a>");
		out.append("</div>");
		out.append("</div>");
		out.append("</form>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("");
		out.append("<script src=\"/adv/js/bootstrap.bundle.min.js\"></script>");
		out.append("</body>");
		out.append("</html>");

		
		// Đóng luồng xuất
		// Khi dùng out.print() => thì phải có out.close().
		// out.close();
	}

	/**
	 * 
	 * Phương thức thường được dùng để xử lý dữ liệu do các doGet gửi cho <br>
	 * 
	 * Được gọi chỉ trong trường hợp sự kiện của form: method="post" <br>
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Lấy thông tin trên giao diện
		String name = request.getParameter("username");
		String pass = request.getParameter("userpass");

		if (name != null && !name.equalsIgnoreCase("") && pass != null && !pass.equalsIgnoreCase("")) {

			// Tham chiếu ngữ cảnh làm việc của Servlet
			/**
			 * không gian bộ nhớ bên phía máy chủ đại diện cho 1 project
			 * không gian bộ nhớ tồn tại ở phía server duy nhất ở ứng dụng
			 */
			
			ServletContext application = getServletConfig().getServletContext();

			// Tìm bộ quản lý kết nối trong ngữ cảnh
			/**
			 * CPool: Tên tham chiếu trong ngữ cảnh lần đầu tiên chưa tồn tại, sau khi gọi
			 * đến basic thì đã đc khởi tạo tên như vậy
			 */
			ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool"); // vì kết quả trả về 1 đối tượng nên
																					// phải ép kiểu

			// Khởi tạo đối tượng thực thi chức năng
			UserControl uc = new UserControl(cp);

			if (cp == null) {
				application.setAttribute("CPool", uc.getCP());

			}
			
			// Thực hiện đăng nhập
			UserObject user = uc.getUserObject(name, pass);

			// Trả về kết nối
			uc.releaseConnection();
			
			
			if (user!=null) {
				// Tham chiếu phiên làm việc mới(session)
				// Không gian bộ nhớ nhưng đại diện cho phía client
				HttpSession session = request.getSession(true);
				
				// Đưa thông tin đăng nhập vào phiên
				session.setAttribute("userLogined", user);
				
				// Trở về giao diện chính
				response.sendRedirect("/adv/view");
			}
			else {
				response.sendRedirect("/adv/user/login?err=notok");
			}
			
			
		} else {
			// Thông báo lỗi tham số lấy giá trị

			response.sendRedirect("/adv/user/login?err=param");
		}
	}

}
