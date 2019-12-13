package com.parcom.news.model.news;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
class NewsDto {

   private String title;
   private String message;
}
