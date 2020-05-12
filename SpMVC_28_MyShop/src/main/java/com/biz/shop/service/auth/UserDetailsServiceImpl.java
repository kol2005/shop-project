package com.biz.shop.service.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biz.shop.domain.AuthVO;
import com.biz.shop.domain.UserDetailsVO;
import com.biz.shop.persistance.AuthDao;
import com.biz.shop.persistance.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{

	private final AuthDao authDao;
	private final UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		UserDetailsVO userVO = userDao.findByUserName(username);
		if(userVO == null) {
			throw new UsernameNotFoundException("UserName이 없습니다");
		}
		
		userVO.setAccountNonExpired(true);
		userVO.setAccountNonLocked(true);
		userVO.setCredentialsNonExpired(true);
		
		userVO.setAuthorities(this.getAuthorities(username));
		
		userVO.setPhone("010-1111-1111");
		userVO.setEmail("ggg@naver.com");
		userVO.setAddress("광주광역시");
		
		return userVO;
	}

	private Collection<GrantedAuthority> getAuthorities(String username) {
		List<AuthVO> authList = authDao.findByUserName(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(AuthVO vo : authList) {
			SimpleGrantedAuthority sAuth = new SimpleGrantedAuthority(vo.getAuthority());
			authorities.add(sAuth);
		}
		
		return authorities;
	}

}
