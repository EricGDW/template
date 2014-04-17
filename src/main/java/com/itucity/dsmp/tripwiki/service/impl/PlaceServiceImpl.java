package com.itucity.dsmp.tripwiki.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.identity.dao.UserDao;
import com.itucity.dsmp.identity.dao.entity.UserPO;
import com.itucity.dsmp.tripwiki.dao.SocialDataDao;
import com.itucity.dsmp.tripwiki.dao.PlaceDao;
import com.itucity.dsmp.tripwiki.dao.ImageDao;
import com.itucity.dsmp.tripwiki.dao.TagDao;
import com.itucity.dsmp.tripwiki.dao.entity.SocialDataPO;
import com.itucity.dsmp.tripwiki.dao.entity.PlacePO;
import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;
import com.itucity.dsmp.tripwiki.dao.entity.TagPO;
import com.itucity.dsmp.tripwiki.service.SocialEnum;
import com.itucity.dsmp.tripwiki.service.PlaceService;
import com.itucity.dsmp.tripwiki.service.model.PlaceVO;
import com.itucity.dsmp.tripwiki.service.model.ImageVO;

/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/24/2014
 */
@Service("destinationService")
@Transactional
public class PlaceServiceImpl implements PlaceService{

	private static Logger logger = LoggerFactory
			.getLogger(PlaceServiceImpl.class);
	
	@Resource
	private PlaceDao destinationDao;
	
	@Resource
	private TagDao tagDao;
	
	@Resource
	private ImageDao imageDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private SocialDataDao collectionDao;
	
	private PlaceVO destinationPOToVO(PlacePO po) {
		PlaceVO vo = new PlaceVO();
		
		BeanUtils.copyProperties(po, vo);
		
		return vo;
	}
	
	private PlacePO destinationVOToPO(PlaceVO vo) {
		PlacePO po = new PlacePO();
		
		BeanUtils.copyProperties(vo, po);
		
		return po;
	}

	
	@Override
	public PlaceVO getDestinationById(Integer id) {
		PlacePO po = destinationDao.find(PlacePO.class, id);
		
		if(po != null){
			PlaceVO vo = new PlaceVO();
			vo = destinationPOToVO(po);
			return vo;
		}
		
		logger.info(String.format("Destination [id : %d] not found", id));
		return null;
	}

	@Override
	public PlaceVO getDestinationByName(String name) {
		PlacePO po = destinationDao.findByName(name);
		
		if(po != null){
			PlaceVO vo = new PlaceVO();
			vo = destinationPOToVO(po);
			return vo;
		}
		
		logger.info(String.format("Destination [name : %s] not found", name));
		return null;	
	}

	@Override
	public List<PlaceVO> getDestinationByTag(String tag) {
		List<PlaceVO> vos = new ArrayList<PlaceVO>();
		
		List<PlacePO> pos = destinationDao.findByTag(tag);
		
		for(PlacePO po : pos){
			PlaceVO vo = new PlaceVO();
			vo = destinationPOToVO(po);
			
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public List<PlaceVO> getDestinationByTags(List<String> tags) {
//		List<PlaceVO> vos = new ArrayList<PlaceVO>();
//		
//		if(tags.size() == 0){
//			return vos;
//		}
//		
//		List<PlacePO> pos = destinationDao.findByTag(tags.get(0));
//
//		tags.remove(0);
//		
//		for(Iterator<PlacePO> iter = pos.iterator(); iter.hasNext();){
//			PlacePO po = (PlacePO) iter.next();
//			
//			List<TagPO> tagList = po.getRelatedTags();
//			
//			List<String> tagNames = new ArrayList<String>();
//			for(TagPO tag : tagList){
//				tagNames.add(tag.getName());
//			}
//			
//			for(String tag : tags){
//				if(!tagNames.contains(tag)){
//					iter.remove();
//					continue;
//				}
//			}
//		}
//		
//		for(PlacePO po : pos){
//			PlaceVO vo = new PlaceVO();
//			vo = destinationPOToVO(po);
//			
//			vos.add(vo);
//		}
//		
//		return vos;
		return null;
	}
	
	@Override
	public Integer addDestination(PlaceVO destination) {
//		PlacePO po = new PlacePO();
//		
//		po = destinationVOToPO(destination);
//		po.setPlaceId(null);
//		
//		List<TagPO> tags = new ArrayList<TagPO>();
//		
//		List<Integer> ids = destination.getTagList();
//		for(Integer id : ids){
//			TagPO tag = tagDao.find(TagPO.class, id);
//			
//			tags.add(tag);
//		}
//		po.setRelatedTags(tags);
//		
//		List<ImagePO> images = new ArrayList<ImagePO>();
//		
//		ids = destination.getAlbumList();
//		for(Integer id : ids){
//			ImagePO image = imageDao.find(ImagePO.class, id);
//			
//			images.add(image);
//		}
//		po.setRelatedImages(images);
//		
//		destinationDao.save(po);
//		
//		return po.getPlaceId();
		return null;
	}


	@Override
	public Boolean updateDestination(PlaceVO destination) {
		PlacePO po = destinationDao.find(PlacePO.class, 
				destination.getPlaceId());
		if(po == null){
			logger.info(String.format("Destination [id : %d] not found", 
					destination.getPlaceId()));
			return false;
		}
		
		BeanUtils.copyProperties(destination, po);
		destinationDao.save(po);
		
		return true;
	}

	@Override
	public Boolean deleteDestination(Integer id) {
		PlacePO po = destinationDao.find(PlacePO.class, id);
		if(po == null){
			logger.info(String.format("Destination [id : %d] not found", id ));
			return false;
		}
		destinationDao.delete(po);
		return true;
	}


	@Override
	public Integer favouriteDestination(Integer uid, Integer placeId) {
		PlacePO po = destinationDao.find(PlacePO.class, placeId);
		
		po.setFavouriteCount(po.getFavouriteCount() + 1);
		destinationDao.save(po);
		
		SocialDataPO collection = new SocialDataPO();
		
		collection.setUid(uid);
		collection.setSourceId(placeId);
		collection.setSourceType(SocialEnum.PLACE_AGREE.toString());
		collection.setCtime(Calendar.getInstance().getTime().toString());
		
		collectionDao.save(collection);
		return po.getFavouriteCount();
	}

	@Override
	public Integer shareDestination(Integer uid, Integer placeId) {
		PlacePO po = destinationDao.find(PlacePO.class, placeId);
		
		po.setShareCount(po.getShareCount() + 1);
		destinationDao.save(po);
		
		SocialDataPO collection = new SocialDataPO();
		
		collection.setUid(uid);
		collection.setSourceId(placeId);
		collection.setSourceType(SocialEnum.PLACE_SHARE.toString());
		collection.setCtime(Calendar.getInstance().getTime().toString());
		
		collectionDao.save(collection);
		return po.getShareCount();
	}

	@Override
	public List<ImageVO> destinationImages(Integer id) {
//		List<ImageVO> vos = new ArrayList<ImageVO>();
//		
//		PlacePO destination = destinationDao.find(PlacePO.class, id);
//		List<ImagePO> images = destination.getRelatedImages();
//		
//		for(ImagePO image : images){
//			ImageVO vo = new ImageVO();
//			
//			vo.setImageId(image.getImageId());
//			vo.setRaw(image.getRaw());
//			vo.setDescription(image.getDescription());
//			
//			vos.add(vo);
//		}
//		
//		return vos;
		
		return null;
	}

}
