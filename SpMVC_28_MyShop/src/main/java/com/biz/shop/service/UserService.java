package com.biz.shop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.biz.shop.domain.AuthVO;
import com.biz.shop.domain.UserVO;
import com.biz.shop.persistance.AuthDao;
import com.biz.shop.persistance.UserDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserDao userDao;
	private final AuthDao authDao;
	
	public UserService(PasswordEncoder passwordEncoder,
			UserDao userDao,
			AuthDao authDao) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
		this.authDao = authDao;
		
		
		String create_user_table = 
				"CREATE TABLE IF NOT EXISTS tbl_users ( " +
				" id bigint PRIMARY KEY AUTO_INCREMENT, " +
				" user_name varchar(50) UNIQUE, " +
				" user_pass varchar(125),  " +
				" enabled boolean DEFAULT true, " +
				" email varchar(50), " +
				" phone varchar(20), " +
				" address varchar(125) " +
				") ";
		
		String create_auth_table = 
				"CREATE TABLE IF NOT EXISTS authorities ( " +
				" id bigint PRIMARY KEY AUTO_INCREMENT, " +
				" username varchar(50), " +
				" authority varchar(50) " +
				") ";
		
		userDao.create_table(create_user_table);
		userDao.create_table(create_auth_table);
		
	}
	
	@Transactional
	public int insert(String username, String password) {
		String encPassword = passwordEncoder.encode(password);
		UserVO userVO = UserVO.builder()
				.username(username)
				.password(encPassword)
				.build();
		
		int ret = userDao.insert(userVO);
		List<AuthVO> authList = new ArrayList();
		authList.add(AuthVO.builder()
				.username(userVO.getUsername())
				.authority("ROLE_USER")
				.build());
		authList.add(AuthVO.builder()
				.username(userVO.getUsername())
				.authority("USER")
				.build());
		
		authDao.insert(authList);
		
		return ret;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
	public int insert(UserVO userVO) {
		userVO.setEnabled(false);
		userVO.setAuthorities(null);
		
		String encPassword = passwordEncoder.encode(userVO.getPassword());
		userVO.setPassword(encPassword);
		
		String sRet;

		int ret = userDao.insert(userVO);
		
		return ret;
	}
	
	public boolean isExistsUserName(String username) {
		UserVO userVO = userDao.findByUserName(username);
		if(userVO != null && userVO.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
		
		return false;
	}
	
	public UserVO findById(long id) {
		UserVO userVO = userDao.findById(id);
		return userVO;
	}
	
	@Transactional
	public UserVO update(String username) {
		UserVO userVO = userDao.update(username);
		return userVO;
	}
	
	public boolean check_password(String password) {
		UserVO userVO = (UserVO) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		return passwordEncoder.matches(password, userVO.getPassword());
	}
	
	@Transactional
	public int update(UserVO userVO,String[] authList) {
		int ret = userDao.update(userVO);
		if(ret > 0) {
			List<AuthVO> authCollection = new ArrayList();
			for(String auth : authList) {
				if(!auth.isEmpty()) {
					AuthVO authVO = AuthVO.builder()
							.username(userVO.getUsername())
							.authority(auth)
							.build();
					authCollection.add(authVO);
				}
			}
			authDao.delete(userVO.getUsername());
			authDao.insert(authCollection);
		}
		return ret;
	}
	
	
	public int update(UserVO userVO) {
		Authentication oldAuth = SecurityContextHolder
				.getContext().getAuthentication();
		
		UserVO oldUserVO = (UserVO) oldAuth.getPrincipal();
		oldUserVO.setEmail(userVO.getEmail());
		oldUserVO.setPhone(userVO.getPhone());
		oldUserVO.setAddress(userVO.getAddress());
		
		int ret = userDao.update(oldUserVO);
		
		if(ret > 0) {
			Authentication newAuth = new UsernamePasswordAuthenticationToken
					(oldUserVO,
					oldAuth.getCredentials(),
					oldAuth.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		return ret;
	}
	
	
	private Collection<GrantedAuthority> getAuthorities(String[] authList){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(String auth : authList) {
			if(!auth.isEmpty()) {
				SimpleGrantedAuthority sAuth = new SimpleGrantedAuthority(auth);
				authorities.add(sAuth);
			}
		}
		return authorities;
	}
	
	@Transactional
	public List<UserVO> selectAll(){
		return userDao.selectAll();
	}
	
	public UserVO findByUserName(String username) {
		return userDao.findByUserName(username);
	}
	
	public UserVO findByEmail2(String email) {
		return userDao.findByUserEmail(email);
	}
	
	
}







