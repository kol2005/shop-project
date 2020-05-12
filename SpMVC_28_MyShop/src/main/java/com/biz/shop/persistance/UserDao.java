package com.biz.shop.persistance;

import java.util.List;

import com.biz.shop.domain.UserDetailsVO;

public interface UserDao {

	public UserDetailsVO findByUserName(String username);

	public void create_table(String create_table);

	public int insert(UserDetailsVO userVO);

	public UserDetailsVO findById(long id);

	public UserDetailsVO update(String username);

	public int update(UserDetailsVO userVO);

	public List<UserDetailsVO> selectAll();

	public UserDetailsVO findByUserEmail(String email);
	
	

}
