package com.biz.shop.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.biz.shop.domain.ProFileVO;

public interface FileUpDao {

	@Select("SELECT * FROM tbl_profile")
	public List<ProFileVO> selectAll();
	
	@Select("SELECT * FROM tbl_profile WHERE file_p_code = #{file_p_code}")
	public List<ProFileVO> findByPCode(String file_p_code);
	
	@Select("SELECT * FROM tbl_profile WHERE id = #{id}")
	public ProFileVO findById(long id);
	
	@Insert("INSERT INTO tbl_profile (file_p_code, file_origin_name, file_upload_name)"
			+ " VALUES (#{file_p_code}, #{file_origin_name}, #{file_upload_name} )")
	public int insert(ProFileVO profileVO);
	
	@Delete("DELETE FROM tbl_profile WHERE id = #{id}")
	public int findByIdDelete(long id);
	
	@Delete("DELETE FROM tbl_profile WHERE file_p_code = #{file_p_code}")
	public int findByPCodeDelete(String file_p_code);

	
	
	
}






