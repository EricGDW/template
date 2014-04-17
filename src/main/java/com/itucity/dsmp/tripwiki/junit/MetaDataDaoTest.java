package com.itucity.dsmp.tripwiki.junit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itucity.dsmp.tripwiki.dao.MetaDataDao;
import com.itucity.dsmp.tripwiki.dao.entity.BkDeleteDataPO;

public class MetaDataDaoTest extends JUnitDaoBase {
	
	@Autowired
	private MetaDataDao metaDataDao;
	
	@Test
	public void testWeather() {
		System.out.println(metaDataDao.findWeather("1").get(0).getWeatherName());
	}
	
	@Test
	public void testSaveBkData() {
		BkDeleteDataPO bkDeleteDataPO = new BkDeleteDataPO();
		bkDeleteDataPO.setTableName("image");
		bkDeleteDataPO.setRecordId(12);
		bkDeleteDataPO.setContent("asfasdfasdfasd");
		metaDataDao.saveDeleteData(bkDeleteDataPO);
	}
	
	@Test
	public void testSaveBKdata2(){
		metaDataDao.buckupData("AreaPO", "areaId", 910011);
	}
	
	

}
