package com.kilric.service;

import com.kilric.po.EssayPO;

import java.util.List;

public interface IEssayService {
	int addEssay(EssayPO essayPO);
	
	int deleteEssayByEssayId(Integer essayId);
	
	EssayPO getEssayByEssayId(Integer essayId);
	
	List<EssayPO> getEssaysByForum(String forumName);
	List<EssayPO> findEssayByQuery(String query);
}
