package jsoft.ads.article;

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
import jsoft.library.ORDER;
import jsoft.objects.ArticleObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class ArticleList
 */
@WebServlet("/article/list")
public class ArticleList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
    public ArticleList() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UserObject user = (UserObject)request.getSession().getAttribute("userLogined");
    	
    	if (user == null) {
    		response.sendRedirect("/adv/user/login");
    	}
    	else {
    		view(request, response);
    	}
    }

	
	protected void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType(CONTENT_TYPE);
		
		// Tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
		ArticleControl ac = new ArticleControl(cp);
		if (cp==null) {
			getServletContext().setAttribute("CPool", ac.getCP());
		}
		
		
		// Lấy cấu trúc hiển thị danh sách
		ArticleObject similar = new ArticleObject();
		
		Quartet<ArticleObject, Integer, Byte, Boolean> infos = new Quartet<>(similar, (Integer) 1, (byte) 10, false);
		Pair<ARTICLE_ORDER, ORDER> order = new Pair<>(ARTICLE_ORDER.ID, ORDER.ASC);
		Pair<String, String> view = ac.viewArticles(infos, order);

		ac.releaseConnection();
		
		PrintWriter out = response.getWriter();
		
		RequestDispatcher h = request.getRequestDispatcher("/header?pos=arlist");
		if (h != null) {
			h.include(request, response);
		}
		
		out.append("<main id=\"main\" class=\"main\">");

		RequestDispatcher error = request.getRequestDispatcher("/error");
		if (error != null) {
			error.include(request, response);
		}

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách bài viết</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/adv/view\"><i class=\"bi bi-house\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Bài viết</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");

		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");

		out.append("<div class=\"card\">\n");
		out.append("<div class=\"card-body pt-3\">\n");
		
		
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		response.sendRedirect("/adv/article/list");
	}

}
