package jsoft.ads.user;

import jsoft.library.Utilities;
import jsoft.objects.*;
import java.util.*;
import org.javatuples.*;

public class UserLibrary {
	public static Pair<String, String> viewUsers(Pair<ArrayList<UserObject>, Short> datas,
			Quartet<UserObject, Integer, Byte, Boolean> infos) {

		ArrayList<UserObject> items = datas.getValue0();
		int total = datas.getValue1();

		UserObject similar = infos.getValue0();
		int page = infos.getValue1();
		byte totalPerPage = infos.getValue2();

		StringBuilder tmp = new StringBuilder();

		// tmp.append("<h5 class=\"card-title\">DANH SÁCH NGƯỜI SỬ DỤNG</h5>");

		tmp.append("<table class=\"table table-striped table-bordered table-sm mt-2 mb-3\">");
		tmp.append("<thead>\n");
		tmp.append("<tr>\n");
		tmp.append("<th scope=\"col\" class=\"text-center\">ID</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Tài khoản</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Họ tên</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Điện thoại</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Hộp thư</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Lần đăng nhập</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Ngày đăng nhập</th>");
		tmp.append("<th scope=\"col\" colspan=\"3\" class=\"text-center\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>");
		for (UserObject item : items) {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\" class=\"align-middle\">" + item.getUser_id()+"</th>");
			tmp.append("<td class=\"align-middle\">"+item.getUser_name()+"</td>");
			tmp.append("<td class=\"align-middle\">"+Utilities.decode(item.getUser_fullname())+"</td>");
			tmp.append("<td class=\"align-middle\">"+item.getUser_mobilephone()+"</td>");
			tmp.append("<td class=\"align-middle\">"+item.getUser_email()+"</td>");
			tmp.append("<td class=\"align-middle text-center\">"+item.getUser_logined()+"</td>");
			tmp.append("<td class=\"align-middle\">"+item.getUser_last_logined()+"</td>");
			tmp.append("<td class=\"align-middle\"><a href=\"/adv/user/profiles?id="+item.getUser_id()+"&t=over\" title=\"Chi tiết\" class=\"btn btn-primary btn-sm\"><i class=\"bi bi-eye\"></i></a></td>");
			tmp.append("<td class=\"align-middle\"><a href=\"/adv/user/profiles?id="+item.getUser_id()+"&t=edit\" title=\"Sửa\" class=\"btn btn-secondary btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
			tmp.append("<td class=\"align-middle\"><button title=\"Xoá\" class=\"btn btn-danger btn-sm\"data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
			tmp.append(UserLibrary.getDelModal(item, false));
			tmp.append("</tr>");
		}
		tmp.append("</tbody>");
		tmp.append("</table>");

		String key = similar.getUser_name();
		String url = "/adv/user/list?";
		if (key != null && !key.equalsIgnoreCase("")) {
			url += "keyword=" + key + "&";
		}

		tmp.append(UserLibrary.viewPaging(url, total, page, totalPerPage));

		Pair<String, String> result = new Pair<>(tmp.toString(), UserLibrary.viewLoginChart(items));

		return result;
	}

	
	public static Pair<String, String> viewTrashUsers(ArrayList<UserObject> items) {

		StringBuilder tmp = new StringBuilder();

		// tmp.append("<h5 class=\"card-title\">DANH SÁCH NGƯỜI SỬ DỤNG</h5>");

		tmp.append("<table class=\"table table-striped table-sm\">");
		tmp.append("<thead>\n");
		tmp.append("<tr>\n");
		tmp.append("<th scope=\"col\">ID</th>");
		tmp.append("<th scope=\"col\">Ngày xoá</th>");
		tmp.append("<th scope=\"col\">Tài khoản</th>");
		tmp.append("<th scope=\"col\">Họ tên</th>");
		tmp.append("<th scope=\"col\">Điện thoại</th>");
		tmp.append("<th scope=\"col\">Hộp thư</th>");
		tmp.append("<th scope=\"col\">Người xoá</th>");
		tmp.append("<th scope=\"col\" colspan=\"2\" class=\"text-center\">Thực hiện</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>");
		for (UserObject item : items) {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\" class=\"align-middle\">"+item.getUser_id()+"</th>");
			tmp.append("<td class=\"align-middle\">"+item.getUser_last_modified()+"</td>");
			tmp.append("<td class=\"align-middle\">"+item.getUser_name()+"</td>");
			tmp.append("<td class=\"align-middle\">"+item.getUser_fullname()+"</td>");
			tmp.append("<td class=\"align-middle\">"+item.getUser_mobilephone()+"</td>");
			tmp.append("<td class=\"align-middle\">"+item.getUser_email()+"</td>");
			tmp.append("<td class=\"align-middle\">"+item.getUser_trash_id()+"</td>");

			tmp.append("<td class=\"align-middle\"><a href=\"/adv/user/profiles?id="+item.getUser_id()+"&dr=res\" title=\"Phục hồi\" class=\"btn btn-warning btn-sm\"><i class=\"bi bi-reply\"></i></a></td>");
			tmp.append("<td class=\"align-middle\"><button title=\"Xoá\" class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");

			tmp.append(UserLibrary.getDelModal(item, true));
			tmp.append("</tr>");
		}
		tmp.append("</tbody>");
		tmp.append("</table>");

		Pair<String, String> result = new Pair<>(tmp.toString(), UserLibrary.viewLoginChart(items));

		return result;
	}

