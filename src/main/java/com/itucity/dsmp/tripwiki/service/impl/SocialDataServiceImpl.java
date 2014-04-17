package com.itucity.dsmp.tripwiki.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.tripwiki.dao.SocialDataDao;
import com.itucity.dsmp.tripwiki.dao.entity.LinePO;
import com.itucity.dsmp.tripwiki.dao.entity.SocialDataPO;
import com.itucity.dsmp.tripwiki.service.SocialDataService;
import com.itucity.dsmp.tripwiki.service.model.SocialDataVO;


/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/30/2014
 */
@Service("collectionService")
@Transactional
public class SocialDataServiceImpl implements SocialDataService{

	private static Logger logger = LoggerFactory
			.getLogger(LineServiceImpl.class);
	
	@Resource
	private SocialDataDao collectionDao;

	
	private SocialDataVO collectionPOToVO(SocialDataPO po) {
		SocialDataVO vo = new SocialDataVO();
		
		BeanUtils.copyProperties(po, vo);
		
		return vo;
	}
	
	private SocialDataPO colletionVOToPO(SocialDataVO colletion) {
		SocialDataPO po = new SocialDataPO();
		
		BeanUtils.copyProperties(colletion, po);
		
		return po;
	}
	
	@Override
	public SocialDataVO getUserCollectionById(Integer id) {
		SocialDataPO po = collectionDao.find(SocialDataPO.class, id);
		
		if(po != null){
			SocialDataVO vo = new SocialDataVO();
			vo = collectionPOToVO(po);
			return vo;
		}
		
		logger.info(String.format("Collection [id : %d] not found", id));
		return null;
	}



	@Override
	public List<SocialDataVO> getUserCollectionByUser(Integer uid, String type) {
		List<SocialDataVO> vos = new ArrayList<SocialDataVO>();
		
		List<SocialDataPO> pos = collectionDao.findByUser(uid, type);
		
		for(SocialDataPO po : pos){
			SocialDataVO vo = new SocialDataVO();
			vo = collectionPOToVO(po);
			
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public List<SocialDataVO> getUserCollectionByPage(Integer uid, String type,
			Integer first, Integer max) {
		List<SocialDataVO> vos = new ArrayList<SocialDataVO>();
		
		List<SocialDataPO> pos = collectionDao.findByPage(uid, type,
											first, max);
		
		for(SocialDataPO po : pos){
			SocialDataVO vo = new SocialDataVO();
			vo = collectionPOToVO(po);
			
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public Integer addUserColletion(SocialDataVO colletion) {
		SocialDataPO po = new SocialDataPO();
		
		po = colletionVOToPO(colletion);
		po.setId(null);
		collectionDao.save(po);
		
		return po.getId();
	}

	@Override
	public Boolean deleteUserCollection(Integer id) {
		LinePO po = collectionDao.find(LinePO.class, id);
		if(po == null){
			logger.info(String.format("Collection [id : %d] not found", id ));
			return false;
		}
		collectionDao.delete(po);
		return true;
	}
	

}
