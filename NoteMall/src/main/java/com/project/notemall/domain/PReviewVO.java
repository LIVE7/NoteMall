package com.project.notemall.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class PReviewVO implements Serializable {

	private Integer ridx;//����۹�ȣ
	private int pnum_fk;//��ǰ��ȣ
	private int idx_fk; //ȸ����ȣ
	private String title;//��������
	private String content;//���䳻��
	private int score;//������
	private String filename;//���ε� �̹������ϸ�
	private java.sql.Date rdate;//���� �ۼ���
	
	private String name;//�ۼ��� �̸�
	private String userid;//�ۼ��� ���̵�
	
}
