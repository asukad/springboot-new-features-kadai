package com.example.samuraitravel.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//追加コンテンツ
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRegisterForm {
private Integer houseId;

private Integer userId;

private String rating;

private String review;


}