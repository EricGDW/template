package com.itucity.dsmp.tripwiki.dao.impl;

import java.util.Hashtable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.tripwiki.dao.CommentDao;
import com.itucity.dsmp.tripwiki.dao.entity.LineCommentPO;
import com.itucity.dsmp.tripwiki.dao.entity.PlaceCommentPO;

/**
 * 
 * 评论DAO层接口实现
 * @author Eric
 * @version 0.1
 * 
 * 4/16/2014
 */
@Transactional
@Repository("commentDao")
public class CommentDaoImpl extends BaseDao implements CommentDao {

	@Override
	public LineCommentPO findLineCommentById(Integer commentId) {
		return super.find(LineCommentPO.class, commentId);
	}

	@Override
	public Integer save(LineCommentPO comment) {
		super.save(comment);
		return comment.getCommentId();
	}

	@Override
	public Boolean update(LineCommentPO comment) {
		super.update(comment);
		return true;
	}

	@Override
	public Boolean delete(LineCommentPO comment) {
		super.delete(comment);
		return true;
	}

	@Override
	public Boolean deleteLineCommentById(Integer commentId) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("DELETE FROM LineCommentPO t WHERE 1 = 1 ");
		hql.append("AND t.commentId =:id ");
		param.put("id", commentId);
		hqlUpdate(hql.toString(), param);
		return true;
	}

	@Override
	public PlaceCommentPO findPlaceCommentById(Integer commentId) {
		return super.find(PlaceCommentPO.class, commentId);
	}

	@Override
	public Integer save(PlaceCommentPO comment) {
		super.save(comment);
		return comment.getCommentId();
	}

	@Override
	public Boolean update(PlaceCommentPO comment) {
		super.update(comment);
		return true;
	}

	@Override
	public Boolean delete(PlaceCommentPO comment) {
		super.delete(comment);
		return true;
	}

	@Override
	public Boolean deletePlaceCommentById(Integer commentId) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("DELETE FROM PlaceCommentPO t WHERE 1 = 1 ");
		hql.append("AND t.commentId =:id ");
		param.put("id", commentId);
		hqlUpdate(hql.toString(), param);
		return true;
	}


}