	private static StringBuilder getDelModal(UserObject item, boolean isAbsoluted) {
		StringBuilder tmp = new StringBuilder();

		tmp.append("<div class=\"modal fade\" id=\"delUser"+item.getUser_id()+"\" tabindex=\"-1\" aria-labelledby=\"delLabel"+item.getUser_id()+"\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header text-bg-danger\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"delLabel"+item.getUser_id()+"\"><i class=\"bi bi-exclamation-triangle\"></i> Xoá người sử dụng</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body text-center\">");
		tmp.append("<h3 class=\"text-danger\">Bạn có chắc chắn muốn xoá?</h3>");
		tmp.append("<p class=\"my-3\">"+item.getUser_fullname()+" ("+Utilities.decode(item.getUser_fullname())+")</p>");

		if (isAbsoluted) {
			tmp.append("<h4 class=\"text-danger\">Người sử dụng sẽ bị xoá khỏi hệ thống, không thể phục hồi lại.</h4>");
		}

		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer text-bg-light\">");
		if (isAbsoluted) {
			tmp.append("<form method=\"post\" action=\"/adv/user/profiles?id="+item.getUser_id()+"&dr=delabs\"> ");
		} else {
			tmp.append("<form method=\"post\" action=\"/adv/user/profiles?id="+item.getUser_id()+"&dr=del\"> ");
		}

		tmp.append("<button type=\"submit\" class=\"btn btn-danger\"><i class=\"bi bi-exclamation-triangle\"></i> Xoá</button>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Huỷ</button>");
		tmp.append("</form>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");

