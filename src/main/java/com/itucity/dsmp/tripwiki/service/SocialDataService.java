package com.itucity.dsmp.tripwiki.service;

import java.util.List;

import com.itucity.dsmp.tripwiki.service.model.SocialDataVO;


/**
 * 用户收藏接口
 * @author Eric
 * @version 0.1
 * 
 * 3/29/2014
 */
public interface SocialDataService {


	/**
	 * 获取用户收藏相关信息
	 * @param id
	 * @return
	 */
	SocialDataVO getUserCollectionById(Integer id);
	
	/**
	 * 获取用户的所有收藏相关信息
	 * @param uid
	 * 			用户ID
	 * @param type
	 * 			搜索的类型
	 * @return
	 */
	List<SocialDataVO> getUserCollectionByUser(Integer uid, String type);
	
	/**
	 * 分页获取用户搜索相关信息
	 * @param uid
	 * 			用户ID
	 * @param type
	 * 			搜索的类型
	 * @param first
	 * 
	 * @param max
	 * 
	 * @return
	 */
	List<SocialDataVO> getUserCollectionByPage(Integer uid, String type,
			Integer first, Integer max);
	
	
	/**
	 * 增加用户搜索记录
	 * @param colletion
	 * 
	 * @return
	 */
	Integer addUserColletion(SocialDataVO colletion);
	
	
	/**
	 * 删除用户收藏记录
	 * @param id
	 * 
	 * @return
	 */
	Boolean deleteUserCollection(Integer id);

}
