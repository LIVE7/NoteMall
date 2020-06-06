package com.project.notemall.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoryVO implements Serializable {

	private int upcg_code;
	private int downcg_code;
	
	private String upcg_name;
	private String downcg_name;
	
}
