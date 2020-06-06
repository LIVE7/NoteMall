-- ȸ������

create table m_member(
	idx number(8) constraint m_member_pk primary key, --ȸ����ȣ
	name varchar2(30) not null, --�̸�
	userid varchar2(20) constraint m_userid_uk unique, --���̵�
	pwd varchar2(256) not null, --��й�ȣ
	hp1 char(3) not null, --����ó1
	hp2 char(4) not null,
	hp3 char(4) not null,
	post char(5), --�����ȣ
	addr1 varchar2(200), --�ּ�1
	addr2 varchar2(100), --�ּ�2
	indate date default sysdate, --�����
	mileage number(10) default 1000, --���ϸ��� (�⺻���� 1000��)
	mstate number(1) default 0 --ȸ������ (�Ϲ�ȸ��:0, ����ȸ��:-1, Ż��ȸ��:-2)
);

create sequence m_member_seq
start with 1
increment by 1
nocache;

--���� ī�װ�

DROP table m_upCategory;

Create table m_upCategory(
 upCode number(5) not null, --���� ī�װ� �ڵ�
 upCgName varchar2(30) not null, --���� ī�װ���
 constraint m_upCode_pk primary key (upCode)
);

DROP SEQUENCE m_upCode_seq;

create sequence m_upCode_seq nocache;

--���� ī�װ�

drop table m_downCategory;

create table m_downCategory(
    upCode number(5) not null,  --���� ī�װ� �ڵ� (FK)
    downCode number(5) not null, -- ���� ī�װ� �ڵ� (PK)
    downCgName varchar2(30) not null, -- ���� ī�װ���
    constraint m_downCode_pk primary key (downCode),
    constraint m_upCode_fk foreign key (upCode)
        references m_upCategory (upCode)
);

drop sequence m_downCode_seq;

create sequence m_downCode_seq nocache;

insert into m_upCategory 
values(m_upCode_seq.nextval,'notebook');

commit;

insert into m_downCategory
values(1,m_downCode_seq.nextval,'ACER');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'AORUS');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'APPLE');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'ASUS');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'DELL');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'GIGABYTE');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'Hansung');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'HP');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'JooyonTech');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'LG');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'MSI');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'Microsoft');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'Razer');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'Renovo');
insert into m_downCategory
values(1,m_downCode_seq.nextval,'Samsung');

--��ǰ ���̺�
drop table m_product;

create table m_product(
   pnum number(8) primary key, --��ǰ ��ȣ
   upCode number(5) REFERENCES m_upCategory(upCode),--����ī�װ� �ڵ� (FK)
   downCode number(5) REFERENCES m_downCategory(downCode),--����ī�װ� �ڵ� (FK)
   pname varchar2(500) not null, --��ǰ��
   pimage1 varchar2(100) default 'noimage.png', --��ǰ�̹���1
   pimage2 varchar2(100) default 'noimage.png',
   pimage3 varchar2(100) default 'noimage.png',
   pimage4 varchar2(100) default 'noimage.png', --��ǰ���� �̹���
   price number(8) default 0, --��ǰ ����
   saleprice number(8) default 0, --��ǰ �ǸŰ�
   pqty number(5) default 0, --��ǰ ����(���� ����)
   point number(8) default 0, --���� ����Ʈ
   pspec varchar2(20), --����(NEW, BEST, HIT )
   pcontents varchar2(1000), --��ǰ����
   pcompany varchar2(50),--������
   pindate date default sysdate --�����
   
);

DROP sequence m_product_seq;

create sequence m_product_seq nocache;

INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'ACER �ƽ����̾� A515-52 ü����','acer_A515-52_1.jpg','acer_A515-52_2.jpg','acer_A515-52_3.jpg','acer_A515-52_4.jpg',550000, 450000,500,4500,'NEW','ACER �ƽ����̾� A515-52 ü����','ACER',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'DELL G5 15 5590 D042G5590107KR','dell_D042G5590107KR_1.jpg','dell_D042G5590107KR_2.jpg','dell_D042G5590107KR_3.jpg','dell_D042G5590107KR_4.jpg',1800000, 1300000,500,130000,'NEW','DELL G5 15 5590 D042G5590107KR','DELL',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'GIGABYTE AERO 15W V10 UHD WIN10','gigabyte_AERO-15W-V10-UHD_1.jpg','gigabyte_AERO-15W-V10-UHD_2.jpg','gigabyte_AERO-15W-V10-UHD_3.jpg','gigabyte_AERO-15W-V10-UHD_4.jpg',2500000, 2200000,500,220000,'NEW','GIGABYTE AERO 15W V10 UHD WIN10','GIGABYTE',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Hansung BossMonster X7967T','hansung_X7967T_1.jpg','hansung_X7967T_2.jpg','hansung_X7967T_3.jpg','hansung_X7967T_4.jpg',1300000, 100000,500,100000,'NEW','Hansung BossMonster X7967T','Hansung',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'HP �ĺ����� ���̹� 15-dk0165TX','hp_15-dk0165TX_1.jpg','hp_15-dk0165TX_2.jpg','hp_15-dk0165TX_3.jpg','hp_15-dk0165TX_4.jpg',1300000, 1100000,500,110000,'NEW','HP �ĺ����� ���̹� 15-dk0165TX','HP',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'JooyonTech �������� L7SUV','jooyon_L7SUV_1.jpg','jooyon_L7SUV_2.jpg','jooyon_L7SUV_3.jpg','jooyon_L7SUV_4.jpg',1600000, 1200000,500,120000,'NEW','JooyonTech �������� L7SUV','JooyonTech',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'LG 2019 �׷� 15ZD990-VX50K','lg_15ZD990-VX50K_1.jpg','lg_15ZD990-VX50K_2.jpg','lg_15ZD990-VX50K_3.jpg','lg_15ZD990-VX50K_4.jpg',1200000, 1000000,500,100000,'NEW','LG 2019 �׷� 15ZD990-VX50K','LG',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'MSI GS�ø��� GS65 Stealth 9SD','msi_GS65-Stealth-9SD_1.jpg','msi_GS65-Stealth-9SD_2.jpg','msi_GS65-Stealth-9SD_3.jpg','msi_GS65-Stealth-9SD_4.jpg',1600000, 1000000,500,100000,'NEW','MSI GS�ø��� GS65 Stealth 9SD','MSI',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Renovo ��ũ�е� L390 YOGA 6KD','renovo_L390-YOGA-6KD_1.jpg','renovo_L390-YOGA-6KD_2.jpg','renovo_L390-YOGA-6KD_3.jpg','renovo_L390-YOGA-6KD_4.jpg',1000000, 800000,500,80000,'NEW','Renovo ��ũ�е� L390 YOGA 6KD','Renovo',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Samsung Odyssey NT850XBC-X719A','samsung_NT850XBC-X719A_1.jpg','samsung_NT850XBC-X719A_2.jpg','samsung_NT850XBC-X719A_3.jpg','samsung_NT850XBC-X719A_4.jpg',2300000, 2000000,500,200000,'NEW','Samsung Odyssey NT850XBC-X719A','Samsung',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'ACER Swift5 SF515-51T i5 SMART','acer_Swift5-SF515-51T-i5-SMART_1.jpg','acer_Swift5-SF515-51T-i5-SMART_2.jpg','acer_Swift5-SF515-51T-i5-SMART_3.jpg','acer_Swift5-SF515-51T-i5-SMART_4.jpg',1100000, 900000,500,90000,'NEW','ACER Swift5 SF515-51T i5 SMART','ACER',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'GIGABYTE AERO 15X V10 UHD LITE','gigabyte_AERO-15X-V10-UHD-LITE_1.jpg','gigabyte_AERO-15X-V10-UHD-LITE_2.jpg','gigabyte_AERO-15X-V10-UHD-LITE_3.jpg','gigabyte_AERO-15X-V10-UHD-LITE_4.jpg',3000000, 2500000,500,250000,'NEW','GIGABYTE AERO 15X V10 UHD LITE','GIGABYTE',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Hansung TFX255GS','hansung_TFX255GS_1.jpg','hansung_TFX255GS_2.jpg','hansung_TFX255GS_3.jpg','hansung_TFX255GS_4.jpg',1300000, 1000000,500,100000,'NEW','Hansung TFX255GS','Hansung',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'HP 15s-du0069tu','hp_15s-du0069tu_1.jpg','hp_15s-du0069tu_2.jpg','hp_15s-du0069tu_3.jpg','hp_15s-du0069tu_4.jpg',400000, 300000,500,30000,'NEW','HP 15s-du0069tu','HP',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'JooyonTech �������� L5SH-16S','jooyontech_L5SH-16S_1.jpg','jooyontech_L5SH-16S_2.jpg','jooyontech_L5SH-16S_3.jpg','jooyontech_L5SH-16S_4.jpg',750000, 650000,500,65000,'NEW','JooyonTech �������� L5SH-16S','JooyonTech',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'LG 2019 �׷� 14ZD990-GX30K','lg_14ZD990-GX30K_1.jpg','lg_14ZD990-GX30K_2.jpg','lg_14ZD990-GX30K_3.jpg','lg_14ZD990-GX30K_4.jpg',950000, 850000,500,85000,'NEW','LG 2019 �׷� 14ZD990-GX30K','LG',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'MSI GF�ø��� GF75 Thin 9SC-i7','msi_GF75-Thin-9SC-i7_1.jpg','msi_GF75-Thin-9SC-i7_2.jpg','msi_GF75-Thin-9SC-i7_3.jpg','msi_GF75-Thin-9SC-i7_4.jpg',1250000, 1100000,500,110000,'NEW','MSI GF�ø��� GF75 Thin 9SC-i7','MSI',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Renovo ���̵���е� L340-15IRH i5 LEGEND','renovo_L340-15IRH_i5_LEGEND_1.jpg','renovo_L340-15IRH_i5_LEGEND_2.jpg','renovo_L340-15IRH_i5_LEGEND_3.jpg','renovo_L340-15IRH_i5_LEGEND_4.jpg',750000, 650000,500,65000,'NEW','Renovo ���̵���е� L340-15IRH i5 LEGEND','Renovo',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Samsung 2019 ��Ʈ��9 Always NT950XBV-A58A WIN10','samsung_Always-NT950XBV-A58A-WIN10_1.jpg','samsung_Always-NT950XBV-A58A-WIN10_2.jpg','samsung_Always-NT950XBV-A58A-WIN10_3.jpg','samsung_Always-NT950XBV-A58A-WIN10_4.jpg',1200000, 1000000,500,100000,'NEW','Samsung 2019 ��Ʈ��9 Always NT950XBV-A58A WIN10','Samsung',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'APPLE �ƺϿ��� 2019���� MVFN2KH/A CTO','apple_MVFN2KHA-CTO_1.jpg','apple_MVFN2KHA-CTO_2.jpg','apple_MVFN2KHA-CTO_3.jpg','apple_MVFN2KHA-CTO_4.jpg',1600000, 1400000,500,140000,'NEW','APPLE �ƺϿ��� 2019���� MVFN2KH/A CTO','APPLE',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Microsoft ���ǽ� ��2 15��ġ i7','microsoft_surfacebook2-15-i7_1.jpg','microsoft_surfacebook2-15-i7_2.jpg','microsoft_surfacebook2-15-i7_3.jpg','microsoft_surfacebook2-15-i7_4.jpg',3200000, 3000000,500,300000,'NEW','Microsoft ���ǽ� ��2 15��ġ i7','Microsoft',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'MSI WT73VR 7RM','msi_WT73VR-7RM_1.jpg','msi_WT73VR-7RM_2.jpg','msi_WT73VR-7RM_3.jpg','msi_WT73VR-7RM_4.jpg',6500000, 5000000,500,500000,'NEW','MSI WT73VR 7RM','MSI',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'AORUS X9 DT V8','aorus_X9-DT-V8_1.jpg','aorus_X9-DT-V8_2.jpg','aorus_X9-DT-V8_3.jpg','aorus_X9-DT-V8_4.jpg',6000000, 5000000,500,50000,'NEW','AORUS X9 DT V8','AORUS',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'ASUS ���� ���� Duo UX581GV-H2001TS','asus_UX581GV-H2001TS_1.jpg','asus_UX581GV-H2001TS_2.jpg','asus_UX581GV-H2001TS_3.jpg','asus_UX581GV-H2001TS_4.jpg',4300000, 4000000,500,400000,'NEW','ASUS ���� ���� Duo UX581GV-H2001TS','ASUS',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Razer BLADE Pro 17 9Gen R80','razer_BLADE-Pro-17-9Gen-R80_1.jpg','razer_BLADE-Pro-17-9Gen-R80_2.jpg','razer_BLADE-Pro-17-9Gen-R80_3.jpg','razer_BLADE-Pro-17-9Gen-R80_4.jpg',4500000, 4200000,500,420000,'NEW','Razer BLADE Pro 17 9Gen R80','Razer',SYSDATE);

--INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
--VALUES(m_product_seq.nextval,1,1,'','','','','',00000, 00000,500,0000,'NEW','','',SYSDATE);

commit;



-------------------------------------------------------------------------------

/* ��ٱ��� */
CREATE TABLE m_CART (
	cartNum NUMBER(8)primary key, /* ��ٱ��Ϲ�ȣ */
	oqty NUMBER(8), /* ���� */
	indate DATE default sysdate, /* ����� */
	pnum NUMBER(8) references m_product (pnum), /* ��ǰ��ȣ */
	idx NUMBER(8) references m_member (idx)/* ȸ����ȣ */
);

create sequence m_CART_SEQ nocache;

commit;

----------------------------------------------------------------------------------

/* �ֹ����� */
CREATE TABLE m_orderDesc (
	ONUM VARCHAR2(20) primary key, /* �ֹ���ȣ */
	midx_fk NUMBER(8) references m_member (idx), /* ȸ����ȣ */
	ototalPrice NUMBER(10) default 0, /* �����Ѿ� */
	ototalPoint NUMBER(10) default 0, /* ���� �� ����Ʈ */
	odeliverPrice NUMBER(10) default 3000, /* ��ۺ� */
	odeliver VARCHAR2(20) default '�̹��', /* ��ۻ��� */
	opaystate VARCHAR2(20) default '�̰���', /* �������� */
	opaymethod VARCHAR2(20), /* ������� */
	orderdate DATE default sysdate, /* �ֹ����� */
	opointuse NUMBER(10) default 0, /* ����� ������ */
	ordermemo VARCHAR2(200) /* ��ۿ�û���� */
);

