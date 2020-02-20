package com.biz.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biz.shop.dao.DeptDao;
import com.biz.shop.domain.DeptVO;
import com.biz.shop.persistance.DeptRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeptService {

	private final DeptRepository deptRepo;
	private final DeptDao deptDao;
	
	public List<DeptVO> selectAll() {
		List<DeptVO> deptList = deptRepo.findAll();
		return deptList;
	}

	public DeptVO save(DeptVO deptVO) {
		DeptVO ret = deptRepo.save(deptVO);
		log.debug("거래처정보 : " + ret.toString());
		return ret;
	}

	public DeptVO findById(long id) {
		Optional<DeptVO> deptVO = deptRepo.findById(id);
		return deptVO.get();
	}

	public List<DeptVO> findByDName(String search) {
		return deptDao.findByDName(search);
	}

}
