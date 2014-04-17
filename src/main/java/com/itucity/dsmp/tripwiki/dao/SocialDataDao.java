package com.itucity.dsmp.tripwiki.dao;

import java.util.List;

import com.itucity.dsmp.common.base.IDao;
import com.itucity.dsmp.tripwiki.dao.entity.SocialDataPO;


/**
 * 行程的所有地点
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/29/2014
 */
public interface SocialDataDao extends IDao {

	/**
	 * 查询用户收藏
	 * @param uid
	 * 			用户ID
	 * @param type
	 * 			收藏内容类型(可以为攻略、景点等)
	 * @return
	 */
	List<SocialDataPO> findByUser(Integer uid, String type);
	
	/**
	 * 分页查询用户收藏内容
	 * @param uid
	 * 			用户ID
	 * @param type
	 * 			收藏类型(可以为攻略、景点等)
	 * @param first
	 * 			开始记录
	 * @param last
	 * 			结束数
	 * @return
	 */
	List<SocialDataPO> findByPage(Integer uid, String type, 
			Integer first, Integer max);
}
