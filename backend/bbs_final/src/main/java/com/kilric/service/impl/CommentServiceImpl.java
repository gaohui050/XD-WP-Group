package com.kilric.service.impl;

import com.kilric.mapper.CommentMapper;
import com.kilric.po.CommentPO;
import com.kilric.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
	public int addComment(CommentPO commentPO) {
		return commentMapper.addComment (commentPO);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
	public int deleteCommentByCommentId(Integer commentId) {
		return commentMapper.deleteCommentById (commentId);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true,propagation = Propagation.REQUIRED)
	public List<CommentPO> getCommentsByEssayId(Integer essayId) {
		return commentMapper.findCommentsByEssayId (essayId);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true,propagation = Propagation.REQUIRED)
	public int getCommentsCountByEssayId(Integer essayId) {
		return commentMapper.getCommentsCountByEssayId (essayId);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true,propagation = Propagation.REQUIRED)
	public CommentPO getCommentByCommentId(Integer commentId) {
		return commentMapper.getCommentByCommentId (commentId);
	}
}
