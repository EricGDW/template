package com.itucity.dsmp.tripwiki.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.identity.dao.UserDao;
import com.itucity.dsmp.identity.dao.entity.UserPO;
import com.itucity.dsmp.tripwiki.dao.PlaceDao;
import com.itucity.dsmp.tripwiki.dao.TagDao;
import com.itucity.dsmp.tripwiki.dao.LineDao;
import com.itucity.dsmp.tripwiki.dao.SocialDataDao;
import com.itucity.dsmp.tripwiki.dao.entity.PlacePO;
import com.itucity.dsmp.tripwiki.dao.entity.TagPO;
import com.itucity.dsmp.tripwiki.dao.entity.LinePO;
import com.itucity.dsmp.tripwiki.dao.entity.SocialDataPO;
import com.itucity.dsmp.tripwiki.service.SocialEnum;
import com.itucity.dsmp.tripwiki.service.LineService;
import com.itucity.dsmp.tripwiki.service.model.LineVO;


/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/24/2014
 */
@Service("travelLineService")
@Transactional
public class LineServiceImpl implements LineService {
	
	private static Logger logger = LoggerFactory
			.getLogger(LineServiceImpl.class);
	
	@Resource
	private LineDao lineDao;
	
	@Resource
	private TagDao tagDao;
	
	@Resource
	private SocialDataDao socialDataDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private PlaceDao destinationDao;
	
	private LineVO travelLinePOToVO(LinePO po) {
		LineVO vo = new LineVO();
		
		BeanUtils.copyProperties(po, vo);
		
		List<Integer> dids = new ArrayList<Integer>();
		
		String destinations = po.getDestinations();
		
		String[] strs = destinations.split("->");
		
		for(int i = 0; i < strs.length; i++){
			dids.add(Integer.parseInt(strs[i]));
		}
		
		vo.setDestinationList(dids);
		
		return vo;
	}
	
	private LinePO travelLineVOToPO(LineVO travelLine) {
		LinePO po = new LinePO();
		
		BeanUtils.copyProperties(travelLine, po);
		
		String destinations = "";
		for(Integer id : travelLine.getDestinationList()){
			destinations += id.toString() + "->";
		}
		
		po.setDestinations(destinations);
		return po;
	}
	
	@Override
	public LineVO getTravelLineById(Integer id) {
		LinePO po = lineDao.find(LinePO.class, id);
		
		if(po != null){
			LineVO vo = new LineVO();
			vo = travelLinePOToVO(po);
			return vo;
		}
		
		logger.info(String.format("TravelLine [id : %d] not found", id));
		return null;
	}

	

	@Override
	public List<LineVO> getTravelLineByType(String type) {
		List<LineVO> vos = new ArrayList<LineVO>();
		
		List<LinePO> pos = lineDao.findByType(type);
		
		for(LinePO po : pos){
			LineVO vo = new LineVO();
			vo = travelLinePOToVO(po);
			
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public List<LineVO> getTravelLineByTags(List<String> tags) {
//		List<LineVO> vos = new ArrayList<LineVO>();
//		
//		if(tags.size() == 0){
//			return vos;
//		}
//		
//		List<LinePO> pos = lineDao.findByTag(tags.get(0));
//
//		tags.remove(0);
//		
//		for(Iterator<LinePO> iter = pos.iterator(); iter.hasNext();){
//			LinePO po = (LinePO) iter.next();
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
//		for(LinePO po : pos){
//			LineVO vo = new LineVO();
//			vo = travelLinePOToVO(po);
//			
//			vos.add(vo);
//		}
//		
//		return vos;
		return null;
	}

	@Override
	public Integer addTravelLine(LineVO travelLine) {
		LinePO po = new LinePO();
		
		po = travelLineVOToPO(travelLine);
		po.setLineId(null);
		
		List<TagPO> tags = new ArrayList<TagPO>();
		
		List<Integer> ids = travelLine.getTagList();
		for(Integer id : ids){
			TagPO tag = tagDao.find(TagPO.class, id);
			
			tags.add(tag);
		}
		//po.setRelatedTags(tags);
		
		List<PlacePO> des = new ArrayList<PlacePO>();
		
		ids = travelLine.getDestinationList();
		for(Integer id : ids){
			PlacePO d = destinationDao.find(PlacePO.class, id);
			
			des.add(d);
		}
		//po.setRelatedDestinations(des);
		
		lineDao.save(po);
		
		return po.getLineId();
	}



	@Override
	public Boolean updateTravelLine(LineVO travelLine) {
		LinePO po = lineDao.find(LinePO.class, 
				travelLine.getLineId());
		if(po == null){
			logger.info(String.format("TravelLine [id : %d] not found", 
					travelLine.getLineId()));
			return false;
		}
		
		BeanUtils.copyProperties(travelLine, po);
		lineDao.save(po);
		
		return true;
	}

	@Override
	public Boolean deleteTravelLine(Integer id) {
		LinePO po = lineDao.find(LinePO.class, id);
		if(po == null){
			logger.info(String.format("TravelLine [id : %d] not found", id ));
			return false;
		}
		lineDao.delete(po);
		return true;
	}

	@Override
	public Integer favouriteTravelLine(Integer uid, Integer lineId) {
		LinePO po = lineDao.find(LinePO.class, lineId);
		UserPO user = userDao.find(UserPO.class, uid);
		
		po.setFavouriteCount(po.getFavouriteCount() + 1);
		lineDao.save(po);
		
		SocialDataPO collection = new SocialDataPO();
		
		//collection.setUser(user);
		collection.setSourceId(lineId);
		collection.setSourceType(SocialEnum.LINE_AGREE.toString());
		collection.setCtime(Calendar.getInstance().getTime().toString());
		
		socialDataDao.save(collection);
		
		return po.getFavouriteCount();
	}

	@Override
	public Integer shareTravelLine(Integer uid, Integer lineId) {
		LinePO po = lineDao.find(LinePO.class, lineId);
		
		po.setShareCount(po.getShareCount() + 1);
		lineDao.save(po);
		
		SocialDataPO collection = new SocialDataPO();
		
		collection.setUid(uid);
		collection.setSourceId(lineId);
		collection.setSourceType(SocialEnum.LINE_SHARE.toString());
		collection.setCtime(Calendar.getInstance().getTime().toString());
		
		socialDataDao.save(collection);
		
		return po.getShareCount();
	}

	@Override
	public List<LineVO> recommendationTravelLine(Integer uid) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<LineVO> topTravelLine(Integer num, Integer sort) {
		List<LineVO> vos = new ArrayList<LineVO>();
		
		List<LinePO> pos = lineDao.findTop(num, sort);
		
		for(LinePO po : pos){
			LineVO vo = new LineVO();
			vo = travelLinePOToVO(po);
			
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public Integer collectTravelLine(Integer uid, Integer lineId) {
		SocialDataPO collection = new SocialDataPO();
		
		collection.setSourceId(lineId);
		collection.setUid(uid);
		collection.setSourceType(SocialEnum.LINE_COLLECT.toString());
		collection.setCtime(Calendar.getInstance().getTime().toString());
		
		socialDataDao.save(collection);
		LinePO line = lineDao.find(LinePO.class, lineId);
		
		line.setCollectCount(line.getCollectCount()+1);
		lineDao.save(line);
		
		return line.getCollectCount();
			
	}
	
}
