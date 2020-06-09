package com.kilric.controller;


import com.kilric.po.CommentPO;
import com.kilric.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {
	
	@Autowired
	private CommentServiceImpl commentService;
	
	@RequestMapping(value = "/addComment",method = {RequestMethod.POST})
	public Map<String,Object> addComment(@RequestBody CommentPO commentPO){
		Map<String,Object> map = new HashMap<> ();
		int i = commentService.addComment (commentPO);
		if (i != 1){
			map.put ("status", HttpStatus.BAD_REQUEST);
			map.put ("message","添加失败");
		}else {
			map.put ("status",HttpStatus.OK.value ());
			map.put ("message","添加成功");
		}
		return map;
	}
	
	@RequestMapping(value = "/deleteComment")
	public Map<String,Object> deleteCommentByCommentId(Integer cmtId){
		Map<String,Object> map = new HashMap<> ();
		int i = commentService.deleteCommentByCommentId (cmtId);
		if (i != 1){
			map.put ("status", HttpStatus.BAD_REQUEST);
			map.put ("message","删除失败");
		}else {
			map.put ("status",HttpStatus.OK.value ());
			map.put ("message","删除成功");
		}
		return map;
	}
	
	@RequestMapping(value = "/comments")
	public Map<String,Object> getCommentsByEssayId(Integer essayId){
		System.out.println (essayId);
		Map<String,Object> res = new HashMap<> ();
		List<CommentPO> comments = commentService.getCommentsByEssayId (essayId);
		res.put ("data",comments);
		res.put ("status",HttpStatus.OK.value ());
		return res;
	}
	
	@RequestMapping(value = "/commentsCount",method = {RequestMethod.GET})
	public Map<String,Object> getCommentsCountByEssayId(@RequestBody Integer essayId){
		Map<String,Object> res = new HashMap<> ();
		int count = commentService.getCommentsCountByEssayId (essayId);
		res.put ("data",count);
		res.put ("status",HttpStatus.OK.value ());
		return res;
	}
}
