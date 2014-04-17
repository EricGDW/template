package com.itucity.dsmp.tripwiki.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.tripwiki.dao.ImageDao;
import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;

/**
 * 图片的Dao层实现
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/24/2014
 */
@Transactional
@Repository("imageDao")
public class ImageDaoImpl extends BaseDao implements ImageDao {

	@Override
	public ImagePO findById(Integer imageId) {
		return super.find(ImagePO.class, imageId);
	}

	@Override
	public Integer save(ImagePO image) {
		super.save(image);
		return image.getImageId();
	}

	@Override
	public Boolean update(ImagePO image) {
		super.update(image);
		return true;
	}

	@Override
	public Boolean delete(ImagePO image) {
		super.delete(image);
		return true;
	}

	@Override
	public Boolean deleteById(Integer imageId) {
		ImagePO image = findById(imageId);
		return delete(image);
	}


}
