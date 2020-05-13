package com.biz.shop.domain;

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
public class AuthorityVO {

	private long id;
	/*
	 * 필수항목 필드변수
	 * ------------------------
	 */
	private String username;
	private String authority;
	
}
