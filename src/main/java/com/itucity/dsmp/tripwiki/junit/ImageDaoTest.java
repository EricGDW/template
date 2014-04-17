package com.itucity.dsmp.tripwiki.junit;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.common.util.JsonUtil;
import com.itucity.dsmp.tripwiki.dao.ImageDao;
import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;
import com.itucity.dsmp.tripwiki.dto.ImageCondition;

public class ImageDaoTest extends JUnitDaoBase{
	@Autowired
	private ImageDao imageDao;
	
	@Test
	public void testGetImages(){
		
		PagesInfo pagesInfo = new PagesInfo();
		ImageCondition condition = new ImageCondition();
		condition.setPlaceId(1);
		
		List<ImagePO> list = pagesInfo.getResultsList();
		for(ImagePO po:list){
			System.out.println(JsonUtil.object2JsonStr(po));
		}
	}

}
