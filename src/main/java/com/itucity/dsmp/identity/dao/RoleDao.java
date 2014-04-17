package com.itucity.dsmp.identity.dao;

import java.util.List;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.identity.dao.entity.RolePO;

public interface RoleDao {
	
	/**
	 * 查询所有角色
	 * @return
	 */
	List<RolePO> findAll();
	
	/**
	 * 根据角色类型查询
	 * @param type
	 * @return
	 */
	List<RolePO> findByType(String type);
	
	/**
	 * 根据角色名进行Like查询
	 * @param roleName
	 * @return
	 */
	List<RolePO> findByNameLike(String roleName);
	
	/**
	 * 对角色进行分页查询
	 * @param pagesInfo
	 * 
	 * @return
	 */
	PagesInfo<RolePO> findByPage(PagesInfo<RolePO> pagesInfo);
	
	/**
	 * 按角色ID查询角色
	 * @param id
	 * @return
	 */
	RolePO findById(Integer id);
	
	/**
	 * 增加角色
	 * @param user
	 * @return
	 */
	Integer save(RolePO role);
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	Boolean update(RolePO role);
	
	/**
	 * 删除角色
	 * @param role
	 * @return
	 */
	Boolean delete(RolePO role);
	
	/**
	 * 按ID删除角色
	 * @param roleId
	 * @return
	 */
	Boolean deleteById(Integer roleId);
}
