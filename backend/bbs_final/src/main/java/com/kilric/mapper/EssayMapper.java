package com.kilric.mapper;

import com.kilric.po.EssayPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EssayMapper {
	
	int addEssay(EssayPO essay);
	
	int deleteEssayById(Integer essayId);
	
	EssayPO findEssayByEssayId(Integer essayId);
	
	List<EssayPO> findEssaysByForum(String forumName);
	
	List<EssayPO> findEssayByQuery(String query);
}
