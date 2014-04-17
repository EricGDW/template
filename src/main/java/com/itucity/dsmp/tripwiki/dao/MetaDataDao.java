package com.itucity.dsmp.tripwiki.dao;

import java.util.List;


import com.itucity.dsmp.tripwiki.dao.entity.AreaPO;
import com.itucity.dsmp.tripwiki.dao.entity.BkDeleteDataPO;
import com.itucity.dsmp.tripwiki.dao.entity.WeatherPO;
import com.itucity.dsmp.tripwiki.dto.AreaCondition;

public interface MetaDataDao {
	
	/**
	 * 获取所有地区信息
	 * @return
	 */
	List<AreaPO> findArea(AreaCondition condition);
	
	/**
	 * 获取天气信息
	 * @param weatherCode
	 * @return
	 */
	List<WeatherPO> findWeather(String weatherCode);
	
	/**
	 * 保存删除的数据
	 * @param buckupDataPO
	 * @return
	 */
	Boolean saveDeleteData(BkDeleteDataPO buckupDataPO);
	
	/**
	 * 备份数据
	 * @param tableName
	 * @param primaryKeyName
	 * @param recordId
	 * @return
	 */
	Boolean buckupData(String tableName,String primaryKeyName,Integer recordId);

}
