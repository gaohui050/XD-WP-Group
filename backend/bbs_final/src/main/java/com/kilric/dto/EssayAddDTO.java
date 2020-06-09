package com.kilric.dto;

import com.kilric.po.EssayPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EssayAddDTO {
	private String username;
	private String title;
	private String tags;
	private String forum;
	private String content;
	
	public static EssayPO toEssayPO(EssayAddDTO essayAddDTO){
		EssayPO essayPO = new EssayPO ();
		essayPO.setUsername (essayAddDTO.getUsername ());
		essayPO.setTitle (essayAddDTO.getTitle ());
		essayPO.setPostTime (Date.valueOf (LocalDate.now ()));
		essayPO.setTags (essayAddDTO.getTags ());
		essayPO.setContent (essayAddDTO.getContent ());
		String content = essayAddDTO.getContent ();
		if (content.length () <= 20){
			essayPO.setSmr (content);
		}else{
			essayPO.setSmr (essayAddDTO.getContent ().substring (0,19) + "...");
		}
		essayPO.setForum (essayAddDTO.getForum ());
		return essayPO;
	}
}
