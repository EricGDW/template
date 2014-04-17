package com.itucity.dsmp.tripwiki.junit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.common.util.JsonUtil;
import com.itucity.dsmp.tripwiki.dao.PlaceDao;
import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;
import com.itucity.dsmp.tripwiki.dto.ImageCondition;

public class PlaceDaoTest extends JUnitDaoBase{
	
	@Autowired
	private PlaceDao placeDao;
	
	@Test
	public void testGetPlaceImages(){
		PagesInfo<ImagePO> pagesInfo = new PagesInfo<ImagePO>();
		ImageCondition condition = new ImageCondition();
		condition.setPlaceId(1);
		pagesInfo.setCountPerPage(2);
		pagesInfo.setPageNumber(1);
		System.out.println(JsonUtil.obj2JsonArr(placeDao.findPlaceImages(pagesInfo,condition)));
	}

}
