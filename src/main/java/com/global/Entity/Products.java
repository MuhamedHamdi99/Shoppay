package com.global.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id",referencedColumnName = "category_id")
	private Category category;
	
	private Double price;
	private String weight;
	private String imageName;
	private String Description;
	

}
