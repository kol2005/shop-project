package com.biz.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * @Email : email형식
 * @NotBlank, @NotNull, @NotEmpty : 공백형식
 * @Null : null 일 경우만 통과
 * @Size(max, min)
 * @Max()
 * @Min()
 * @DecimalMax(x) : x 값 이하의 실수
 * @DecimalMin(x) : x 값 이상의 실수
 * @Digits(정수) : 정수 자릿수 검사
 * @Digits(숫자, fraction=y) : 숫자 자릿수 이하이면서 소수점 y 자릿수 이하
 * @Pattern(ragexp = "\\d{1,15}" ) 1 부터 15 자리까지의 숫자만 가능
 * 	전화번호를 순수 숫자로만 입력받고 싶을때
 * 	전체가 숫자로만 이루어지고 숫자 15자릿수 미만
 * @Pattern(ragexp = "\\d[30-55]" ) 30 ~ 55 까지만 가능
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
@Table(name="tbl_product", schema = "emsDB")
public class ProductVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="p_id")
	long id;
	
	// 입력값이 공백일 경우 error
	@NotBlank(message = "* 상품코드는 공백이 되면 아니되옵니다")
	@NotEmpty
	@NotNull
	//문자열의 길이가 맞지 않을 경우
	// Size(min,max)
	// Min(), Max()
	//@Max(13)
	@Size(max = 13, message = "* 상품코드는 13자리 이하만 가능하옵니다")
	@Column(name="p_code",length = 13,unique = true,nullable = false)// 길이는 13자리,UNIQUE,NOT NULL
	private String p_code;
	
	// @phoneNumber() : 전화번호 형식 062-111-1234
	// @Pattern(ragexp = "/(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/")
	// 문자열이 email형식과 맞지 않을 경우
	// @Email()
	@NotBlank(message = "* 상품이름은 공백이 될수 없읍니다")
	@Column(name="p_name")
	private String p_name;
	
	@Size(min=5,max=5,message="* 품목 코드를 확인하세요")
	@Column(name="p_bcode",length = 5)
	private String p_bcode;

	@Size(min=5,max=5,message="* 거래처 코드를 확인하세요")
	@Column(name="p_dcode",length = 5)
	private String p_dcode;
	
	@Column(name="p_iprice")
	private int p_iprice;
	
	@Column(name="p_oprice")
	private int p_oprice;
	
	@Column(name="p_detail")
	@Type(type = "text")
	private String p_detail;
	
}
