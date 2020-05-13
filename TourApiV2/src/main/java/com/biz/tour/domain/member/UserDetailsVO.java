package com.biz.tour.domain.member;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class UserDetailsVO implements UserDetails{
	//VO 객체를 map에 담아서 req | res에 실어서 보낼때 객체를 문자열형으로 변환하는 과정이 있다.
		//이과정을 serialize 라고 하는데, 각 변환된 문자열이 서로 흐트러지지 않도록 설정하는 키값
		private static final long serialVersionUID = 1L;
		
		private long id;
		private String username;
		private String password;
		private boolean enabled;
		
		private boolean accountNonExpired;
		private boolean accountNonLocked;
		private boolean credentialsNonExpired;
		
		private Collection<? extends GrantedAuthority> authorities;
		
		private String email;
		private String phone;
		private String address;
}
