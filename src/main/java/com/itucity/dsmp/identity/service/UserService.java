package com.itucity.dsmp.identity.service;

import java.util.List;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.identity.service.model.UserVO;



public interface UserService {
	
	/**
	 * 获取所有用户
	 * @return
	 */
	List<UserVO> getAllUser();
	
	/**
	 * 分页获取所有用户
	 * @param limit 	每页记录数
	 * @param pageNo	第几页
	 * @return
	 */
	PagesInfo<UserVO> getUserByPage(Integer limit, Integer pageNo);
	
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
	 * @param limit 	每页记录数
	 * @param pageNo	第几页
	 * 
	 * 不传limit或pageNo时获取所有数据，当一页处理
	 * 
	 * @return
	 */
	PagesInfo<UserVO> getUserByLike(String nameLike, Integer limit, Integer pageNo);
	
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
