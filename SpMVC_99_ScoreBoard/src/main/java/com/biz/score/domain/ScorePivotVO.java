package com.biz.score.domain;

import org.apache.ibatis.type.Alias;

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
@Alias("scorepivotVO")
public class ScorePivotVO {

	private String s_num;
	private int s_kor;//국
	private int s_eng;//영
	private int s_math;//수
	private int s_si;//과
	private int s_his;//국
	
}
