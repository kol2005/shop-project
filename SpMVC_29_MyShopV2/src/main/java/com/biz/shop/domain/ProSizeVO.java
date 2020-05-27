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
public class ProSizeVO {

	private long s_seq;
	private String p_code;
	private String s_size;
	
	// tbl_options 테이블에 있는 칼럼으로
	// JOIN을 하여 size이름을 가져오기 위한 변수
	private String o_name;
	
}
