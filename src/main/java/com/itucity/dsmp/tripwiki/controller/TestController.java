package com.itucity.dsmp.tripwiki.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.itucity.dsmp.common.util.Config;
import com.itucity.dsmp.tripwiki.service.ImageService;
import com.itucity.dsmp.tripwiki.service.PlaceService;
import com.itucity.dsmp.tripwiki.service.TagService;
import com.itucity.dsmp.tripwiki.service.model.ImageVO;
import com.itucity.dsmp.tripwiki.service.model.PlaceVO;
import com.itucity.dsmp.tripwiki.service.model.TagVO;

@Controller
public class TestController {
	
	@Resource
	private PlaceService destinationService;
	
	@Resource
	private ImageService imageService;
	
	@Resource
	private TagService tagService;

	@RequestMapping(value="/test",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> addDestination() 
			throws IOException {
		
		addData();
		
		Map<String, String> map = new HashMap<String, String>(1);
	    map.put("status", "1");
	    
	    return map;
	}
	
	public void addData(){
		for(int c= 1;c<=104;c++){
			
			String url = "http://www.itangogo.com:8082/twdata/data/record/search?collection=Linkkk_Travle_PlaceInfo&page_size=20&page_index="+c;
	
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
			
			form.add("timestamp", String.valueOf(System.currentTimeMillis()));
			form.add("appkey", "tripwiki");
			String signature = "";
			form.add("signature", signature);
			
			String result = restTemplate.getForObject(url, String.class, form);
			result = Config.codeGetMethodChinese(result.substring(1, result.length()-1));
//			System.out.println(result);
			
			JSONObject jsonObject = new JSONObject(result);
			JSONArray resultArray = jsonObject.getJSONArray("resultsList");  
			
			for(int i=0;i<resultArray.length();i++){
				JSONObject tmpJsonObject = resultArray.getJSONObject(i);
				
				JSONObject placeInfoJsonObject = new JSONObject(tmpJsonObject.get("content").toString());
				
				//System.out.println(placeInfoJsonObject.get("content"));
				PlaceVO dVO = new PlaceVO();
				
				List<Integer> albumList = new ArrayList<Integer>();
				List<Integer> tagList = new ArrayList<Integer>();
				
				//景点基础信息
	//			Integer id = placeInfoJsonObject.getInt("id");
	//			dVO.setId(id);
				dVO.setContent(placeInfoJsonObject.get("content").toString());
				dVO.setAddress(placeInfoJsonObject.get("address").toString());
				dVO.setLatitude(Float.valueOf(placeInfoJsonObject.get("latitude").toString()==null? "0":placeInfoJsonObject.get("latitude").toString()));
				dVO.setLongitude(Float.valueOf(placeInfoJsonObject.get("longitude").toString()==null? "0":placeInfoJsonObject.get("longitude").toString()));
				dVO.setName(placeInfoJsonObject.get("title").toString());
				dVO.setCityCode(placeInfoJsonObject.get("city").toString());
	//			dVO.setTags("");
				dVO.setHateCount(10);
				dVO.setFavouriteCount(501);
				dVO.setScore("四星");
				dVO.setShareCount(Integer.valueOf(placeInfoJsonObject.get("count_favourite").toString()));
				
				Integer destinationId = 0;
				//景点封面
				Integer coverImgId = null;
				String coverStr = placeInfoJsonObject.get("cover").toString();
				if(coverStr!=null && !coverStr.equals("null")){
					JSONObject cover = new JSONObject(coverStr);
					System.out.println("cover--->"+cover);
				
					ImageVO imageVO = new ImageVO();
					imageVO.setDestinationId(destinationId);
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
						
					coverImgId = imageService.addImage(imageVO);
				}
				
				//相册信息
				JSONArray albums = placeInfoJsonObject.getJSONArray("album");
				System.out.println("albums----->"+albums);
				
				for(int j=0;j<albums.length();j++){
					ImageVO imageVOalbum = new ImageVO();
					JSONObject album = new JSONObject(albums.get(j).toString());
					imageVOalbum.setDestinationId(destinationId);
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
					
					albumList.add(imageService.addImage(imageVOalbum));
				}
	
				//标签信息
				JSONArray tags = placeInfoJsonObject.getJSONArray("tags");
				System.out.println("tags--->"+tags);
				for(int j=0;j<tags.length();j++){
					JSONObject tag = new JSONObject(tags.get(j).toString());
					TagVO tagVO = new TagVO();
					tagVO.setTagType(tag.getString("tag_type"));
					tagVO.setCodeName(tag.getString("code_name"));
					tagVO.setStarred(tag.getBoolean("starred"));
					tagVO.setWeight(Float.valueOf(tag.get("weight").toString()));
					tagVO.setName(tag.getString("name"));
					//tagVO.setCoverId(tag.get("cover")==null?0:Integer.valueOf(tag.get("cover").toString()));
					//tagVO.setParentId(tag.getInt("base_tag"));
					tagList.add(tagService.addTag(tagVO));
				}
				
				dVO.setCoverId(coverImgId);
				dVO.setAlbumList(albumList);
				dVO.setTagList(tagList);
				
				//保存景点信息
				destinationService.addDestination(dVO);
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			}
			System.out.println(c);
		}
	}
}
