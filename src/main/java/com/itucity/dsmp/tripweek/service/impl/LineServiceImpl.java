package com.itucity.dsmp.tripweek.service.impl;

import org.springframework.stereotype.Service;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.tripweek.dto.LineCondition;
import com.itucity.dsmp.tripweek.service.LineService;

@Service("lineService")
public class LineServiceImpl implements LineService{

	@Override
	public PagesInfo<Object> findLines(LineCondition lineCondition,
			PagesInfo<Object> pagesInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
