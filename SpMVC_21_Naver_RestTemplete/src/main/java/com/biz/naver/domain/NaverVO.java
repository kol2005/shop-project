package com.biz.naver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NaverVO {

	// 뉴스
	private String title;//: 콘크리트연합회, <b>코로나</b>19 피해복구 지원 성금,
	private String originallink;//: http://www.naeil.com/news_view/?id_art=343967,
	private String link;//: http://www.naeil.com/news_view/?id_art=343967,
	private String description;//: 성금은 <b>코로나</b>19 발생으로 가장 큰 피해를 입은 대구·경북지역 중소기업과 소외계층에 지원된다. 김동우(왼쪽 세번째) 한국콘크리트공업협동조합 연합회장이 김기문(왼쪽 네번째) 중소기업중앙회장에게 성금을... ,
	private String pubDate;//: Wed, 18 Mar 2020 13:35:00 +0900
	
	// 영화
	private String image;
	private String subtitle;
	private String director;
	private String actor;
	private String userRating;
	
	// 책
	private String author;
	private String price;
	private String discount;
	private String publisher;
	private String pubdate;
	private String isbn;
}
