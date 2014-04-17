package com.itucity.dsmp.tripwiki.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.itucity.dsmp.tripwiki.dao.CommentDao;
import com.itucity.dsmp.tripwiki.dao.entity.LineCommentPO;
import com.itucity.dsmp.tripwiki.dao.entity.PlaceCommentPO;
import com.itucity.dsmp.tripwiki.dto.CommentVO;
import com.itucity.dsmp.tripwiki.service.CommentService;

/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 4/16/2014
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

	private static Logger log = LoggerFactory
			.getLogger(CommentServiceImpl.class);
	
	@Resource
	private CommentDao commentDao;
	
	@Override
	public CommentVO getById(Integer id, Integer type) {
		CommentVO result = new CommentVO();
		if(type == 0){
			LineCommentPO comment = commentDao.findLineCommentById(id);
			BeanUtils.copyProperties(comment, result);
			return result;
		}else if(type == 1){
			PlaceCommentPO comment = commentDao.findPlaceCommentById(id);
			BeanUtils.copyProperties(comment, result);
			return result;
		}else{
			log.error("wrong 'type' parameter");
			return null;
		}
		
	}

	@Override
	public Integer addComment(CommentVO comment, Integer type) {
		if(type == 0){
			LineCommentPO commentPO = new LineCommentPO();
			BeanUtils.copyProperties(comment, commentPO);
			return commentDao.save(commentPO);
		}else if(type == 1){
			PlaceCommentPO commentPO = new PlaceCommentPO();
			BeanUtils.copyProperties(comment, commentPO);
			return commentDao.save(commentPO);
		}else{
			log.error("wrong 'type' parameter");
			return null;
		}
	}

	@Override
	public Boolean updateComment(CommentVO comment, Integer type) {
		if(type == 0){
			LineCommentPO commentPO = new LineCommentPO();
			BeanUtils.copyProperties(comment, commentPO);
			return commentDao.update(commentPO);
		}else if(type == 1){
			PlaceCommentPO commentPO = new PlaceCommentPO();
			BeanUtils.copyProperties(comment, commentPO);
			return commentDao.update(commentPO);
		}else{
			log.error("wrong 'type' parameter");
			return false;
		}
	}

	@Override
	public Boolean deleteCommentById(Integer id, Integer type) {
		if(type == 0){
			LineCommentPO commentPO = commentDao.findLineCommentById(id);
			if(commentPO != null){
				return commentDao.delete(commentPO);
			}
			return false;
		}else if(type == 1){
			PlaceCommentPO commentPO = commentDao.findPlaceCommentById(id);
			if(commentPO != null){
				return commentDao.delete(commentPO);
			}
			return false;
		}else{
			log.error("wrong 'type' parameter");
			return false;
		}
	}

}
