package jsoft.ads.article;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsoft.ConnectionPool;
import jsoft.ads.user.UserControl;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class ArticleAdd
 */
@WebServlet("/article/upload")
public class ArticleEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if (user == null) {
			response.sendRedirect("/adv/user/login");
		} else {
			view(request, response);
		}
	}

	protected void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		request.setCharacterEncoding("UTF-8");

		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		ArticleControl ac = new ArticleControl(cp);
		if (cp == null) {
			getServletContext().setAttribute("CPool", ac.getCP());
		}

		PrintWriter out = response.getWriter();

		RequestDispatcher h = request.getRequestDispatcher("/header?pos=aradd");
		if (h != null) {
			h.include(request, response);
		}
		
		
		out.append("<main id=\"main\" class=\"main\">");

		RequestDispatcher error = request.getRequestDispatcher("/error");
		if (error != null) {
			error.include(request, response);
		}

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Thêm mới</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Bài viết</li>");
		out.append("<li class=\"breadcrumb-item active\">Thêm mới</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");

		out.append("<div class=\"card bg-light\">\n");
		out.append("<div class=\"card-body pt-3\">\n");
		
		
		
		
		
		
		
		out.append("<div class=\"container-lg\">");
		out.append("<div class=\"row bg-white\">");
		out.append("<div class=\"col-lg-12 text-bg-white\">");
		out.append("<!-- form -->");
		out.append("<form action=\"\" class=\"loginview\" name=\"\" method=\"\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-12 text-bg-primary py-3 text-center fw-bolder text-uppercase\">");
		out.append("</div>");
		out.append("</div>");
		out.append("");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-9\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-6\">");
		out.append("<div class=\"mb-3 mt-3\">");
		out.append("<div class=\"mb-3\">");
		out.append("<div class=\"input-group\">");
		out.append("<span class=\"input-group-text fw-semibold title_section\">");
		out.append("<i class=\"bi bi-folder fs-6\"></i>&nbsp;");
		out.append("Chuyên mục");
		out.append("</span>");
		out.append("<select class=\"form-select\" aria-label=\"Default select example\">");
		out.append("<option selected>---Chọn---</option>");
		out.append("<option value=\"1\">One</option>");
		out.append("<option value=\"2\">Two</option>");
		out.append("<option value=\"3\">Three</option>");
		out.append("</select>");
		out.append("<span class=\"input-group-text\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\">");
		out.append("</span>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div class=\"col-lg-6 category\">");
		out.append("<div class=\"mb-3 mt-3\">");
		out.append("<div class=\"input-group\">");
		out.append("<span class=\"input-group-text fw-semibold title_category\">");
		out.append("<i class=\"bi bi-folder2 fs-6\"></i>&nbsp;");
		out.append("Thể loại");
		out.append("</span>");
		out.append("<select class=\"form-select\" aria-label=\"Default select example\">");
		out.append("<option selected>---Chọn---</option>");
		out.append("<option value=\"1\">One</option>");
		out.append("<option value=\"2\">Two</option>");
		out.append("<option value=\"3\">Three</option>");
		out.append("</select>");
		out.append("<span class=\"input-group-text\">");
		out.append("<input class=\"form-check-input\" type=\"checkbox\">");
		out.append("</span>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("");
		out.append("");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"input-group mb-3\">");
		out.append("<span class=\"input-group-text fw-semibold title\">Tiêu đề</span>");
		out.append("<input type=\"text\" class=\"form-control\">");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"input-group mb-3\">");
		out.append("<span class=\"input-group-text fw-semibold title_summary\">Tóm tắt</span>");
		out.append("<textarea class=\"form-control\" rows=\"7\"></textarea>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"input-group mb-3\">");
		out.append("<span class=\"input-group-text fw-semibold other_select\">Tuỳ chọn khác</span>");
		out.append("<span class=\"input-group-text fw-semibold\">");
		out.append("<input type=\"checkbox\">&nbsp;");
		out.append("Nổi bật?");
		out.append("</span>");
		out.append("<span class=\"input-group-text fw-semibold\">");
		out.append("<input type=\"checkbox\">&nbsp;");
		out.append("Trang chủ?");
		out.append("</span>");
		out.append("<span class=\"input-group-text fw-semibold\">");
		out.append("<input type=\"checkbox\">&nbsp;");
		out.append("Song ngữ/tiếng anh?");
		out.append("</span>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("");
		out.append("");
		out.append("");
		out.append("<div class=\"col-lg-3 mt-3 mb-3\">");
		out.append("<label for=\"image\" class=\"form-control text-bg-primary mb-3 label_add_image\">");
		out.append("<i class=\"bi bi-image-alt\"></i>&nbsp;");
		out.append("Chọn ảnh chính");
		out.append("</label>");
		out.append("<input type=\"file\" id=\"image\" class=\"file_image col-lg-12\">");
		out.append("</div>");
		out.append("</div>");
		out.append("");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div id=\"PDetil\"></div>");
		out.append("</div>");
		out.append("</div>");
		out.append("");
		out.append("<div class=\"row mb-3 me-2 button_add_article\">");
		out.append("<button type=\"submit\" class=\"btn btn-primary\">");
		out.append("<i class=\"bi bi-bag-plus-fill\"></i>&nbsp;");
		out.append("Thêm mới");
		out.append("</button>");
		out.append("</div>");
		out.append("");
		out.append("</form>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		
		out.append("<script src=\"https://cdn.ckeditor.com/ckeditor5/40.1.0/super-build/ckeditor.js\"></script>");		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		out.append("</div>");// card-body
		out.append("</div>");// card

		out.append("</div>");// col-lg-12
		out.append("</div>");// row

		out.append("</section>");

		out.append("</main><!-- End #main -->");

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
		doGet(request, response);
	}

}
