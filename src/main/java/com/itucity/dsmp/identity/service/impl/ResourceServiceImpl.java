package com.itucity.dsmp.identity.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.identity.dao.ResourceDao;
import com.itucity.dsmp.identity.dao.entity.UrlPO;
import com.itucity.dsmp.identity.service.ResourceService;
import com.itucity.dsmp.identity.service.model.ResourceVO;
import com.itucity.dsmp.identity.service.model.UrlVO;


/**
 * @author Eric
 * @version 0.1
 * 
 * 3/16/2014
 */
@Service("resourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService{

	enum ResourceTypeEnum {
		URL
	}
	
	@Resource
	private ResourceDao resourceDao;
	
	@Override
	@Transactional 
	public List<ResourceVO> loadForAll() {
		List<ResourceVO> resources = new ArrayList<ResourceVO>();
		
		List<UrlPO> urlPos =  resourceDao.getAllUrl();
		for(UrlPO po : urlPos){
			Hibernate.initialize(po.getGroups());
			ResourceVO vo = new ResourceVO();
			vo.setId(po.getId());
			vo.setContent(po.getContent());
			vo.setOwner(po.getGroups());
			vo.setType(ResourceTypeEnum.URL.toString());
			resources.add(vo);
		}
			
		return resources;
	}

	public UrlVO urlPOToVO(UrlPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	public UrlPO urlVOToPO(UrlVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
