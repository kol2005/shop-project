package com.biz.shop.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.biz.shop.domain.UserDetailsVO;

public class AuthProviderImpl implements AuthenticationProvider{

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsServiceImpl userDService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		UserDetailsVO userVO = (UserDetailsVO) userDService.loadUserByUsername(username);
		if(!passwordEncoder.matches(password.trim(), userVO.getPassword().trim())) {
			throw new BadCredentialsException("비밀번호 오류");
		}
		
		if(!userVO.isEnabled()) {
			throw new BadCredentialsException(username + ": 접근권한 없음");
		}
		
		return new UsernamePasswordAuthenticationToken(userVO, null,userVO.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
