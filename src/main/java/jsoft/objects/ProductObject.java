package jsoft.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class ProductObject {
	public short product_id;
	public String product_name;
	public String product_image;
	public String product_price;
	public String product_discount_price;
	public boolean product_enable;
	public boolean product_delete;
	public short product_visited;
	public String product_total;
	public int product_manager_id;
	public String product_intro;
	public String product_notes;
	public String product_code;
	public String product_created_date;
	public String product_modified_date;
	public short product_pc_id;
	public short product_pg_id;
	public short product_ps_id;
	public boolean product_is_detail;
	public String product_deleted_date;
	public String product_deleted_author;
	public String product_promotion_price;
	public String product_sold;
	public String product_best_seller;
	public String product_promotion;
	public String product_price_calc_description;
	public short product_size;
	public String product_name_en;
	public int product_customer_id;
	public int product_perspective_id;
}
