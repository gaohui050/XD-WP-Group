package com.kilric.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kilric.dto.EssayAddDTO;
import com.kilric.dto.EssayPageEntityDTO;
import com.kilric.dto.EssaySearchDTO;
import com.kilric.po.EssayPO;
import com.kilric.service.impl.EssayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/essay")
@CrossOrigin
public class EssayController {
	@Autowired
	private EssayServiceImpl essayService;
	
	@RequestMapping(value = "/add",method = {RequestMethod.POST})
	public Map<String,Object> addEssay(@RequestBody EssayAddDTO essayAddDTO){
		EssayPO essayPO = EssayAddDTO.toEssayPO (essayAddDTO);
		int i = essayService.addEssay (essayPO);
		Map<String,Object> map = new HashMap<> ();
		if (i == 1){
			map.put ("status", HttpStatus.OK.value ());
		}else {
			map.put ("status",HttpStatus.BAD_REQUEST);
		}
		return map;
	}
	@RequestMapping(value = "/delete")
	public Map<String,Object> deleteEssay(Integer essayId){
		int i = essayService.deleteEssayByEssayId (essayId);
		Map<String,Object> map = new HashMap<> ();
		if (i == 1){
			map.put ("status", HttpStatus.OK.value ());
		}else {
			map.put ("status",HttpStatus.BAD_REQUEST);
		}
		return map;
	}
	
	@RequestMapping(value = "/forum",method = {RequestMethod.GET})
	public Map<String,Object> getEssays(EssayPageEntityDTO pageEntityDTO){
		Map<String,Object> map = new HashMap<> ();
		PageHelper.startPage (pageEntityDTO.getPageNum (),pageEntityDTO.getPageSize ());
		List<EssayPO> essays = essayService.getEssaysByForum (pageEntityDTO.getForum ());
		PageInfo<EssayPO> pageInfo = new PageInfo<> (essays);
		map.put ("data",pageInfo);
		map.put ("status",HttpStatus.OK.value ());
		return map;
	}
	
	
	@RequestMapping(value = "/search",method = {RequestMethod.GET})
	public Map<String,Object> searchEssays(EssaySearchDTO essaySearchDTO){
		Map<String,Object> map = new HashMap<> ();
		PageHelper.startPage (essaySearchDTO.getPageNum (),essaySearchDTO.getPageSize ());
		List<EssayPO> essays = essayService.findEssayByQuery (essaySearchDTO.getQuery ());
		PageInfo<EssayPO> pageInfo = new PageInfo<> (essays);
		map.put ("data",pageInfo);
		map.put ("status",HttpStatus.OK.value ());
		return map;
	}
}
