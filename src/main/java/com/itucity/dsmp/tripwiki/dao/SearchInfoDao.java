package com.itucity.dsmp.tripwiki.dao;

import java.util.List;

import com.itucity.dsmp.common.base.IDao;
import com.itucity.dsmp.tripwiki.dao.entity.SearchInfoPO;


/**
 * 行程的所有地点
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/29/2014
 */
public interface SearchInfoDao extends IDao {
	
	/**
	 * 查询用户搜索记录
	 * @param uid
	 * 			用户ID
	 * @param type
	 * 			搜索类型(可以为攻略、景点等)
	 * @return
	 */
	List<SearchInfoPO> findByUser(Integer uid, String type);
	
	/**
	 * 分页查询用户搜索记录
	 * @param uid
	 * 			用户ID
	 * @param type
	 * 			搜索类型(可以为攻略、景点等)
	 * @param first
	 * 			开始记录
	 * @param max
	 * 			结束数
	 * @return
	 */
	List<SearchInfoPO> findByPage(Integer uid, String type, 
			Integer first, Integer max);
	
	/**
	 * 按查询类型获取查询最多的Top
	 * @param type
	 * @param topNum
	 * @return
	 */
	List<SearchInfoPO> findTopSearchConditon(String type, Integer topNum);
}
