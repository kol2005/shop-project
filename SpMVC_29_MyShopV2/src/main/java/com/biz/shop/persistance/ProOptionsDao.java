package com.biz.shop.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.biz.shop.domain.ProColorVO;
import com.biz.shop.domain.ProOptionsVO;
import com.biz.shop.domain.ProSizeVO;

public interface ProOptionsDao {

	@Select("SELECT * FROM tbl_options WHERE o_division = 'COLOR' ")
	public List<ProOptionsVO> getColorList();
	
	@Select("SELECT * FROM tbl_options WHERE o_division = 'SIZE' ")
	public List<ProOptionsVO> getSizeList();
	
	/*
	 * mysql auto_increment로 되어 있는 s_seq 칼럼을
	 * @Options로 설정을 해주면
	 * insert를 수행한 후 ProSizeVO의 s_seq 변수에
	 * 새로 생성된 s_seq값을 담아서 이후 Service,Controller 등에서
	 * 사용할수 있도록 만들어준다.
	 */
	@Options(useGeneratedKeys = true,keyProperty = "s_seq")
	@Insert("INSERT INTO tbl_pro_size(p_code,s_size) "
			+ " VALUES (#{p_code}, #{s_size})" )
	public int insert_size(ProSizeVO proSizeVO);
	
	@Delete("DELETE FROM tbl_pro_size "
			+ " WHERE s_seq = #{s_seq} ")
	public int delete_size(ProSizeVO proSizeVO);
	
	public int insert_color(ProColorVO proColorVO);
	
	@Select("SELECT count(*) FROM tbl_pro_size "
			+ " WHERE p_code = #{p_code} AND s_size = #{s_size}")
	public int getProSize(ProSizeVO proSizeVO);
	
}
