package com.itucity.dsmp.common.page;

import java.io.Serializable;

/**
 * 分页排序条件类
 */
public class PageSortCondition implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 每页记录条数 */
	private int countPerPage;
	
	/** 页码(第几页) */
	private int pageIndex;
	
	/** 排序字段(需要排序的字段 例如: a.name desc, b.id asc) */
	private String sortField;
	
	public PageSortCondition(){}
	
	public PageSortCondition(int pageIndex, int countPerPage, String sortField) {
		this.pageIndex=pageIndex;
		this.countPerPage=countPerPage;
		this.sortField=sortField;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

}
