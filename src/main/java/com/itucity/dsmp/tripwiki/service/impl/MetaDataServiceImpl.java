package com.itucity.dsmp.tripwiki.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.itucity.dsmp.tripwiki.dao.MetaDataDao;
import com.itucity.dsmp.tripwiki.dao.entity.AreaPO;
import com.itucity.dsmp.tripwiki.dao.entity.BkDeleteDataPO;
import com.itucity.dsmp.tripwiki.dto.AreaCondition;
import com.itucity.dsmp.tripwiki.dto.AreaVO;
import com.itucity.dsmp.tripwiki.service.MetaDataService;

@Service("metaDataService")
public class MetaDataServiceImpl implements MetaDataService {

	@Resource
	private MetaDataDao metaDataDao;

	@Override
	public List<AreaVO> findArea(AreaCondition condition) {
		List<AreaVO> vos = new ArrayList<AreaVO>();
		List<AreaPO> pos = metaDataDao.findArea(condition);
		for (AreaPO po : pos) {
			AreaVO vo = new AreaVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}//end for
		return vos;
	}

	@Override
	public Boolean deleteData(String tableName, Integer recordId) {
		// TODO Auto-generated method stub
		BkDeleteDataPO buckupDataPO = new BkDeleteDataPO();
		buckupDataPO.setContent("");
		buckupDataPO.setTableName(tableName);
		buckupDataPO.setRecordId(recordId);
		buckupDataPO.setUid(1);
		
		metaDataDao.saveDeleteData(buckupDataPO);
		return null;
	}

}
