package jsoft.ads.main;

import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sidebar
 */
@WebServlet("/sidebar")
public class sidebar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public sidebar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		HashMap<String, String> collapsed = new HashMap<String, String>();
		HashMap<String, String> show = new HashMap<String, String>();
		HashMap<String, String> active = new HashMap<String, String>();

		// Tìm tham số xác định vị trí menu
		String pos = request.getParameter("pos");
		if (pos != null) {
			String module = pos.substring(0, 2);
			String action = pos.substring(2);

			switch (module) {
			case "ur":
				collapsed.put("user", "");
				show.put("user", "show");
				switch (action) {
				case "list":
					String trash = request.getParameter("trash");
					if (trash != null) {
						active.put("utrash", "class=\"active\"");
					} else {
						active.put("list", "class=\"active\"");
					}

					break;
				case "update":

					break;
				case "trash":

					break;
				case "profiles":
					active.put("profiles", "class=\"active\" ");
					break;

				}
				break;
			case "ar":
				collapsed.put("article", "");
				show.put("article", "show");
				switch (action) {
				case "list":
					active.put("list", "class=\"active\"");
					break;
				case "add":
					active.put("add", "class=\"active\"");
					break;
				case "section":
					active.put("section", "class=\"active\"");
					break;
				case "category":
					active.put("category", "class=\"active\"");
					break;
				case "trash":
					active.put("trash", "class=\"active\"");
					break;
				}

				break;
			case "pr":

				break;

			case "or":

			}

		} else {
			collapsed.put("Dashboard", "");
		}

		out.append("<!-- ======= Sidebar ======= -->");
		out.append("<aside id=\"sidebar\" class=\"sidebar\">");

		out.append("<ul class=\"sidebar-nav\" id=\"sidebar-nav\">");

		out.append("<li class=\"nav-item\">");

		
		//-------------------------------------------Dashboard------------------------------------------------//
		out.append("<a class=\"nav-link " + collapsed.getOrDefault("Dashboard", "collapsed") + "\" href=\"/adv/view\">");
		out.append("<i class=\"bi bi-house\"></i>");
		out.append("<span>Dashboard</span>");
		out.append("</a>");
		out.append("</li><!-- End Dashboard Nav -->");
		
		//------------------------------------------END (Dashboard)-------------------------------------------//

		
		
		
		//------------------------------------------- USER----------------------------------------------------//
		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link " + collapsed.getOrDefault("user", "collapsed") + "\" data-bs-target=\"#user-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append("<i class=\"bi bi-menu-button-wide\"></i><span>Người sử dụng</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("</a>");

		out.append("<ul id=\"user-nav\" class=\"nav-content collapse " + show.getOrDefault("user", "") + "\" data-bs-parent=\"#sidebar-nav\">");
		out.append("<li>");
		out.append("<a href=\"/adv/user/list\" " + active.getOrDefault("list", "") + " >");
		out.append("<i class=\"bi bi-circle\"></i><span>Danh sách</span>");
		out.append("</a>");
		out.append("</li>");

		out.append("<li>");
		out.append("<a href=\"/adv/user/profiles\" " + active.getOrDefault("profiles", "") + ">");
		out.append("<i class=\"bi bi-circle\"></i><span>Cập nhật</span>");
		out.append("</a>");
		out.append("</li>");

		out.append("<li>");
		out.append("<a href=\"/adv/user/list?trash\" " + active.getOrDefault("utrash", "") + ">");
		out.append("<i class=\"bi bi-circle\"></i><span>Thùng rác</span>");
		out.append("</a>");
		out.append("</li>");

		out.append("</ul>");
		out.append("</li><!-- End Components Nav -->");
		
		//------------------------------------------------END (USER)------------------------------------------//

		
		
		
		//-------------------------------------------------ARTICLE--------------------------------------------//
		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link " + collapsed.getOrDefault("article", "collapsed") + "\" data-bs-target=\"#article-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append("<i class=\"bi bi-journal-text\"></i><span>Bài viết & Tin tức</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("</a>");

		out.append("<ul id=\"article-nav\" class=\"nav-content collapse " + show.getOrDefault("article", "") + "\" data-bs-parent=\"#sidebar-nav\">");
		out.append("<li>");
		out.append("<a href=\"/adv/article/list\" " + active.getOrDefault("list", "") + " >");
		out.append("<i class=\"bi bi-list-ul fs-6\"></i><span>Danh sách</span>");
		out.append("</a>");
		out.append("</li>");

