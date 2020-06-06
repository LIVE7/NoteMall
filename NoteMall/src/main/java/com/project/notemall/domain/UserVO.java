package com.project.notemall.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserVO implements Serializable {
	private int idx;
	private String name;
	private String userid;
	private String pwd;
	private String hp1;
	private String hp2;
	private String hp3;
	private String post;
	private String addr1;
	private String addr2;
	private java.sql.Date indate;
	private int mileage;
	private int mstate;
}
