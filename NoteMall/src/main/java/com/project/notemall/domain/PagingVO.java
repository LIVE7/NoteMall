package com.project.notemall.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import lombok.Data;
/* ����¡ ó�� ���� ��*/
@Data
public class PagingVO {
	//����¡ ó�� ���� ������Ƽ ����
	private int cpage;//���� ������ ������ ��ȣ
	private int pageSize;//�� �������� ������ ��� ����
	private int totalCount;//�ѰԽǱۼ�
	private int pageCount;//������ �� 
	
	//DB���� ���ڵ带 ����� �� ����� ���۰�, ����
	private int start;
	private int end;
	
	//����¡ �� ó�� ���� ������Ƽ
	private int pagingBlock=5;//�� ���� ������ ������ ��
	private int prevBlock;//���� 5��
	private int nextBlock;//���� 5��

	//�˻� ���� ������Ƽ
	private String findType;//�˻� ����
	private String findKeyword;//�˻���
	
	/**����¡ ó���� ���� ������ �����ϴ� �޼ҵ�*/
	public void init(HttpSession ses) {
		if(pageSize<0) {
			pageSize=10;//�� �������� ������ ��� ���� 10���� ����Ʈ�� ����
		}
		if(pageSize==0) {
			//�Ķ���ͷ� pageSize�� �Ѿ���� �ʴ´ٸ� ���ǿ� ����� pageSize�� �ִ��� 
			//ã�ƺ���.
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
			cpage=1;//����Ʈ�� ������ ������ 1�������� ����
		}
		
		if(cpage>pageCount) {
			cpage=pageCount;//������ �������� ����
		}
		
		end=cpage*pageSize;
		start=end-(pageSize-1);
		
		//����¡ �� ����------------
		prevBlock=(cpage-1)/pagingBlock*pagingBlock;
		nextBlock=prevBlock+(pagingBlock+1);
		
	}//-------------------------
	/**������ �׺���̼� ���ڿ��� ��ȯ�ϴ� �޼ҵ�*/
	public String getPageNavi(String myctx, String loc) {
		//myctx: ���ؽ�Ʈ�� (myapp)
		//loc  : �Խ��� ��� ���  "/board/list"
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
			//���� 5��
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
			//���� 5��
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

















