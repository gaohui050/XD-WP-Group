package com.kilric.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPO implements Serializable {
	private Integer commentId;
	private Integer essayId;
	private String username;
	private Date postTime = Date.valueOf (LocalDate.now ());
	private String cmtContent;
}
