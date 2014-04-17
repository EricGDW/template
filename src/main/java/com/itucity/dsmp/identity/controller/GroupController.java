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
import com.itucity.dsmp.identity.service.GroupService;
import com.itucity.dsmp.identity.service.model.GroupVO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/18/2014
 */
@Controller
@RequestMapping("group")
public class GroupController {

	@Resource
	private GroupService groupService;

	/**
	 * 
	 * @param gid
	 * @return
	 */
	@RequestMapping(value="/{gid}",method=RequestMethod.GET)
	@ResponseBody
	public Object getGroupByID(@PathVariable Integer gid) {
		GroupVO vo = groupService.getGroupByID(gid);
		
		if(vo == null){
			Map<String , Object> result = new HashMap<String , Object>(3);
			
			result.put("status", Constants.STATUS_FAILED);
			result.put("errcode", Constants.ERROR_NOT_FOUND);
			result.put("errmsg", "cannot find group [id : " + gid + "]");
			
			return result;
		}
		
		return vo;
	}
	
	/**
	 * 
	 * @param groupname
	 * @return
	 */
	@RequestMapping(value="/name/{groupname}",method=RequestMethod.GET)
	public Object getGroupByName(@PathVariable String groupname) {
		GroupVO group = new GroupVO();
		
		group = groupService.getGroupByName(groupname);
		
		if(group == null){
			Map<String , Object> result = new HashMap<String , Object>(3);
			
			result.put("status", Constants.STATUS_FAILED);
			result.put("errcode", Constants.ERROR_NOT_FOUND);
			result.put("errmsg", "cannot find group [name : " + groupname + "]");
			
			return result;
		}
		
		return group;

	}
	
	/**
	 * 
	 * @param name
	 * @param limit
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/like/{name}",method=RequestMethod.GET)
	public Object getByLike(@PathVariable String name,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "page", required = false) Integer page) {
		List<GroupVO> groups = new ArrayList<GroupVO>();
		
		if(page != null && limit != null){
			Integer start = (page- 1) * limit;
			groups = groupService.getGroupByLike(name, start, limit);
		}else{
			groups = groupService.getGroupByLike(name, null, null);
		}
		
		return groups;
		
	}
	
	

	/**
	 * 
	 */
	@RequestMapping(value="/all",method=RequestMethod.GET)
	@ResponseBody
	public Object getAllUser() {
		
		List<GroupVO> groups = new ArrayList<GroupVO>();
		
		groups = groupService.getAvailableRoles();

		return groups;
		
	}
	
	
	/**
	 * 
	 * @param group
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Object addUser(@RequestBody GroupVO group) {
		
		Boolean flag = false;
		flag = groupService.addGroup(group);
		
		if(flag){
			Map<String , Object> successInfo = new HashMap<String , Object>(2);
			
			successInfo.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
			successInfo.put("id", group.getId());
			
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
	public Object editGroup(@RequestBody GroupVO group) {
		
		Boolean flag = groupService.editGroup(group);
		
		if(flag){
			Map<String , String> successInfo = new HashMap<String , String>(1);
			
			successInfo.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
			
			return successInfo;
		}else{
			Map<String , Object> result = new HashMap<String , Object>(3);
			
			result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
			result.put("errcode", Constants.ERROR_NOT_FOUND);
			result.put("errmsg", "cannot find group [id : " + group.getId() + "]");
			
			return result;
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteGroup(@PathVariable Integer gid) {
		Boolean flag = groupService.deleteGroup(gid);
		
		if(flag){
			Map<String , Object> successInfo = new HashMap<String , Object>(1);
			
			successInfo.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
			
			return successInfo;
		}else{
			Map<String , Object> result = new HashMap<String , Object>(3);
			
			result.put("status", Constants.STATUS_FAILED);
			result.put("errcode", Constants.ERROR_NOT_FOUND);
			result.put("errmsg", "cannot find group [id : " + gid + "]");
			
			return result;
		}
	}

}
