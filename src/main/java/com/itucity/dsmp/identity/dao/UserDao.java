package com.itucity.dsmp.identity.dao;

import java.util.List;

import com.itucity.dsmp.common.base.IDao;
import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.identity.dao.entity.GroupPO;
import com.itucity.dsmp.identity.dao.entity.RolePO;
import com.itucity.dsmp.identity.dao.entity.UserDetailPO;
import com.itucity.dsmp.identity.dao.entity.UserPO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
public interface UserDao extends IDao {
	
	/**
	 * 获取用户详细信息
	 * @param uid
	 * @return
	 */
	UserDetailPO findUserDetail(Integer uid);
	
	/**
	 * 根据用户名查询用户
	 * 
	 * @param userName
	 * @return
	 */
	UserPO findUserByUserName(String userName);
	
	
	/**
	 * 获取所有用户
	 * @return
	 */
	List<UserPO> findAll();
	
	/**
	 * 获取所有有效用户
	 * @return
	 */
	List<UserPO> findAllAvailable();
	
	/**
	 * 对用户名进行like查询
	 * @param username
	 * @param pagesInfo
s	 * @return
	 */
	PagesInfo<UserPO> findByNameLike(String username, PagesInfo<UserPO> pagesInfo);
	
	/**
	 * 分页获取用户
	 * @param pagesInfo
	 * @return
	 */
	PagesInfo<UserPO> findUserByPage(PagesInfo<UserPO> pagesInfo);
	
	/**
	 * 添加用户到组
	 * @param uid
	 * @param groupId
	 * @return
	 */
	Boolean addUserToGroup(Integer uid, Integer groupId);
	
	/**
	 * 查找用户所属的所有组
	 * @param uid
	 * @return
	 */
	List<GroupPO> findUserGroup(Integer uid);
	
	/**
	 * 添加用户权限
	 * @param roleId
	 * @param uid
	 * @return
	 */
	Boolean addRoleToUser(Integer roleId, Integer uid);
	
	/**
	 * 获取用户的权限
	 * @param uid
	 * @return
	 */
	List<RolePO> findUserRole(Integer uid);
	
	/**
	 * 按用户ID查询用户
	 * @param uid
	 * @return
	 */
	UserPO findById(Integer uid);
	
	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	Integer save(UserPO user);
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	Boolean update(UserPO user);
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	Boolean delete(UserPO user);
	
	/**
	 * 按ID删除用户
	 * @param uid
	 * @return
	 */
	Boolean deleteById(Integer uid);
}
