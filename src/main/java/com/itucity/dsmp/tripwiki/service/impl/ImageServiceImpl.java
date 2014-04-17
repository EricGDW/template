package com.itucity.dsmp.tripwiki.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.itucity.dsmp.tripwiki.dao.ImageDao;
import com.itucity.dsmp.tripwiki.dao.PlaceDao;
import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;
import com.itucity.dsmp.tripwiki.dto.ImageVO;
import com.itucity.dsmp.tripwiki.service.ImageService;

/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/26/2014
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService{

	private static Logger logger = LoggerFactory
			.getLogger(ImageServiceImpl.class);
	
	@Resource
	private ImageDao imageDao;
	
	@Resource
	private PlaceDao placeDao;
	
	
	private ImageVO imagePOToVO(ImagePO po) {
		ImageVO vo = new ImageVO();
		
		BeanUtils.copyProperties(po, vo);
		
		return vo;	
	}
	
	private ImagePO imageVOToPO(ImageVO vo) {
		ImagePO po = new ImagePO();
		
		BeanUtils.copyProperties(vo, po);
		
		return po;
	}
	
	@Override
	public ImageVO getImageById(Integer id) {
		ImagePO po = imageDao.findById(id);
		
		if(po != null){
			ImageVO vo = new ImageVO();
			vo = imagePOToVO(po);
			return vo;
		}
		
		logger.info(String.format("Image [id : %d] not found", id));
		return null;
	}

	@Override
	public Integer addImage(ImageVO image) {
		ImagePO po = new ImagePO();
		
		po = imageVOToPO(image);
		
		imageDao.save(po);
		
		return po.getImageId();
	}

}
