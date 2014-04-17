package com.itucity.dsmp.tripwiki.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itucity.dsmp.tripwiki.dto.AreaCondition;
import com.itucity.dsmp.tripwiki.dto.AreaVO;
import com.itucity.dsmp.tripwiki.dto.DataReturnDTO;
import com.itucity.dsmp.tripwiki.service.MetaDataService;

/**
 * <b>说明: </b> 
 * TODO 基础信息（meta_data）数据接口
 * <b>Remarks: </b> 
 * TODO Controller类
 * @author Kevin 
 * @Email zhyacn@gmail.com
 * @created 2014-5-15
 */
@Controller
@RequestMapping("api/metadata")
public class MetaDataController {
	
	@Resource
	private MetaDataService metaDataService;
	

	/**
	 * 获取支持景点搜索的最新城市列表
	 * @return
	 */
	@RequestMapping(value = "get_cities_with_place", method = RequestMethod.GET)
	@ResponseBody
	public Object getCitiesWithPalce() {
		AreaCondition condition = new AreaCondition();
		condition.setParentId(0);
		List<AreaVO> list = metaDataService.findArea(condition);
		DataReturnDTO returnDTO = new DataReturnDTO();
		returnDTO.setData(list);
		return returnDTO;
	}

	/**
	 * 获取支持景点搜索的最新城市下属区域列表
	 * @param cityId 城市ID
	 * @return
	 */
	@RequestMapping(value = "get_regions_with_place", method = RequestMethod.GET)
	@ResponseBody
	public Object getRegionsWithPlace(
			@RequestParam(value = "cityId", required = true, defaultValue = "310000") Integer cityId) {
		AreaCondition condition = new AreaCondition();
		condition.setParentId(cityId);
		List<AreaVO> list = metaDataService.findArea(condition);
		DataReturnDTO returnDTO = new DataReturnDTO();
		returnDTO.setData(list);
		return returnDTO;
	}

}
