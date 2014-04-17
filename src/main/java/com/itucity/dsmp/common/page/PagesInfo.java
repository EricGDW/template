package com.itucity.dsmp.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * <p>分页类</p>
 * @param <T>
 */
public class PagesInfo<T> implements Serializable{
	
	private static final long serialVersionUID = -5142765687292994856L;

	/**
	 * 每页记录数
	 */
	public int countPerPage;
	
	/**
	 * 当前页码
	 */
	public int pageNumber;

	/**
	 * 总页数
	 */
	public long totalPage;

	/**
	 * 总记录数
	 */
	public long totalRecord;
	
	
	/**
	 * 当前页的结果集
	 */
	public List<T> resultsList;

	/**
	 * <p>获取每页记录数</p>
	 * @return 返回每页记录数
	 */
	public int getCountPerPage() {
		return countPerPage;
	}

	/**
	 * <p>设置每页记录数</p>
	 * @param countPerPage 每页记录数
	 */
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	/**
	 * <p>获取当前页码</p>
	 * @return 返回当前页码
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * <p>设置当前页码</p>
	 * @param pageNumber 当前页码
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * <p>获取总页数</p>
	 * @return 返回总页数
	 */
	public long getTotalPage() {
		return totalPage;
	}

	/**
	 * <p>设置总页数</p>
	 * @param totalPage 总页数
	 */
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * <p>获取总记录数</p>
	 * @return 返回总记录数
	 */
	public long getTotalRecord() {
		return totalRecord;
	}

	/**
	 * <p>设置总记录数</p>
	 * @param totalRecord 总记录数
	 */
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	/**
	 * <p>获取当前页的结果集</p>
	 * @return 返回当前页码的结果集
	 */
	public List<T> getResultsList() {
		return resultsList;
	}

	/**
	 * <p>设置当前页的结果集</p>
	 * @param resultsList 当前页的结果集
	 */
	public void setResultsList(List<T> resultsList) {
		this.resultsList = resultsList;
	}	
	
}
