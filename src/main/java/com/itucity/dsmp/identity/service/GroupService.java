package com.itucity.dsmp.identity.service;

import java.util.List;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.identity.service.model.GroupVO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/14/2014
 */
public interface GroupService {

	/**
	 * 获取所有可用的组
	 * @return
	 */
	List<GroupVO> getAvailableRoles();
	
	/**
	 * 按组id获取组
	 * @param id
	 * @return
	 */
	GroupVO getGroupByID(Integer id);
	
	/**
	 * 按组名模糊查询
	 * @param nameLike
	 * 
	 * @return
	 */
	List<GroupVO> getGroupByLike(String nameLike);
	
	/**
	 * 分页获取组信息
	 * @param limit 	每页记录数量
	 * @param pageNo	第几页
	 * 
	 * 不传limit或pageNo时获取所有数据，当一页处理
	 * 
	 * @return
	 */
	PagesInfo<GroupVO> getGroupByPage(Integer limit, Integer pageNo);
	
	/**
	 * 添加组
	 * @param group
	 * @return
	 */
	Boolean addGroup(GroupVO group);
	
	/**
	 * 编辑组
	 * @param group
	 * @return
	 */
	Boolean editGroup(GroupVO group);
	
	/**
	 * 删除组
	 * @param id
	 * @return
	 */
	Boolean deleteGroup(Integer id);
}
