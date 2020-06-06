<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="/top" />

<div class="single-product-area section-padding-100 clearfix">
	<div class="section">
		<div class="container text-center">
			<h1>문의게시판</h1>
		</div>
	</div>

	<div class="row">
		<div align="center" id="bbs" class="col-md-10 offset-md-1">

			<table class="table table-bordered">
				<tr>
					<td width="20%">글번호</td>
					<td width="30%"><c:out value="${board.idx}" /></td>
					<td width="20%">작성일</td>
					<td width="30%"><fmt:formatDate value="${board.wdate}"
							pattern="yyyy년 MM월 dd일 HH시 mm분 ss초" /></td>
				</tr>
				<tr>
					<td width="20%">글쓴이</td>
					<td width="30%"><c:out value="${board.name}" /></td>
					<td width="20%">조회수</td>
					<td width="30%"><c:out value="${board.readnum}" /></td>
				</tr>

				<tr>
					<td width="20%">제목</td>
					<td colspan="3" align="left"><c:out value="${board.subject}" />
					</td>
				</tr>
				<tr height="60">
					<td width="20%">글내용</td>
					<td colspan="3" align="left"><pre id="pre_small">
							<br><c:out value="${board.content}" />
						</pre></td>
				</tr>
				<tr>
					<td width="20%">첨부파일</td>
					<td colspan="3"><c:if test="${board.originFilename eq null}">
						첨부파일이 없습니다.
				</c:if> <c:if test="${board.originFilename ne null}">

							<a
								href="<%=request.getContextPath()%>/fileDown?filename=${board.filename}&origin=${board.originFilename}">
								<c:out value="${board.originFilename}" />
							</a> (<c:out value="${board.filesize}" />bytes)  
				
				
		<c:if
								test="${fn:endsWith(board.filename,'.jpg') or fn:endsWith(board.filename,'.png') or fn:endsWith(board.filename,'.gif') or fn:endsWith(board.filename,'.JPG') or fn:endsWith(board.filename,'.PNG') or fn:endsWith(board.filename,'.GIF') }">
								<img width="80px" class="img img-thumbnail"
									src="${pageContext.request.contextPath}/upload/${board.filename}">
							</c:if>
						</c:if></td>
				</tr>
				<tr>
					<td colspan="4" align=center><a href="input"><button
								class="btn btn-success">글쓰기</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="list"><button class="btn btn-primary">목록</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" onclick="go(${board.idx},1)"><button
								class="btn btn-warning">글수정</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" onclick="go(${board.idx},2)"><button
								class="btn btn-danger">삭제</button></a> <c:if
							test="${loginUser.name eq '관리자' }">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:goRe()"><button class="btn btn-dark">답변</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
				</tr>
				<tr>
					<td colspan="4" align=center>${board.pwd}</c:if>
					</td>
				</tr>
			</table>

			<!--편집 또는 삭제 관련 form 시작----------------  -->
			<form name="vf">
				<input type="hidden" name="idx"> <input type="hidden"
					name="mode">
			</form>
			<!-- -------------------------------------------- -->
			<!-- 답변 달기 form 시작------------------------------- -->
			<form name="reF" action="rewrite" method="POST">
				<!-- hidden으로 부모글(원글)의 글번호(idx)와 제목 subject를 넘기자. -->
				<input type="hidden" name="idx"
					value="<c:out value="${board.idx}"/>"> <input type="hidden"
					name="subject" value="<c:out value="${board.subject}"/>">
			</form>
		</div>
	</div>
</div>


</div>
<!-- ##### Main Content Wrapper End ##### -->
<script type="text/javascript">
	var goRe=function(){
		reF.submit();
	}


	var go = function(num, md) {
		//alert(num+"/"+md);
		vf.idx.value = num;
		vf.mode.value = md;//mode가 1이면 편집, 2면 삭제
		vf.method = "POST";
		//삭제시 한번 확인하기
		if (md == 2) {
			var yn = confirm("해당 글을 삭제 하시겠습니까?");
			if (!yn) {
				return;
			}
		}
		vf.action = "boardPasswd";//비밀번호 확인 페이지로 이동
		vf.submit();
	}
	
</script>

<c:import url="/foot" />

