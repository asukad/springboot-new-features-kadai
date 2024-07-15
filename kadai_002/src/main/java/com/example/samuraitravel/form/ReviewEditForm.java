package com.example.samuraitravel.form;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//追加コンテンツ
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEditForm {
	@NotNull
	private Integer id;
	
	private Integer houseId;
	
	private String rating;
	
	private String review;

}