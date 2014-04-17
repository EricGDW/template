package com.itucity.dsmp.identity.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.identity.dao.GroupDao;
import com.itucity.dsmp.identity.dao.entity.GroupPO;
import com.itucity.dsmp.identity.service.GroupService;
import com.itucity.dsmp.identity.service.ResourceService;
import com.itucity.dsmp.identity.service.UserService;
import com.itucity.dsmp.identity.service.model.GroupVO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/14/2014
 */
@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService{

	private static Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);
	
	@Resource
	private GroupDao groupDao;
	
	@Resource
	private UserService userService;
	
	@Resource
	private ResourceService resourceService;
	
	@Override
	public List<GroupVO> getAvailableRoles() {
		List<GroupVO> voList = new ArrayList<GroupVO>();
		
		List<GroupPO> poList = groupDao.findAll();		
		for(GroupPO po : poList){
			GroupVO vo = groupPOToVO(po);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	public GroupVO getGroupByID(Integer id) {
		GroupPO po = groupDao.find(GroupPO.class, id);
		if(po != null){
			return groupPOToVO(po);
		}
		logger.info(String.format("Group [id : %d] not found", id ));
		return null;
		
	}

	@Override
	public List<GroupVO> getGroupByLike(String nameLike) {
		List<GroupPO> pos =  groupDao.findByNameLike(nameLike);
		
		List<GroupVO> vos = new ArrayList<GroupVO>();
		for(GroupPO po : pos){
			GroupVO vo = groupPOToVO(po);
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public Boolean addGroup(GroupVO group) {
		GroupPO po = new GroupPO();
		po = groupVOToPO(group);

		groupDao.save(po);
		return true;
	}

	@Override
	public Boolean editGroup(GroupVO group) {
		GroupPO tempPO = groupDao.find(GroupPO.class, group.getGroupId());
		if(tempPO == null){
			logger.info(String.format("Group [name : %s, id : %d] not found", 
					group.getGroupName(), group.getGroupId()));
			return false;
		}
		GroupPO po = new GroupPO();
		po = groupVOToPO(group);
		groupDao.update(po);
		return true;
	}

	@Override
	public Boolean deleteGroup(Integer id) {
		GroupPO po = groupDao.find(GroupPO.class, id);
		if(po == null){
			logger.info(String.format("Group [id : %d] not found", id));
			return false;
		}
		groupDao.delete(po);
		return true;
	}
	
	public GroupVO groupPOToVO(GroupPO po){
		GroupVO vo = new GroupVO();
		
		BeanUtils.copyProperties(po, vo);
		
//		List<UserPO> users = po.getUsers();
//		for(UserPO user : users){
//			UserVO uvo = new UserVO();
//			uvo = userService.userPOToVO(user);
//			vo.getUser().add(uvo);
//		}
//		
//		List<UrlPO> urls = po.getUrls();
//		for(UrlPO url : urls){
//			UrlVO uvo = new UrlVO();
//			uvo = resourceService.urlPOToVO(url);
//			vo.getUrl().add(uvo);
//		}
		return vo;
	}
	
	public GroupPO groupVOToPO(GroupVO vo){
		GroupPO po = new GroupPO();
		
		BeanUtils.copyProperties(vo, po);
		
//		List<UserVO> users = vo.getUser();
//		for(UserVO user : users){
//			UserPO upo = new UserPO();
//			upo = userService.userVOToPO(user);
//			po.getUsers().add(upo);
//		}
//		
//		List<UrlVO> urls = vo.getUrl();
//		for(UrlVO url : urls){
//			UrlPO upo = new UrlPO();
//			upo = resourceService.urlVOToPO(url);
//			po.getUrls().add(upo);
//		}
		
		return po;
	}

	@Override
	public PagesInfo<GroupVO> getGroupByPage(Integer limit, Integer pageNo) {
		PagesInfo<GroupPO> page = new PagesInfo<GroupPO>();
		page.setCountPerPage(limit);
		page.setPageNumber(pageNo);
		
		PagesInfo<GroupPO> result =  groupDao.findByPage(page);
		
		List<GroupPO> pos = result.getResultsList();
		
		List<GroupVO> vos = new ArrayList<GroupVO>();
		for(GroupPO po : pos){
			GroupVO vo = new GroupVO();
			vo = groupPOToVO(po);
			vos.add(vo);
		}
		
		PagesInfo<GroupVO> pages = new PagesInfo<GroupVO>();
		pages.setCountPerPage(limit);
		pages.setPageNumber(pageNo);
		pages.setResultsList(vos);
		pages.setTotalPage(result.getTotalPage());
		pages.setTotalRecord(result.getTotalRecord());
		
		return pages;
	}

}
