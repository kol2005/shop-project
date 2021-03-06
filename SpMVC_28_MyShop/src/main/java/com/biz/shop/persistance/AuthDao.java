package com.biz.shop.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.shop.domain.AuthVO;

public interface AuthDao {

	@Select("SELECT * FROM authorities WHERE username = #{username}")
	List<AuthVO> findByUserName(String username);

	int insert(List<AuthVO> authList);
	
	@Delete("DELETE FROM authorities WHERE username = #{username}")
	int delete(String username);
}
