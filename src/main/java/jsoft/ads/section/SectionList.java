package jsoft.ads.section;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.ads.article.ArticleControl;
import jsoft.library.ORDER;
import jsoft.library.Utilities;
import jsoft.library.Utilities_date;
import jsoft.objects.ArticleObject;
import jsoft.objects.SectionObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class SectionList
 */
@WebServlet("/section/list")
public class SectionList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SectionList() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserObject user = (UserObject)request.getSession().getAttribute("userLogined");
		if (user == null) {
			response.sendRedirect("/adv/user/login");
		} else {
			view(request, response);
		}
	}
	
	protected void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType(CONTENT_TYPE);
		
		
		ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
		SectionControl sc = new SectionControl(cp);
		if (cp == null) {
			getServletContext().setAttribute("CPool", sc.getCP());
		}
		
		
		SectionObject similar = new SectionObject();
		Quartet<SectionObject, Integer, Byte, Boolean> infos = new Quartet<>(similar, 1, (byte)10, false);
		Pair<SECTION_ORDER, ORDER> so = new Pair<>(SECTION_ORDER.ID, ORDER.DESC);
		
		Pair<String, String> view = sc.viewSections(infos, so);
		
		
		PrintWriter out = response.getWriter();
		
		
		RequestDispatcher h = request.getRequestDispatcher("/header?pos=arsection");
		if (h != null) {
			h.include(request, response);
		}
		
		
		
		out.append("<main id=\"main\" class=\"main\">");

		RequestDispatcher error = request.getRequestDispatcher("/error");
		if (error != null) {
			error.include(request, response);
		}

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách chuyên mục</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Bài viết</li>");
		out.append("<li class=\"breadcrumb-item active\">Chuyên mục</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");

		out.append("<div class=\"card\">\n");
		out.append("<div class=\"card-body pt-3\">\n");
		
		
		
		
		
		
		out.append("<button type=\"button\" class=\"btn btn-primary mb-3\" data-bs-toggle=\"modal\" data-bs-target=\"#addSection\">");
		out.append("<i class=\"bi bi-folder-plus\"></i>&nbsp;Thêm chuyên mục");
		out.append("</button>");

		out.append("<div class=\"modal fade\" id=\"addSection\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"addSectionLabel\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-lg\">");
		out.append("<div class=\"modal-content\">");
		out.append("<form method=\"post\" action=\"/adv/section/list\" class=\"needs-validation\" novalidate>");
		out.append("<div class=\"modal-header text-bg-primary\">");
		out.append("<h1 class=\"modal-title fs-5\" id=\"addSectionLabel\">Thêm chuyên mục mới</h1>");
		out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body\">");

		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-lg-6\">");
		out.append("<label for=\"SectionNameVi\" class=\"form-label\">Tên chuyên mục (Vietnamese)</label>");
		out.append("<input type=\"text\" class=\"form-control\" id=\"SectionNameVi\" name=\"txtSectionNameVi\" required>");
		out.append("<div class=\"invalid-feedback\">Cần có tên chuyên mục!</div>");
		out.append("</div>"); // col-lg-6
		out.append("<div class=\"col-lg-6\">");
		out.append("<label for=\"SectionNameEn\" class=\"form-label\">Section name (English)</label>");
		out.append("<input type=\"text\" class=\"form-control\" id=\"SectionNameEn\" name=\"txtSectionNameEn\" required>");
		out.append("<div class=\"invalid-feedback\">Section name is required!</div>");
		out.append("</div>"); // col-lg-6
		out.append("</div>"); // row mb-3
		
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-lg-6\">");
		out.append("<label for=\"author\" class=\"form-label\">Tác giả</label>");
		out.append("<input type=\"text\" class=\"form-control\" id=\"author\" name=\"txtAuthor\" required>");
		out.append("<div class=\"invalid-feedback\">Cần có Tác giả!</div>");
		out.append("</div>"); // col-lg-6
		out.append("</div>");


		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<label for=\"notes\" class=\"form-label\">Ghi chú chuyên mục</label>");
		out.append("<textarea class=\"form-control\" name=\"txtNotes\" ></textarea>");
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
		
		
		
		
		out.append(view.getValue0());

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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
		
		String sectionNameVi = request.getParameter("txtSectionNameVi");
		String sectionNameEn = request.getParameter("txtSectionNameEn");
		String author = request.getParameter("txtAuthor");
		
		if ((sectionNameVi!=null && !sectionNameVi.equalsIgnoreCase("")) && (sectionNameEn!=null &&
			!sectionNameEn.equalsIgnoreCase("")) && (author!=null && !author.equalsIgnoreCase(""))) {
			
			String notes = request.getParameter("txtNotes");
			
			SectionObject nSection = new SectionObject();
			
			nSection.setSection_name(Utilities.encode(sectionNameVi));
			nSection.setSection_name_en(sectionNameEn);
			nSection.setSection_author(author);
			nSection.setSection_created_date(Utilities_date.getDate());
			nSection.setSection_notes(notes);
			
			
			ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
			SectionControl sc = new SectionControl(cp);
			if (cp == null) {
				getServletContext().setAttribute("CPool", sc.getCP());
			}
			
			boolean result = sc.addSection(nSection);
			
			sc.releaseConnection();
			
			if (result) {
				response.sendRedirect("/adv/section/list");
			} else {
				response.sendRedirect("/adv/section/list?err=add");
			}
			
		} else {
			response.sendRedirect("/adv/section/list?err=param");
		}
		
	}

}
