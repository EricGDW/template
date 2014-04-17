package com.itucity.dsmp.identity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itucity.dsmp.identity.service.model.GroupVO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/14/2014
 */
@Service("resourceService")
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
	 * 按组名获取组
	 * @param groupName
	 * @return
	 */
	GroupVO getGroupByName(String groupName);
	
	/**
	 * 按组名模糊查询
	 * @param nameLike
	 * @param first 开始记录
	 * @param max	结果数
	 * @return
	 */
	List<GroupVO> getGroupByLike(String nameLike, Integer first, Integer max);
	
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
