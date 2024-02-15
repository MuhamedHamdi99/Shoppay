package com.global.DTO;
import lombok.Data;

@Data
public class ProductDTO {
	private Long Id;
	private String name;
	private Long category_id;
	private Double price;
	private String weight;
	private String imageName;
	private String Description;
}
