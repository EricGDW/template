package com.itucity.dsmp.tripwiki.service;

import com.itucity.dsmp.tripwiki.dto.CommentVO;

public interface CommentService {

	/**
	 * 查询评论
	 * @param id 评论ID
	 * @param type 评论类型(0：线路评论,1：景点评论)
	 * @return
	 */
	CommentVO getById(Integer id, Integer type);
	
	/**
	 * 
	 * @param comment
	 * @param type 评论类型(0：线路评论:1：景点评论)
	 * @return
	 */
	Integer addComment(CommentVO comment, Integer type);
	
	
	/**
	 * 更新评论
	 * @param comment 评论内容
	 * @param type 评论类型(0：线路评论,1：景点评论)
	 * @return
	 */
	Boolean updateComment(CommentVO comment, Integer type);
	
	/**
	 * 根据评论ID删除评论
	 * @param id 评论ID
	 * @param type 评论类型:0：线路评论:1：景点评论)
	 * @return
	 */
	Boolean deleteCommentById(Integer id, Integer type);
}
