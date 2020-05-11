package com.biz.shop.persistance;

import java.util.List;

import com.biz.shop.domain.UserVO;

public interface UserDao {

	public UserVO findByUserName(String username);

	public void create_table(String create_table);

	public int insert(UserVO userVO);

	public UserVO findById(long id);

	public UserVO update(String username);

	public int update(UserVO userVO);

	public List<UserVO> selectAll();

	public UserVO findByUserEmail(String email);
	
	

}
