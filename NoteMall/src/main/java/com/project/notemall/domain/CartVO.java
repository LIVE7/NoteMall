package com.project.notemall.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartVO implements Serializable {

	private int cartNum;//��ٱ��� ��ȣ
	private int idx;//ȸ�� ��ȣ
	private int pnum;//��ǰ��ȣ
	private int oqty;//����
	private java.sql.Date indate;//��ٱ��� �԰���
	
	//��ǰ���� ����----
	private String pname;//��ǰ��
	private String pimage;//��ǰ�̹���
	private int saleprice;//�ǸŰ�
	
	private int point;//����Ʈ
	private int upCategory_code;//���� ī�װ�
	private int downCategory_code;//���� ī�װ�
	
	private int totalPrice;//��ٱ��� Ư�� ��ǰ�� �Ѿ�
	private int totalPoint;//��ٱ��� Ư�� ��ǰ�� ������Ʈ
	
	private int cartTotalPrice;//��ٱ��� ��� ��ǰ�� ���� �Ѿ�
	private int cartTotalPoint;//��ٱ��� ��� ��ǰ�� ���� ����Ʈ
}
