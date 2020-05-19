package com.biz.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.shop.domain.ProFileVO;
import com.biz.shop.persistance.DDL_Dao;
import com.biz.shop.persistance.FileUpDao;
import com.biz.shop.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService{

	private final FileUpDao fileUpDao;
	private final DDL_Dao ddl_dao;
	
	public FileUploadServiceImpl(FileUpDao fileUpDao,DDL_Dao ddl_dao) {
		this.fileUpDao = fileUpDao;
		this.ddl_dao = ddl_dao;
		
		String create_fileup_table = "CREATE TABLE IF NOT EXISTS tbl_profile ("
				+ " id bigint AUTO_INCREMENT PRIMARY KEY, "
				+ " file_p_code VARCHAR(6), "
				+ " file_origin_name VARCHAR(255), "
				+ " file_upload_name VARCHAR(255) )";
		
		ddl_dao.create_table(create_fileup_table);
	}
	
	@Override
	public List<ProFileVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProFileVO> findByPCode(String file_p_code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProFileVO findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ProFileVO profileVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String file_p_code) {
		// TODO Auto-generated method stub
		return 0;
	}

}
