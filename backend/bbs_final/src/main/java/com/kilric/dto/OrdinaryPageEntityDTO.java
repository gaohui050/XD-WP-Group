package com.kilric.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdinaryPageEntityDTO {
	private int pageNum;
	private int pageSize;
}
