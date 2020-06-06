package com.project.notemall.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class SumVO implements Serializable {
	
	private String oyear;
	private String omonth;
	private long osum;
	
}
