package com.itucity.dsmp.tripwiki.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.itucity.dsmp.tripwiki.dao.TagDao;
import com.itucity.dsmp.tripwiki.dao.entity.TagPO;
import com.itucity.dsmp.tripwiki.dto.TagVO;
import com.itucity.dsmp.tripwiki.service.TagService;


/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/30/2014
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

	private static Logger logger = LoggerFactory
			.getLogger(TagServiceImpl.class);
	
	@Resource
	private TagDao tagDao;
	
	private TagVO tagPOToVO(TagPO po) {
		TagVO vo = new TagVO();
		
		BeanUtils.copyProperties(po, vo);
		
		return vo;	
	}
	
	private TagPO tagVOToPO(TagVO tag) {
		TagPO po = new TagPO();
		
		BeanUtils.copyProperties(tag, po);
		
		return po;
	}
	
	@Override
	public TagVO getById(Integer id) {
		TagPO po = tagDao.findById(id);
		
		if(po != null){
			TagVO vo = new TagVO();
			vo = tagPOToVO(po);
			return vo;
		}
		
		logger.info(String.format("Tag [id : %d] not found", id));
		return null;
	}

	

	@Override
	public TagVO getByName(String name) {
		TagPO po = tagDao.findByName(name);
		
		if(po != null){
			TagVO vo = new TagVO();
			vo = tagPOToVO(po);
			return vo;
		}
		
		logger.info(String.format("Tag [name : %d] not found", name));
		return null;
	}

	@Override
	public Integer addTag(TagVO tag) {
		TagPO tagpo = tagDao.findByName(tag.getName());
		
		if(tagpo != null){
			logger.info(String.format("Tag [name : %d] exist", tag.getName()));
			return tagpo.getTagId();
		}
		
		TagPO po = new TagPO();
		
		po = tagVOToPO(tag);
		try {
			tagDao.save(po);
		} catch (Exception e) {
			System.out.println("重复的景点-标签"+po.getTagId());
		}
		return po.getTagId();
	}

	@Override
	public Boolean updateTag(TagVO tag) {
		TagPO po = tagDao.findById(tag.getTagId());
		if(po == null){
			logger.info(String.format("Tag [id : %d] not found", 
					tag.getTagId()));
			return false;
		}
		
		BeanUtils.copyProperties(tag, po);
		tagDao.save(po);
		
		return true;
	}

	@Override
	public Boolean deleteTag(Integer id) {
		TagPO po = tagDao.findById(id);
		if(po == null){
			logger.info(String.format("Tag [id : %d] not found", id ));
			return false;
		}
		tagDao.delete(po);
		return true;
	}

}
