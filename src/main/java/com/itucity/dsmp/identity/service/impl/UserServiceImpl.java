package com.itucity.dsmp.identity.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
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
		logger.info(String.format("User [username : %s] not found", userName));
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
	public List<UserVO> getUserByPage(Integer first, Integer last) {
		List<UserPO> pos =  userDao.findUserByPage(first, last);
		
		List<UserVO> vos = new ArrayList<UserVO>();
		for(UserPO po : pos){
			UserVO vo = new UserVO();
			vo = userPOToVO(po);
			vos.add(vo);
		}
		
		return vos;
	}

	@Override
	public UserVO getUserByID(Integer userID) {
		UserPO po = userDao.find(UserPO.class, userID);
		if (po !=null ) {
			UserVO vo = new UserVO();
			vo = userPOToVO(po);
			return vo;
		}
		logger.info(String.format("User [id : %d] not found", userID));
		return null;
	}

	@Override
	public List<UserVO> getUserByLike(String userLike, Integer start, 
			Integer max) {
		List<UserPO> pos =  userDao.findByLike(userLike, start, max);
		
		List<UserVO> vos = new ArrayList<UserVO>();
		for(UserPO po : pos){
			UserVO vo = new UserVO();
			vo = userPOToVO(po);
			vos.add(vo);
		}
		
		return vos;
	}
	
	@Override
	public Boolean addUser(UserVO user) {
		UserPO po = new UserPO();
		po = userVOToPO(user);
		userDao.save(po);
		return true;
	}

	@Override
	public Boolean deleteUser(Integer id) {
		UserPO user = userDao.find(UserPO.class, id);
		if(user == null){
			logger.info(String.format("User [id : %d] not found", id ));
			return false;
		}
		userDao.delete(user);
		return true;
	}

	@Override
	public Boolean editUser(UserVO user) {
		// TODO Auto-generated method stub
		UserPO tempUser = userDao.find(UserPO.class, user.getId());
		if(tempUser == null){
			logger.info(String.format("User [id : %d] not found", user.getId()));
			return false;
		}
		UserPO po = new UserPO();
		po = userVOToPO(user);
		userDao.update(po);
		return true;
	}

	@Override
	public Boolean addUserToGroup(Integer userid, Integer groupid) {
		UserPO user = userDao.find(UserPO.class, userid);
		GroupPO group = groupDao.find(GroupPO.class, groupid);
		if(user == null){
			logger.info(String.format("User [id : %d] not found", userid));
			return false;
		}
		if(group == null){
			logger.info(String.format("Group [id : %d] not found", groupid));
			return false;
		}
		List<GroupPO> groups = user.getGroups();
		groups.add(group);
		user.setGroups(groups);
		
		userDao.save(user);
		return true;
	}
	
	public UserVO userPOToVO(UserPO po){
		UserVO vo = new UserVO();
		
		BeanUtils.copyProperties(po, vo);
		
		UserDetailPO userDetail = po.getUserDetail();
		if(userDetail != null){
			vo.setNo(userDetail.getIdCard());
			vo.setSex(userDetail.getSex());
			vo.setRemarks(userDetail.getRemarks());
			vo.setCity(userDetail.getCity());
			vo.setCountry(userDetail.getCountry());
			vo.setHeadImgUrl(userDetail.getHeadImgUrl());
			vo.setLanguage(userDetail.getLanguage());
			vo.setProvince(userDetail.getProvince());
			vo.setNickname(userDetail.getNickname());
		}
		
		
		return vo;
	}
	
	public UserPO userVOToPO(UserVO vo){
		UserPO po = new UserPO();
		
		BeanUtils.copyProperties(vo, po);
		
		UserDetailPO userDetail = po.getUserDetail();
		String sex = vo.getSex();
		if(sex != null){
			userDetail.setSex(sex);
		}
		String no = vo.getNo();
		if(no != null){
			userDetail.setIdCard(no);
		}
		String remarks = vo.getRemarks();
		if(sex != null){
			userDetail.setRemarks(remarks);
		}
		String city = vo.getCity();
		if(city != null){
			userDetail.setCity(city);
		}
		String country = vo.getCountry();
		if(country != null){
			userDetail.setCountry(country);
		}
		String headImgUrl = vo.getHeadImgUrl();
		if(headImgUrl != null){
			userDetail.setHeadImgUrl(headImgUrl);
		}
		String language = vo.getLanguage();
		if(language != null){
			userDetail.setLanguage(language);
		}
		
		String province = vo.getProvince();
		if(province != null){
			userDetail.setProvince(province);
		}
		
		String nickname = vo.getNickname();
		if(nickname != null){
			userDetail.setNickname(nickname);
		}
		
		po.setUserDetail(userDetail);
		return po;
	}

}
