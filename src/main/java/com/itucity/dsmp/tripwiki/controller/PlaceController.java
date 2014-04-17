package com.itucity.dsmp.tripwiki.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.itucity.dsmp.common.Constants;
import com.itucity.dsmp.common.page.PageSortCondition;
import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.common.util.Config;
import com.itucity.dsmp.tripwiki.dto.DataReturnDTO;
import com.itucity.dsmp.tripwiki.dto.ImageCondition;
import com.itucity.dsmp.tripwiki.dto.ImageVO;
import com.itucity.dsmp.tripwiki.dto.PlaceVO;
import com.itucity.dsmp.tripwiki.dto.TagVO;
import com.itucity.dsmp.tripwiki.service.ImageService;
import com.itucity.dsmp.tripwiki.service.PlaceService;
import com.itucity.dsmp.tripwiki.service.TagService;
import com.itucity.dsmp.tripwiki.util.ReturnPackaging;

/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/24/2014
 */
@Controller
@RequestMapping("api/place")
public class PlaceController {
	
	@Resource
	private PlaceService placeService;
	
	@Resource
	private ImageService imageService;
	
	@Resource
	private TagService tagService;
	
	@Resource
	private ReturnPackaging errorMessage;
	
	
	/**
	 * 根据条件搜索景点
	 * 
	 * @param city
	 *            城市名称，与经纬度坐标二者必选其一传入
	 * @param region
	 *            城市区域名，如传入城市区域名，则城市名称必须传入
	 * @param latitude
	 *            纬度坐标，须与经度坐标同时传入，与城市名称二者必选其一传入
	 * @param longitude
	 *            经度坐标，须与纬度坐标同时传入，与城市名称二者必选其一传入
	 * @param type
	 *            景点类型
	 * @param tag
	 *            景点标签
	 * @param sort
	 *            排序，1:默认，2:星级高优先，3:评论最高优先，4:分享最高优先，5:离传入经纬度坐标距离近优先
	 * @param limit
	 *            每页返回的结果条目数上限，最小值1，最大值40，如不传入默认为20
	 * @param page
	 *            页码，如不传入默认为1，即第一页
	 * @return
	*/
	@RequestMapping(value = "find_places", method = RequestMethod.GET)
	@ResponseBody
	public Object findPalces(
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "region", required = false) String region,
			@RequestParam(value = "latitude", required = false) Float latitude,
			@RequestParam(value = "longitude", required = false) Float longitude,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "tag", required = false) List<String> tags,
			@RequestParam(value = "sort", required = true, defaultValue = "1") Integer sort,
			@RequestParam(value = "limit", required = true, defaultValue = "20") Integer limit,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer page) {

		DataReturnDTO dataReturnDTO = new DataReturnDTO();
		//PagesInfo<Object> pagesInfo = new PagesInfo<Object>();
		PageSortCondition pageSortCondition = new PageSortCondition();
		pageSortCondition.setPageIndex(page);
		pageSortCondition.setCountPerPage(limit);

		String sortField = "";
		pageSortCondition.setSortField(sortField);
		dataReturnDTO.setData(pageSortCondition);
		return dataReturnDTO;
	}
 
	/**
	 * 根据多个景点ID批量获取指定景点的详细信息
	 * @param placeIds	多个景点ID，如place_ids=1,2,3
	 * @return
	 */
	@RequestMapping(value = "get_place_detail", method = RequestMethod.GET)
	@ResponseBody
	public Object getPalceDetail(
			@RequestParam(value = "place_ids", required = false) List<Integer> placeIds) {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		DataReturnDTO dto = new DataReturnDTO();
		if(placeIds!=null){
			for (Integer id : placeIds) {
				if (id != null && Config.isNumeric(String.valueOf(id))) {
					PlaceVO place = placeService.getPlaceById(id);
					if (place == null) {
						dto.setErrorInfo(Constants.ERROR_NOT_FOUND,"Cannot find Place [place_id:"+ id + "]");
					} 
					list.add(place);
				} else {
					dto.setErrorInfo(Constants.ERROR_INVALID_PARAMETER,"Request contains the invalid parameters [place_id:"+ id + "]");
				}
			}
		} else {
			dto.setErrorInfo(Constants.ERROR_INVALID_PARAMETER,"Request contains the invalid parameters [place_ids:"+ placeIds + "]");
		}
		dto.setData(list);
		return dto;
	}
	
	/**
	 * 添加地点信息
	 * @param Place
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "add_place", method = RequestMethod.POST)
	@ResponseBody
	public Object addPlace(@RequestBody PlaceVO Place) throws IOException {
		Integer id = placeService.addPlace(Place);
		DataReturnDTO dto = new DataReturnDTO();
		if (id == null) {
			dto.setErrorInfo(Constants.ERROR_ADD_DATA, "Add place data error!");
		} else {
			dto.setData(id);
		}
		return dto;
	}
	
	/**
	 * 更新地点信息
	 * @param Place
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "update_place", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePlace(@RequestBody PlaceVO Place) throws IOException {
		DataReturnDTO dto = new DataReturnDTO();
		if (placeService.updatePlace(Place)) {
			dto.setData(1);
		} else {
			dto.setErrorInfo(Constants.ERROR_UPDATE_DATA, "Update place data error [placeId:"+Place.getPlaceId()+"]!");
		}
		return dto;
	}
	
	/**
	 * 按地点ID删除地点
	 * @param place_id	地点ID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "delete_place", method = RequestMethod.GET)
	@ResponseBody
	public Object deletePlace(
			@RequestParam(value = "place_id", required = false) Integer placeId)
			throws IOException {
		DataReturnDTO dto = new DataReturnDTO();
		if (placeId == null) {
			dto.setErrorInfo(Constants.ERROR_INVALID_PARAMETER,"Request contains the invalid parameters [place_id:"+ placeId + "]");
		} else if (placeService.deletePlace(placeId)) {
			dto.setData(1);
		} else {
			dto.setErrorInfo(Constants.ERROR_DELETE_DATA,"Delete place data error [placeId:" + placeId + "]!");
		}
		return dto;
	}
	
	/**
	 * 用户对景点的点赞操作
	 * @param place_id	景点ID
	 * @param uid	用户ID
	 * @return 点赞数
	 */
	@RequestMapping(value = "set_place_recommend_count", method = RequestMethod.GET)
	@ResponseBody
	public Object setPlaceRecommendCount(
			@RequestParam(value = "place_id", required = true) Integer placeId,
			@RequestParam(value = "uid") Integer uid) {
		DataReturnDTO dto = new DataReturnDTO();
		return dto;
	}

	/**
	 * 用户对景点的分享操作
	 * @param place_id	景点ID
	 * @param uid 用户ID
	 * @return 分享数
	 */
	@RequestMapping(value = "set_place_share_count", method = RequestMethod.GET)
	@ResponseBody
	public Object setPlaceShareCount(
			@RequestParam(value = "place_id", required = true) Integer placeId,
			@RequestParam(value = "uid") Integer uid) {
		DataReturnDTO dto = new DataReturnDTO();
		return dto;
	}

	/**
	 * 用户对景点的签到操作
	 * @param place_id	景点ID
	 * @param uid	用户ID
	 * @return 签到状态
	 */
	@RequestMapping(value = "sign_in_by_user", method = RequestMethod.GET)
	@ResponseBody
	public Object signInByUser(
			@RequestParam(value = "place_id", required = false) Integer placeId,
			@RequestParam(value = "uid", required = false) Integer uid) {
		DataReturnDTO dto = new DataReturnDTO();
		if (placeId == null) {
			dto.setErrorInfo(Constants.ERROR_INVALID_PARAMETER,"Request contains the invalid parameters [place_id:"+ placeId + "]");
		} else if(uid==null){
			dto.setErrorInfo(Constants.ERROR_INVALID_PARAMETER,"Request contains the invalid parameters [uid:"+ uid + "]");
		}
		return dto;
	}

	/**
	 * 景点照片列表
	 * @param placeId	景点ID
	 * @param limit		每页图片数
	 * @param page		页码
	 * @return
	 */
	@RequestMapping(value="get_place_images",method=RequestMethod.GET)
	@ResponseBody
	public Object getPlaceImages(
			@RequestParam(value = "place_id", required = false) Integer placeId,
			@RequestParam(value = "limit", required = false,defaultValue="20") Integer limit,
			@RequestParam(value = "page", required = false,defaultValue="1") Integer page) {
		PagesInfo<ImageVO> pagesInfo = new PagesInfo<ImageVO>();
		DataReturnDTO dto = new DataReturnDTO();
		if (placeId != null) {
			pagesInfo.setCountPerPage(limit);
			pagesInfo.setPageNumber(page);
	
			ImageCondition condition = new ImageCondition();
			condition.setPlaceId(placeId);
			PagesInfo<ImageVO> result = placeService.getPlaceImages(pagesInfo, condition);
			
			dto.setData(result);
		} else {
			dto.setErrorInfo(Constants.ERROR_INVALID_PARAMETER,"Request contains the invalid parameters [place_id:"+ placeId + "]");
		}
		return dto;
	}
	
	/**
	 * 景点数据导入
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="importdata",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> importData() throws IOException {
		addData();
		Map<String, String> map = new HashMap<String, String>(1);
	    map.put("status", "1");
	    return map;
	}
	
	public void addData(){
		for(int c= 1;c<=104;c++){
			String url = "http://hailangya.eicp.net:8081/twdata/data/record/search?collection=Linkkk_Travle_PlaceInfo&page_size=20&page_index="+c;
	
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
			
			form.add("timestamp", String.valueOf(System.currentTimeMillis()));
			form.add("appkey", "tripwiki");
			String signature = "";
			form.add("signature", signature);
			
			String result = restTemplate.getForObject(url, String.class, form);
			result = Config.codeGetMethodChinese(result.substring(1, result.length()-1));
			
			JSONObject jsonObject = new JSONObject(result);
			JSONArray resultArray = jsonObject.getJSONArray("resultsList");  
			
			for(int i=0;i<resultArray.length();i++){
				JSONObject tmpJsonObject = resultArray.getJSONObject(i);
				
				JSONObject placeInfoJsonObject = new JSONObject(tmpJsonObject.get("content").toString());
				
				PlaceVO dVO = new PlaceVO();
				
				//景点基础信息
				Date createTime = new Date();
				dVO.setCreateTime(createTime);
				dVO.setModifyTime(createTime);
				dVO.setContent(placeInfoJsonObject.get("content").toString());
				dVO.setAddress(placeInfoJsonObject.get("address").toString());
				dVO.setLatitude(Float.valueOf(placeInfoJsonObject.get("latitude").toString()==null? "0":placeInfoJsonObject.get("latitude").toString()));
				dVO.setLongitude(Float.valueOf(placeInfoJsonObject.get("longitude").toString()==null? "0":placeInfoJsonObject.get("longitude").toString()));
				dVO.setTitle(placeInfoJsonObject.get("title").toString());
				dVO.setAreaCode(placeInfoJsonObject.get("city").toString());
				dVO.setContact(placeInfoJsonObject.get("contact").toString());
				dVO.setTips(placeInfoJsonObject.get("tip").toString());
				try {
					dVO.setPrice(Float.valueOf(placeInfoJsonObject.get("price").toString()==null || placeInfoJsonObject.get("price").toString()==""? "0":placeInfoJsonObject.get("price").toString()));
				} catch (Exception e) {
			
				}
				
				dVO.setTimeDesc(placeInfoJsonObject.get("time_desc").toString());
				dVO.setTimeStart(placeInfoJsonObject.get("time_start").toString());
				dVO.setTimeEnd(placeInfoJsonObject.get("time_end").toString());
				
				//保存景点信息
				Integer placeId = placeService.addPlace(dVO);
				//景点封面
				String coverStr = placeInfoJsonObject.get("cover").toString();
				if(coverStr!=null && !coverStr.equals("null")){
					JSONObject cover = new JSONObject(coverStr);
					System.out.println("cover--->"+cover);
				
					ImageVO imageVO = new ImageVO();
					imageVO.setPlaceId(placeId);
					imageVO.setType("cover");
					imageVO.setIsDel(0);
					imageVO.setIsPrivate(0);
					imageVO.setDescription(cover.getString("caption"));
					imageVO.setLarge(cover.getString("large"));
					imageVO.setMedium(cover.getString("medium"));
					imageVO.setMini(cover.getString("mini"));
					imageVO.setRaw(cover.getString("raw"));
					imageVO.setRawHeight(cover.getInt("raw_height"));
					imageVO.setRawWidth(cover.getInt("raw_width"));
					imageVO.setSmall(cover.getString("small"));
					imageVO.setSquare(cover.getString("square"));
						
					Integer coverImgId = imageService.addImage(imageVO);
					placeService.addPlaceImage(placeId, coverImgId);
				}
				
				//相册信息
				JSONArray albums = placeInfoJsonObject.getJSONArray("album");
				System.out.println("albums----->"+albums);
				
				for(int j=0;j<albums.length();j++){
					ImageVO imageVOalbum = new ImageVO();
					JSONObject album = new JSONObject(albums.get(j).toString());
					imageVOalbum.setPlaceId(placeId);
					imageVOalbum.setType("album");
					imageVOalbum.setIsDel(0);
					imageVOalbum.setIsPrivate(0);
					imageVOalbum.setDescription(album.getString("caption"));
					imageVOalbum.setLarge(album.getString("large"));
					imageVOalbum.setMedium(album.getString("medium"));
					imageVOalbum.setMini(album.getString("mini"));
					imageVOalbum.setRaw(album.getString("raw"));
					imageVOalbum.setRawHeight(album.getInt("raw_height"));
					imageVOalbum.setRawWidth(album.getInt("raw_width"));
					imageVOalbum.setSmall(album.getString("small"));
					imageVOalbum.setSquare(album.getString("square"));
					
					Integer imageId = imageService.addImage(imageVOalbum);
					placeService.addPlaceImage(placeId, imageId);
				}
	
				//标签信息
				JSONArray tags = placeInfoJsonObject.getJSONArray("tags");
				System.out.println("tags--->"+tags);
				for(int j=0;j<tags.length();j++){
					JSONObject tag = new JSONObject(tags.get(j).toString());
					TagVO tagVO = new TagVO();
					tagVO.setTagType(tag.getString("tag_type"));
					tagVO.setCodeName(tag.getString("code_name"));
					//tagVO.setStarred(tag.getBoolean("starred"));
					tagVO.setWeight((Integer)tag.get("weight"));
					tagVO.setName(tag.getString("name"));
					//tagVO.setCoverId(tag.get("cover")==null?0:Integer.valueOf(tag.get("cover").toString()));
					//tagVO.setParentId(tag.getInt("base_tag"));
					Integer tagId = tagService.addTag(tagVO);
					try {
						placeService.addPlaceTag(placeId, tagId);	
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			}
			System.out.println(c);
		}
	}
}
