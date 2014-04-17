package com.itucity.dsmp.tripwiki.service;

import java.util.List;

import com.itucity.dsmp.tripwiki.dto.AreaCondition;
import com.itucity.dsmp.tripwiki.dto.AreaVO;

public interface MetaDataService {
	
	/**
	 * 获取所有地区信息
	 * @return
	 */
	List<AreaVO> findArea(AreaCondition condition);
	
	/**
	 * 删除数据备份
	 * @param tableName
	 * @param id
	 * @return
	 */
	Boolean deleteData(String tableName, Integer id);
	
	

}
