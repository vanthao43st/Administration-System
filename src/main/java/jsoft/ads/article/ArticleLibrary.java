package jsoft.ads.article;

import java.util.*;

import jsoft.library.Utilities;
import jsoft.objects.*;
import org.javatuples.*;

public class ArticleLibrary {
	public static Pair<String, String> viewArticles (Pair<ArrayList<ArticleObject>, Short> datas, 
			Quartet<ArticleObject, Integer, Byte, Boolean> infos){
		
		ArrayList<ArticleObject> items = datas.getValue0();
		
		StringBuilder tmp = new StringBuilder();
		tmp.append("<table class=\"table table-striped table-sm table-bordered\">\n");
		tmp.append("<thead>\n");
		tmp.append("<tr class=\"bg-secondary\">");
		tmp.append("<th scope=\"col\" class=\"text-center text-light\">ID</th>");
		tmp.append("<th scope=\"col\" class=\"text-center text-light\"><i class=\"bi bi-calendar-plus\"></i>&nbsp;Ngày tạo</th>");
		tmp.append("<th scope=\"col\" class=\"text-center text-light\">Tiêu đề</th>");
		tmp.append("<th scope=\"col\" class=\"text-center text-light\"><i class=\"bi bi-folder2 fs-6\"></i>&nbsp;Thể loại</th>");
		tmp.append("<th scope=\"col-2\" class=\"text-center text-light\"><i class=\"bi bi-pencil-square\"></i>&nbsp;Tác giả</th>");
		tmp.append("<th scope=\"col\" colspan=\"3\" class=\"text-center text-light\">Thực hiện</th>\n");
		tmp.append("</tr>\n");
		tmp.append("</thead>\n");
		tmp.append("<tbody>\n");
		for (ArticleObject item : items) {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\">"+item.getArticle_id()+"</th>");
			tmp.append("<td class=\"align-middle\">"+item.getArticle_created_date()+"</td>");
			tmp.append("<td class=\"align-middle\">"+item.getArticle_title()+"</td>");
			tmp.append("<td class=\"align-middle\">"+item.getCategory_name()+"</td>");
			tmp.append("<td class=\"align-middle\">"+item.getArticle_author_name()+"</td>");
			tmp.append("<td class=\"align-middle\"><a href=\"/adv/article/upload?id="+item.getArticle_id()+" &t=over\" title=\"Chi tiết\" class=\"btn btn-primary btn-sm\"><i class=\"bi bi-eye\"></i></a></td>");
			tmp.append("<td class=\"align-middle\"><a href=\"/adv/article/upload?id="+item.getArticle_id()+" &t=edit\" title=\"Sửa\" class=\"btn btn-success btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
			tmp.append("<td class=\"align-middle\"><button title=\"Xoá\" class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delArticle"+item.getArticle_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
//			tmp.append(ArticleLibrary.getDelModal(item, false));
			tmp.append("</tr>\n");
		}
		tmp.append("</tbody>\n");
		tmp.append("</table>\n");
		
		tmp.append("</div>\n");
		tmp.append("</div>");
		
		
		
		return new Pair<>(tmp.toString(), ArticleLibrary.viewVisitedChart(items));
	}
	
	
	private static String viewVisitedChart(ArrayList<ArticleObject> items) {

		StringBuilder values = new StringBuilder();
		StringBuilder names = new StringBuilder();
		items.forEach(item -> {
			values.append(item.getArticle_visited());
			names.append("'" + Utilities.decode(item.getArticle_title()) + "'");

			if (items.indexOf(items) < items.size() - 1) {
				values.append(",");
				names.append(",");
			}
		});

		StringBuilder tmp = new StringBuilder();

		tmp.append("<div class=\"card\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<h5 class=\"card-title\">Biểu đồ lượt truy cập</h5>");
		tmp.append("<div id=\"barChart\"></div>");
		tmp.append("<script>");
		tmp.append("document.addEventListener(\"DOMContentLoaded\", () => {");
		tmp.append("new ApexCharts(document.querySelector(\"#barChart\"), {");
		tmp.append("series: [{");
		tmp.append("name: 'Số lượt xem',");
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
	
	
	
//	public static Pair<String, String> viewArticlesCategory (Pair<ArrayList<ArticleObject>, Short> datas, 
//			Quartet<ArticleObject, Integer, Byte, Boolean> infos){
//		
//		ArrayList<ArticleObject> items = datas.getValue0();
//		
//		StringBuilder tmp = new StringBuilder();
//		tmp.append("<table class=\"table table-striped table-sm table-bordered\">\n");
//		tmp.append("<thead>\n");
//		tmp.append("<tr class=\"bg-success\">\n");
//		tmp.append("<th scope=\"col\" class=\"align-middle text-center text-light\">ID</th>");
//		tmp.append("<th scope=\"col\" class=\"align-middle text-center text-light\"><i class=\"bi bi-calendar-plus\"></i>&nbsp;Ngày tạo / cập nhật</th>");
//		tmp.append("<th scope=\"col\" colspan=\"2\" class=\"align-middle text-center text-light\"><i class=\"bi bi-folder fs-6\"></i>&nbsp;Thể loại</th>");
//		
//		tmp.append("<th scope=\"col\" class=\"text-center text-light\">");
//		tmp.append("<div class=\"input-group\">");
//		tmp.append("<span class=\"input-group-text fw-semibold title_section\">");
//		tmp.append("<i class=\"bi bi-folder2 fs-6\"></i>");
//		tmp.append("</span>");
//		tmp.append("<select class=\"form-select\">");
//		tmp.append("<option selected>---Chọn---</option>");
//		tmp.append("<option value=\"1\">One</option>");
//		tmp.append("<option value=\"2\">Two</option>");
//		tmp.append("<option value=\"3\">Three</option>");
//		tmp.append("</select>");
//		tmp.append("</th>");
//		
//		tmp.append("<th scope=\"col\" class=\"align-middle text-center text-light\"><i class=\"bi bi-person\"></i>&nbsp;Quản lý</th>");
//		tmp.append("<th scope=\"col\" colspan=\"2\" class=\"align-middle text-center text-light\">Thực hiện</th>\n");
//		tmp.append("</tr>\n");
//		tmp.append("</thead>\n");
//		tmp.append("<tbody>\n");
//		for (ArticleObject item : items) {
//			tmp.append("<tr>");
//			tmp.append("<th scope=\"row\" class=\"text-center\">"+item.getArticle_id()+"</th>");
//			tmp.append("<td class=\"align-middle text-center\">"+item.getArticle_created_date()+"</td>");
//			tmp.append("<td class=\"align-middle text-center\">"+item.getCategory_name()+"</td>");
//			tmp.append("<td class=\"align-middle text-center\">"+item.getCategory_name_en()+"</td>");
//			tmp.append("<td class=\"align-middle text-center\"></td>");
//			tmp.append("<td class=\"align-middle text-center\">"+item.getArticle_author_name()+"</td>");
//			tmp.append("<td class=\"align-middle text-center\"><a href=\"/adv/user/profiles?id="+item.getArticle_id()+" &t=edit\" title=\"Sửa\" class=\"btn btn-info btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
//			tmp.append("<td class=\"align-middle text-center\"><button title=\"Xoá\" class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delArticle"+item.getArticle_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
//			tmp.append("</tr>\n");
//		}
//		tmp.append("</tbody>\n");
//		tmp.append("</table>\n");
//		
//		tmp.append("</div>\n");
//		tmp.append("</div>");
//		
//		
//		
//		return new Pair<>(tmp.toString(), "");
//	}
	
	
	
	private static StringBuilder getDelModal(ArticleObject item, boolean isAbsoluted) {
		StringBuilder tmp = new StringBuilder();

		tmp.append("<div class=\"modal fade\" id=\"delUser"+item.getArticle_id()+"\" tabindex=\"-1\" aria-labelledby=\"delLabel"+item.getArticle_id()+"\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header text-bg-danger\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"delLabel"+item.getArticle_id()+"\"><i class=\"bi bi-exclamation-triangle\"></i> Xoá người sử dụng</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body text-center\">");
		tmp.append("<h3 class=\"text-danger\">Bạn có chắc chắn muốn xoá?</h3>");
		tmp.append("<p class=\"my-3\">"+item.getArticle_title()+" ("+Utilities.decode(item.getArticle_title_en())+")</p>");

		if (isAbsoluted) {
			tmp.append("<h4 class=\"text-danger\">Người sử dụng sẽ bị xoá khỏi hệ thống, không thể phục hồi lại.</h4>");
		}

		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer text-bg-light\">");
		if (isAbsoluted) {
			tmp.append("<form method=\"post\" action=\"/adv/article/profiles?id="+item.getArticle_id()+"&dr=delabs\"> ");
		} else {
			tmp.append("<form method=\"post\" action=\"/adv/article/profiles?id="+item.getArticle_id()+"&dr=del\"> ");
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
}
