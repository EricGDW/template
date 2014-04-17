package com.itucity.dsmp.tripwiki.dao;

import java.util.List;

import com.itucity.dsmp.common.base.IDao;
import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;

/**
 * 行程的所有地点
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/22/2014
 */

public interface ImageDao extends IDao {

	/**
	 * 按图片类型查询图片信息
	 * @param type
	 * @return
	 */
	List<ImagePO> findByType(String type);
	
	
	/**
	 * 按图片描述模糊查询图片信息
	 * @param description
	 * @return
	 */
	List<ImagePO> findByDescription(String description);
	
}
