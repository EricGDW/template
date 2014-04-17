package com.itucity.dsmp.identity.dao;

import java.util.List;

import com.itucity.dsmp.common.base.IDao;
import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.identity.dao.entity.GroupPO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
public interface GroupDao extends IDao {
	
	/**
	 * 查询所有组
	 * @return
	 */
	List<GroupPO> findAll();
	
	/**
	 * 根据组类型查询
	 * @param type
	 * @return
	 */
	List<GroupPO> findByType(String type);
	
	/**
	 * 根据组名进行Like查询
	 * @param groupName
	 * @return
	 */
	List<GroupPO> findByNameLike(String groupName);
	
	/**
	 * 对组名进行分页查询
	 * @param pagesInfo
	 * 
	 * @return
	 */
	PagesInfo<GroupPO> findByPage(PagesInfo<GroupPO> pagesInfo);
	
	/**
	 * 按组ID查询组
	 * @param id
	 * @return
	 */
	GroupPO findById(Integer id);
	
	/**
	 * 增加组
	 * @param user
	 * @return
	 */
	Integer save(GroupPO group);
	
	/**
	 * 修改组
	 * @param group
	 * @return
	 */
	Boolean update(GroupPO group);
	
	/**
	 * 删除组
	 * @param group
	 * @return
	 */
	Boolean delete(GroupPO group);
	
	/**
	 * 按ID删除组
	 * @param id
	 * @return
	 */
	Boolean deleteById(Integer id);
}
