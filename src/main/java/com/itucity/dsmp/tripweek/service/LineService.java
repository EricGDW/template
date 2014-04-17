package com.itucity.dsmp.tripweek.service;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.tripweek.dto.LineCondition;

public interface LineService {
	
	/**
	 * 根据条件搜索路线
	 * @param lineCondition	路线条件
	 * @param pagesInfo	
	 * @return pagesInfo
	 */
	PagesInfo<Object> findLines(LineCondition lineCondition,PagesInfo<Object> pagesInfo);

}
