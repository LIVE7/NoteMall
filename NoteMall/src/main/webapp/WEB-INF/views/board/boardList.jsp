<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="/top" />
<%
	String ctx = request.getContextPath();
%>
<script type="text/javascript">
	var check = function() {
		if (!sf.findType.value) {
			alert('검색 유형을 선택하세요');
			sf.findType.focus();
			return false;
		}

		if (!sf.findKeyword.value) {
			alert('검색어를 입력하세요');
			sf.findKeyword.focus();
			return false;
		}
		sf.submit();
	}
</script>

<div class="single-product-area section-padding-100 clearfix">
	<div class="section">
		<div class="container text-center">
			<h1>문의게시판</h1>
		</div>
	</div>

	<div class="row">
		<div align="center" id="bbs" class="col-md-10 offset-md-1">
			<br>
			<table class="table">
				<tr>
					<td>
						<form name="pf" id="pf" action="list">

							<select name="pageSize" class="form-control" onchange="submit()">
								<option value="10">페이지 사이즈</option>
								<c:forEach var="ps" begin="10" end="50" step="10">
									<option value="${ps}">페이지 사이즈 ${ps}</option>
								</c:forEach>
							</select>

						</form>
					</td>
				</tr>
			</table>
			<table class="table">
				<tr class="text-center">
					<th>글번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
				<!-- ---------------------------- -->
				<c:forEach var="board" items="${boardList}">
					<tr class="text-center">
						<td width="10%">${board.idx}</td>
						<td align="left" width="50%"><c:forEach var="k" begin="0"
								end="${board.lev}">
						&nbsp;&nbsp;&nbsp;
					</c:forEach> <c:if test="${board.lev>0}">
								<img src="../img/core-img/re.PNG">
							</c:if> <a href="view?idx=${board.idx}"> ${board.subject} </a></td>
						<td width="20%">${board.name}</td>
						<td width="10%"><fmt:formatDate value="${board.wdate}"
								pattern="yyyy-MM-dd" /></td>
						<td width="10%">${board.readnum}</td>
					</tr>
				</c:forEach>
				<!-- ----------------------------- -->
				<tr>
					<td colspan="3" class="text-center" align="center">
						<!-- 페이지 네비게이션 ==>PagingVO 의 getPageNavi()라는 메소드에서
					문자열로 만들어 반환 --> ${navi}

					</td>
					<td colspan="2">총게시물수: <span class="text-danger"
						style="font-weight: bold">${totalCount}개</span> <br> <span
						class="text-danger" style="font-weight: bold">
							${paging.cpage}</span> /${paging.pageCount} pages
					</td>
				</tr>
				<tr>
					<td>
						<form name="sf" action="list" onsubmit="return check()">
							<select name="findType" class="form-control">
								<option value="">검색 유형</option>
								<option value="1">글제목</option>
								<option value="2">작성자</option>
								<option value="3">글내용</option>
							</select>
					</td>
					<td colspan="2"><input type="text" name="findKeyword"
						class="form-control" placeholder="검색어를 입력하세요"></td>
					<td>
						<button type="button" onclick="check()" class="btn btn-warning">검색</button>
						</form>
					</td>
					<td><a href="${pageContext.request.contextPath}/board/input"><button
								type="button" class="btn btn-info">글작성</button></a></td>
				</tr>

			</table>
		</div>
	</div>

</div>

</div>
<!-- ##### Main Content Wrapper End ##### -->
<c:import url="/foot" />