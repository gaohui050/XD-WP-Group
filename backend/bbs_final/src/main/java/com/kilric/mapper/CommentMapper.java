package com.kilric.mapper;

import com.kilric.po.CommentPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {
	int addComment(CommentPO comment);
	
	int deleteCommentById(Integer cmtId);
	
	List<CommentPO> findCommentsByEssayId(Integer essayId);
	
	int getCommentsCountByEssayId(Integer essayId);
	
	CommentPO getCommentByCommentId(Integer cmtId);
}
