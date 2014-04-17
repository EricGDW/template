package com.itucity.dsmp.identity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itucity.dsmp.common.Constants;
import com.itucity.dsmp.identity.service.UserService;
import com.itucity.dsmp.identity.service.model.UserVO;


/**
 * @author Eric
 * @version 0.1
 * 
 * 3/17/2014
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	/**
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/{uid}",method=RequestMethod.GET)
	@ResponseBody
	public Object getUserByID(@PathVariable Integer uid) {
		
		UserVO user = userService.getUserByID(uid);
		
		if(user == null){
			Map<String , Object> result = new HashMap<String , Object>(3);
			
			result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
			result.put("errcode", Constants.ERROR_NOT_FOUND);
			result.put("errmsg", "cannot find user [id : " + uid + "]");
			
			return result;
		}
		
		return user;
	}
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/name/{username}",method=RequestMethod.GET)
	@ResponseBody
	public Object findUserByUsername(@PathVariable String username) {
		UserVO user = new UserVO();
		
		user = userService.getUserByName(username);
		
		if(user == null){ 
			Map<String , Object> result = new HashMap<String , Object>(3);
			
			result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
			result.put("errcode", Constants.ERROR_NOT_FOUND);
			result.put("errmsg", "cannot find user [name : " + username + "]");
			
			return result;
		}

		return user;

	}
	
	/**
	 * 
	 * @param name
	 * @param limit
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/like/{name}",method=RequestMethod.GET)
	@ResponseBody
	public Object findByLike(@PathVariable String name,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "page", required = false) Integer page) {
		List<UserVO> users = new ArrayList<UserVO>();
		
		if(page != null && limit != null){
			Integer start = (page- 1) * limit;
			users = userService.getUserByLike(name, start, limit);
		}else{
			users = userService.getUserByLike(name, null, null);
		}
		
		return users;
		
	}
	
	/**
	 * 分页查询
	 * 
	 * @param limit	每页返回的结果条目数上限，最小值1，最大值40，如不传入默认为10
	 * @param page 页码，如不传入默认为1，即第一页
	 * 
	 */
	@RequestMapping(value="/page",method=RequestMethod.GET)
	@ResponseBody
	public Object getByPage(
			@RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(value = "page", required = true, defaultValue="1") Integer page
			) {
		List<UserVO> users = new ArrayList<UserVO>();
		
		limit = (limit > 0 && limit < 41) ? limit : 10;
		
		page = page > 0 ? page : 1;
		
		Integer start = (page- 1) * limit;
		
		users = userService.getUserByPage(start, limit);

		return users;
		
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/all",method=RequestMethod.GET)
	@ResponseBody
	public Object getAllUser() {
		
		List<UserVO> users = new ArrayList<UserVO>();
		
		users = userService.getAllUser();

		return users;
		
	}
	
	/**
	 * 添加用户
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Object addUser(@RequestBody UserVO user) {
		
		Boolean flag = false;
		
		flag = userService.addUser(user);
		
		if(flag){
			Map<String , Object> successInfo = new HashMap<String , Object>(1);
			
			successInfo.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
			successInfo.put("id", user.getId());
			
			return successInfo;
		}else{
			Map<String , Object> result = new HashMap<String , Object>(3);
			
			result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
			result.put("errcode", Constants.ERROR_SERVER);
			result.put("errmsg", "server error");
			
			return result;
		}
	}
	
	/**
	 * 修改用户信息  参数用户userid类型为MUST
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Object editUser(@RequestBody UserVO uservo) {
		Integer uid = uservo.getId();
		
		UserVO user = userService.getUserByID(uid);
		
		if(user == null){
			Map<String , Object> result = new HashMap<String , Object>(3);
			
			result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
			result.put("errcode", Constants.ERROR_NOT_FOUND);
			result.put("errmsg", "cannot find user [id : " + uid + "]");
			
			return result;
		}
		
		Boolean flag = userService.editUser(user);
		
		if(flag){
			Map<String , Object> successInfo = new HashMap<String , Object>(1);
			
			successInfo.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
			
			return successInfo;
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/delete/{uid}",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteUser(@PathVariable Integer uid) {
		Boolean flag = userService.deleteUser(uid);
		
		if(flag){
			Map<String , Object> successInfo = new HashMap<String , Object>(1);
			
			successInfo.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
			
			return successInfo;
		}else{
			Map<String , Object> result = new HashMap<String , Object>(3);
			
			result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
			result.put("errcode", Constants.ERROR_NOT_FOUND);
			result.put("errmsg", "cannot find user [id : " + uid + "]");
			
			return result;
		}
	}
	
	/**
	 * 用户分配到组
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/{uid}/group/{gid}",method=RequestMethod.POST)
	@ResponseBody
	public Object addUserToGroup(@PathVariable Integer uid,
			@PathVariable Integer gid) {
		
		Boolean flag = userService.addUserToGroup(uid, gid);
		
		if(flag){
			Map<String , Object> successInfo = new HashMap<String , Object>(1);
			
			successInfo.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
			
			return successInfo;
		}else{
			Map<String , Object> result = new HashMap<String , Object>(3);
			
			result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
			result.put("errcode", Constants.ERROR_NOT_FOUND);
			result.put("errmsg", "add user [id : " + uid + "] to group "
					+ "[id : " + gid + "] error " );
			
			return result;
		}
		
	}
	
}
