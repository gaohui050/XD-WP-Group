package com.kilric.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EssayPO implements Serializable {
	private Integer essayId;
	private String username;
	private String title;
	private Date postTime;
	private String tags;
	private String content;
	private String smr;
	private String forum;
}
