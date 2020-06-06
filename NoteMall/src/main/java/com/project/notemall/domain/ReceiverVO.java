package com.project.notemall.domain;

import java.io.Serializable;

import lombok.Data;

@Data

public class ReceiverVO implements Serializable {
	
	private String onum;
	private String name;
	private String hp1;
	private String hp2;
	private String hp3;
	private String post;
	private String addr1;
	private String addr2;
	
}
