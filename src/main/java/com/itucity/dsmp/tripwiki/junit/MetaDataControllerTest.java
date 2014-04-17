package com.itucity.dsmp.tripwiki.junit;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itucity.dsmp.tripwiki.controller.MetaDataController;

public class MetaDataControllerTest extends JUnitActionBase{
	@Autowired
	private MetaDataController metaDataController;

	@Test
	public void testfindArea() {
		request.setRequestURI("metadata/get_cities_with_place");
		request.setMethod(HttpMethod.GET.name());
		ModelAndView mv = null;
		try {
			mv = handlerAdapter.handle(request, response, new HandlerMethod(metaDataController, "getCitiesWithPalce"));
			//返回值比较
			String result = response.getContentAsString();
			Assert.assertNotNull(result);
			//打印返回值
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(mv);
		Assert.assertEquals(response.getStatus(), 200);
		Assert.assertEquals(mv.getViewName(), "/metadata/get_cities_with_place");
	}
	
	@Test
	public void testfindAreaRegion() {
		request.setRequestURI("metadata/get_regions_with_place");
		//request.addParameter("cityId", "230000");
		request.setMethod(HttpMethod.GET.name());
		ModelAndView mv1 = null;
		try {
			mv1 = handlerAdapter.handle(request, response, new HandlerMethod(metaDataController, "getRegionsWithPlace"));
			//返回值比较
			String result = response.getContentAsString();
			Assert.assertNotNull(result);
			//打印返回值
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(mv1);
		Assert.assertEquals(response.getStatus(), 200);
		Assert.assertEquals(mv1.getViewName(), "/metadata/get_regions_with_place");
	}
}