		return tmp;
	}

	private static String viewLoginChart(ArrayList<UserObject> items) {

		StringBuilder values = new StringBuilder();
		StringBuilder names = new StringBuilder();
		items.forEach(item -> {
			values.append(item.getUser_logined());
			names.append("'" + item.getUser_name() + " (" + Utilities.decode(item.getUser_fullname()) + ")'");

			if (items.indexOf(items) < items.size() - 1) {
				values.append(",");
				names.append(",");
			}
		});

		StringBuilder tmp = new StringBuilder();

		tmp.append("<div class=\"card\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<h5 class=\"card-title\">Biểu đồ đăng nhập</h5>");
		tmp.append("<div id=\"barChart\"></div>");
		tmp.append("<script>");
		tmp.append("document.addEventListener(\"DOMContentLoaded\", () => {");
		tmp.append("new ApexCharts(document.querySelector(\"#barChart\"), {");
		tmp.append("series: [{");
		tmp.append("name: 'Số lần đăng nhập',");
		tmp.append("data: ["+values+"]");
		tmp.append("}],");
		tmp.append("chart: {type: 'bar', height: 500, fontFamily: 'Tahoma, sans-serif'},");
		tmp.append("plotOptions: {bar: {borderRadius: 4, horizontal: true,}},");
		tmp.append("dataLabels: {enabled: false},");

		tmp.append("xaxis: {");
		tmp.append("categories: ["+names+"],");
		tmp.append("labels: {");
		tmp.append("show: true,");
		tmp.append("style: {");
		tmp.append("colors: [],");
		tmp.append("fontSize: '15px',");
		tmp.append("fontFamily: 'Helvetica, Arial, sans-serif',");
		tmp.append("fontWeight: 600,");
		tmp.append("cssClass: 'apexcharts-xaxis-label',");
		tmp.append("},");
		tmp.append("}");
		tmp.append("},");

		tmp.append("yaxis: {");
		tmp.append("show: true,");
		tmp.append("labels: {");
		tmp.append("show: true,");
		tmp.append("align: 'right',");
		tmp.append("minWidth: 0,");
		tmp.append("maxWidth: 350,");
		tmp.append("style: {");
		tmp.append("colors: [],");
		tmp.append("fontSize: '14px',");
		tmp.append("fontFamily: 'Helvetica, Arial, sans-serif',");
		tmp.append("fontWeight: 400,");
		tmp.append("cssClass: 'apexcharts-yaxis-label',");
		tmp.append("},");
		tmp.append("},");
		tmp.append("}");
		tmp.append("}).render();");
		tmp.append("});");
		tmp.append("</script>");
		tmp.append("</div>");
		tmp.append("</div>");

		return tmp.toString();
	}

	private static StringBuilder viewPaging(String url, int total, int page, byte totalPerPage) {
		StringBuilder tmp = new StringBuilder();

		// Tính số trang
		short countPages = (short) (total / totalPerPage);

		if (total % totalPerPage != 0) {
			countPages++;
		}

		// Xác định sự hợp lệ của trang hiện tại
		if (page <= 0 || page > countPages) {
			page = 1;
		}

		tmp.append("<nav aria-label=\"...\">");
		tmp.append("<ul class=\"pagination justify-content-center\">");

		short pre = (short) (((page - 1) > 0) ? (page - 1) : 1);
		String dis = "";
		if (page == 1) {
			dis = "disabled";
		}
		tmp.append("<li class=\"page-item\"><a class=\"page-link "+dis+"\" href=\""+url+"p="+pre+"\"><span aria-hidden=\"true\">&laquo;</span></a></li>");
		// tmp.append("<li class=\"page-item\"><a class=\"page-link\"
		// href=\""+url+"?p=1\">1</a></li>");

		StringBuilder left = new StringBuilder();
		for (int i = page - 1; i > 0; i--) {
			if (page - i > 2) {
				break;
			}
			left.insert(0, "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"p="+i+"\">"+i+"</a></li>");
		}
		if (page >= 4) {
			left.insert(0, "<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">...</a></li>");
		}

		tmp.append(left);

		tmp.append("<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"#\">"+page+"</a></li>");

		StringBuilder right = new StringBuilder();
		for (int i = page + 1; i <= countPages; i++) {
			if (i - page > 2) {
				break;
			}
			right.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"p="+i+"\">"+i+"</a></li>");
		}

		if (countPages - page > 4) {
			right.append("<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">...</a></li>");
		}

		tmp.append(right);

		short next = (short) (((page + 1) <= countPages) ? (page + 1) : countPages);
		dis = "";
		if (page == countPages) {
			dis = "disabled";
		}
		tmp.append("<li class=\"page-item\"><a class=\"page-link "+dis+"\" href=\""+url+"p="+next+"\"><span aria-hidden=\"true\">&raquo;</span></a></li>");
		tmp.append("</ul>");
		tmp.append("</nav>");

		return tmp;
	}
}
