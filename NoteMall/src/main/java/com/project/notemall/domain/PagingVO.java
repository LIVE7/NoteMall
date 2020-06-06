package com.project.notemall.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import lombok.Data;
/* 페이징 처리 전담 빈*/
@Data
public class PagingVO {
	//페이징 처리 관련 프로퍼티 선언
	private int cpage;//현재 보여줄 페이지 번호
	private int pageSize;//한 페이지당 보여줄 목록 갯수
	private int totalCount;//총게실글수
	private int pageCount;//페이지 수 
	
	//DB에서 레코드를 끊어올 때 사용할 시작값, 끝값
	private int start;
	private int end;
	
	//페이징 블럭 처리 관련 프로퍼티
	private int pagingBlock=5;//한 블럭당 보여줄 페이지 수
	private int prevBlock;//이전 5개
	private int nextBlock;//이후 5개

	//검색 관련 프로퍼티
	private String findType;//검색 유형
	private String findKeyword;//검색어
	
	/**페이징 처리를 위해 연산을 수행하는 메소드*/
	public void init(HttpSession ses) {
		if(pageSize<0) {
			pageSize=10;//한 페이지당 보여줄 목록 개수 10개를 디폴트로 설정
		}
		if(pageSize==0) {
			//파라미터로 pageSize가 넘어오지 않는다면 세션에 저장된 pageSize가 있는지 
			//찾아보자.
			Integer ps=(Integer) ses.getAttribute("pageSize");
			if(ps==null) {
				pageSize=10;
			}else {
				pageSize=ps;
			}
		}//----------
		ses.setAttribute("pageSize", pageSize);
		
		pageCount=(totalCount-1)/pageSize+1;
		
		if(cpage<=0) {
			cpage=1;//디폴트로 보여줄 페이지 1페이지로 지정
		}
		
		if(cpage>pageCount) {
			cpage=pageCount;//마지막 페이지로 지정
		}
		
		end=cpage*pageSize;
		start=end-(pageSize-1);
		
		//페이징 블럭 연산------------
		prevBlock=(cpage-1)/pagingBlock*pagingBlock;
		nextBlock=prevBlock+(pagingBlock+1);
		
	}//-------------------------
	/**페이지 네비게이션 문자열을 반환하는 메소드*/
	public String getPageNavi(String myctx, String loc) {
		//myctx: 컨텍스트명 (myapp)
		//loc  : 게시판 목록 결로  "/board/list"
		findType=(findType==null)?"":findType;
		
		try {
		findKeyword=(findKeyword==null)?"":
			URLEncoder.encode(findKeyword,"UTF-8");
		}catch(UnsupportedEncodingException e) {
			System.out.println(e);
		}
		
		String qStr="&findType="+findType+"&findKeyword="+findKeyword;
		
		StringBuffer sub=new StringBuffer()
				.append("<ul class='pagination'>");
		if(prevBlock>0) {
			//이전 5개
			sub.append("<li class='page-item'><a class='page-link' href='"+myctx+"/"+loc+"?cpage="+prevBlock+qStr+"'>")
			.append("Prev")
			.append("</a></li>");
		}
		for(int i=prevBlock+1;i<=nextBlock-1 && i<=pageCount;i++) {
			String css="";
			if(i==cpage) {
				css="active";
			}else {
				css="";
			}
			
			sub.append("<li class='page-item "+css+"'><a class='page-link' href='"+myctx+"/"+loc+"?cpage="+i+qStr+"'>")
			.append(i)
			.append("</a></li>");
		}
		
		if(nextBlock<pageCount) {
			//이후 5개
			sub.append("<li class='page-item'><a class='page-link' href='"+myctx+"/"+loc+"?cpage="+nextBlock+qStr+"'>")
			.append("Next")
			.append("</a></li>");
		}
		sub.append("</ul>");
		String str=sub.toString();
		System.out.println(str);
		return str;
	}
	

}

















