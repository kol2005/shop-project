package com.biz.score.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.score.domain.ScorePivotVO;
import com.biz.score.domain.ScoreVO;

public interface ScoreDao {

	
//	@Select("SELECT * FROM tbl_score")
	public List<ScorePivotVO> selectAll();

	public int update(ScoreVO scoreVO);

	public int insert(ScoreVO scoreVO);
	
	@Delete("DELETE tbl_score WHERE s_num = #{s_num}")
	public int delete(String s_num);

	@Select("SELECT * FROM tbl_score WHERE s_num = #{s_num}")
	public ScoreVO findById(String s_num);

}
