package com.itucity.dsmp.tripwiki.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.common.exception.SystemRuntimeException;
import com.itucity.dsmp.common.util.JsonUtil;
import com.itucity.dsmp.identity.UserContext;
import com.itucity.dsmp.tripwiki.dao.MetaDataDao;
import com.itucity.dsmp.tripwiki.dao.entity.AreaPO;
import com.itucity.dsmp.tripwiki.dao.entity.BkDeleteDataPO;
import com.itucity.dsmp.tripwiki.dao.entity.WeatherPO;
import com.itucity.dsmp.tripwiki.dto.AreaCondition;

@Service("metaDataDao")
public class MetaDataDaoImpl extends BaseDao implements MetaDataDao {

	@Override
	public List<AreaPO> findArea(AreaCondition condition) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from AreaPO t where 1=1 ");

		if (condition.getAreaId() != null) {
			hql.append(" and t.areaId=").append(condition.getAreaId());
		}
		if (condition.getParentId() != null) {
			hql.append(" and t.parentId=").append(condition.getParentId());
		}
		if (StringUtils.hasText(condition.getAreaName())) {
			hql.append(" and t.areaName like '%")
					.append(condition.getAreaName()).append("%'");
		}
		hql.append(" order by sort asc");

		List<AreaPO> list = super.hqlQuery(hql.toString());
		return list;
	}

	@Override
	public List<WeatherPO> findWeather(String weatherCode) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from WeatherPO t where 1=1 ");

		if (StringUtils.hasText(weatherCode)) {
			hql.append(" and t.weatherCode='").append(weatherCode).append("'");
		}
		hql.append(" order by weatherCode asc");

		List<WeatherPO> list = super.hqlQuery(hql.toString());
		return list;
	}

	@Override
	public Boolean saveDeleteData(BkDeleteDataPO buckupDataPO) {
		super.save(buckupDataPO);
		return true;
	}

	@Override
	public Boolean buckupData(String tableName, String primaryKeyName,
			Integer recordId) {
		StringBuffer hqlDelete = new StringBuffer("delete " + tableName	+ " as t where 1=1");
		StringBuffer hqlFrom = new StringBuffer("from " + tableName + " as t where 1=1");
		
		StringBuffer hqlBase = new StringBuffer(" and t.");
		hqlBase.append(primaryKeyName);
		hqlBase.append("='").append(recordId).append("'");
		
		List<Object> list = super.hqlQuery(hqlFrom.append(hqlBase).toString());
		String content = "";
		if (list.size() > 0) {
			content = JsonUtil.object2JsonStr(list.get(0));
		} else {
			throw new SystemRuntimeException("errorNoResult", errorNoResult, "表'"+tableName+"'中记录'"+recordId+"'为空");
		}
		BkDeleteDataPO buckupDataPO = new BkDeleteDataPO();
		
		buckupDataPO.setDeleteTime(new Date());
		buckupDataPO.setRecordId(recordId);
		buckupDataPO.setUid(UserContext.getUserDetails().getUserId());
		buckupDataPO.setTableName(tableName);
		buckupDataPO.setContent(content);
		// 执行删除
		super.hqlUpdate(hqlDelete.append(hqlBase).toString());
		return saveDeleteData(buckupDataPO);
	}

}
