package com.itucity.dsmp.tripwiki.dao;

import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;

/**
 * 行程的所有地点
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/22/2014
 */

public interface ImageDao {
	
	ImagePO findById(Integer imageId);
	
	Integer save(ImagePO image);
	
	Boolean update(ImagePO image);
	
	Boolean delete(ImagePO image);
	
	Boolean deleteById(Integer imageId);
	
}
