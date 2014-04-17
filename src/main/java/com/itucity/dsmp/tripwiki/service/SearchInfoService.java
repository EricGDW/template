package com.itucity.dsmp.tripwiki.service;

import java.util.List;

import com.itucity.dsmp.tripwiki.service.model.SearchInfoVO;


/**
 * 用户搜索信息接口
 * @author Eric
 * @version 0.1
 * 
 * 3/29/2014
 */
public interface SearchInfoService {
	/**
	 * 获取用户搜索相关信息
	 * @param id
	 * @return
	 */
	SearchInfoVO getUserSearchInfoById(Integer id);
	
	/**
	 * 获取用户搜索相关信息
	 * @param uid
	 * 			用户ID
	 * @param type
	 * 			搜索的类型
	 * @return
	 */
	List<SearchInfoVO> getUserSearchInfoByUser(Integer uid, String type);
	
	/**
	 * 获取用户搜索相关信息
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
	List<SearchInfoVO> getUserSearchInfoByPage(Integer uid, String type,
			Integer first, Integer max);
	
	
	/**
	 * 增加用户搜索记录
	 * @param searchInfo
	 * 
	 * @return
	 */
	Integer addUserSearchInfo(SearchInfoVO searchInfo);
	
	
	/**
	 * 删除用户搜索记录
	 * @param id
	 * 
	 * @return
	 */
	Boolean deleteUserSearchInfo(Integer id);
	
	/**
	 * 查询次数最多的查询条件列表
	 * @param num
	 * 
	 * @return
	 */
	List<SearchInfoVO> topSearchInfo(Integer num);
}
