package com.itucity.dsmp.identity.service;

import java.util.List;

import com.itucity.dsmp.identity.service.model.UserVO;



public interface UserService {
	
	/**
	 * 获取所有用户
	 * @return
	 */
	List<UserVO> getAllUser();
	
	/**
	 * 分页获取所有用户
	 * @param first 开始记录
	 * @param last	结束记录
	 * @return
	 */
	List<UserVO> getUserByPage(Integer first, Integer last);
	
	/**
	 * 根据用户名查询用户
	 * 
	 * @param userName
	 * @return
	 */
	UserVO getUserByName(String userName);
	
	
	/**
	 * 根据用户ID查询用户
	 * 
	 * @param userID
	 * @return
	 */
	UserVO getUserByID(Integer userID);
	
	/**
	 * 按用户名模糊查询用户
	 * @param nameLike
	 * @param first 开始记录
	 * @param max	结束记录
	 * 
	 * @return
	 */
	List<UserVO> getUserByLike(String nameLike, Integer first, Integer max);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	Boolean addUser(UserVO user);
	
	/**
	 * 删除用户
	 * @param userid
	 * @return
	 */
	Boolean deleteUser(Integer userid);
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	Boolean editUser(UserVO user);

	/**
	 * 添加用户到组
	 * @param userid
	 * @param groupid
	 * @return
	 */
	Boolean addUserToGroup(Integer userid, Integer groupid);
}
