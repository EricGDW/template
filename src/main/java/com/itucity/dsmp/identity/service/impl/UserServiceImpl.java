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
import com.itucity.dsmp.identity.dao.UserDao;
import com.itucity.dsmp.identity.dao.entity.GroupPO;
import com.itucity.dsmp.identity.dao.entity.UserDetailPO;
import com.itucity.dsmp.identity.dao.entity.UserPO;
import com.itucity.dsmp.identity.service.UserService;
import com.itucity.dsmp.identity.service.model.UserVO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/14/2014
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private GroupDao groupDao;
	
	@Override
	public UserVO getUserByName(String userName) {
		UserPO user = userDao.findUserByUserName(userName);
		if (user !=null ) {
			UserVO userVO = new UserVO();
			userVO = userPOToVO(user);
			return userVO;
		} 
		log.info(String.format("User [username : %s] not found", userName));
		return null;
	}

	@Override
	public List<UserVO> getAllUser() {
		List<UserPO> pos =  userDao.findAll();
		
		List<UserVO> vos = new ArrayList<UserVO>();
		for(UserPO po : pos){
			UserVO vo = new UserVO();
			vo = userPOToVO(po);
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public PagesInfo<UserVO> getUserByPage(Integer limit, Integer pageNo) {
		PagesInfo<UserPO> page = new PagesInfo<UserPO>();
		page.setCountPerPage(limit);
		page.setPageNumber(pageNo);
		
		PagesInfo<UserPO> result =  userDao.findUserByPage(page);
		
		List<UserPO> pos = result.getResultsList();
		
		List<UserVO> vos = new ArrayList<UserVO>();
		for(UserPO po : pos){
			UserVO vo = new UserVO();
			vo = userPOToVO(po);
			vos.add(vo);
		}
		
		PagesInfo<UserVO> pages = new PagesInfo<UserVO>();
		pages.setCountPerPage(limit);
		pages.setPageNumber(pageNo);
		pages.setResultsList(vos);
		pages.setTotalPage(result.getTotalPage());
		pages.setTotalRecord(result.getTotalRecord());
		
		return pages;
	}

	@Override
	public UserVO getUserByID(Integer userID) {
		UserPO po = userDao.find(UserPO.class, userID);
		if (po !=null ) {
			UserVO vo = new UserVO();
			vo = userPOToVO(po);
			return vo;
		}
		log.info(String.format("User [id : %d] not found", userID));
		return null;
	}

	@Override
	public PagesInfo<UserVO> getUserByLike(String userLike, Integer limit, 
			Integer pageNo) {
		PagesInfo<UserPO> result = new PagesInfo<UserPO>();
		
		if(limit == null || pageNo == null){
			result = userDao.findByNameLike(userLike, null);
		}else{
			PagesInfo<UserPO> page = new PagesInfo<UserPO>();
			
			page.setCountPerPage(limit);
			page.setPageNumber(pageNo);
			
			result = userDao.findByNameLike(userLike, page);
		}
		
		List<UserPO> pos = result.getResultsList();
		
		List<UserVO> vos = new ArrayList<UserVO>();
		for(UserPO po : pos){
			UserVO vo = new UserVO();
			vo = userPOToVO(po);
			vos.add(vo);
		}
		
		PagesInfo<UserVO> pages = new PagesInfo<UserVO>();
		pages.setCountPerPage(limit);
		pages.setPageNumber(pageNo);
		pages.setResultsList(vos);
		pages.setTotalPage(result.getTotalPage());
		pages.setTotalRecord(result.getTotalRecord());
		
		return pages;
	}
	
	@Override
	public Boolean addUser(UserVO user) {
		UserPO po = new UserPO();
		po = userVOToPO(user);
		userDao.save(po);
		
		UserDetailPO userDetail = new UserDetailPO();
		BeanUtils.copyProperties(user, userDetail);
		
		userDao.save(userDetail);
		return true;
	}

	@Override
	public Boolean deleteUser(Integer id) {
		UserPO user = userDao.find(UserPO.class, id);
		if(user == null){
			log.info(String.format("User [id : %d] not found", id ));
			return false;
		}
		userDao.delete(user);
		return true;
	}

	@Override
	public Boolean editUser(UserVO user) {
		UserPO tempUser = userDao.find(UserPO.class, user.getId());
		if(tempUser == null){
			log.info(String.format("User [id : %d] not found", user.getId()));
			return false;
		}
		UserPO po = new UserPO();
		po = userVOToPO(user);
		userDao.update(po);
		
		UserDetailPO userDetail = new UserDetailPO();
		BeanUtils.copyProperties(user, userDetail);
		
		userDao.update(userDetail);
		
		return true;
	}

	@Override
	public Boolean addUserToGroup(Integer uid, Integer groupid) {
		UserPO user = userDao.find(UserPO.class, uid);
		GroupPO group = groupDao.find(GroupPO.class, groupid);
		if(user == null){
			log.info(String.format("User [id : %d] not found", uid));
			return false;
		}
		if(group == null){
			log.info(String.format("Group [id : %d] not found", groupid));
			return false;
		}
		
		return userDao.addUserToGroup(uid, groupid);
	}
	
	public UserVO userPOToVO(UserPO po){
		UserVO vo = new UserVO();
		
		BeanUtils.copyProperties(po, vo);
		
		UserDetailPO userDetail = userDao.findUserDetail(po.getUid());
		if(userDetail != null){
			vo.setIdcard(userDetail.getIdcard());
			vo.setSex(userDetail.getSex());
			vo.setCity(userDetail.getCity());
			vo.setCountry(userDetail.getCountry());
			vo.setHeadImgId(userDetail.getImageId());
			vo.setLanguage(userDetail.getLanguage());
			vo.setProvince(userDetail.getProvince());
			vo.setNickname(userDetail.getNickName());
			vo.setRealname(userDetail.getRealName());
			vo.setRegisterTime(userDetail.getRegisterTime());
		}
		
		
		return vo;
	}
	
	public UserPO userVOToPO(UserVO vo){
		UserPO po = new UserPO();
		
		BeanUtils.copyProperties(vo, po);
		
//		UserDetailPO userDetail = new UserDetailPO();
//		
//		BeanUtils.copyProperties(vo, userDetail);
//		
//		userDetail.setSex(vo.getSex());
//		userDetail.setIdcard(vo.getIdcard());
//		userDetail.setCity(vo.getCity());
//		userDetail.setCountry(vo.getCountry());
//		userDetail.setImageId(vo.getHeadImgId());
//		userDetail.setLanguage(vo.getLanguage());
//		userDetail.setProvince(vo.getProvince());
//		userDetail.setNickName(vo.getNickname());
//		userDetail.setRealName(vo.getRealname());
//		userDetail.setRegisterTime(vo.getRegisterTime());
		
		return po;
	}

}
