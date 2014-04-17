package com.itucity.dsmp.tripwiki.junit;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * 说明：JUnit测试action时使用的基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-actions.xml", "classpath:applicationContext.xml" })
public class JUnitActionBase {
	@Autowired
	public RequestMappingHandlerAdapter handlerAdapter;

	public static MockHttpServletRequest request;
	public static MockHttpServletResponse response;

	@BeforeClass
	public static void before() {
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
	}
}