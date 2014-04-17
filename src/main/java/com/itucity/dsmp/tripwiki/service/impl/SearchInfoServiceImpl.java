package com.itucity.dsmp.tripwiki.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.tripwiki.dao.SearchInfoDao;
import com.itucity.dsmp.tripwiki.dao.entity.SearchInfoPO;
import com.itucity.dsmp.tripwiki.service.SearchInfoService;
import com.itucity.dsmp.tripwiki.service.model.SearchInfoVO;


/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/30/2014
 */
@Service("searchService")
@Transactional
public class SearchInfoServiceImpl implements SearchInfoService {

	private static Logger logger = LoggerFactory
					.getLogger(SearchInfoServiceImpl.class);
	
	@Resource
	private SearchInfoDao searchDao;
	
	
	private SearchInfoVO searchPOToVO(SearchInfoPO po) {
		SearchInfoVO vo = new SearchInfoVO();
		
		BeanUtils.copyProperties(po, vo);
		
		return vo;
	}
	
	private SearchInfoPO searchVOToPO(SearchInfoVO searchInfo) {
		SearchInfoPO po = new SearchInfoPO();
		
		BeanUtils.copyProperties(searchInfo, po);
		
		return po;
	}
	
	@Override
	public SearchInfoVO getUserSearchInfoById(Integer id) {
		SearchInfoPO po = searchDao.find(SearchInfoPO.class, id);
		
		if(po != null){
			SearchInfoVO vo = new SearchInfoVO();
			vo = searchPOToVO(po);
			return vo;
		}
		
		logger.info(String.format("Collection [id : %d] not found", id));
		return null;
	}

	

	@Override
	public List<SearchInfoVO> getUserSearchInfoByUser(Integer uid, String type) {
		List<SearchInfoVO> vos = new ArrayList<SearchInfoVO>();
		
		List<SearchInfoPO> pos = searchDao.findByUser(uid, type);
		
		for(SearchInfoPO po : pos){
			SearchInfoVO vo = new SearchInfoVO();
			vo = searchPOToVO(po);
			
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public List<SearchInfoVO> getUserSearchInfoByPage(Integer uid, String type,
			Integer first, Integer max) {
		List<SearchInfoVO> vos = new ArrayList<SearchInfoVO>();
		
		List<SearchInfoPO> pos = searchDao.findByPage(uid, type, first, max);
		
		for(SearchInfoPO po : pos){
			SearchInfoVO vo = new SearchInfoVO();
			vo = searchPOToVO(po);
			
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public Integer addUserSearchInfo(SearchInfoVO searchInfo) {
		SearchInfoPO po = new SearchInfoPO();
		
		po = searchVOToPO(searchInfo);
		po.setSearchId(null);
		searchDao.save(po);
		
		return po.getSearchId();
	}


	@Override
	public Boolean deleteUserSearchInfo(Integer id) {
		SearchInfoPO po = searchDao.find(SearchInfoPO.class, id);
		if(po == null){
			logger.info(String.format("UserSearch [id : %d] not found", id ));
			return false;
		}
		searchDao.delete(po);
		return true;
	}

	@Override
	public List<SearchInfoVO> topSearchInfo(Integer num) {
		List<SearchInfoVO> vos = new ArrayList<SearchInfoVO>();
		
		List<SearchInfoPO> pos = searchDao.findTopSearchConditon("", num);
		
		for(SearchInfoPO po : pos){
			SearchInfoVO vo = new SearchInfoVO();
			vo = searchPOToVO(po);
			
			vos.add(vo);
		}
		
		return vos;
	}
	
}
