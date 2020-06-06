-- 회원가입

create table m_member(
	idx number(8) constraint m_member_pk primary key, --회원번호
	name varchar2(30) not null, --이름
	userid varchar2(20) constraint m_userid_uk unique, --아이디
	pwd varchar2(256) not null, --비밀번호
	hp1 char(3) not null, --연락처1
	hp2 char(4) not null,
	hp3 char(4) not null,
	post char(5), --우편번호
	addr1 varchar2(200), --주소1
	addr2 varchar2(100), --주소2
	indate date default sysdate, --등록일
	mileage number(10) default 1000, --마일리지 (기본점수 1000점)
	mstate number(1) default 0 --회원상태 (일반회원:0, 정지회원:-1, 탈퇴회원:-2)
);

create sequence m_member_seq
start with 1
increment by 1
nocache;

--상위 카테고리

DROP table m_upCategory;

Create table m_upCategory(
 upCode number(5) not null, --상위 카테고리 코드
 upCgName varchar2(30) not null, --상위 카테고리명
 constraint m_upCode_pk primary key (upCode)
);

DROP SEQUENCE m_upCode_seq;

create sequence m_upCode_seq nocache;

--하위 카테고리

drop table m_downCategory;

create table m_downCategory(
    upCode number(5) not null,  --상위 카테고리 코드 (FK)
    downCode number(5) not null, -- 하위 카테고리 코드 (PK)
    downCgName varchar2(30) not null, -- 하위 카테고리명
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

--상품 테이블
drop table m_product;

create table m_product(
   pnum number(8) primary key, --상품 번호
   upCode number(5) REFERENCES m_upCategory(upCode),--상위카테고리 코드 (FK)
   downCode number(5) REFERENCES m_downCategory(downCode),--하위카테고리 코드 (FK)
   pname varchar2(500) not null, --상품명
   pimage1 varchar2(100) default 'noimage.png', --상품이미지1
   pimage2 varchar2(100) default 'noimage.png',
   pimage3 varchar2(100) default 'noimage.png',
   pimage4 varchar2(100) default 'noimage.png', --상품정보 이미지
   price number(8) default 0, --상품 정가
   saleprice number(8) default 0, --상품 판매가
   pqty number(5) default 0, --상품 수량(보유 수량)
   point number(8) default 0, --지급 포인트
   pspec varchar2(20), --스펙(NEW, BEST, HIT )
   pcontents varchar2(1000), --상품설명
   pcompany varchar2(50),--제조사
   pindate date default sysdate --등록일
   
);

DROP sequence m_product_seq;

create sequence m_product_seq nocache;

INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'ACER 아스파이어 A515-52 체인져','acer_A515-52_1.jpg','acer_A515-52_2.jpg','acer_A515-52_3.jpg','acer_A515-52_4.jpg',550000, 450000,500,4500,'NEW','ACER 아스파이어 A515-52 체인져','ACER',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'DELL G5 15 5590 D042G5590107KR','dell_D042G5590107KR_1.jpg','dell_D042G5590107KR_2.jpg','dell_D042G5590107KR_3.jpg','dell_D042G5590107KR_4.jpg',1800000, 1300000,500,130000,'NEW','DELL G5 15 5590 D042G5590107KR','DELL',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'GIGABYTE AERO 15W V10 UHD WIN10','gigabyte_AERO-15W-V10-UHD_1.jpg','gigabyte_AERO-15W-V10-UHD_2.jpg','gigabyte_AERO-15W-V10-UHD_3.jpg','gigabyte_AERO-15W-V10-UHD_4.jpg',2500000, 2200000,500,220000,'NEW','GIGABYTE AERO 15W V10 UHD WIN10','GIGABYTE',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Hansung BossMonster X7967T','hansung_X7967T_1.jpg','hansung_X7967T_2.jpg','hansung_X7967T_3.jpg','hansung_X7967T_4.jpg',1300000, 100000,500,100000,'NEW','Hansung BossMonster X7967T','Hansung',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'HP 파빌리온 게이밍 15-dk0165TX','hp_15-dk0165TX_1.jpg','hp_15-dk0165TX_2.jpg','hp_15-dk0165TX_3.jpg','hp_15-dk0165TX_4.jpg',1300000, 1100000,500,110000,'NEW','HP 파빌리온 게이밍 15-dk0165TX','HP',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'JooyonTech 리오나인 L7SUV','jooyon_L7SUV_1.jpg','jooyon_L7SUV_2.jpg','jooyon_L7SUV_3.jpg','jooyon_L7SUV_4.jpg',1600000, 1200000,500,120000,'NEW','JooyonTech 리오나인 L7SUV','JooyonTech',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'LG 2019 그램 15ZD990-VX50K','lg_15ZD990-VX50K_1.jpg','lg_15ZD990-VX50K_2.jpg','lg_15ZD990-VX50K_3.jpg','lg_15ZD990-VX50K_4.jpg',1200000, 1000000,500,100000,'NEW','LG 2019 그램 15ZD990-VX50K','LG',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'MSI GS시리즈 GS65 Stealth 9SD','msi_GS65-Stealth-9SD_1.jpg','msi_GS65-Stealth-9SD_2.jpg','msi_GS65-Stealth-9SD_3.jpg','msi_GS65-Stealth-9SD_4.jpg',1600000, 1000000,500,100000,'NEW','MSI GS시리즈 GS65 Stealth 9SD','MSI',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Renovo 씽크패드 L390 YOGA 6KD','renovo_L390-YOGA-6KD_1.jpg','renovo_L390-YOGA-6KD_2.jpg','renovo_L390-YOGA-6KD_3.jpg','renovo_L390-YOGA-6KD_4.jpg',1000000, 800000,500,80000,'NEW','Renovo 씽크패드 L390 YOGA 6KD','Renovo',SYSDATE);
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
VALUES(m_product_seq.nextval,1,1,'JooyonTech 리오나인 L5SH-16S','jooyontech_L5SH-16S_1.jpg','jooyontech_L5SH-16S_2.jpg','jooyontech_L5SH-16S_3.jpg','jooyontech_L5SH-16S_4.jpg',750000, 650000,500,65000,'NEW','JooyonTech 리오나인 L5SH-16S','JooyonTech',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'LG 2019 그램 14ZD990-GX30K','lg_14ZD990-GX30K_1.jpg','lg_14ZD990-GX30K_2.jpg','lg_14ZD990-GX30K_3.jpg','lg_14ZD990-GX30K_4.jpg',950000, 850000,500,85000,'NEW','LG 2019 그램 14ZD990-GX30K','LG',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'MSI GF시리즈 GF75 Thin 9SC-i7','msi_GF75-Thin-9SC-i7_1.jpg','msi_GF75-Thin-9SC-i7_2.jpg','msi_GF75-Thin-9SC-i7_3.jpg','msi_GF75-Thin-9SC-i7_4.jpg',1250000, 1100000,500,110000,'NEW','MSI GF시리즈 GF75 Thin 9SC-i7','MSI',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Renovo 아이디어패드 L340-15IRH i5 LEGEND','renovo_L340-15IRH_i5_LEGEND_1.jpg','renovo_L340-15IRH_i5_LEGEND_2.jpg','renovo_L340-15IRH_i5_LEGEND_3.jpg','renovo_L340-15IRH_i5_LEGEND_4.jpg',750000, 650000,500,65000,'NEW','Renovo 아이디어패드 L340-15IRH i5 LEGEND','Renovo',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Samsung 2019 노트북9 Always NT950XBV-A58A WIN10','samsung_Always-NT950XBV-A58A-WIN10_1.jpg','samsung_Always-NT950XBV-A58A-WIN10_2.jpg','samsung_Always-NT950XBV-A58A-WIN10_3.jpg','samsung_Always-NT950XBV-A58A-WIN10_4.jpg',1200000, 1000000,500,100000,'NEW','Samsung 2019 노트북9 Always NT950XBV-A58A WIN10','Samsung',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'APPLE 맥북에어 2019년형 MVFN2KH/A CTO','apple_MVFN2KHA-CTO_1.jpg','apple_MVFN2KHA-CTO_2.jpg','apple_MVFN2KHA-CTO_3.jpg','apple_MVFN2KHA-CTO_4.jpg',1600000, 1400000,500,140000,'NEW','APPLE 맥북에어 2019년형 MVFN2KH/A CTO','APPLE',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Microsoft 서피스 북2 15인치 i7','microsoft_surfacebook2-15-i7_1.jpg','microsoft_surfacebook2-15-i7_2.jpg','microsoft_surfacebook2-15-i7_3.jpg','microsoft_surfacebook2-15-i7_4.jpg',3200000, 3000000,500,300000,'NEW','Microsoft 서피스 북2 15인치 i7','Microsoft',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'MSI WT73VR 7RM','msi_WT73VR-7RM_1.jpg','msi_WT73VR-7RM_2.jpg','msi_WT73VR-7RM_3.jpg','msi_WT73VR-7RM_4.jpg',6500000, 5000000,500,500000,'NEW','MSI WT73VR 7RM','MSI',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'AORUS X9 DT V8','aorus_X9-DT-V8_1.jpg','aorus_X9-DT-V8_2.jpg','aorus_X9-DT-V8_3.jpg','aorus_X9-DT-V8_4.jpg',6000000, 5000000,500,50000,'NEW','AORUS X9 DT V8','AORUS',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'ASUS 젠북 프로 Duo UX581GV-H2001TS','asus_UX581GV-H2001TS_1.jpg','asus_UX581GV-H2001TS_2.jpg','asus_UX581GV-H2001TS_3.jpg','asus_UX581GV-H2001TS_4.jpg',4300000, 4000000,500,400000,'NEW','ASUS 젠북 프로 Duo UX581GV-H2001TS','ASUS',SYSDATE);
INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
VALUES(m_product_seq.nextval,1,1,'Razer BLADE Pro 17 9Gen R80','razer_BLADE-Pro-17-9Gen-R80_1.jpg','razer_BLADE-Pro-17-9Gen-R80_2.jpg','razer_BLADE-Pro-17-9Gen-R80_3.jpg','razer_BLADE-Pro-17-9Gen-R80_4.jpg',4500000, 4200000,500,420000,'NEW','Razer BLADE Pro 17 9Gen R80','Razer',SYSDATE);

--INSERT INTO m_product(pnum,upCode,downCode,pname,pimage1,pimage2,pimage3,pimage4,price, saleprice,pqty,point,pspec,pcontents,pcompany,pindate)
--VALUES(m_product_seq.nextval,1,1,'','','','','',00000, 00000,500,0000,'NEW','','',SYSDATE);

commit;



-------------------------------------------------------------------------------

/* 장바구니 */
CREATE TABLE m_CART (
	cartNum NUMBER(8)primary key, /* 장바구니번호 */
	oqty NUMBER(8), /* 수량 */
	indate DATE default sysdate, /* 등록일 */
	pnum NUMBER(8) references m_product (pnum), /* 상품번호 */
	idx NUMBER(8) references m_member (idx)/* 회원번호 */
);

create sequence m_CART_SEQ nocache;

commit;

----------------------------------------------------------------------------------

/* 주문개요 */
CREATE TABLE m_orderDesc (
	ONUM VARCHAR2(20) primary key, /* 주문번호 */
	midx_fk NUMBER(8) references m_member (idx), /* 회원번호 */
	ototalPrice NUMBER(10) default 0, /* 결제총액 */
	ototalPoint NUMBER(10) default 0, /* 결제 총 포인트 */
	odeliverPrice NUMBER(10) default 3000, /* 배송비 */
	odeliver VARCHAR2(20) default '미배송', /* 배송상태 */
	opaystate VARCHAR2(20) default '미결제', /* 결제상태 */
	opaymethod VARCHAR2(20), /* 결제방법 */
	orderdate DATE default sysdate, /* 주문일자 */
	opointuse NUMBER(10) default 0, /* 사용한 적립금 */
	ordermemo VARCHAR2(200) /* 배송요청사항 */
);

select * from m_orderDesc;

/* 주문상품 */
CREATE TABLE m_order_product (
	onum VARCHAR2(20) references m_orderDesc(onum), /* 주문번호 */
	pnum Number(8) references m_product(pnum), /* 상품번호 */
	saleprice Number(10), /* 단가 */
	opoint Number(10), /* 포인트 */
	oqty Number(10), /* 수량 */
	pimage VARCHAR2(200), /* 상품이미지 */
	constraint m_order_product_pk primary key (onum, pnum)	/* 하나의 주문건에 상품은 여러개가 가능*/
);

select * from m_order_product;

/* 수령자 */
CREATE TABLE m_receiver (
	onum VARCHAR2(20) NOT NULL, /* 주문번호 */
	name VARCHAR2(30) not null, /* 이름 */
	hp1 CHAR(3) not null, /* 연락처1 */
	hp2 CHAR(4) not null, /* 연락처2 */
	hp3 CHAR(4) not null, /* 연락처3 */
	POST CHAR(10) not null, /* 우편번호 */
	addr1 VARCHAR2(200) not null, /* 주소1 */
	addr2 VARCHAR2(200) not null, /* 주소2 */
	constraint m_receiver_onum_pk primary key (onum),
	constraint m_receiver_onum_fk foreign key (onum)
				references m_orderDesc (onum)
);


--------------------------------------------------------------

--답변형 게시판
create table m_board(
	idx number(8) primary key,--글번호
	name varchar2(30) not null,--작성자
	pwd varchar2(20) not null,--비번
	subject varchar2(2000),--제목
	content varchar2(4000),--내용
	wdate timestamp default systimestamp,--작성일
	readnum number(8) default 0, --조회수
	filename varchar2(300),--첨부파일명[년월일시분초_a.jpg]
	originFilename varchar2(300), --원본파일명[a.jpg]
  filesize number(8), --첨부파일 크기
	refer  number(8),--글그룹번호[답변형 게시판일 때 사용]
	lev  number(8), --답변 레벨[답변형 게시판일 때 사용]
	sunbun number(8)--같은 글 그룹내의 순서 정렬[답변형 게시판일 때 사용]
);

create sequence m_board_seq nocache;

select * from m_board;

--------------------------------------------------------------------

--이메일 등록

create table m_email(
  idx number(8) primary key,--등록번호
  email varchar2(4000) not null --이메일
);

create sequence m_email_seq nocache;

--카트 뷰

CREATE OR REPLACE FORCE VIEW "MYDEV"."M_CARTVIEW" ("CARTNUM", "PNUM", "IDX", "PNAME", "PIMAGE", "SALEPRICE", "POINT", "OQTY", "TOTALPRICE", "TOTALPOINT") AS 
  SELECT c.cartNum, p.pnum, c.idx, p.pname, p.pimage1 pimage, p.saleprice, p.point, c.oqty,(p.saleprice * c.oqty) totalPrice,(p.point * c.oqty) totalPoint
FROM M_CART C JOIN M_PRODUCT P
ON C.PNUM = P.PNUM;

commit;