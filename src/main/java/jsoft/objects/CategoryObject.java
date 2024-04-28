package jsoft.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class CategoryObject extends SectionObject{
	private short category_id;
	private String category_name;
	private short category_section_id;
	private String category_notes;
	private String category_created_date;
	private int category_created_author_id;
	private String category_last_modified;
	private int category_manager_id;
	private boolean category_enable;
	private boolean category_delete;
	private String category_image;
	private String category_name_en;
	private byte category_language;
}
