package com.itucity.dsmp.tripwiki.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.identity.dao.UserDao;
import com.itucity.dsmp.tripwiki.dao.ImageDao;
import com.itucity.dsmp.tripwiki.dao.PlaceDao;
import com.itucity.dsmp.tripwiki.dao.TagDao;
import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;
import com.itucity.dsmp.tripwiki.dao.entity.PlacePO;
import com.itucity.dsmp.tripwiki.dto.ImageCondition;
import com.itucity.dsmp.tripwiki.dto.ImageVO;
import com.itucity.dsmp.tripwiki.dto.PlaceVO;
import com.itucity.dsmp.tripwiki.service.PlaceService;

/**
 * 
 * @author Eric
 * @version 0.1
 * 
 *          3/24/2014
 */
@Service("placeService")
@Transactional
public class PlaceServiceImpl implements PlaceService {

	private static Logger logger = LoggerFactory
			.getLogger(PlaceServiceImpl.class);

	@Resource
	private PlaceDao placeDao;

	@Resource
	private TagDao tagDao;

	@Resource
	private ImageDao imageDao;

	@Resource
	private UserDao userDao;

	private PlaceVO placePOToVO(PlacePO po) {
		PlaceVO vo = new PlaceVO();

		BeanUtils.copyProperties(po, vo);

		return vo;
	}

	private PlacePO placeVOToPO(PlaceVO vo) {
		PlacePO po = new PlacePO();

		BeanUtils.copyProperties(vo, po);

		return po;
	}

	
	/**
	 * 
	 */
	@Override
	public PlaceVO getPlaceById(Integer id) {
		PlacePO po = placeDao.findById(id);
		
		if (po != null) {
			PlaceVO vo = new PlaceVO();
			vo = placePOToVO(po);
			
			ImagePO image = imageDao.findById(id);
			vo.setCoverUrl(image.getMedium());
			return vo;
		}

		logger.info(String.format("place [id : %d] not found", id));
		return null;
	}

	@Override
	public Integer addPlace(PlaceVO place) {
		PlacePO po = new PlacePO();
		
		po = placeVOToPO(place);
		
		placeDao.save(po);
		
		return po.getPlaceId();
	}

	@Override
	public Boolean updatePlace(PlaceVO place) {
		PlacePO po = placeDao.findById(place.getPlaceId());
		if(po == null){
			logger.info(String.format("place [id : %d] not found", place.getPlaceId()));
			return false;
		}

		BeanUtils.copyProperties(place, po);
		placeDao.save(po);

		return true;
	}

	@Override
	public Boolean deletePlace(Integer id) {
		PlacePO po = placeDao.findById(id);
		if(po == null){
			logger.info(String.format("place [id : %d] not found", id ));
			return false;
		}
		placeDao.delete(po);
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public PagesInfo<ImageVO> getPlaceImages(PagesInfo pagesInfo, ImageCondition condition) {
		PagesInfo<ImageVO> result = new PagesInfo<ImageVO>();
		
		PagesInfo<ImagePO> page = placeDao.findPlaceImages(pagesInfo,condition);
		
		List<ImageVO> imagevos = new ArrayList<ImageVO>();
		List<ImagePO> images = page.getResultsList();
		for(ImagePO image : images){
			ImageVO vo = new ImageVO();
			BeanUtils.copyProperties(image, vo);
			imagevos.add(vo);
		}
		result.setResultsList(imagevos);
		result.setCountPerPage(page.getCountPerPage());
		result.setPageNumber(page.getPageNumber());
		result.setTotalPage(page.getTotalPage());
		result.setTotalRecord(page.getTotalRecord());
		return result;
	}

	@Override
	public Boolean addPlaceTag(Integer placeId, Integer tagId) {
		return placeDao.addPlaceTag(placeId, tagId);
	}

	@Override
	public Boolean addPlaceImage(Integer placeId, Integer imageId) {
		return placeDao.addPlaceImage(placeId, imageId);
	}

}
