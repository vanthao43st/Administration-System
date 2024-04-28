package jsoft.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class SectionObject {
	private short section_id;
	private String section_name;
	private String section_notes;
	private String section_created_date;
	private int section_manager_id;
	private boolean section_enable;
	private boolean section_delete;
	private String section_last_modified;
	private int section_created_author_id;
	private String section_name_en;
	private byte section_language;
	private String section_author;
}
