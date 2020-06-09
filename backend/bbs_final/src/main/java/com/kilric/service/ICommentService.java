package com.kilric.service;

import com.kilric.po.CommentPO;

import java.util.List;

public interface ICommentService {
	
	int addComment(CommentPO commentPO);
	
	int deleteCommentByCommentId(Integer commentId);
	
	List<CommentPO> getCommentsByEssayId(Integer essayId);
	
	int getCommentsCountByEssayId(Integer essayId);
	
	CommentPO getCommentByCommentId(Integer commentId);
}
