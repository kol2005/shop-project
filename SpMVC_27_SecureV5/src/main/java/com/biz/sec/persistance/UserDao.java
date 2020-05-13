package com.biz.sec.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.sec.domain.UserDetailsVO;

public interface UserDao {

	public List<UserDetailsVO> selectAll();
	
	public void create_table(String create_table);
	
	
	public UserDetailsVO findByUserName(String username);
	
	public UserDetailsVO findByUserEmail(String email);
	
	public int insert(UserDetailsVO userVO);

	
	public UserDetailsVO findById(long id);

	public UserDetailsVO update(String username);

	public int update(UserDetailsVO userVO);

	public int re_update(UserDetailsVO userVO);
	
}