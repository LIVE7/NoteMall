package com.project.notemall.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderVO implements Serializable {
	
	//orderDesc���� ������Ƽ---
	private String onum; //�ֹ���ȣ
	private int ototalPrice;//�ֹ��Ѿ�
	private int ototalPoint;//������Ʈ
	private String odeliver;//��ۻ���
	private String odeliverPrice;//��ۺ�(����Ʈ 3000��)
	private String opaystate;//���һ���
	private Date orderDate;
	private String orderMemo;
	private int opointuse;//��� ����Ʈ
	
	private int midx_fk;//�ֹ��� ��ȣ
	private String uname;//�ֹ��� �̸�
	private String userid;//�ֹ��� ���̵�
	private int cnt;//�ֹ��� ��ǰ����
	private String pimage;//�ֹ��� ��ǰ�� ��ǥ �̹���
	
	private int payMethod;//��������(�������Ա�:100, ī�����:200)
	private int bank;//�������Ա��� ��� (��������:1, �츮:2, ����:3)
	
	
	//�ֹ� ��ǰ ���� ������Ƽ----
	private List<ProductVO> orderList;
	//�ϳ��� �ֹ� �Ǽ��� ���� �ֹ� ��ǰ�� ���� �� ���� �� �ִ�.
	
	//������ ���� ������Ƽ
	private ReceiverVO receiver;
	//�ϳ��� �ֹ� �Ǽ��� ���� �����ڴ� 1��
	
}