select * from m_orderDesc;

/* �ֹ���ǰ */
CREATE TABLE m_order_product (
	onum VARCHAR2(20) references m_orderDesc(onum), /* �ֹ���ȣ */
	pnum Number(8) references m_product(pnum), /* ��ǰ��ȣ */
	saleprice Number(10), /* �ܰ� */
	opoint Number(10), /* ����Ʈ */
	oqty Number(10), /* ���� */
	pimage VARCHAR2(200), /* ��ǰ�̹��� */
	constraint m_order_product_pk primary key (onum, pnum)	/* �ϳ��� �ֹ��ǿ� ��ǰ�� �������� ����*/
);

select * from m_order_product;

/* ������ */
CREATE TABLE m_receiver (
	onum VARCHAR2(20) NOT NULL, /* �ֹ���ȣ */
	name VARCHAR2(30) not null, /* �̸� */
	hp1 CHAR(3) not null, /* ����ó1 */
	hp2 CHAR(4) not null, /* ����ó2 */
	hp3 CHAR(4) not null, /* ����ó3 */
	POST CHAR(10) not null, /* �����ȣ */
	addr1 VARCHAR2(200) not null, /* �ּ�1 */
	addr2 VARCHAR2(200) not null, /* �ּ�2 */
	constraint m_receiver_onum_pk primary key (onum),
	constraint m_receiver_onum_fk foreign key (onum)
				references m_orderDesc (onum)
);


--------------------------------------------------------------

--�亯�� �Խ���
create table m_board(
	idx number(8) primary key,--�۹�ȣ
	name varchar2(30) not null,--�ۼ���
	pwd varchar2(20) not null,--���
	subject varchar2(2000),--����
	content varchar2(4000),--����
	wdate timestamp default systimestamp,--�ۼ���
	readnum number(8) default 0, --��ȸ��
	filename varchar2(300),--÷�����ϸ�[����Ͻú���_a.jpg]
	originFilename varchar2(300), --�������ϸ�[a.jpg]
  filesize number(8), --÷������ ũ��
	refer  number(8),--�۱׷��ȣ[�亯�� �Խ����� �� ���]
	lev  number(8), --�亯 ����[�亯�� �Խ����� �� ���]
	sunbun number(8)--���� �� �׷쳻�� ���� ����[�亯�� �Խ����� �� ���]
);

create sequence m_board_seq nocache;

select * from m_board;

--------------------------------------------------------------------

--�̸��� ���

create table m_email(
  idx number(8) primary key,--��Ϲ�ȣ
  email varchar2(4000) not null --�̸���
);

create sequence m_email_seq nocache;

--īƮ ��

CREATE OR REPLACE FORCE VIEW "MYDEV"."M_CARTVIEW" ("CARTNUM", "PNUM", "IDX", "PNAME", "PIMAGE", "SALEPRICE", "POINT", "OQTY", "TOTALPRICE", "TOTALPOINT") AS 
  SELECT c.cartNum, p.pnum, c.idx, p.pname, p.pimage1 pimage, p.saleprice, p.point, c.oqty,(p.saleprice * c.oqty) totalPrice,(p.point * c.oqty) totalPoint
FROM M_CART C JOIN M_PRODUCT P
ON C.PNUM = P.PNUM;

commit;