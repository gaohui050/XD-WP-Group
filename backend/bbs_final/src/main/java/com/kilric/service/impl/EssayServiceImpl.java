package com.kilric.service.impl;

import com.kilric.mapper.EssayMapper;
import com.kilric.po.EssayPO;
import com.kilric.service.IEssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EssayServiceImpl implements IEssayService {
	@Autowired
	private EssayMapper essayMapper;
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
	public int addEssay(EssayPO essayPO) {
		return essayMapper.addEssay (essayPO);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
	public int deleteEssayByEssayId(Integer essayId) {
		return essayMapper.deleteEssayById (essayId);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true,propagation = Propagation.REQUIRED)
	@CachePut(value = "redisCache",key = "'redis_essay_'+#essayId")
	public EssayPO getEssayByEssayId(Integer essayId) {
		return essayMapper.findEssayByEssayId (essayId);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true,propagation = Propagation.REQUIRED)
	public List<EssayPO> getEssaysByForum(String forumName) {
		return essayMapper.findEssaysByForum (forumName);
	}
	
	@Override
	public List<EssayPO> findEssayByQuery(String query){
		return essayMapper.findEssayByQuery (query);
	}
}
