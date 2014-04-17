package com.itucity.dsmp.tripwiki.junit;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itucity.dsmp.tripwiki.controller.PlaceController;

public class PlaceControllerTest extends JUnitActionBase{
	@Autowired
	private PlaceController placeController;

	@Test
	public void testfindPlace() {
		request.setRequestURI("api/place/11");
		request.setMethod(HttpMethod.GET.name());
		ModelAndView mv = null;
		try {
			mv = handlerAdapter.handle(request, response, new HandlerMethod(placeController, "getPlaceById"));
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
	
	public void testImportPlaceData() {
		request.setRequestURI("api/place/importdata");
		request.setMethod(HttpMethod.GET.name());
		ModelAndView mv = null;
		try {
			mv = handlerAdapter.handle(request, response, new HandlerMethod(placeController, "importData"));
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
		Assert.assertEquals(mv.getViewName(), "/api/place/importdata");
	}
	
	@Test
	public void testGetPlaceImages() {
		request.setRequestURI("api/place/get_place_images");
		request.setMethod(HttpMethod.GET.name());
		ModelAndView mv = null;
		try {
			mv = handlerAdapter.handle(request, response, new HandlerMethod(placeController, "getPlaceImages"));
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
		Assert.assertEquals(mv.getViewName(), "/api/place/get_place_images");
	}
	
}