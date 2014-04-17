package com.itucity.dsmp.tripwiki.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itucity.dsmp.common.Constants;
import com.itucity.dsmp.common.page.PageSortCondition;
import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.tripwiki.dto.DataReturnDTO;
import com.itucity.dsmp.tripwiki.dto.LineCondition;
import com.itucity.dsmp.tripwiki.dto.LineDTO;
import com.itucity.dsmp.tripwiki.dto.LineVO;
import com.itucity.dsmp.tripwiki.service.ImageService;
import com.itucity.dsmp.tripwiki.service.LineService;
import com.itucity.dsmp.tripwiki.service.PlaceService;

/**
 * @author Eric
 * @version 0.1
 * 3/24/2014
 */
@Controller
@RequestMapping("api/line")
public class LineController {

	@Resource
	private LineService lineService;
	
	@Resource
	private PlaceService placeService;
	
	@Resource
	private ImageService imageService;
	
	
	/**
	 * 根据条件搜索路线
	 * 
	 * @param city 
	 *            城市名称，与经纬度坐标二者必选其一传入
	 * @param latitude
	 *            纬度坐标，须与经度坐标同时传入，与城市名称二者必选其一传入
	 * @param longitude
	 *            经度坐标，须与纬度坐标同时传入，与城市名称二者必选其一传入
	 * @param who
	 *            和谁
	 * @param when
	 *            时段
	 * @param type
	 *            景点类型
	 * @param sort
	 *            排序,1:默认，2:星级高优先，3:评论最高优先，4:分享最高优先
	 * @param limit
	 *            每页返回的结果条目数上限，最小值1，最大值40，如不传入默认为20
	 * @param page
	 *            页码，如不传入默认为1，即第一页
	 * @return
	 */
	@RequestMapping(value = "find_lines", method = RequestMethod.GET)
	@ResponseBody
	public Object findLines(
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "latitude", required = false) Float latitude,
			@RequestParam(value = "longitude", required = false) Float longitude,
			@RequestParam(value = "who", required = false, defaultValue = "3") Integer who,
			@RequestParam(value = "when", required = false, defaultValue = "4") Integer when,
			@RequestParam(value = "type", required = false, defaultValue = "6") Integer type,
			@RequestParam(value = "sort", required = false, defaultValue = "1") Integer sort,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {

		DataReturnDTO dataReturnDTO = new DataReturnDTO();

		LineCondition lineCondition = new LineCondition();
		lineCondition.setCity(city);

		if (latitude != null & longitude == null) {
			dataReturnDTO.setErrorInfo(Constants.ERROR_INVALID_PARAMETER, "longitude can't be empty");
		} else if (latitude == null & longitude != null) {
			dataReturnDTO.setErrorInfo(Constants.ERROR_INVALID_PARAMETER, "latitude can't be empty");
		}

		lineCondition.setLatitude(latitude);
		lineCondition.setLongitude(longitude);
		lineCondition.setWho(who);
		lineCondition.setWhen(when);
		lineCondition.setType(type);
		
		PageSortCondition pageSortCondition = new PageSortCondition();
		// 设置分页参数
		String sortField = null;// 排序条件
		pageSortCondition.setCountPerPage(limit);
		pageSortCondition.setPageIndex(page);
		if (sort == 1) {
			sortField = "";
		}
		pageSortCondition.setSortField(sortField);
		lineCondition.setPageSortCondition(pageSortCondition);

		PagesInfo<Object> pagesInfo = new PagesInfo<Object>();
		pagesInfo.setCountPerPage(limit);
		pagesInfo.setPageNumber(page);

		pagesInfo = lineService.getLineByCondition(lineCondition);

		dataReturnDTO.setData(pagesInfo);

		return dataReturnDTO;
	}

	/**
	 * 根据路线ID获取指定路线的详细信息，景点排序根据景点地理位置排序
	 * @param sort  景点排序，1:按地理位置降序排序，2:按地理位置升序排序
	 * @param lineId 路线ID
	 * @return 简介信息和景点列表
	 */
	@RequestMapping(value = "get_line_detail", method = RequestMethod.GET)
	@ResponseBody
	public Object getLineDetail(
			@RequestParam(value = "line_id", required = false) Integer lineId,
			@RequestParam(value = "sort", required = false, defaultValue = "1") Integer sort) {
		LineVO line = lineService.getLineById(lineId);
		
		DataReturnDTO dto = new DataReturnDTO();
		LineDTO lineDTO = new LineDTO();

		if (line == null) {
			dto.setErrorInfo(Constants.ERROR_NOT_FOUND,	"Cannot find line [id :" + lineId + "]");
		} else {
			lineDTO.setLine(line);
			lineDTO.setCover(imageService.getImageById(line.getCoverId()));
			lineDTO.setTags(lineService.getLineTag(lineId));
			lineDTO.setPlaces(lineService.getLinePlaces(lineId, sort));
		}
		dto.setData(lineDTO);
		return dto;
	}
	
	/**
	 * 添加线路
	 * @param line
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "add_line", method = RequestMethod.POST)
	@ResponseBody
	public Object addLine(@RequestBody LineVO line) throws IOException {
		DataReturnDTO dto = new DataReturnDTO();
		Integer id = lineService.addLine(line);
		if (id != null) {
			dto.setData(id);
		} else {
			dto.setErrorInfo(Constants.ERROR_ADD_DATA, "Add data failed!");
		}
		return dto;
	}
	
	/**
	 * 更新线路
	 * @param line
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "update_line", method = RequestMethod.POST)
	@ResponseBody
	public Object updateLine(@RequestBody LineVO line) throws IOException {
		DataReturnDTO dto = new DataReturnDTO();
		if (lineService.updateLine(line)) {
			dto.setData(1);
		} else {
			dto.setErrorInfo(Constants.ERROR_UPDATE_DATA,"Update failed  [id :" + line.getLineId() + "]");
		}
		return dto;
	}
	
	/**
	 * 删除一条线路
	 * @param lineId 线路ID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "delete_line", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteLine(
			@RequestParam(value = "line_id", required = false) Integer lineId)
			throws IOException {
		DataReturnDTO dto = new DataReturnDTO();
		if (lineService.deleteLine(lineId)) {
			dto.setData(1);
		} else {
			dto.setErrorInfo(Constants.ERROR_DELETE_DATA,"Delete failed [id :" + lineId + "]");
		}
		return dto;
	}
	
}
