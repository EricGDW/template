package com.itucity.dsmp.tripwiki.dao;

import com.itucity.dsmp.tripwiki.dao.entity.LineCommentPO;
import com.itucity.dsmp.tripwiki.dao.entity.PlaceCommentPO;

/**
 * 
 * 评论DAO层接口
 * @author Eric
 * @version 0.1
 * 
 * 4/16/2014
 */
public interface CommentDao {

	/**
	 * 查询线路评论
	 * @param commentId 评论Id
	 * @return
	 */
	LineCommentPO findLineCommentById(Integer commentId);
	
	/**
	 * 增加线路评论
	 * @param comment 评论内容
	 * @return
	 */
	Integer save(LineCommentPO comment);
	
	/**
	 * 更新线路评论
	 * @param comment 评论内容
	 * @return
	 */
	Boolean update(LineCommentPO comment);
	
	/**
	 * 删除线路评论
	 * @param comment 评论内容
	 * @return
	 */
	Boolean delete(LineCommentPO comment);
	
	/**
	 * 按评论ID删除线路评论
	 * @param commentId
	 * @return
	 */
	Boolean deleteLineCommentById(Integer commentId);
	
	/**
	 * 查询景点评论
	 * @param commentId
	 * @return
	 */
	PlaceCommentPO findPlaceCommentById(Integer commentId);
	
	/**
	 * 添加景点评论
	 * @param comment
	 * @return
	 */
	Integer save(PlaceCommentPO comment);
	
	/**
	 * 更新景点评论
	 * @param comment
	 * @return
	 */
	Boolean update(PlaceCommentPO comment);
	
	/**
	 * 删除景点评论
	 * @param comment
	 * @return
	 */
	Boolean delete(PlaceCommentPO comment);
	
	/**
	 * 按评论ID删除景点评论
	 * @param commentId
	 * @return
	 */
	Boolean deletePlaceCommentById(Integer commentId);
}
