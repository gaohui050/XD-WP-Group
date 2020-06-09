package com.kilric.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EssayPageEntityDTO {
	private int pageNum;
	private int pageSize;
	private String forum;
}
