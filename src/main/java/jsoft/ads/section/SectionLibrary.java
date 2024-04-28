package jsoft.ads.section;

import java.util.*;
import jsoft.library.*;
import jsoft.objects.*;
import org.javatuples.*;

public class SectionLibrary {
	public static Pair<String, String> viewSections(Quartet<SectionObject, Integer, Byte, Boolean> infos,
			Pair<ArrayList<SectionObject>, Short> datas) {
		
		ArrayList<SectionObject> items = datas.getValue0();

		StringBuilder tmp = new StringBuilder();

		tmp.append("<table class=\"table table-striped table-sm table-bordered\">\n");
		tmp.append("<thead>\n");
		tmp.append("<tr class=\"bg-success\">\n");
		tmp.append("<th scope=\"col\" class=\"text-center text-light\">ID</th>");
		tmp.append("<th scope=\"col\" class=\"text-center text-light\"><i class=\"bi bi-calendar-plus\"></i>&nbsp;Ngày tạo / cập nhật</th>");
		tmp.append("<th scope=\"col\" colspan=\"2\" class=\"text-center text-light\"><i class=\"bi bi-folder fs-6\"></i>&nbsp;Chuyên mục</th>");
		tmp.append("<th scope=\"col\" class=\"text-center text-light\"><i class=\"bi bi-person\"></i>&nbsp;Tác giả</th>");
		tmp.append("<th scope=\"col\" colspan=\"2\" class=\"text-center text-light\">Thực hiện</th>\n");
		tmp.append("</tr>\n");
		tmp.append("</thead>\n");
		tmp.append("<tbody>\n");
		for (SectionObject item : items) {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\" class=\"text-center\">" + item.getSection_id() + "</th>");
			tmp.append("<td class=\"align-middle text-center\">" + item.getSection_created_date() + "</td>");
			tmp.append("<td class=\"align-middle text-center\">" + item.getSection_name() + "</td>");
			tmp.append("<td class=\"align-middle text-center\">" + item.getSection_name_en() + "</td>");
			tmp.append("<td class=\"align-middle text-center\">" + item.getSection_author() + "</td>");
			tmp.append("<td class=\"align-middle text-center\"><a href=\"/adv/user/profiles?id="+item.getSection_id()+"&t=edit\" title=\"Sửa\" class=\"btn btn-info btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
			tmp.append("<td class=\"align-middle text-center\"><button title=\"Xoá\" class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delArticle"+item.getSection_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
			tmp.append("</tr>\n");
		}
		tmp.append("</tbody>\n");
		tmp.append("</table>\n");

		tmp.append("</div>\n");
		tmp.append("</div>");

		return new Pair<String, String>(tmp.toString(), "");
	}
	
	public static Pair<String, String> viewSectionsTrash(Quartet<SectionObject, Integer, Byte, Boolean> infos,
			Pair<ArrayList<SectionObject>, Short> datas) {
		
		ArrayList<SectionObject> items = datas.getValue0();

		StringBuilder tmp = new StringBuilder();

		tmp.append("<table class=\"table table-striped table-sm table-bordered\">\n");
		tmp.append("<thead>\n");
		tmp.append("<tr class=\"bg-success\">\n");
		tmp.append("<th scope=\"col\" class=\"text-center text-light\">ID</th>");
		tmp.append("<th scope=\"col\" class=\"text-center text-light\"><i class=\"bi bi-calendar-plus\"></i>&nbsp;Ngày tạo / cập nhật</th>");
		tmp.append("<th scope=\"col\" colspan=\"2\" class=\"text-center text-light\"><i class=\"bi bi-folder fs-6\"></i>&nbsp;Chuyên mục</th>");
		tmp.append("<th scope=\"col\" class=\"text-center text-light\"><i class=\"bi bi-person\"></i>&nbsp;Tác giả</th>");
		tmp.append("<th scope=\"col\" colspan=\"2\" class=\"text-center text-light\">Thực hiện</th>\n");
		tmp.append("</tr>\n");
		tmp.append("</thead>\n");
		tmp.append("<tbody>\n");
		for (SectionObject item : items) {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\" class=\"text-center\">" + item.getSection_id() + "</th>");
			tmp.append("<td class=\"align-middle text-center\">" + item.getSection_created_date() + "</td>");
			tmp.append("<td class=\"align-middle text-center\">" + item.getSection_name() + "</td>");
			tmp.append("<td class=\"align-middle text-center\">" + item.getSection_name_en() + "</td>");
			tmp.append("<td class=\"align-middle text-center\">" + item.getSection_author() + "</td>");
			tmp.append("<td class=\"align-middle text-center\"><a href=\"/adv/user/profiles?id="+item.getSection_id()+"&t=edit\" title=\"Sửa\" class=\"btn btn-info btn-sm\"><i class=\"bi bi-pencil-square\"></i></a></td>");
			tmp.append("<td class=\"align-middle text-center\"><button title=\"Xoá\" class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#delArticle"+item.getSection_id()+"\"><i class=\"bi bi-trash3\"></i></button></td>");
			tmp.append("</tr>\n");
		}
		tmp.append("</tbody>\n");
		tmp.append("</table>\n");

		tmp.append("</div>\n");
		tmp.append("</div>");

		return new Pair<String, String>(tmp.toString(), "");
	}
}
