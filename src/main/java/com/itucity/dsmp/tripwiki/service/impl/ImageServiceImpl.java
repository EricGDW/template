package com.itucity.dsmp.tripwiki.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.itucity.dsmp.tripwiki.dao.PlaceDao;
import com.itucity.dsmp.tripwiki.dao.ImageDao;
import com.itucity.dsmp.tripwiki.dao.entity.PlacePO;
import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;
import com.itucity.dsmp.tripwiki.service.ImageService;
import com.itucity.dsmp.tripwiki.service.model.ImageVO;

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
	private PlaceDao destinationDao;
	
	
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
		ImagePO po = imageDao.find(ImagePO.class, id);
		
		if(po != null){
			ImageVO vo = new ImageVO();
			vo = imagePOToVO(po);
			return vo;
		}
		
		logger.info(String.format("Image [id : %d] not found", id));
		return null;
	}

	

	@Override
	public List<ImageVO> getImageByType(String type) {
		List<ImageVO> vos = new ArrayList<ImageVO>();
		
		List<ImagePO> pos = imageDao.findByType(type);
		
		for(ImagePO po : pos){
			ImageVO vo = new ImageVO();
			vo = imagePOToVO(po);
			
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public List<ImageVO> getImageByDescription(String description) {
		List<ImageVO> vos = new ArrayList<ImageVO>();
		
		List<ImagePO> pos = imageDao.findByDescription(description);
		
		for(ImagePO po : pos){
			ImageVO vo = new ImageVO();
			vo = imagePOToVO(po);
			
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public Integer addImage(ImageVO image) {
		ImagePO po = new ImagePO();
		
		po = imageVOToPO(image);
		po.setImageId(null);
		
		Integer id = image.getDestinationId();
		if( id != null){
			PlacePO d = destinationDao.find(PlacePO.class, id);
			po.setDestination(d);
		}
		imageDao.save(po);
		
		return po.getImageId();
	}

}
