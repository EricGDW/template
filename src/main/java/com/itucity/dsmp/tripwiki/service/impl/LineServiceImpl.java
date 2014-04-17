package com.itucity.dsmp.tripwiki.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.identity.dao.UserDao;
import com.itucity.dsmp.tripwiki.dao.ImageDao;
import com.itucity.dsmp.tripwiki.dao.LineDao;
import com.itucity.dsmp.tripwiki.dao.PlaceDao;
import com.itucity.dsmp.tripwiki.dao.TagDao;
import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;
import com.itucity.dsmp.tripwiki.dao.entity.LinePO;
import com.itucity.dsmp.tripwiki.dao.entity.PlacePO;
import com.itucity.dsmp.tripwiki.dao.entity.TagPO;
import com.itucity.dsmp.tripwiki.dto.LineCondition;
import com.itucity.dsmp.tripwiki.dto.LineVO;
import com.itucity.dsmp.tripwiki.dto.PlaceVO;
import com.itucity.dsmp.tripwiki.dto.TagVO;
import com.itucity.dsmp.tripwiki.service.LineService;
import com.itucity.dsmp.tripwiki.util.Distance;


/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/24/2014
 */
@Service("lineService")
@Transactional
public class LineServiceImpl implements LineService {
	
	private static Logger logger = LoggerFactory
			.getLogger(LineServiceImpl.class);
	
	@Resource
	private LineDao lineDao;
	
	@Resource
	private TagDao tagDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private PlaceDao placeDao;
	
	@Resource
	private ImageDao imageDao;
	
	private LineVO linePOToVO(LinePO po) {
		LineVO vo = new LineVO();
		
		BeanUtils.copyProperties(po, vo);
		
		return vo;
	}
	
	private LinePO LineVOToPO(LineVO line) {
		LinePO po = new LinePO();
		
		BeanUtils.copyProperties(line, po);
		
		return po;
	}
	
	@Override
	public LineVO getLineById(Integer id) {
		LinePO po = lineDao.findById(id);
		
		if(po != null){
			LineVO vo = new LineVO();
			vo = linePOToVO(po);
			return vo;
		}
		
		logger.info(String.format("line [id : %d] not found", id));
		return null;
	}

	@Override
	public Integer addLine(LineVO line) {
		LinePO po = new LinePO();
		po = LineVOToPO(line);
		lineDao.save(po);
		return po.getLineId();
	}



	@Override
	public Boolean updateLine(LineVO line) {
		LinePO po = lineDao.findById(line.getLineId());
		if(po == null){
			logger.info(String.format("line [id : %d] not found", 
					line.getLineId()));
			return false;
		}
		
		BeanUtils.copyProperties(line, po);
		lineDao.save(po);
		
		return true;
	}

	@Override
	public Boolean deleteLine(Integer id) {
		LinePO po = lineDao.findById(id);
		if(po == null){
			logger.info(String.format("line [id : %d] not found", id ));
			return false;
		}
		lineDao.delete(po);
		return true;
	}

	@Override
	public List<PlaceVO> getLinePlaces(Integer lineId, Integer sort) {
		
		List<PlaceVO> result = new ArrayList<PlaceVO>();
		
		List<PlacePO> places = new ArrayList<PlacePO>();
		places = lineDao.findLinePlace(lineId);
		
		for(PlacePO place : places){
			PlaceVO vo = new PlaceVO();
			BeanUtils.copyProperties(place, vo);
			
			/**
			 * 临时增加景点封面信息，后面需要改
			 */
			ImagePO image = imageDao.findById(place.getPlaceId());
			vo.setCoverUrl(image.getSmall());
			result.add(vo);
		}
		
		
		
		//根据经纬度排序
		Collections.sort(result, new Comparator<PlaceVO>(){
			@Override
			public int compare(PlaceVO o1, PlaceVO o2) {
				PlaceVO p1=(PlaceVO)o1;
				PlaceVO p2=(PlaceVO)o2;
				int flag = p1.getLongitude().compareTo(p2.getLongitude());
				if(flag == 0){
					flag = p1.getLatitude().compareTo(p2.getLatitude());
					if(flag == 0){
						return p1.getTitle().compareTo(p2.getTitle());
					}
					else{
						return flag;
					}
				}else{
					return flag;
				}
			}
		});
 		
		if(sort == 2){
			Collections.reverse(result);
		}
		return result;
	}

	@Override
	public PagesInfo<Object> getLineByCondition(LineCondition condition) {
		PagesInfo<Object> result = new PagesInfo<Object>();
		
		PagesInfo<LinePO> page = new PagesInfo<LinePO>();
		page.setCountPerPage(condition.getPageSortCondition().getCountPerPage());
		page.setPageNumber(condition.getPageSortCondition().getPageIndex());
		
		page = lineDao.findByCondition(condition, page);
		
		List<Object> linevos = new ArrayList<Object>();
		List<LinePO> lines = page.getResultsList();
		
		/*for(LinePO line : lines){
			List<PlacePO> places = lineDao.findLinePlace(line.getLineId());
			for(PlacePO place : places){
				Double distance = Distance.getDistance(condition.getLatitude(), 
						condition.getLongitude(), place.getLatitude(), place.getLongitude());
			}
			
			
			
		}*/
		
		for(LinePO line : lines){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("lineId", line.getLineId());
			map.put("title", line.getTitle());
			map.put("description", line.getDescription());
			map.put("bestTime", line.getBestTime());
			
			ImagePO cover = imageDao.findById(line.getCoverId());
			map.put("coverUrl", cover.getSmall());
			
			List<String> placeNameList = new ArrayList<String>();
			List<PlacePO> places = lineDao.findLinePlace(line.getLineId());
			for(PlacePO place : places){
				placeNameList.add(place.getTitle());
			}
			map.put("places", placeNameList);
			
			linevos.add(map);
		}
		result.setResultsList(linevos);
		result.setCountPerPage(page.getCountPerPage());
		result.setPageNumber(page.getPageNumber());
		result.setTotalPage(page.getTotalPage());
		result.setTotalRecord(page.getTotalRecord());
		
		
		return result;
		
	}

	@Override
	public List<TagVO> getLineTag(Integer lineId) {
		List<TagVO> result = new ArrayList<TagVO>();
		
		List<TagPO> tags = lineDao.findLineTag(lineId);
		for(TagPO tag : tags){
			TagVO vo = new TagVO();
			BeanUtils.copyProperties(tag, vo);
			result.add(vo);
		}
		return result;
	}
}