//		out.append("<li class=\"d-flex\" style=\"padding-right:0px\">");
//		out.append("<a href=\"/adv/article/upd\" style=\"margin-right:2px\" "+active.getOrDefault("add", "")+">");
//		out.append("<i class=\"bi bi-person-add fs-6\"></i> Thêm mới");
//		out.append("</a>");
//		out.append("<a href=\"#\" style=\"padding-left:0\">&nbsp| Chỉnh sửa");
//		out.append("</a>");
//		out.append("</li>");
		
		out.append("<li>");
		out.append("<a href=\"/adv/article/upload\" " + active.getOrDefault("add", "") + " >");
		out.append("<i class=\"bi bi-person-add fs-6\"></i><span>Thêm mới | Chỉnh sửa</span>");
		out.append("</a>");
		out.append("</li>");
		
		out.append("<li>");
		out.append("<a href=\"/adv/section/list\" " + active.getOrDefault("section", "") + ">");
		out.append("<i class=\"bi bi-folder fs-6\"></i><span>Chuyên mục</span>");
		out.append("</a>");
		out.append("</li>");

		out.append("<li>");
		out.append("<a href=\"/adv/article/category/list\" " + active.getOrDefault("category", "") + ">");
		out.append("<i class=\"bi bi-folder2 fs-6\"></i><span>Thể loại</span>");
		out.append("</a>");
		out.append("</li>");

		out.append("<li>");
		out.append("<a href=\"/adv/article/list?trash\" " + active.getOrDefault("trash", "") + ">");
		out.append("<i class=\"bi bi-trash3 fs-6\"></i><span>Bài viết đã xoá</span>");
		out.append("</a>");
		out.append("</li>");

		out.append("</ul>");
		out.append("</li><!-- End Forms Nav -->");
		
		//----------------------------------------------END (ARTICLE)-----------------------------------------//

		
		
		
		out.append("<li class=\"nav-item\">");
		out.append(
				"<a class=\"nav-link collapsed\" data-bs-target=\"#tables-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append(
				"<i class=\"bi bi-layout-text-window-reverse\"></i><span>Tables</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("</a>");
		out.append("<ul id=\"tables-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.append("<li>");
		out.append("<a href=\"tables-general.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>General Tables</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("<li>");
		out.append("<a href=\"tables-data.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>Data Tables</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("</ul>");
		out.append("</li><!-- End Tables Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append(
				"<a class=\"nav-link collapsed\" data-bs-target=\"#charts-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append("<i class=\"bi bi-bar-chart\"></i><span>Charts</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("</a>");
		out.append("<ul id=\"charts-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.append("<li>");
		out.append("<a href=\"charts-chartjs.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>Chart.js</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("<li>");
		out.append("<a href=\"charts-apexcharts.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>ApexCharts</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("<li>");
		out.append("<a href=\"charts-echarts.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>ECharts</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("</ul>");
		out.append("</li><!-- End Charts Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append(
				"<a class=\"nav-link collapsed\" data-bs-target=\"#icons-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append("<i class=\"bi bi-gem\"></i><span>Icons</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("</a>");
		out.append("<ul id=\"icons-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.append("<li>");
		out.append("<a href=\"icons-bootstrap.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>Bootstrap Icons</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("<li>");
		out.append("<a href=\"icons-remix.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>Remix Icons</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("<li>");
		out.append("<a href=\"icons-boxicons.html\">");
		out.append("<i class=\"bi bi-circle\"></i><span>Boxicons</span>");
		out.append("</a>");
		out.append("</li>");
		out.append("</ul>");
		out.append("</li><!-- End Icons Nav -->");

		out.append("<li class=\"nav-heading\">Pages</li>");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed\" href=\"users-profile.html\">");
		out.append("<i class=\"bi bi-person\"></i>");
		out.append("<span>Profile</span>");
		out.append("</a>");
		out.append("</li><!-- End Profile Page Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed\" href=\"pages-faq.html\">");
		out.append("<i class=\"bi bi-question-circle\"></i>");
		out.append("<span>F.A.Q</span>");
		out.append("</a>");
		out.append("</li><!-- End F.A.Q Page Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed\" href=\"pages-contact.html\">");
		out.append("<i class=\"bi bi-envelope\"></i>");
		out.append("<span>Contact</span>");
		out.append("</a>");
		out.append("</li><!-- End Contact Page Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed\" href=\"pages-register.html\">");
		out.append("<i class=\"bi bi-card-list\"></i>");
		out.append("<span>Register</span>");
		out.append("</a>");
		out.append("</li><!-- End Register Page Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed\" href=\"pages-login.html\">");
		out.append("<i class=\"bi bi-box-arrow-in-right\"></i>");
		out.append("<span>Login</span>");
		out.append("</a>");
		out.append("</li><!-- End Login Page Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed\" href=\"pages-error-404.html\">");
		out.append("<i class=\"bi bi-dash-circle\"></i>");
		out.append("<span>Error 404</span>");
		out.append("</a>");
		out.append("</li><!-- End Error 404 Page Nav -->");

		out.append("<li class=\"nav-item\">");
		out.append("<a class=\"nav-link collapsed \" href=\"pages-blank.html\">");
		out.append("<i class=\"bi bi-file-earmark\"></i>");
		out.append("<span>Blank</span>");
		out.append("</a>");
		out.append("</li><!-- End Blank Page Nav -->");

		out.append("</ul>");

		out.append("</aside><!-- End Sidebar-->");
		
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
