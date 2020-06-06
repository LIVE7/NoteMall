package com.project.notemall.domain;

import java.io.Serializable;

public class ProductVO implements Serializable{

	//property
	private int pnum;
	private int upcg_code;
	private int downcg_code;
	
	private String pname;
	private String pimage1;
	private String pimage2;
	private String pimage3;
	private String pimage4;
	
	private int price;//정가
	private int saleprice;//판매가
	private int pqty;//수량
	private int point;//적립포인트
	
	private String pspec;
	private String pcontents;
	private String pcompany;
	private java.sql.Date pindate;
	
	private int totalPrice;//총 구매가격 (setter는 없고 getter만 있다.)
							//수량(pqty)이 정해지면 판매가(saleprice)x 수량(pqty)로 연산하여
							//결정될 예정
	private int totalPoint;//총 지급포인트
	
	//주문관련 프로퍼티
	private String onum;
	
	public ProductVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOnum() {
		return onum;
	}

	public void setOnum(String onum) {
		this.onum = onum;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getUpcg_code() {
		return upcg_code;
	}

	public void setUpcg_code(int upcg_code) {
		this.upcg_code = upcg_code;
	}

	public int getDowncg_code() {
		return downcg_code;
	}

	public void setDowncg_code(int downcg_code) {
		this.downcg_code = downcg_code;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPimage1() {
		return pimage1;
	}

	public void setPimage1(String pimage1) {
		this.pimage1 = pimage1;
	}

	public String getPimage2() {
		return pimage2;
	}

	public void setPimage2(String pimage2) {
		this.pimage2 = pimage2;
	}

	public String getPimage3() {
		return pimage3;
	}

	public void setPimage3(String pimage3) {
		this.pimage3 = pimage3;
	}
	
	public String getPimage4() {
		return pimage4;
	}

	public void setPimage4(String pimage4) {
		this.pimage4 = pimage4;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}

	public int getPqty() {
		return pqty;
	}

	public void setPqty(int pqty) {
		this.pqty = pqty;
		//totalPrice totalPoint
		//수량이 정해지면 총 구매 가격과 총 지급 포인트를 연산한다.
		this.totalPrice =this.saleprice * this.pqty;
		this.totalPoint =this.point* this.pqty;
		///////////////////////////////////////////
	}
	
	//할인율을 구하는 메소드
	public int getPercent() {
		int percent = (price-saleprice)*100/price; //(정가-판매가)*100)/정가
		return percent;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getPspec() {
		return pspec;
	}

	public void setPspec(String pspec) {
		this.pspec = pspec;
	}

	public String getPcontents() {
		return pcontents;
	}

	public void setPcontents(String pcontents) {
		this.pcontents = pcontents;
	}

	public String getPcompany() {
		return pcompany;
	}

	public void setPcompany(String pcompany) {
		this.pcompany = pcompany;
	}

	public java.sql.Date getPindate() {
		return pindate;
	}

	public void setPindate(java.sql.Date pindate) {
		this.pindate = pindate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public int getTotalPoint() {
		return totalPoint;
	}


	@Override
	public String toString() {
		return "ProductVO [pnum=" + pnum + ", upcg_code=" + upcg_code + ", downcg_code=" + downcg_code + ", pname="
				+ pname + ", pimage1=" + pimage1 + ", pimage2=" + pimage2 + ", pimage3=" + pimage3 + ", pimage4=" + pimage4 + ", price=" + price
				+ ", saleprice=" + saleprice + ", pqty=" + pqty + ", point=" + point + ", pspec=" + pspec
				+ ", pcontents=" + pcontents + ", pcompany=" + pcompany + ", pindate=" + pindate + ", totalPrice="
				+ totalPrice + ", totalPoint=" + totalPoint + "]";
	}

}
